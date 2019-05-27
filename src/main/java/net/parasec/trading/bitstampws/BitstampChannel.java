package net.parasec.trading.bitstampws;

import net.parasec.trading.bitstampws.websocket.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class BitstampChannel {

	private final Logger logger = LogManager.getLogger(BitstampChannel.class);
	private final ClientEndpointConfig clientEndpointConfig;
	private final MessageHandler messageHandler;

	public BitstampChannel(ClientEndpointConfig clientEndpointConfig, MessageHandler messageHandler) {
		this.clientEndpointConfig = clientEndpointConfig;
		this.messageHandler = messageHandler;
	}

	Session init(final Channel channel, final String pair) throws URISyntaxException, IOException, DeploymentException {

		WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();

		final class MyEndpoint extends Endpoint {

			@Override
			public void onOpen(Session session, EndpointConfig endpointConfig) {
				System.out.println("Websocket connected");
				session.addMessageHandler(messageHandler);
				try {

					RemoteEndpoint.Basic basicRemoteEndpoint = session.getBasicRemote();
					basicRemoteEndpoint.sendObject(new Command(Event.SUBSCRIBE, channel, pair));

				} catch(EncodeException ee) {
					ee.printStackTrace();
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}

			@Override
			public void onClose(Session session, CloseReason closeReason) {
				super.onClose(session, closeReason);
				System.out.println("closed: " + closeReason);
			}

			@Override
			public void onError(Session session, Throwable thr) {
				super.onError(session, thr);
				System.out.println("error: " + thr);
			}
		}

		Endpoint endpoint = new MyEndpoint();

		return webSocketContainer.connectToServer(endpoint, clientEndpointConfig, new URI("wss://ws.bitstamp.net"));

	}
}
