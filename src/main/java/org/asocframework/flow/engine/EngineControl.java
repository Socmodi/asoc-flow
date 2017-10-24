package org.asocframework.flow.engine;

import org.asocframework.flow.common.exception.EngineRuntimeException;
import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.store.AccidentStore;
import org.asocframework.flow.store.condition.QueryCondition;
import org.asocframework.flow.store.domain.AccidentMirror;

import java.util.List;

/**
 * @author jiqing
 * @version $Id: EngineControl，v 1.0 2017/10/24 09:52 jiqing Exp $
 * @desc
 */
public class EngineControl {

    private AccidentStore accidentStore;

    private FlowEngineApplication flowEngineApplication;

    public EngineControl(AccidentStore accidentStore, FlowEngineApplication flowEngineApplication) {
        this.accidentStore = accidentStore;
        this.flowEngineApplication = flowEngineApplication;
    }

    public EventContext recoverById(String id){
        EventContext context;
        AccidentMirror mirror = accidentStore.getAccidentDao().find(id);
        if (mirror!=null && mirror.getStatus()==0){
            context = flowEngineApplication.recover(mirror.getRecoverContext());

        }else {
            throw new EngineRuntimeException("记录不存在或状态不合法");
        }
        if(context!=null && context.isSuccess()){
            deleteAccidentMirror(id);
        }
        return context;
    }

    public List<AccidentMirror> queryAccidents(QueryCondition condition){
        return accidentStore.query(condition);
    }

    public int deleteAccidentMirror(String id){
        return accidentStore.delete(id);
    }

}
