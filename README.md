# bitstamp-ws
[![active](http://www.repostatus.org/badges/latest/active.svg)](http://www.repostatus.org/#active)
![GitHub](https://img.shields.io/github/license/phil8192/webtri.sh.svg)

> Btstamp Websocket API v2 Java client

Java client for Bitstamp's [Websocket API v2](https://www.bitstamp.net/websocket/v2/). 

## Usage

See
[Client](https://github.com/phil8192/bitstamp-ws/blob/master/src/main/java/net/parasec/trading/bitstampws/Client.java)
interface.

For example, to stream Limit Orders:

```java
// Specify a message handler
BitstampMessageHandler<OrderEvent> orderHandler = order
  -> System.out.println(order);
// subscribe to channel
client.subscribeOrders("btcusd", orderHandler);
```

It is expected that the message handler puts the message on a non-blocking queue or similar (performs minimal work). 
