package org.asocframework.flow.store;

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
@MappedJdbcTypes(value = {JdbcType.BLOB})
public class ObjectTypeHandler extends BaseTypeHandler{

    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        preparedStatement.setObject(i,o);
    }

    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        /*byte[] bytes = resultSet.getBytes(s);
        Object result = convert(bytes);
        return result;*/
        return convert(resultSet.getBytes(s));
    }

    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        /*byte[] bytes = resultSet.getBytes(i);
        Object result = convert(bytes);*/
        return convert(resultSet.getBytes(i));
    }

    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
/*        byte[] bytes = callableStatement.getBytes(i);
        Object result = convert(bytes);*/
        return convert(callableStatement.getBytes(i));
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
}
