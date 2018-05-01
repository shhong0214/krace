package kr.co.krace.common;


import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;

public class ExceptionLogger {
	public static void logException(Logger logger, Throwable ex) {
		StringWriter sw = new StringWriter();
		ex.printStackTrace(new PrintWriter(sw));
		String stacktrace = sw.toString();
		logger.error(stacktrace);
	}
}
