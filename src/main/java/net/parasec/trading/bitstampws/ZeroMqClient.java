package net.parasec.trading.bitstampws;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

public class ZeroMqClient {
    public static void main(String[] args) throws Exception {
        String port = args[1];

        ZMQ.Context ctx = ZMQ.context(1);
        ZMQ.Socket pub = ctx.socket(SocketType.PUB);
        pub.bind("tcp://*:" + port);

        Client client = new BitstampClient();

        for (String pair : args) {

            BitstampMessageHandler<OrderEvent> orderHandler = order
                    -> pub.send(Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + " " + order);
            client.subscribeOrders(pair, orderHandler);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> client.close()));

        while (true) {
            Thread.sleep(10000);
        }
    }
}
