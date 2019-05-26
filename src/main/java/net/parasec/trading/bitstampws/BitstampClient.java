package net.parasec.trading.bitstampws;

import net.parasec.trading.bitstampws.websocket.CommandEncoder;
import net.parasec.trading.bitstampws.websocket.OrderDecoder;
import net.parasec.trading.bitstampws.websocket.TradeDecoder;

import javax.websocket.*;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


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

	private final List<Session> sessions = new LinkedList<Session>();

		private void initChannel(MessageHandler messageHandler, Class<? extends Decoder> decoder) {

		ClientEndpointConfig clientEndpointConfig = ClientEndpointConfig.Builder.create()
				.encoders(Collections.<Class<? extends Encoder>>singletonList(CommandEncoder.class))
				.decoders(Collections.<Class<? extends Decoder>>singletonList(decoder)).build();

		BitstampChannel bitstampChannel = new BitstampChannel(clientEndpointConfig, messageHandler);
		try {
			Session session = bitstampChannel.init();
			sessions.add(session);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	public String subscribeOrders(String pair, final BitstampMessageHandler<Order> bitstampMessageHandler) {

		MH<Order> messageHandler = new MH<Order>(bitstampMessageHandler);
		initChannel(messageHandler, OrderDecoder.class);

		return null;
	}

	public String subscribeTrades(String pair, final BitstampMessageHandler<Trade> bitstampMessageHandler) {

		MH<Trade> messageHandler = new MH<Trade>(bitstampMessageHandler);
		initChannel(messageHandler, TradeDecoder.class);

		return null;
	}

	public void unsubscribe(String id) {

	}

	public void close() {
		try {
			for (Session session : sessions) {
				session.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
