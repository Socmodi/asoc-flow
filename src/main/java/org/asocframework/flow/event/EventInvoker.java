package org.asocframework.flow.event;


import org.asocframework.flow.common.exception.EngineRuntimeException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author jiqing
 * @version $Id: EventInvoker，v 1.0 2017/9/27 14:40 jiqing Exp $
 * @desc
 */
public class EventInvoker {

    private Object bean;

    private int order;

    private boolean useful;

    private boolean async;

    private String method;

    private Method invoke;

    private AtomicBoolean inited = new AtomicBoolean(false);

    public EventContext execute(EventContext context){
        if (!useful){
            return context;
        }
        return doExecute(context);
    }

    protected EventContext doExecute(EventContext context){
        if(!inited.get()){
            initInvoker();
        }
        try{
            return (EventContext) invoke.invoke(bean,context);
        } catch (InvocationTargetException e) {
            context.setSuccess(false);
            throw new EngineRuntimeException();
        }catch (Exception e) {
            context.setSuccess(false);
            throw new EngineRuntimeException();
        }
    }

    protected void initInvoker(){
        if(inited.compareAndSet(false,true)){
            try {
                invoke = bean.getClass().getMethod(method,new Class[]{EventContext.class});
            } catch (NoSuchMethodException e) {
                throw new EngineRuntimeException();
            }
        }
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isUseful() {
        return useful;
    }

    public void setUseful(boolean useful) {
        this.useful = useful;
    }

    public boolean isAsync() {
        return async;
    }

    public void setAsync(boolean async) {
        this.async = async;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Method getInvoke() {
        return invoke;
    }

    public void setInvoke(Method invoke) {
        this.invoke = invoke;
    }

    public AtomicBoolean getInited() {
        return inited;
    }

    public void setInited(AtomicBoolean inited) {
        this.inited = inited;
    }
}
