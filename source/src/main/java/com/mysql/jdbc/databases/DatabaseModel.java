package com.mysql.jdbc.databases;

import lombok.Data;

@Data
public class DatabaseModel {
    private Boolean isSuccess;
    private Object result;
    private Object params;
}
