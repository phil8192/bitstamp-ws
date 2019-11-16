package net.parasec.trading.bitstampws;

import net.parasec.trading.bitstampws.websocket.Event;

public interface BitstampMessageHandler<T extends Event> {
	/**
	 * Message handler for various Bitstamp message types.
	 *
	 * Implementer must return immediately.
	 * It is assumed that the message is placed on a queue, e.g., @see java.util.concurrent.ConcurrentLinkedQueue
	 *
	 * Examples:
	 *
	 * BitstampMessageHandler<OrderEvent> orderHandler = order
	 *   -> System.out.println(order);
	 *
	 * BitstampMessageHandler<TradeEvent> tradeHandler = trade
	 *   -> System.out.println(trade);
	 *
	 * BitstampMessageHandler<OrderBookEvent> orderBookHandler = orderBookEvent
	 * 	 -> System.out.println(orderBookEvent);
	 *
	 * BitstampMessageHandler<DetailOrderBookEvent> detailOrderBookHandler = detailOrderBookEvent
	 *   -> System.out.println(detailOrderBookEvent);
	 *
	 * BitstampMessageHandler<DiffOrderBookEvent> diffOrderBookHandler = diffOrderBookEvent
	 *   -> System.out.println(diffOrderBookEvent);
	 *
	 * @param message @see net.parasec.trading.bitstampws.websocket.Event
	 */
	void onMessage(T message);
}
