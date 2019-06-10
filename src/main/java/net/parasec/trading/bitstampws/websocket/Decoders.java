package net.parasec.trading.bitstampws.websocket;

import net.parasec.trading.bitstampws.*;

public class Decoders {
	public static class TradeDecoder extends EventDecoder<TradeEvent> {
	}

	public static class OrderDecoder extends EventDecoder<OrderEvent> {
	}

	public static class OrderBookDecoder extends EventDecoder<OrderBookEvent> {
	}

	public static class DetailOrderBookDecoder extends EventDecoder<DetailOrderBookEvent> {
	}

	public static class DiffOrderBookDecoder extends EventDecoder<DiffOrderBookEvent> {
	}
}
