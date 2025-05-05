#!/bin/bash
curl -s "https://www.bitstamp.net/api/v2/order_book/$1?group=2" > "$1"-ob-"$(date --utc +%s)".json
