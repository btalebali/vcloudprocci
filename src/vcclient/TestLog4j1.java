package vcclient;

// class to TEST debug library
// rich documentation could be founded in http://www.jmdoudoux.fr/java/dej/chap-logging.htm#logging-2
import org.apache.log4j.Logger;
 
public class TestLog4j1 
{ 
private static Logger logger = Logger.getLogger(TestLog4j1.class);
public static void main(String[] args) 
{
logger.debug("msg de debogage");
logger.info("msg d'information");
logger.warn("msg d'avertissement");
logger.error("msg d'erreur");
logger.fatal("msg d'erreur fatale"); 
logger.trace("msg trace");
}
}
