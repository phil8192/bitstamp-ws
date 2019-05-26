package net.parasec.trading.bitstampws;

public interface BitstampMessageHandler<T> {

	// implementer must return immediately.
	// it is assumed that this will go on some queue.
	void onMessage(T message);

}
