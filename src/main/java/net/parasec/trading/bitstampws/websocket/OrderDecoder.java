package net.parasec.trading.bitstampws.websocket;

import net.parasec.trading.bitstampws.Order;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.Decoder;

public class OrderDecoder implements Decoder.Text<Order> {

	public Order decode(String s) throws DecodeException {
		return new Order(s);
	}

	public boolean willDecode(String s) {
		return true;
	}

	public void init(EndpointConfig endpointConfig) {

	}

	public void destroy() {

	}
}
