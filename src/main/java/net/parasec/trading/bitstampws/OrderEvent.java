package net.parasec.trading.bitstampws;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import net.parasec.trading.bitstampws.websocket.Event;

@CompiledJson
public class OrderEvent extends Event {

	@JsonAttribute(name = "data", nullable = false)
	public Order order;

	public static class Order {

		@JsonAttribute(name = "id", nullable = false)
		public long id;
		@JsonAttribute(name = "amount", nullable = false)
		public double amount;
		@JsonAttribute(name = "amount_str", nullable = false)
		public double amountStr;
		@JsonAttribute(name = "price", nullable = false)
		public double price;
		@JsonAttribute(name = "price_str", nullable = false)
		public String priceStr;
		// todo: might be more efficient to have different (nullable) fields for these.
		@JsonAttribute(name = "order_type", alternativeNames = {"type"}, nullable = false)
		public short type;
		@JsonAttribute(name = "datetime", alternativeNames = {"timestamp"}, nullable = false)
		public int timestamp;
		@JsonAttribute(name = "microtimestamp", nullable = false)
		public long microTimestamp;

		@Override
		public String toString() {
			return "Order{" +
					"id=" + id +
					", amount=" + amount +
					", amountStr=" + amountStr +
					", price=" + price +
					", priceStr='" + priceStr + '\'' +
					", type=" + type +
					", timestamp=" + timestamp +
					", microTimestamp=" + microTimestamp +
					'}';
		}
	}

	@Override
	public String toString() {
		return "OrderEvent{" +
				"event='" + event + '\'' +
				", channel='" + channel + '\'' +
				", order=" + order +
				'}';
	}
}