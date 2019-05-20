package net.parasec.trading.bitstampws.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class WSEncoder implements Encoder.Text<Command> {

	public String encode(Command command) throws EncodeException {
		System.out.println("encode.");
		return "{\"event\": \"bts:subscribe\", \"data\": { \"channel\": \"live_orders_btcusd\" }}";
	}

	public void init(EndpointConfig endpointConfig) {

	}

	public void destroy() {

	}
}