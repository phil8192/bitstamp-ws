package net.parasec.trading.bitstampws.websocket;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

@CompiledJson
public class Event {

	@JsonAttribute(name = "event", nullable = false)
	public EventType event;

	@JsonAttribute(name = "channel", nullable = false)
	public String channel;

	public String toCsv() {
		return "Event";
	}
}