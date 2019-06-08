package net.parasec.trading.bitstampws.websocket;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class CommandEncoder implements Encoder.Text<Command> {

	public String encode(Command command) {
		return command.toString();
	}

	public void init(EndpointConfig endpointConfig) {

	}

	public void destroy() {

	}
}