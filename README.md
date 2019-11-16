# bitstamp-ws
[![active](http://www.repostatus.org/badges/latest/active.svg)](http://www.repostatus.org/#active)
![GitHub](https://img.shields.io/github/license/phil8192/webtri.sh.svg)

> Btstamp Websocket API v2 Java client

Java client for Bitstamp's [https://www.bitstamp.net/websocket/v2/](Websocket API v2). 

## Usage

See `Client` interface.

For example, to stream Limit Orders:

```java
// Specify a message handler
BitstampMessageHandler<OrderEvent> orderHandler = order
  -> System.out.println(order);
// subscribe to channel
client.subscribeOrders("btcusd", orderHandler);
```

It is expected that the message handler puts the message on a non-blocking queue or similar (performs minimal work). 
