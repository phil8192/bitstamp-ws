package net.parasec.trading.bitstampws;

public interface Client {
	String subscribeOrders(String pair, BitstampMessageHandler<OrderEvent> bitstampMessageHandler);
	String subscribeTrades(String pair, BitstampMessageHandler<TradeEvent> bitstampMessageHandler);
	void unsubscribe(String id);
	void close();
}
