package net.parasec.trading.bitstampws;

public interface Client {
	String subscribeOrders(String pair, BitstampMessageHandler<OrderEvent> bitstampMessageHandler);
	String subscribeTrades(String pair, BitstampMessageHandler<TradeEvent> bitstampMessageHandler);
	String subscribeOrderBook(String pair, BitstampMessageHandler<OrderBookEvent> bitstampMessageHandler);
	String subscribeDetailOrderBook(String pair, BitstampMessageHandler<DetailOrderBookEvent> bitstampMessageHandler);
	String subscribeDiffOrderBook(String pair, BitstampMessageHandler<DiffOrderBookEvent> bitstampMessageHandler);
	void unsubscribe(String id);
	void close();
}
