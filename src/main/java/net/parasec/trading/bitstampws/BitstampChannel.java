package net.parasec.trading.bitstampws;

import jdk.vm.ci.code.site.Call;
import net.parasec.trading.bitstampws.websocket.*;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class BitstampChannel {
	public final static String URI = "wss://ws.bitstamp.net";

	private final ClientEndpointConfig clientEndpointConfig;
	private final MessageHandler messageHandler;

	private final Channel channel;
	private final String pair;

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

		final class MyEndpoint extends Endpoint {
			private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

			@Override
			public void onOpen(Session session, EndpointConfig endpointConfig) {
				System.err.println(Util.timeMicroSeconds() + " connection established");
				if(!session.getMessageHandlers().contains(messageHandler)) {
					session.addMessageHandler(messageHandler);
				}

				try {

					RemoteEndpoint.Basic basicRemoteEndpoint = session.getBasicRemote();
					Command command = new Command(ChannelEvent.SUBSCRIBE, channel, pair);
					basicRemoteEndpoint.sendObject(command);

				} catch (Exception e) {
					e.printStackTrace();
				}

				executorService.scheduleAtFixedRate(() -> {
					//System.err.println(Util.timeMicroSeconds() + " PING");
					try {
						ByteBuffer payload = ByteBuffer.wrap("PING".getBytes());
						session.getAsyncRemote().sendPing(payload);
					} catch (IOException e) {
						e.printStackTrace();
					}
					}, 1, 1, TimeUnit.SECONDS);
			}

			@Override
			public void onClose(Session session, CloseReason closeReason) {
				System.err.println(Util.timeMicroSeconds() + " onClose(" + closeReason + ")");
				super.onClose(session, closeReason);
				if (closeReason.getCloseCode().equals(CloseReason.CloseCodes.CLOSED_ABNORMALLY)) {
					System.err.println(Util.timeMicroSeconds() + " try to reconnect");
					try {
						session.getContainer().connectToServer(this, clientEndpointConfig, new URI(URI));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onError(Session session, Throwable thr) {
				System.err.println("onError(" + thr + ")");
				super.onError(session, thr);
			}
		}

		final WebSocketContainer webSocketContainer = ContainerProvider.getWebSocketContainer();

		this.session = webSocketContainer.connectToServer(
				new MyEndpoint(), clientEndpointConfig,
				new URI(URI)
		);
	}

	void close() {
		try {
			session.getBasicRemote().sendObject(new Command(ChannelEvent.UNSUBSCRIBE, channel, pair));
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
