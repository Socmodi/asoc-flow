package org.asocframework.flow.plugin;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import org.asocframework.flow.common.component.LogComponent;
import org.slf4j.LoggerFactory;

/**
 * @author jiqing
 * @version $Id: LogPlugin，v 1.0 2017/10/18 14:25 jiqing Exp $
 * @desc
 */
public class LogPlugin extends AbstrctPlugin{

    public void init() {
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
        rootLogger.addAppender(appender);
        LogComponent.registerAccidentLogger(rootLogger);
    }


}
