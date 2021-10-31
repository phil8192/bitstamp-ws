package net.parasec.trading.bitstampws;

import java.time.Instant;

public class Util {
    public static long timeMicroSeconds() {
        final Instant now = Instant.now();
        return now.getEpochSecond() * 1000_000 + now.getNano() / 1000;
    }
}
