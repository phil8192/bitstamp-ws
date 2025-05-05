package net.parasec.trading.bitstampws.websocket;

// https://www.bitstamp.net/websocket/v2/
public class Command {

	private final ChannelEvent event;
	private final Channel channel;
	private final String pair;

	public Command(ChannelEvent event, Channel channel, String pair) {
		this.event = event;
		this.channel = channel;
		this.pair = pair;
	}

	@Override
	public String toString() {
		// dirty to json.
		return "{" +
				"\"event\": \"bts:" + event.toString().toLowerCase() + "\"," +
				"\"data\": {" +
				"\"channel\": \"" + channel.toString().toLowerCase() + "_" + pair + "\"" +
				"}}";
	}
}