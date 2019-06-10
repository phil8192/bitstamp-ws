package net.parasec.trading.bitstampws;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import net.parasec.trading.bitstampws.websocket.Event;

@CompiledJson
public class TradeEvent extends Event {

	@JsonAttribute(name = "data", nullable = false)
	public Trade trade;

	static class Trade {

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
		@JsonAttribute(name = "type", nullable = false)
		public short type;
		@JsonAttribute(name = "timestamp", nullable = false)
		public int timestamp;
		@JsonAttribute(name = "microtimestamp", nullable = false)
		public long microTimestamp;
		@JsonAttribute(name = "buy_order_id", nullable = false)
		public long buyOrderId;
		@JsonAttribute(name = "sell_order_id", nullable = false)
		public long sellOrderId;

		@Override
		public String toString() {
			return "Trade{" +
					"id=" + id +
					", amount=" + amount +
					", amountStr=" + amountStr +
					", price=" + price +
					", priceStr='" + priceStr + '\'' +
					", type=" + type +
					", timestamp=" + timestamp +
					", microTimestamp=" + microTimestamp +
					", buyOrderId=" + buyOrderId +
					", sellOrderId=" + sellOrderId +
					'}';
		}
	}

	@Override
	public String toString() {
		return "TradeEvent{" +
				"trade=" + trade +
				", event=" + event +
				", channel='" + channel + '\'' +
				'}';
	}
}