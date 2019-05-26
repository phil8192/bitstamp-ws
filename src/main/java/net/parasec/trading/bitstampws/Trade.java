package net.parasec.trading.bitstampws;

public class Trade {
	private String s;

	public Trade(String s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return s;
	}
}