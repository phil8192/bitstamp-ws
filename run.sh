#!/bin/bash
# standalone mode
# ./run.sh btcusd orders >orders.csv
java -jar target/bitstamp-ws-jar-with-dependencies.jar "$@"


# notes
# =====
# r, real-time plot
# library(scales)
# par(mar=c(0,0,0,0), bg="black", fg="white") ;while(T) { x <- tail(read.csv("orders.csv"), 250000) ;with(x, plot(local_time, price, ylim=quantile(price, probs=c(0.02,0.98)), col=alpha(ifelse(side == "BID", "white", "white"), 0.1), bty="n", axes=F, pch=ifelse(action == "ORDER_CREATED", 16, 1), cex=log(1+volume))) ;Sys.sleep(5) }
#
# added orders:
# par(mar=c(0,0,0,0), bg="black", fg="white") ;while(T) { x <- tail(read.csv("orders.csv"), 250000) ;with(x[x$action == "ORDER_CREATED", ], plot(local_time, log(volume*price), ylim=c(3, 20), pch=15, cex=0.25, col=ifelse(side == "BID", "blue", "red"))) ;Sys.sleep(5) }

