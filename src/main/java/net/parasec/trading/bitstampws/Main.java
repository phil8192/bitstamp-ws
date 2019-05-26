package net.parasec.trading.bitstampws;

public class Main {

	public static void main(String[] args) throws Exception {

		Client client = new BitstampClient();

		BitstampMessageHandler<Order> orderHandler = new BitstampMessageHandler<Order>() {
			public void onMessage(Order order) {
				System.out.println(Thread.currentThread().getName() + " " + order);
			}
		};

		BitstampMessageHandler<Trade> tradeHandler = new BitstampMessageHandler<Trade>() {

			public void onMessage(Trade order) {
				System.out.println(Thread.currentThread().getName() + " " + order);
			}
		};

		client.subscribeOrders("btc_usd", orderHandler);
		client.subscribeTrades("btc_usd", tradeHandler);

	}
}
