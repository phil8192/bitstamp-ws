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

		client.subscribeOrders("btcusd", orderHandler);
		client.subscribeTrades("btcusd", tradeHandler);

		client.subscribeOrders("ethusd", orderHandler);
		client.subscribeTrades("ethusd", tradeHandler);

		client.subscribeOrders("xrpusd", orderHandler);
		client.subscribeTrades("xrpusd", tradeHandler);

		Thread.sleep(10000);

		client.close();
	}
}
