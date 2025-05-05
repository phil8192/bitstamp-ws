package net.parasec.trading.bitstampws.websocket;

import com.dslplatform.json.JsonValue;

public enum EventType {
	ORDER_CREATED("order_created"),
	ORDER_DELETED("order_deleted"),
	ORDER_UPDATED("order_changed"),
	TRADE("trade"),
	SUBSCRIPTION_SUCCEEDED("bts:subscription_succeeded"),
	FORCED_RECONNECT("bts:request_reconnect"),
	DATA("data"); // ob

	final String s;

	EventType(String s) {
		this.s = s;
	}

	@JsonValue
	public String getValue() {
		return s;
	}
}
