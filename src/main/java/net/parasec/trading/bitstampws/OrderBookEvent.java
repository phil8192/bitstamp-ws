package net.parasec.trading.bitstampws;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import net.parasec.trading.bitstampws.websocket.EventType;

import java.util.Arrays;
import java.util.stream.Collectors;

@CompiledJson
public class OrderBookEvent {
	private EventType event;
	private String channel;
	private OrderBook orderBook;

	@JsonAttribute(name = "event", nullable = false)
	public void setEvent(EventType event) {
		this.event = event;
	}

	@JsonAttribute(name = "channel", nullable = false)
	public void setChannel(String channel) {
		this.channel = channel;
	}

	@JsonAttribute(name = "data", nullable = false)
	public void setOrderBook(OrderBook orderBook) {
		this.orderBook = orderBook;
	}


	public OrderBookEvent(EventType event, String channel, OrderBook orderBook) {
		this.event = event;
		this.channel = channel;
		this.orderBook = orderBook;
	}

	public EventType getEvent() {
		return event;
	}

	public String getChannel() {
		return channel;
	}

	public OrderBook getOrderBook() {
		return orderBook;
	}


	static class OrderBook {
		@JsonAttribute(name = "timestamp", nullable = false)
		public int timestamp;
		@JsonAttribute(name = "microtimestamp", nullable = false)
		public long microTimestamp;
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