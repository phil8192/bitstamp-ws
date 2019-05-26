package net.parasec.trading.bitstampws;

public class Order {
	private String s;
	public Order(String s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return s;
	}
}
