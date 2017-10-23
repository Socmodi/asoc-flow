package org.asocframework.flow.store;

import org.asocframework.flow.store.dal.AccidentDao;

/**
 * @author jiqing
 * @version $Id: AccidentStore，v 1.0 2017/10/19 11:19 jiqing Exp $
 * @desc
 */
public class AccidentStore {

    private AccidentDao accidentDao;

    public AccidentDao getAccidentDao() {
        return accidentDao;
    }

    public void setAccidentDao(AccidentDao accidentDao) {
        this.accidentDao = accidentDao;
    }
}
