package org.asocframework.flow.store;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.asocframework.flow.event.RecoverContext;
import org.mybatis.spring.MyBatisSystemException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author jiqing
 * @version $Id: ObjectTypeHandler，v 1.0 2017/10/20 13:53 jiqing Exp $
 * @desc
 */
@MappedTypes(value = {RecoverContext.class})
@MappedJdbcTypes(value = {JdbcType.VARCHAR})
public class ObjectTypeHandler extends BaseTypeHandler{

    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        preparedStatement.setObject(i,o);
        //preparedStatement.setString(i, JSON.toJSONString(o));
    }

    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return convert(resultSet.getBytes(s));
        //return convert(resultSet.getString(s));
    }

    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return convert(resultSet.getBytes(i));
        //return convert(resultSet.getString(i));
    }

    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return convert(callableStatement.getBytes(i));
        //return convert(callableStatement.getString(i));
    }

    private Object convert(byte[] bytes){
        Object result = null;
        ObjectInputStream objectInputStream = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(inputStream);
            result = objectInputStream.readObject();
        } catch (Exception e) {
            throw new MyBatisSystemException(e);
        }finally {
            try {
                if(objectInputStream!=null){
                    objectInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private Object convert(String json){
        return JSON.parseObject(json,RecoverContext.class);
    }
}
