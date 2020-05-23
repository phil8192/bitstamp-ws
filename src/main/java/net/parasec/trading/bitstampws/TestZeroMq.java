package net.parasec.trading.bitstampws;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

public class TestZeroMq {
    public static void main(String[] args) {
        ZMQ.Context ctx = ZMQ.context(1);
        ZMQ.Socket sub = ctx.socket(SocketType.SUB);

        sub.connect("tcp://127.0.0.1:12345");
        sub.subscribe("".getBytes());

        while(true) {
            System.out.println("SUB: " + sub.recvStr());
        }
    }
}
