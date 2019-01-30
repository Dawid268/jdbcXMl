package com.jdbc.jdbc.Repository;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
    List<Map<String, Object>> pesel;
    XStream xstream = new XStream(new StaxDriver());
    PrintWriter writer;
//    MultipleTables
//    dieta
    String dbName ="MultipleTables";
    @Autowired
    JdbcTemplate jdbctemplate;


    public List<Object> getUserNames(String name, String sql) throws FileNotFoundException {
        List<Object> usernames = new ArrayList<>();
        usernames.addAll(jdbctemplate.queryForList(sql + "." + name,Object.class));
        writer = new PrintWriter(name+".xml");
        String dataXml = xstream.toXML(usernames);
        writer.write(dataXml);
        writer.close();
        return usernames;
    }

    public List<Object> getData(String name, String sql) {
        List<Object> pesel = new ArrayList<>();
        pesel.addAll(jdbctemplate.queryForList(sql + "." + name,Object.class));
        return pesel;
    }

    public List<Map<String, Object>> getSQL(String name, String sql) throws FileNotFoundException {
        System.out.println(sql);
        pesel = new ArrayList<>();
        pesel.addAll(jdbctemplate.queryForList(sql + "." + name));
        getUserNames(name,sql);
        return pesel;
    }
    //pobiera kilka wartości
    public List<Map<String, Object>> retrieveMultipleRowsColumns(String name, String sql) throws FileNotFoundException {
        mapList =  new ArrayList<>();
        mapList.addAll(jdbctemplate.queryForList(sql + "." + name));
        for (Object s:mapList) {
            System.out.println(s);
        }
        List<Map<String, Object>> mapListTest = new ArrayList<>();
        mapListTest =  mapList;
        writer = new PrintWriter(name+".xml");
        String dataXml = xstream.toXML(mapListTest);
        writer.write(dataXml);
        writer.close();
        return mapList;
    }

    public List<String> getConn() throws SQLException {
        dbnames =  new ArrayList<>();
        dataSource = jdbctemplate.getDataSource();
        connection = DataSourceUtils.getConnection(dataSource);

        ResultSet dbProductName = connection.getMetaData().getTables(dbName, null, null, new String[]{"TABLE"});
        while (dbProductName.next()) {
            tableName = dbProductName.getString("TABLE_NAME");
            dbnames.addAll(Collections.singleton(tableName));

        }
        return dbnames;
    }

    public List<String> getTablesName(String name) throws SQLException {
        tableNameList = new ArrayList<>();
        tableTypeList = new ArrayList<>();
        dataSource = jdbctemplate.getDataSource();
        connection = DataSourceUtils.getConnection(dataSource);
        dbProductName1 = connection.getMetaData().getColumns(dbName, null, name, null);
        System.out.println(dbProductName1);
        while (dbProductName1.next()) {
            columsize++;
            columName = dbProductName1.getString("COLUMN_NAME");
            columType = dbProductName1.getString("TYPE_NAME");
            tableNameList.addAll(Collections.singleton(columName));
            tableTypeList.addAll(Collections.singleton(columType));
            System.out.println("nazwa: " + columName + " " + "typ: " + columType);
        }
        System.out.println("Liczba kolumn: " + columsize);
        System.out.println("---------------------------------------------------------");
        columsize = 0;
        return tableNameList;
    }
}
