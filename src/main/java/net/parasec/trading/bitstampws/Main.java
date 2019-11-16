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

		Client client = new BitstampClient();

		for(String pair : args) {

			BitstampMessageHandler<OrderEvent> orderHandler = order
					-> System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + " " + order);
			client.subscribeOrders(pair, orderHandler);
		}

		Runtime.getRuntime().addShutdownHook(new Thread(() -> client.close()));

		while (true) {
			Thread.sleep(10000);
		}
	}
}
