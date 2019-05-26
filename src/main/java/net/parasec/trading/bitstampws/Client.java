package net.parasec.trading.bitstampws;

public interface Client {
	void subscribeOrders(String pair, BitstampMessageHandler<Order> bitstampMessageHandler);
	void subscribeTrades(String pair, BitstampMessageHandler<Trade> bitstampMessageHandler);
}
