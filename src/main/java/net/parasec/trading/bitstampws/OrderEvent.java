package net.parasec.trading.bitstampws;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;

@CompiledJson
public class OrderEvent {

	private String event;
	private String channel;
	private Order order;

	public OrderEvent(String event, String channel, Order order) {
		this.event = event;
		this.channel = channel;
		this.order = order;
	}

	@JsonAttribute(name = "event", nullable = false)
	public void setEvent(String event) {
		this.event = event;
	}

	@JsonAttribute(name = "channel", nullable = false)
	public void setChannel(String channel) {
		this.channel = channel;
	}

	@JsonAttribute(name = "data", nullable = false)
	public void setOrder(Order order) {
		this.order = order;
	}

	public String getEvent() {
		return event;
	}

	public String getChannel() {
		return channel;
	}

	public Order getOrder() {
		return order;
	}

	static class Order {

		private long id;
		private double amount;
		private double amountStr;
		private double price;
		private String priceStr;
		private short type;
		private int dateTime;
		private long microTimestamp;

		public Order(long id, double amount, double amountStr, double price, String priceStr, short type, int dateTime, long microTimestamp) {
			this.id = id;
			this.amount = amount;
			this.amountStr = amountStr;
			this.price = price;
			this.priceStr = priceStr;
			this.dateTime = dateTime;
			this.microTimestamp = microTimestamp;
		}

		@JsonAttribute(name = "id", nullable = false)
		public void setId(long id) {
			this.id = id;
		}

		@JsonAttribute(name = "amount", nullable = false)
		public void setAmount(double amount) {
			this.amount = amount;
		}

		@JsonAttribute(name = "amount_str", nullable = false)
		public void setAmountStr(double amountStr) {
			this.amountStr = amountStr;
		}

		@JsonAttribute(name = "price", nullable = false)
		public void setPrice(double price) {
			this.price = price;
		}

		@JsonAttribute(name = "price_str", nullable = false)
		public void setPriceStr(String priceStr) {
			this.priceStr = priceStr;
		}

		@JsonAttribute(name = "order_type", nullable = false)
		public void setType(short type) {
			this.type = type;
		}

		@JsonAttribute(name = "datetime", nullable = false)
		public void setDateTime(int dateTime) {
			this.dateTime = dateTime;
		}

		@JsonAttribute(name = "microtimestamp", nullable = false)
		public void setMicroTimestamp(long microTimestamp) {
			this.microTimestamp = microTimestamp;
		}

		public long getId() {
			return id;
		}

		public double getAmount() {
			return amount;
		}

		public double getAmountStr() {
			return amountStr;
		}

		public double getPrice() {
			return price;
		}

		public String getPriceStr() {
			return priceStr;
		}

		public short getType() {
			return type;
		}

		public int getDateTime() {
			return dateTime;
		}

		public long getMicroTimestamp() {
			return microTimestamp;
		}

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