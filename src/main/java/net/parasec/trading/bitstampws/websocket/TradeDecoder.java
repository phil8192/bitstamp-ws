package net.parasec.trading.bitstampws.websocket;

import net.parasec.trading.bitstampws.Trade;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.Decoder;

public class TradeDecoder implements Decoder.Text<Trade> {

	public Trade decode(String s) throws DecodeException {
		return new Trade(s);
	}

	public boolean willDecode(String s) {
		return true;
	}

	public void init(EndpointConfig endpointConfig) {

	}

	public void destroy() {

	}
}