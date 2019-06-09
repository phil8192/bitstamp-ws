package net.parasec.trading.bitstampws;

import net.parasec.trading.bitstampws.websocket.Event;

public interface BitstampMessageHandler<T extends Event> {

	// implementer must return immediately.
	// it is assumed that this will go on some queue.
	void onMessage(T message);

}
