package com.zpf.demo.global.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({List.class})
public class StringListTypeHandler extends JsonTypeHandler<List<String>> {

    public StringListTypeHandler(Class<List<String>> type) {
        super(type);
        System.out.println("StringListTypeHandler--type==" + type);
    }

    @Override
    protected String toJsonString(Object parameter) {
        System.out.println("StringListTypeHandler--toJsonString==>");
        return super.toJsonString(parameter);
    }

    @Override
    protected List<String> parse(String json) {
        System.out.println("StringListTypeHandler--parse==>");
        System.out.println("json==" + json);
        System.out.println("type==" + type);
        List<String> result = null;
        try {
            result = objectMapper.readValue(json, type);
            System.out.println("result==" + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
