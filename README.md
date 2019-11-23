# bitstamp-ws
[![active](http://www.repostatus.org/badges/latest/active.svg)](http://www.repostatus.org/#active)
![GitHub](https://img.shields.io/github/license/phil8192/webtri.sh.svg)

> Btstamp Websocket API v2 Java client

Java client for Bitstamp's [Websocket API v2](https://www.bitstamp.net/websocket/v2/).
Makes use of [dsl-json](https://github.com/ngs-doo/dsl-json) and [tyrus](https://tyrus-project.github.io/).

## Usage

See
[Client](https://github.com/phil8192/bitstamp-ws/blob/master/src/main/java/net/parasec/trading/bitstampws/Client.java)
interface.

For example, to stream limit orders for `btcusd`:

```java
// 1. Specify a message handler
Client client = new BitstampClient();
BitstampMessageHandler<OrderEvent> orderHandler = order -> System.out.println(order);

// 2. Subscribe to channel
String subscriptionId = client.subscribeOrders("btcusd", orderHandler);

// 3. Close channel
client.unsubscribe(subscriptionId)
```

Example [here](https://github.com/phil8192/limit-order-book/blob/master/src/main/java/net/parasec/ob/OrderBookStream.java).


It is expected that the message handler puts the message on a non-blocking queue or similar (performs minimal work). 
