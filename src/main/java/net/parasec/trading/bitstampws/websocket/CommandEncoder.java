package net.parasec.trading.bitstampws.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class CommandEncoder implements Encoder.Text<Command> {

	public String encode(Command command) throws EncodeException {
		String s = command.toString();
		System.out.println(s);
		return s;
	}

	public void init(EndpointConfig endpointConfig) {

	}

	public void destroy() {

	}
}