package com.zpf.demo.global.handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;
import java.util.Set;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes({List.class})
public class StringSetTypeHandler extends JsonTypeHandler<Set<String>> {

    public StringSetTypeHandler(Class<Set<String>> type) {
        super(type);
    }

    @Override
    protected Set<String> parse(String json) {
        try {
            return objectMapper.readValue(json, type);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
