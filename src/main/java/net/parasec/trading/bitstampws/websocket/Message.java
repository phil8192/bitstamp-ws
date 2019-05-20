package net.parasec.trading.bitstampws.websocket;

public class Message {
	private String s;
	public Message(String s) {
		this.s = s;
	}

	@Override
	public String toString() {
		return "Message{" +
				"s='" + s + '\'' +
				'}';
	}
}
