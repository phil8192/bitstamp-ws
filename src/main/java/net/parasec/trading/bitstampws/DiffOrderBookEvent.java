package net.parasec.trading.bitstampws;

import com.dslplatform.json.CompiledJson;

@CompiledJson
public class DiffOrderBookEvent extends OrderBookEvent {

	@Override
	public String toString() {
		return "DiffOrderBookEvent{" +
				"orderBook=" + orderBook +
				", event=" + event +
				", channel='" + channel + '\'' +
				'}';
	}
}