package net.parasec.trading.bitstampws.websocket;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.Decoder;

public class WSDecoder implements Decoder.Text<Message> {

	public Message decode(String s) throws DecodeException {
		return new Message("x");
	}

	public boolean willDecode(String s) {
		return true;
	}

	public void init(EndpointConfig endpointConfig) {

	}

	public void destroy() {

	}
}
