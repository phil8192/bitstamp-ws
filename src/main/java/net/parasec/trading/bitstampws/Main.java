package net.parasec.trading.bitstampws;

public class Main {

	public static void main(String[] args) throws Exception {

		Client client = new BitstampClient();

		BitstampMessageHandler<OrderEvent> orderHandler = new BitstampMessageHandler<OrderEvent>() {
			public void onMessage(OrderEvent order) {
				System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + " " + order);
			}
		};

		BitstampMessageHandler<TradeEvent> tradeHandler = new BitstampMessageHandler<TradeEvent>() {

			public void onMessage(TradeEvent trade) {
				System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + " " + trade);
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
