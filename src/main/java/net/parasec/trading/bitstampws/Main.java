package net.parasec.trading.bitstampws;

public class Main {

	public static void main(String[] args) throws Exception {

		Client client = new BitstampClient();

		BitstampMessageHandler<Order> orderHandler = new BitstampMessageHandler<Order>() {
			public void onMessage(Order order) {
				System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + " " + order);
			}
		};

		BitstampMessageHandler<Trade> tradeHandler = new BitstampMessageHandler<Trade>() {

			public void onMessage(Trade order) {
				System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + " " + order);
			}
		};

		client.subscribeOrders("btc_usd", orderHandler);
		//client.subscribeOrders("eth_usd", orderHandler);

		//client.subscribeTrades("btc_usd", tradeHandler);



		Thread.sleep(10000);

		client.close();

	}
}
