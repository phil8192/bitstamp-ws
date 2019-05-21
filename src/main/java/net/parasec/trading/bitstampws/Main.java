package net.parasec.trading.bitstampws;

public class Main {
	public static void main(String[] args) throws Exception {
		Client client = new BitstampClient();
		client.subscribe("live_orders", "btc_usd");
	}
}
