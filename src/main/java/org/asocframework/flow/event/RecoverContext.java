package org.asocframework.flow.event;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiqing
 * @version $Id: RecoverContext，v 1.0 2017/10/19 13:51 jiqing Exp $
 * @desc
 */
public class RecoverContext<P,R> implements Serializable{

    /**
     * 指令
     */
    private String event;

    /**
     * 恢复处理点
     */
    public String  recoverInvokers;


    private P param;

    private R result;

    /**
     * 调用结果
     */
    private boolean success = true;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 中断
     */
    private boolean breaked;

    /**
     * 透传数据
     */
    private Map<String, Object> attachments = new HashMap();

    /**
     * 重试次数
     */
    private int retryCount;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getRecoverInvokers() {
        return recoverInvokers;
    }

    public void setRecoverInvokers(String recoverInvokers) {
        this.recoverInvokers = recoverInvokers;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isBreaked() {
        return breaked;
    }

    public void setBreaked(boolean breaked) {
        this.breaked = breaked;
    }

    public Map<String, Object> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, Object> attachments) {
        this.attachments = attachments;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public P getParam() {
        return param;
    }

    public void setParam(P param) {
        this.param = param;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }
}
