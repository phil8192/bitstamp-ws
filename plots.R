library(scales)
library(data.table)
library(bit64)

orders <- fread("orders7.csv")
trades <- fread("trades7.csv")

par(mar=c(0,0,0,0), bg="black", fg="white")
with(orders[orders$action == "ORDER_CREATED", ], plot(local_time, log(volume*price), ylim=c(4, 14), pch=15, cex=0.15, col=alpha(ifelse(side == "BID", "blue", "red"), 0.6)))
par(new=T)
with(trades, plot(local_time, price, type="l"))
