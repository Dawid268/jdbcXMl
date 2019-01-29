package com.jdbc.jdbc.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Repository
public class Repository {
    String tableName = null;
    ResultSet dbProductName1 = null;
    List<String> dbnames;
    List<Map<String, Object>> mapList;
    String columName;
    String columType;
    int columsize = 0;
    DataSource dataSource;
    Connection connection;
    List<String> tableNameList;
    List<String> tableTypeList;
    @Autowired
    JdbcTemplate jdbctemplate;


    public List<Object> getUserNames(String name) {
        List<Object> usernames = new ArrayList<>();
        usernames.addAll(jdbctemplate.queryForList("SELECT first_name FROM MultipleTables." + name, String.class));
        return usernames;
    }

    public List<Object> getData(String name, String dane) {
        List<Object> pesel = new ArrayList<>();
        pesel.addAll(jdbctemplate.queryForList("SELECT " + dane + " FROM MultipleTables." + name, String.class));
        return pesel;
    }

    public List<Object> getSQL(String name, String sql) {
        System.out.println(sql);
        List<Object> pesel = new ArrayList<>();
        pesel.addAll(jdbctemplate.queryForList(sql + "." + name, String.class));
        return pesel;
    }

    public List<Map<String, Object>> retrieveMultipleRowsColumns(String name, String sql) {
        mapList =  new ArrayList<>();
        mapList.addAll(jdbctemplate.queryForList(sql + "." + name));
        return mapList;
    }

    public List<String> getConn() throws SQLException {
        dbnames =  new ArrayList<>();
        dataSource = jdbctemplate.getDataSource();
        connection = DataSourceUtils.getConnection(dataSource);

        ResultSet dbProductName = connection.getMetaData().getTables("MultipleTables", null, null, new String[]{"TABLE"});
        while (dbProductName.next()) {
            tableName = dbProductName.getString("TABLE_NAME");
            if (tableName.equals("hibernate_sequence")) {
            } else {
                dbnames.addAll(Collections.singleton(tableName));
            }
        }
        return dbnames;
    }

    public List<String> getTablesName(String name) throws SQLException {
        tableNameList = new ArrayList<>();
        tableTypeList = new ArrayList<>();
        dataSource = jdbctemplate.getDataSource();
        connection = DataSourceUtils.getConnection(dataSource);
        dbProductName1 = connection.getMetaData().getColumns("MultipleTables", null, name, null);
        System.out.println(dbProductName1);
        while (dbProductName1.next()) {
            columsize++;
            columName = dbProductName1.getString("COLUMN_NAME");
            columType = dbProductName1.getString("TYPE_NAME");
            tableNameList.addAll(Collections.singleton(columName + " type: " + columType));
            tableTypeList.addAll(Collections.singleton(columType));
            System.out.println("nazwa: " + columName + " " + "typ: " + columType);
        }
        System.out.println("Liczba kolumn: " + columsize);
        System.out.println("---------------------------------------------------------");
        columsize = 0;
        return tableNameList;
    }
}
