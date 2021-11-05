package likou.threadlocaldemo.Logger;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

import java.io.File;

//系统的跟踪和提示等信息日志的统一处理，在这里
public final class Logger {
	static {
		String userDir = System.getProperty("user.dir");
		String configFilename = userDir + File.separator + "log4j.xml";
		File file = new File(configFilename);
		if (file.exists() && file.isFile() && file.canRead()) {
			DOMConfigurator.configure(configFilename);
		}
	}

	private static final org.apache.log4j.Logger logger = LogManager.getLogger(Logger.class);

	public static void info(Object message) {
		logger.info(message);
	}

	public static void info(String message) {
		logger.info(message);
	}

	//防止字符串的组合
	public static void info(String message, Object... infos) {
		if (infos.length > 0) {
			//仅在日志需要打印的时候才启用
			if (Level.INFO.isGreaterOrEqual(logger.getEffectiveLevel())) {
				StringBuilder sb = new StringBuilder(message);
				for (Object o : infos) {
					sb.append(" ");
					sb.append(o);
				}

				logger.info(sb.toString());
			}
		} else {
			logger.info(message);
		}
	}

	public static void info(String message, Throwable t) {
		logger.info(message, t);
	}

	public static void debug(String message) {
		logger.debug(message);
	}

	public static void debug(String message, Throwable t) {
		logger.debug(message, t);
	}

	public static void debug(String message, Object... infos) {
		if (infos.length > 0) {
			//仅在日志需要打印的时候才启用
			if (Level.DEBUG.isGreaterOrEqual(logger.getEffectiveLevel())) {
				StringBuilder sb = new StringBuilder(message);
				for (Object o : infos) {
					sb.append(" ");
					sb.append(o);
				}

				logger.debug(sb.toString());
			}
		} else {
			logger.debug(message);
		}
	}

	public static void warn(String message) {
		logger.warn(message);
	}

	public static void warn(String message, Object... infos) {
		//仅在日志需要打印的时候才启用
		if (Level.WARN.isGreaterOrEqual(logger.getEffectiveLevel())) {
			StringBuilder sb = new StringBuilder(message);
			for (Object o : infos) {
				sb.append(" ");
				sb.append(o);
			}

			logger.warn(sb.toString());
		} else {
			logger.warn(message);
		}
	}

	public static void warn(String message, Throwable t) {
		logger.warn(message, t);
	}

	public static void warn(String message, Throwable t, Object... infos) {
		//仅在日志需要打印的时候才启用
		if (Level.WARN.isGreaterOrEqual(logger.getEffectiveLevel())) {
			StringBuilder sb = new StringBuilder(message);
			for (Object o : infos) {
				sb.append(" ");
				sb.append(o);
			}

			logger.warn(sb.toString(), t);
		} else {
			logger.warn(message, t);
		}
	}

	public static void error(String message) {
		logger.error(message);
		assert (false);
	}

	public static void error(String message, Object... infos) {
		//仅在日志需要打印的时候才启用
		if (Level.ERROR.isGreaterOrEqual(logger.getEffectiveLevel())) {
			StringBuilder sb = new StringBuilder(message);
			for (Object o : infos) {
				sb.append(" ");
				sb.append(o);
			}

			logger.error(sb.toString());
		} else {
			logger.error(message);
		}
	}

	public static void error(String message, Throwable t) {
		logger.error(message, t);
		assert (false);
	}

	public static void error(String message, Throwable t, Object... infos) {
		//仅在日志需要打印的时候才启用
		if (Level.ERROR.isGreaterOrEqual(logger.getEffectiveLevel())) {
			StringBuilder sb = new StringBuilder(message);
			for (Object o : infos) {
				sb.append(" ");
				sb.append(o);
			}

			logger.error(sb.toString(), t);
		} else {
			logger.error(message);
		}
	}

	public static void setLevel(Level level) {
		logger.setLevel(level);
	}

}
