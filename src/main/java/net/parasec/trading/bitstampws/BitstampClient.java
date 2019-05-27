package net.parasec.trading.bitstampws;

import net.parasec.trading.bitstampws.websocket.Channel;
import net.parasec.trading.bitstampws.websocket.CommandEncoder;
import net.parasec.trading.bitstampws.websocket.OrderDecoder;
import net.parasec.trading.bitstampws.websocket.TradeDecoder;

import javax.websocket.*;
import java.io.IOException;
import java.util.*;


public class BitstampClient implements Client {

	// de-couple javax.websocket.MessageHandler from Client.
	private class MH<T> implements MessageHandler.Whole<T> {
		BitstampMessageHandler<T> bitstampMessageHandler;

		MH(BitstampMessageHandler<T> bitstampMessageHandler) {
			this.bitstampMessageHandler = bitstampMessageHandler;
		}

		public void onMessage(T message) {
			bitstampMessageHandler.onMessage(message);
		}
	}

	private final Map<String, BitstampChannel> bitstampChannels = new HashMap<String, BitstampChannel>();


	private String initChannel(MessageHandler messageHandler, Class<? extends Decoder> decoder,
														 Channel channel,
														 String pair) {

		ClientEndpointConfig clientEndpointConfig = ClientEndpointConfig.Builder.create()
				.encoders(Collections.<Class<? extends Encoder>>singletonList(CommandEncoder.class))
				.decoders(Collections.<Class<? extends Decoder>>singletonList(decoder)).build();

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

	public String subscribeOrders(String pair, BitstampMessageHandler<Order> bitstampMessageHandler) {
		MH<Order> messageHandler = new MH<Order>(bitstampMessageHandler);
		return initChannel(messageHandler, OrderDecoder.class, Channel.LIVE_ORDERS, pair);
	}

	public String subscribeTrades(String pair, BitstampMessageHandler<Trade> bitstampMessageHandler) {
		MH<Trade> messageHandler = new MH<Trade>(bitstampMessageHandler);
		return initChannel(messageHandler, TradeDecoder.class, Channel.LIVE_TRADES, pair);
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
