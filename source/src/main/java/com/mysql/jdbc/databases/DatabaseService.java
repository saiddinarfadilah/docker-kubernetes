package com.mysql.jdbc.databases;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public DatabaseModel execute(Map<String, Object> parameterValues) {
        String type = parameterValues.get("type").toString();
        String sql = parameterValues.get("query").toString();
        List<?> params = (List<?>) parameterValues.get("params");
        DatabaseModel model = new DatabaseModel();
        switch (type) {
            case "find" -> {
                try {
                    List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, params.toArray());
                    model.setIsSuccess(true);
                    model.setResult(maps);
                    model.setParams(parameterValues);
                } catch (Exception e) {
                    model.setIsSuccess(false);
                    model.setResult(e.getMessage());
                    model.setParams(parameterValues);
                }
                return model;
            }
            case "exec" -> {
                try {
                    jdbcTemplate.update(sql, params.toArray());
                    model.setIsSuccess(true);
                    model.setResult("1 row affected with " + sql + " " + parameterValues);
                    model.setParams(parameterValues);
                    return model;
                } catch (Exception e) {
                    model.setIsSuccess(false);
                    model.setResult(e.getMessage());
                    model.setParams(parameterValues);
                }
                return model;
            }
            default -> {
                model.setIsSuccess(false);
                model.setResult("type not found");
                model.setParams(parameterValues);
                return model;
            }
        }
    }
}
