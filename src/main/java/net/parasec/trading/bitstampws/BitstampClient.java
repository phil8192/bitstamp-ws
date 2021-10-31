package net.parasec.trading.bitstampws;

import net.parasec.trading.bitstampws.websocket.*;

import javax.websocket.*;
import java.util.*;


public class BitstampClient implements Client {

	// de-couple javax.websocket.MessageHandler from Client.
	private class MH<T extends Event> implements MessageHandler.Whole<T> {
		BitstampMessageHandler<T> bitstampMessageHandler;

		MH(BitstampMessageHandler<T> bitstampMessageHandler) {
			this.bitstampMessageHandler = bitstampMessageHandler;
		}

		public void onMessage(T message) {
			// intercept control messages here.
			// do not pass onto client.
			switch (message.event) {
				case ORDER_CREATED:
				case ORDER_DELETED:
				case ORDER_UPDATED:
				case TRADE:
				case DATA:
					bitstampMessageHandler.onMessage(message);
					break;
				case FORCED_RECONNECT:
					System.err.println("forced reconnect received");
					break;
				case SUBSCRIPTION_SUCCEEDED:
			}
		}
	}

	private final Map<String, BitstampChannel> bitstampChannels = new HashMap<>();


	private String initChannel(MessageHandler messageHandler,
							   Class<? extends Decoder> decoder,
							   Channel channel, String pair) {

		ClientEndpointConfig clientEndpointConfig = ClientEndpointConfig.Builder.create()
				.encoders(Collections.singletonList(CommandEncoder.class))
				.decoders(Collections.singletonList(decoder)).build();

		BitstampChannel bitstampChannel = new BitstampChannel(clientEndpointConfig, messageHandler, channel, pair);

		try {
			bitstampChannel.init();
			String id = UUID.randomUUID().toString();
			bitstampChannels.put(id, bitstampChannel);
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String subscribeOrders(String pair,
								  BitstampMessageHandler<OrderEvent> bitstampMessageHandler) {
		return initChannel(
				new MH<>(bitstampMessageHandler),
				Decoders.OrderDecoder.class,
				Channel.LIVE_ORDERS,
				pair
		);
	}

	public String subscribeTrades(String pair,
								  BitstampMessageHandler<TradeEvent> bitstampMessageHandler) {
		return initChannel(
				new MH<>(bitstampMessageHandler),
				Decoders.TradeDecoder.class,
				Channel.LIVE_TRADES,
				pair
		);
	}

	public String subscribeOrderBook(String pair,
									 BitstampMessageHandler<OrderBookEvent> bitstampMessageHandler) {
		return initChannel(
				new MH<>(bitstampMessageHandler),
				Decoders.OrderBookDecoder.class,
				Channel.ORDER_BOOK,
				pair);
	}

	public String subscribeDetailOrderBook(String pair,
										   BitstampMessageHandler<DetailOrderBookEvent> bitstampMessageHandler) {
		return initChannel(
				new MH<>(bitstampMessageHandler),
				Decoders.DetailOrderBookDecoder.class,
				Channel.DETAIL_ORDER_BOOK,
				pair);
	}

	public String subscribeDiffOrderBook(String pair,
										 BitstampMessageHandler<DiffOrderBookEvent> bitstampMessageHandler) {
		return initChannel(
				new MH<>(bitstampMessageHandler),
				Decoders.DiffOrderBookDecoder.class,
				Channel.DIFF_ORDER_BOOK,
				pair);
	}

	public void unsubscribe(String id) {
		BitstampChannel bitstampChannel = bitstampChannels.remove(id);
		bitstampChannel.close();
	}

	public void close() {
		for (Iterator<Map.Entry<String, BitstampChannel>> it = bitstampChannels.entrySet().iterator();
			 it.hasNext(); ) {
			Map.Entry<String, BitstampChannel> entry = it.next();
			entry.getValue().close();
			it.remove();
		}
	}
}
