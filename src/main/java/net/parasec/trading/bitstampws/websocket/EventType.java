package net.parasec.trading.bitstampws.websocket;

import com.dslplatform.json.JsonValue;

public enum EventType {
	ORDER_CREATED("order_created"),
	ORDER_DELETED("order_deleted"),
	ORDER_UPDATED("order_updated"),
  TRADE("trade"),
	SUBSCRIPTION_SUCCEEDED("bts:subscription_succeeded");

	String s;
	EventType(String s) {
		this.s = s;
	}

	@JsonValue
	public String getValue() {
		return s;
	}
}
