package net.parasec.trading.bitstampws.websocket;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.runtime.Settings;
import net.parasec.trading.bitstampws.TradeEvent;

import javax.websocket.EndpointConfig;
import javax.websocket.Decoder;
import java.io.IOException;

public class TradeDecoder implements Decoder.Text<TradeEvent> {

	private DslJson<Object> dslJson
			= new DslJson<Object>(Settings.withRuntime().allowArrayFormat(true).includeServiceLoader());


	public TradeEvent decode(String s) {
		try {
			byte[] bytes = s.getBytes("UTF-8");
			return dslJson.deserialize(TradeEvent.class, bytes, bytes.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean willDecode(String s) {
		return true;
	}

	public void init(EndpointConfig endpointConfig) {

	}

	public void destroy() {

	}
}