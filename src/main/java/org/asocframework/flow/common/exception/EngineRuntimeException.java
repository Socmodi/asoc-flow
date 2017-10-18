package org.asocframework.flow.common.exception;

/**
 * @author jiqing
 * @version $Id: EngineRuntimeException，v 1.0 2017/9/27 15:46 jiqing Exp $
 * @desc
 */
public class EngineRuntimeException extends RuntimeException{

    public EngineRuntimeException() {
        super();
    }

    public EngineRuntimeException(String message) {
        super(message);
    }

    public EngineRuntimeException(Exception e) {
        super(e);
    }
}
