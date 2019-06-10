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

		BitstampMessageHandler<OrderBookEvent> orderBookHandler = new BitstampMessageHandler<OrderBookEvent>() {
			public void onMessage(OrderBookEvent orderBookEvent) {
				System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + " " + orderBookEvent);
			}
		};

		BitstampMessageHandler<DetailOrderBookEvent> detailOrderBookHandler = new BitstampMessageHandler<DetailOrderBookEvent>() {
			public void onMessage(DetailOrderBookEvent detailOrderBookEvent) {
				System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + " " + detailOrderBookEvent);
			}
		};

		BitstampMessageHandler<DiffOrderBookEvent> diffOrderBookHandler = new BitstampMessageHandler<DiffOrderBookEvent>() {
			public void onMessage(DiffOrderBookEvent diffOrderBookEvent) {
				System.out.println(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + " " + diffOrderBookEvent);
			}
		};


		//client.subscribeOrders("btcusd", orderHandler);
		client.subscribeTrades("btcusd", tradeHandler);
//
//		client.subscribeOrders("ethusd", orderHandler);
		//client.subscribeTrades("ethusd", tradeHandler);
//
//		client.subscribeOrders("xrpusd", orderHandler);
		//client.subscribeTrades("xrpusd", tradeHandler);

		//client.subscribeOrderBook("btcusd", orderBookHandler);
		//client.subscribeDetailOrderBook("btcusd", detailOrderBookHandler);
		//client.subscribeDiffOrderBook("btcusd", diffOrderBookHandler);


		Runtime.getRuntime().addShutdownHook(new Thread(() -> client.close()));

		while (true) {
			Thread.sleep(10000);
		}


	}
}
