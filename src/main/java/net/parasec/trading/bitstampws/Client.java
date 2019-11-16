package net.parasec.trading.bitstampws;

/**
 * Main Bitstamp API interface.
 */
public interface Client {
	/**
	 * Subscribe to a Limit Order (OrderEvent) stream.
	 *
	 * Example:
	 *
	 * BitstampMessageHandler<OrderEvent> orderHandler = order
	 *   -> System.out.println(order);
	 * client.subscribeOrders("btcusd", orderHandler);
	 *
	 * @param pair The pair. E.g., "btcusd"
	 * @param bitstampMessageHandler @see net.parasec.trading.bitstampws.BitstampMessageHandler
	 * @return A subscription ID.
	 */
	String subscribeOrders(String pair, BitstampMessageHandler<OrderEvent> bitstampMessageHandler);

	/**
	 * Subscribe to a trade stream (OrderEvent)
	 *
	 * Example:
	 *
	 * BitstampMessageHandler<TradeEvent> tradeHandler = trade
	 *   -> System.out.println(trade);
	 * client.subscribeTrades("btcusd", tradeHandler);
	 *
	 * @param pair The pair. E.g., "btcusd"
	 * @param bitstampMessageHandler @see net.parasec.trading.bitstampws.BitstampMessageHandler
	 * @return A subscription ID.
	 */
	String subscribeTrades(String pair, BitstampMessageHandler<TradeEvent> bitstampMessageHandler);

	/**
	 * Subscribe to an order book event stream (OrderBookEvent)
	 *
	 * Example:
	 *
	 * BitstampMessageHandler<OrderBookEvent> orderBookHandler = orderBookEvent
	 *   -> System.out.println(orderBookEvent);
	 * client.subscribeOrderBook("btcusd", orderBookHandler);
	 *
	 * @param pair The pair. E.g., "btcusd"
	 * @param bitstampMessageHandler @see net.parasec.trading.bitstampws.BitstampMessageHandler
	 * @return A subscription ID.
	 */
	String subscribeOrderBook(String pair, BitstampMessageHandler<OrderBookEvent> bitstampMessageHandler);

	/**
	 * Subscribe to a detailed order book stream (DetailOrderBookEvent)
	 *
	 * Example:
	 *
	 * BitstampMessageHandler<DetailOrderBookEvent> detailOrderBookHandler = detailOrderBookEvent
	 *   -> System.out.println(detailOrderBookEvent);
	 * client.subscribeDetailOrderBook("btcusd", detailOrderBookHandler);
	 *
	 * @param pair The pair. E.g., "btcusd"
	 * @param bitstampMessageHandler @see net.parasec.trading.bitstampws.BitstampMessageHandler
	 * @return A subscription ID.
	 */
	String subscribeDetailOrderBook(String pair, BitstampMessageHandler<DetailOrderBookEvent> bitstampMessageHandler);

	/**
	 * Subscribe to an order book delta stream (DiffOrderBookEvent)
	 *
	 * Example:
	 *
	 * BitstampMessageHandler<DiffOrderBookEvent> diffOrderBookHandler = diffOrderBookEvent
	 *   -> System.out.println(diffOrderBookEvent);
	 * client.subscribeDiffOrderBook("btcusd", diffOrderBookHandler);
	 *
	 * @param pair The pair. E.g., "btcusd"
	 * @param bitstampMessageHandler @see net.parasec.trading.bitstampws.BitstampMessageHandler
	 * @return A subscription ID.
	 */
	String subscribeDiffOrderBook(String pair, BitstampMessageHandler<DiffOrderBookEvent> bitstampMessageHandler);

	/**
	 * Unsubscribe/tear down a subscription.
	 * @param id A subscription ID.
	 */
	void unsubscribe(String id);

	/**
	 * Tear everything down.
	 */
	void close();
}
