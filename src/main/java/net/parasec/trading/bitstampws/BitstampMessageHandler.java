package net.parasec.trading.bitstampws;

public interface BitstampMessageHandler<T> {

	void onMessage(T message);

}
