package net.parasec.trading.bitstampws;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.TimeZone;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

public class Util {
	public static long timeMicroSeconds() {
		final Instant now = Instant.now();
		return now.getEpochSecond() * 1000_000 + now.getNano() / 1000;
	}

	public static String dateNow() {
		long ts = Instant.now().toEpochMilli();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
		return format.format(ts);
	}

	public static void log(String pair, String what, String csvLine, String header) {
		String date = Util.dateNow();
		try {
			Path p = Paths.get(pair + "-" + what + "-" + date + ".csv");
			if (Files.notExists(p)) {
				System.out.println("logging to " + p);
				Files.writeString(p, header + "\n", CREATE);
			}
			Files.writeString(p, csvLine + "\n", APPEND);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
