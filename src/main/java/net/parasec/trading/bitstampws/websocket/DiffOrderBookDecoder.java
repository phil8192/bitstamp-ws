package net.parasec.trading.bitstampws.websocket;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.runtime.Settings;
import net.parasec.trading.bitstampws.DiffOrderBookEvent;

import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

public class DiffOrderBookDecoder implements Decoder.Text<DiffOrderBookEvent> {

	private DslJson<Object> dslJson
			= new DslJson<Object>(Settings.withRuntime().allowArrayFormat(true).includeServiceLoader());

	public DiffOrderBookEvent decode(String s) {
		System.out.println(s);
		try {
			byte[] bytes = s.getBytes("UTF-8");
			return dslJson.deserialize(DiffOrderBookEvent.class, bytes, bytes.length);
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
