package net.parasec.trading.bitstampws;

public class Main {

	/**
	 * Just output (orders) to terminal.
	 *
	 * Expects a list of pairs. E.g.,
	 *
	 * Fiat:         eurusd
	 * Bitcoin:      btcusd, btceur
	 * Ether:        ethusd, etheur, ethbtc
	 * Ripple:       xrpusd, xrpeur, xrpbtc
	 * Litecoin:     ltcusd, ltceur, ltcbtc
	 * Bitcoin Cash: bchusd, btheur, bchbtc
	 *
	 * Example:
	 *
	 * btcusd btceur ethusd
	 *
	 * @param args List of currency pairs
	 * @throws Exception :)
	 */
	public static void main(String[] args) throws Exception {
		String pair = args[0];
		String what = args[1];

		Client client = new BitstampClient();

		if(what.equals("orders")) {
			System.out.println("local_time,exchange_time,order_id,action,side,price,volume");
			BitstampMessageHandler<OrderEvent> orderHandler = message -> System.out.println(message.toCsv());
			client.subscribeOrders(pair, orderHandler);
		} else if(what.equals("trades")) {
			System.out.println("local_time,exchange_time,trade_id,maker_id,taker_id,action,price,volume");
			BitstampMessageHandler<TradeEvent> tradeHandler = message -> System.out.println(message.toCsv());
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
