package net.parasec.trading.bitstampws;

public interface Client {
	void subscribe(String channel, String pair);
}
