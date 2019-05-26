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

			MessageHandler messageHandler = new MessageHandler.Whole<Order>() {
				public void onMessage(Order s) {
					bitstampMessageHandler.onMessage(s);
				}
			};

			initChannel(messageHandler, OrderDecoder.class);
	}

	public void subscribeTrades(String pair, final BitstampMessageHandler<Trade> bitstampMessageHandler) {

		MessageHandler messageHandler = new MessageHandler.Whole<Trade>() {
			public void onMessage(Trade s) {
				bitstampMessageHandler.onMessage(s);
			}
		};

		initChannel(messageHandler, TradeDecoder.class);
	}



}
