package org.asocframework.flow.store;

import org.asocframework.flow.store.condition.QueryCondition;
import org.asocframework.flow.store.dal.AccidentDao;
import org.asocframework.flow.store.domain.AccidentMirror;

import java.util.List;

/**
 * @author jiqing
 * @version $Id: AccidentStore，v 1.0 2017/10/19 11:19 jiqing Exp $
 * @desc 目前设计成单一存储结构模式，mysql
 */
public class AccidentStore {

    /*优化点：桥接模式 ，可支持多种模式存储*/
    private AccidentDao accidentDao;

    public AccidentMirror find(String id){
        return accidentDao.find(id);
    }

    public List<AccidentMirror> query(QueryCondition queryCondition){
        return accidentDao.query(queryCondition);
    }

    public int delete(String id){
        return accidentDao.delete(id);
    }

    public AccidentDao getAccidentDao() {
        return accidentDao;
    }

    public void setAccidentDao(AccidentDao accidentDao) {
        this.accidentDao = accidentDao;
    }
}
