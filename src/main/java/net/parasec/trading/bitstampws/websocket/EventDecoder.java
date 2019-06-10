package net.parasec.trading.bitstampws.websocket;

import com.dslplatform.json.DslJson;
import com.dslplatform.json.runtime.Settings;

import javax.websocket.EndpointConfig;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;

public class EventDecoder<T> implements javax.websocket.Decoder.Text<T> {

	private Class<T> type;
	private DslJson<Object> dslJson;


	public T decode(String s) {
		try {
			byte[] bytes = s.getBytes("UTF-8");
			return dslJson.deserialize(type, bytes, bytes.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean willDecode(String s) {
		return true;
	}

	public void init(EndpointConfig endpointConfig) {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.dslJson = new DslJson<>(Settings.withRuntime().allowArrayFormat(true).includeServiceLoader());
	}

	public void destroy() {

	}
}