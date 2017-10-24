package org.asocframework.flow.plugin;

import org.apache.http.impl.cookie.DateUtils;
import org.asocframework.flow.common.component.LogComponent;
import org.asocframework.flow.common.exception.EngineRuntimeException;
import org.asocframework.flow.event.EventContext;
import org.asocframework.flow.event.EventInvoker;
import org.asocframework.flow.event.RecoverContext;
import org.asocframework.flow.store.AccidentStore;
import org.asocframework.flow.store.dal.AccidentDao;
import org.asocframework.flow.store.domain.AccidentMirror;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.ClassPathResource;
import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

/**
 * @author jiqing
 * @version $Id: AccidentPlugin，v 1.0 2017/9/27 19:17 jiqing Exp $
 * @desc
 */
public class AccidentPlugin extends AbstrctPlugin{

    private boolean accidentMirror;

    private DataSource dataSource;

    private boolean persistence;

    private AccidentStore accidentStore;


    public void init() {
        persistence = accidentMirror&&dataSource!=null;
        if(persistence){
            initDbStore();
        }
    }

    private void  initDbStore(){
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            Thread.currentThread().setContextClassLoader(classLoader);

            ClassPathResource[] resources = new ClassPathResource[]{
                    new ClassPathResource("META-INF/mappers/accident_mapper.xml")
            };
            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
            sqlSessionFactoryBean.setDataSource(this.dataSource);
            sqlSessionFactoryBean.setMapperLocations(resources);
            sqlSessionFactoryBean.afterPropertiesSet();
            AccidentDao accidentDao = new AccidentDao();
            accidentDao.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
            accidentDao.setDataSource(this.dataSource);
            AccidentStore accidentStore = new AccidentStore();
            accidentStore.setAccidentDao(accidentDao);
            this.accidentStore = accidentStore;
        }catch (Exception e){
            throw  new EngineRuntimeException("dao init error",e);
        }finally {
            Thread.currentThread().setContextClassLoader(contextClassLoader);
        }
    }

    public boolean processAccident(EventContext context){
        AccidentMirror accidentMirror = createAccidentMirror(context);
        context.setAccidentMirror(accidentMirror);
        doProcessAccident(accidentMirror,this.persistence);
        return true;
    }

    public boolean processAccident(EventContext context,boolean persistence){
        AccidentMirror accidentMirror = createAccidentMirror(context);
        context.setAccidentMirror(accidentMirror);
        doProcessAccident(accidentMirror,persistence);
        return true;
    }

    private void doProcessAccident(AccidentMirror accidentMirror,boolean persistence){
        LogComponent.info(accidentMirror.toMirrorString());
        if(persistence&&this.persistence){
            accidentStore.getAccidentDao().insert(accidentMirror);
        }
    }

    private AccidentMirror createAccidentMirror(EventContext context){
        AccidentMirror accidentMirror = new AccidentMirror();
        accidentMirror.setId(createBizId());
        accidentMirror.setHappenedTime(new Date());
        accidentMirror.setEventName(context.getEvent());
        RecoverContext recoverContext = new RecoverContext();
        recoverContext.setEvent(context.getEvent());
        recoverContext.setErrorMsg(context.getErrorMsg());
        recoverContext.setSuccess(context.isSuccess());
        recoverContext.setRecoverInvokers(createRecoverInvokers(context.getInvokers()));
        recoverContext.setParam(context.getParam());
        recoverContext.setResult(context.getResult());
        accidentMirror.setInvokersInfo(recoverContext.getRecoverInvokers());
        accidentMirror.setRecoverContext(recoverContext);
        return accidentMirror;
    }

    private String createRecoverInvokers(List<EventInvoker> list){
        if(list==null||list.isEmpty()){
            return null;
        }
        StringBuilder builder = new StringBuilder();
        for(EventInvoker invoker:list){
            builder.append(invoker.getInvokerName());
            builder.append("|");
        }
        String invokersInfo = builder.toString();
        invokersInfo = invokersInfo.substring(0,invokersInfo.length()-1);
        return invokersInfo;
    }

    public boolean isAccidentMirror() {
        return accidentMirror;
    }

    public void setAccidentMirror(boolean accidentMirror) {
        this.accidentMirror = accidentMirror;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public AccidentStore getAccidentStore() {
        return accidentStore;
    }

    public void setAccidentStore(AccidentStore accidentStore) {
        this.accidentStore = accidentStore;
    }

    /**
     *
     * @return
     */
    private static String createBizId(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DateUtils.formatDate(new Date(), "yyMMddHHmmssSSSS"));
        Double random = (Math.random()*9+1)*10000000;
        stringBuilder.append(random.longValue());
        return stringBuilder.toString();
    }

    public boolean isPersistence() {
        return persistence;
    }

    public void setPersistence(boolean persistence) {
        this.persistence = persistence;
    }
}
