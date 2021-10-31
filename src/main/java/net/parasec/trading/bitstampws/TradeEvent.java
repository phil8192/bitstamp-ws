package net.parasec.trading.bitstampws;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import net.parasec.trading.bitstampws.websocket.Event;

@CompiledJson
public class TradeEvent extends Event {

	@JsonAttribute(name = "data", nullable = false)
	public Trade trade;

	public static class Trade extends OrderEvent.Order {

		@JsonAttribute(name = "buy_order_id", nullable = false)
		public long buyOrderId;
		@JsonAttribute(name = "sell_order_id", nullable = false)
		public long sellOrderId;

		@Override
		public String toString() {
			return "Trade{" +
					"buyOrderId=" + buyOrderId +
					", sellOrderId=" + sellOrderId +
					", id=" + id +
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
		return "TradeEvent{" +
				"trade=" + trade +
				", event=" + event +
				", channel='" + channel + '\'' +
				'}';
	}

	@Override
	public String toCsv() {
		final Trade trade = this.trade;
		final long now = Util.timeMicroSeconds();
		final String makerTaker;
		if(trade.type == 0) {
			makerTaker = trade.sellOrderId + "," + trade.buyOrderId + ",0";
		} else {
			makerTaker = trade.buyOrderId + "," + trade.sellOrderId + ",1";
		}
		return now + "," +
				trade.microTimestamp + "," +
				trade.id + "," +
				makerTaker + "," +
				trade.priceStr + "," +
				trade.amountStr;
	}
}