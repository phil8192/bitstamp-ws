package net.parasec.trading.bitstampws;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import net.parasec.trading.bitstampws.websocket.Event;

import java.util.Arrays;
import java.util.stream.Collectors;

@CompiledJson
public class DetailOrderBookEvent extends Event {

	@JsonAttribute(name = "data", nullable = false)
	public OrderBook orderBook;

	static class OrderBook {
		@JsonAttribute(name = "timestamp", nullable = false)
		public int timestamp;
		@JsonAttribute(name = "microtimestamp", nullable = false)
		public long microTimestamp;

		// [price, amount, order id]
		@JsonAttribute(name = "asks", nullable = false)
		public String[][] asks;
		@JsonAttribute(name = "bids", nullable = false)
		public String[][] bids;

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