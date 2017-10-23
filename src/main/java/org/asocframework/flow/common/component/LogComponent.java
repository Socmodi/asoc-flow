package org.asocframework.flow.common.component;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import org.asocframework.flow.common.classloader.LoaderTest;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author jiqing
 * @version $Id: LogComponent，v 1.0 2017/10/18 10:46 jiqing Exp $
 * @desc
 */
public class LogComponent {

    private static Logger accidentLogger;

    private static final AtomicBoolean initd = new AtomicBoolean(false);

    public static void registerAccidentLogger(Logger logger){
        if(logger==null){
            return;
        }
        if(initd.compareAndSet(false,true)){
            accidentLogger  = logger;
        }
    }

    public static Logger getAccidentLogger(){
        return accidentLogger;
    }

    public static void info(String message){
        accidentLogger.info(message);
    }

    public static Logger getLogger(Class<?> clazz) {

        Logger rootLogger = (Logger) LoggerFactory.getLogger("root");
        LoggerContext loggerContext = rootLogger.getLoggerContext();
        loggerContext.reset();
        rootLogger.setLevel(Level.INFO);
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS}  %-5level %logger{36}:%L - %message%n");
        encoder.start();

        RollingFileAppender appender = new RollingFileAppender();
        TimeBasedRollingPolicy policy = new TimeBasedRollingPolicy();
        policy.setFileNamePattern("/data/logs/asoc-flow/asoc-flow-%d{yyyy-MM-dd}.%i.log");
        policy.setContext(loggerContext);
        policy.setMaxHistory(30);
        SizeAndTimeBasedFNATP triggeringPolicy = new SizeAndTimeBasedFNATP();
        triggeringPolicy.setMaxFileSize("100MB");
        triggeringPolicy.setTimeBasedRollingPolicy(policy);
        policy.setTimeBasedFileNamingAndTriggeringPolicy(triggeringPolicy);
        policy.setParent(appender);
        policy.start();

        appender.setTriggeringPolicy(triggeringPolicy);
        appender.setRollingPolicy(policy);
        appender.setContext(loggerContext);
        appender.setEncoder(encoder);
        appender.start();



/*        FileAppender<ILoggingEvent> appender = new FileAppender<ILoggingEvent>();
        String fileName = getTime("yyyyMMdd") + ".log";
        String filePath = "/data/logs/"+clazz.getSimpleName() + "/" + fileName;
        appender.setFile(filePath);
        appender.setContext(loggerContext);
        appender.setEncoder(encoder);
        appender.start();*/
        rootLogger.addAppender(appender);
        return rootLogger;
    }

    private static String getTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    static public void main(String[] args) throws Exception {
        Logger rootLogger = (Logger) LoggerFactory.getLogger("root");
        LoggerContext loggerContext = rootLogger.getLoggerContext();
        loggerContext.reset();
        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(loggerContext);
        encoder.setPattern("%-5level [%thread]: %message%n");
        encoder.start();

        FileAppender<ILoggingEvent> appender = new FileAppender<ILoggingEvent>();
        String fileName = getTime("yyyyMMdd") + ".log";
        String filePath = "/data/logs/"+ LoaderTest.class.getSimpleName() + "/" + fileName;
        appender.setFile(filePath);
        appender.setContext(loggerContext);
        appender.setEncoder(encoder);
        appender.start();
        rootLogger.addAppender(appender);

        rootLogger.debug("Message 1");
        rootLogger.warn("Message 2");
    }

}
