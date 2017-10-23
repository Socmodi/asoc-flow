package org.asocframework.flow.store.dal;

import org.asocframework.flow.store.condition.QueryCondition;
import org.asocframework.flow.store.domain.AccidentMirror;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import javax.sql.DataSource;
import java.util.List;

/**
 * @author jiqing
 * @version $Id: AccidentDao，v 1.0 2017/10/19 11:20 jiqing Exp $
 * @desc
 */
public class AccidentDao extends SqlSessionDaoSupport {

    private DataSource dataSource;


    public int insert(AccidentMirror accidentMirror){
        return getSqlSession().insert("asoc.flow.accident.insert",accidentMirror);
    }

    public int delete(String id){
        return getSqlSession().delete("asoc.flow.accident.delete",id);
    }

    public AccidentMirror find(String id){
        return getSqlSession().selectOne("asoc.flow.accident.find",id);
    }

    public List<AccidentMirror> query(QueryCondition condition){
        return getSqlSession().selectList("asoc.flow.accident.query",condition);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
