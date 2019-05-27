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

	private final Map<String, Session> sessions = new HashMap<String, Session>();


	private String initChannel(MessageHandler messageHandler, Class<? extends Decoder> decoder,
														 Channel channel,
														 String pair) {

		ClientEndpointConfig clientEndpointConfig = ClientEndpointConfig.Builder.create()
				.encoders(Collections.<Class<? extends Encoder>>singletonList(CommandEncoder.class))
				.decoders(Collections.<Class<? extends Decoder>>singletonList(decoder)).build();

		BitstampChannel bitstampChannel = new BitstampChannel(clientEndpointConfig, messageHandler);
		try {
			Session session = bitstampChannel.init(channel, pair);
			String id = UUID.randomUUID().toString();
			sessions.put(id, session);
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
		Session session = sessions.remove(id);
		try {
			session.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			for (Iterator<Map.Entry<String, Session>> it = sessions.entrySet().iterator(); it.hasNext(); ) {
				Map.Entry<String, Session> entry = it.next();
				entry.getValue().close();
				it.remove();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
