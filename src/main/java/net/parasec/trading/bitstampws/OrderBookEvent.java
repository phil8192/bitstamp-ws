package net.parasec.trading.bitstampws;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import net.parasec.trading.bitstampws.websocket.Event;
import net.parasec.trading.bitstampws.websocket.EventType;

import java.util.Arrays;
import java.util.stream.Collectors;

@CompiledJson
public class OrderBookEvent extends Event {
//	@JsonAttribute(name = "event", nullable = false)
//	public EventType event;
//	@JsonAttribute(name = "channel", nullable = false)
//	public String channel;
	@JsonAttribute(name = "data", nullable = false)
	public OrderBook orderBook;

	static class OrderBook {
		@JsonAttribute(name = "timestamp", nullable = false)
		public int timestamp;
		@JsonAttribute(name = "microtimestamp", nullable = false)
		public long microTimestamp;

		// price level, volume at price level (not individual orders)
		@JsonAttribute(name = "asks", nullable = false)
		public double[][] asks;
		@JsonAttribute(name = "bids", nullable = false)
		public double[][] bids;

		@Override
		public String toString() {
			return "OrderBook{" +
					"timestamp=" + timestamp +
					", microTimestamp=" + microTimestamp +
					", asks=" + Arrays.stream(asks).map(Arrays::toString).collect(Collectors.joining(", ", "[", "]")) +
					", bids=" + Arrays.stream(bids).map(Arrays::toString).collect(Collectors.joining(", ", "[", "]")) +
					'}';
		}
	}

	@Override
	public String toString() {
		return "OrderBookEvent{" +
				"event=" + event +
				", channel='" + channel + '\'' +
				", orderBook=" + orderBook +
				'}';
	}
}