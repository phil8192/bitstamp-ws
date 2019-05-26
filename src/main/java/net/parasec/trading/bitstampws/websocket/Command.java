package net.parasec.trading.bitstampws.websocket;

// https://www.bitstamp.net/websocket/v2/
public class Command {

	private Event event;
	private Channel channel;
	private String pair;

	public Command(Event event, Channel channel, String pair) {
		this.event = event;
		this.channel = channel;
		this.pair = pair;
	}

	@Override
	public String toString() {
		// dirty to json.
		return "{" +
				"\"event\": \"bts:" + event.toString().toLowerCase() + "," +
				"\"data\": {" +
				"\"channel\": \"[" + channel.toString().toLowerCase() + "_" + pair + "]" +
				"}}";
	}
}