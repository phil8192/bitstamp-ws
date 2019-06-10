package net.parasec.trading.bitstampws;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import net.parasec.trading.bitstampws.websocket.Event;

@CompiledJson
public class OrderEvent extends Event {

	@JsonAttribute(name = "data", nullable = false)
	public Order order;

	static class Order {

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
		@JsonAttribute(name = "order_type", nullable = false)
		public short type;
		@JsonAttribute(name = "datetime", nullable = false)
		public int dateTime;
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
					", dateTime=" + dateTime +
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