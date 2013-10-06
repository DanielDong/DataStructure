
import org.slf4j.*;

public class Main {

	private static class Item{
		public int val;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	private final static Item item = new Item();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String msg = "a o";
		System.out.println(msg.split("z", 2)[0]);
		item.val = 999;		
		logger.info("info-Hello world {}", 1);
		logger.debug("debug-Hello world");
		logger.error("error-Hello world");
		logger.trace("trace-Hello world");
		logger.warn("warn-Hello world");
		System.out.println(logger.isDebugEnabled());
		System.out.println(logger.isTraceEnabled());
		System.out.println(logger.isInfoEnabled());
		System.out.println(logger.isErrorEnabled());
		System.out.println(logger.isWarnEnabled());
		System.out.println(Runtime.getRuntime().availableProcessors());
		System.out.println("===========string substring===================");
		String str = "a";
		System.out.println(str.substring(1).length());
	}

}
