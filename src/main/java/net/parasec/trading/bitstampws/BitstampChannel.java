package net.parasec.trading.bitstampws;

import net.parasec.trading.bitstampws.websocket.*;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class BitstampChannel {

	private final ClientEndpointConfig clientEndpointConfig;
	private final MessageHandler messageHandler;

	private Channel channel;
	private String pair;

	private Session session;

	public BitstampChannel(ClientEndpointConfig clientEndpointConfig,
												 MessageHandler messageHandler,
												 Channel channel,
												 String pair) {
		this.clientEndpointConfig = clientEndpointConfig;
		this.messageHandler = messageHandler;
		this.channel = channel;
		this.pair = pair;
	}

	void init() throws URISyntaxException, IOException, DeploymentException {

		WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();

		final class MyEndpoint extends Endpoint {

			@Override
			public void onOpen(Session session, EndpointConfig endpointConfig) {
				session.addMessageHandler(messageHandler);
				try {

					RemoteEndpoint.Basic basicRemoteEndpoint = session.getBasicRemote();
					Command command = new Command(ChannelEvent.SUBSCRIBE, channel, pair);
					basicRemoteEndpoint.sendObject(command);

				} catch(Exception e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onClose(Session session, CloseReason closeReason) {
				super.onClose(session, closeReason);
			}

			@Override
			public void onError(Session session, Throwable thr) {
				super.onError(session, thr);
				System.err.println("error: " + thr);
			}
		}
		this.session = webSocketContainer.connectToServer(new MyEndpoint(), clientEndpointConfig, new URI("wss://ws.bitstamp.net"));
	}

	void close() {
		try {
			session.getBasicRemote().sendObject(new Command(ChannelEvent.UNSUBSCRIBE, channel, pair));
			session.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
