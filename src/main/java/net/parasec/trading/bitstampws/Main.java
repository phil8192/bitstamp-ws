package net.parasec.trading.bitstampws;

public class Main {

	static String HEADER_TRADES = "local_time,exchange_time,trade_id,maker_id,taker_id,action,price,volume";
	static String HEADER_ORDERS = "local_time,exchange_time,order_id,action,side,price,volume";

	/**
	 * Output to log-files.
	 * <p>
	 * Expects a list of pairs. E.g.,
	 * <p>
	 * Fiat:         eurusd
	 * Bitcoin:      btcusd, btceur
	 * Ether:        ethusd, etheur, ethbtc
	 * Ripple:       xrpusd, xrpeur, xrpbtc
	 * Litecoin:     ltcusd, ltceur, ltcbtc
	 * Bitcoin Cash: bchusd, btheur, bchbtc
	 * <p>
	 * Example:
	 * <p>
	 * btcusd btceur ethusd
	 *
	 * @param args List of currency pairs
	 * @throws Exception :)
	 */
	public static void main(String[] args) throws Exception {
		String pair = args[0];
		String what = args[1];

		Client client = new BitstampClient();

		if (what.equals("orders")) {
			BitstampMessageHandler<OrderEvent> orderHandler = message -> Util.log(pair, what, message.toCsv(), HEADER_ORDERS);
			client.subscribeOrders(pair, orderHandler);
		} else if (what.equals("trades")) {
			BitstampMessageHandler<TradeEvent> tradeHandler = message -> Util.log(pair, what, message.toCsv(), HEADER_TRADES);
			client.subscribeTrades(pair, tradeHandler);
		} else {
			System.err.println("expected <orders|trades>");
			System.exit(-1);
		}

		Runtime.getRuntime().addShutdownHook(new Thread(() -> client.close()));

		while (true) {
			Thread.sleep(10000);
		}
	}
}
