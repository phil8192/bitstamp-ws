package net.parasec.trading.bitstampws;

import net.parasec.trading.bitstampws.websocket.CommandEncoder;
import net.parasec.trading.bitstampws.websocket.OrderDecoder;
import net.parasec.trading.bitstampws.websocket.TradeDecoder;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.Decoder;
import javax.websocket.Encoder;
import javax.websocket.MessageHandler;
import java.util.Collections;


public class BitstampClient implements Client {


	private class MH<T> implements MessageHandler.Whole<T> {
		BitstampMessageHandler<T> bitstampMessageHandler;
		MH(BitstampMessageHandler<T> bitstampMessageHandler) {
			this.bitstampMessageHandler = bitstampMessageHandler;
		}
		public void onMessage(T message) {
			bitstampMessageHandler.onMessage(message);
		}
	}

	private void initChannel(MessageHandler messageHandler, Class<? extends Decoder> decoder) {

		ClientEndpointConfig clientEndpointConfig = ClientEndpointConfig.Builder.create()
				.encoders(Collections.<Class<? extends Encoder>>singletonList(CommandEncoder.class))
				.decoders(Collections.<Class<? extends Decoder>>singletonList(decoder)).build();

		BitstampChannel bitstampChannel = new BitstampChannel(clientEndpointConfig, messageHandler);
		try {
			bitstampChannel.init();
		} catch(Exception e) {

		}
	}

	public void subscribeOrders(String pair, final BitstampMessageHandler<Order> bitstampMessageHandler) {

		MH<Order> messageHandler = new MH<Order>(bitstampMessageHandler);
		initChannel(messageHandler, OrderDecoder.class);

	}

	public void subscribeTrades(String pair, final BitstampMessageHandler<Trade> bitstampMessageHandler) {

		MH<Trade> messageHandler = new MH<Trade>(bitstampMessageHandler);
		initChannel(messageHandler, TradeDecoder.class);
	}



}
