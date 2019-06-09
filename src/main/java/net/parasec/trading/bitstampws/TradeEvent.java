package net.parasec.trading.bitstampws;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import net.parasec.trading.bitstampws.websocket.Event;
import net.parasec.trading.bitstampws.websocket.EventType;

@CompiledJson
public class TradeEvent extends Event {

	@JsonAttribute(name = "data", nullable = false)
	public Trade trade;


	static class Trade {
		private long id;
		private double amount;
		private double amountStr;
		private double price;
		private String priceStr;
		private short type;
		private int timestamp;
		private long microTimestamp;
		private long buyOrderId;
		private long sellOrderId;

		public Trade(long id, double amount, double amountStr, double price, String priceStr, short type, int timestamp, long microTimestamp, long buyOrderId, long sellOrderId) {
			this.id = id;
			this.amount = amount;
			this.amountStr = amountStr;
			this.price = price;
			this.priceStr = priceStr;
			this.type = type;
			this.timestamp = timestamp;
			this.microTimestamp = microTimestamp;
			this.buyOrderId = buyOrderId;
			this.sellOrderId = sellOrderId;
		}

		public long getId() {
			return id;
		}

		@JsonAttribute(name = "id", nullable = false)
		public void setId(long id) {
			this.id = id;
		}

		public double getAmount() {
			return amount;
		}

		@JsonAttribute(name = "amount", nullable = false)
		public void setAmount(double amount) {
			this.amount = amount;
		}

		public double getAmountStr() {
			return amountStr;
		}

		@JsonAttribute(name = "amount_str", nullable = false)
		public void setAmountStr(double amountStr) {
			this.amountStr = amountStr;
		}

		public double getPrice() {
			return price;
		}

		@JsonAttribute(name = "price", nullable = false)
		public void setPrice(double price) {
			this.price = price;
		}

		public String getPriceStr() {
			return priceStr;
		}

		@JsonAttribute(name = "price_str", nullable = false)
		public void setPriceStr(String priceStr) {
			this.priceStr = priceStr;
		}

		public short getType() {
			return type;
		}

		@JsonAttribute(name = "type", nullable = false)
		public void setType(short type) {
			this.type = type;
		}

		public int getTimestamp() {
			return timestamp;
		}

		@JsonAttribute(name = "timestamp", nullable = false)
		public void setTimestamp(int timestamp) {
			this.timestamp = timestamp;
		}

		public long getMicroTimestamp() {
			return microTimestamp;
		}

		@JsonAttribute(name = "microtimestamp", nullable = false)
		public void setMicroTimestamp(long microTimestamp) {
			this.microTimestamp = microTimestamp;
		}

		public long getBuyOrderId() {
			return buyOrderId;
		}

		@JsonAttribute(name = "buy_order_id", nullable = false)
		public void setBuyOrderId(long buyOrderId) {
			this.buyOrderId = buyOrderId;
		}

		public long getSellOrderId() {
			return sellOrderId;
		}

		@JsonAttribute(name = "sell_order_id", nullable = false)
		public void setSellOrderId(long sellOrderId) {
			this.sellOrderId = sellOrderId;
		}

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