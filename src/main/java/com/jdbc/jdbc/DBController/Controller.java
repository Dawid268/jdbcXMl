package com.jdbc.jdbc.DBController;

import com.jdbc.jdbc.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class Controller {

    @Autowired
    Repository repository;


    @GetMapping
    public String check(){
        return "Hello from my api";
    }


    @GetMapping("/dane/{dbname}")
    public List<Object> getUserNames(@PathVariable String dbname){
            return repository.getUserNames(dbname);
    }
    @GetMapping("/dane/{dbname}/{data}")
    public List<Object> getDatas(@PathVariable String dbname,@PathVariable String data){
            return repository.getData(dbname,data);
    }

    @GetMapping("/info")
    public List<String> getDBNAMES() throws SQLException {
        return repository.getConn();
    }
    @GetMapping("/table/{name}")
    public List<String> getTableName(@PathVariable String name) throws SQLException {
        return repository.getTablesName(name);
    }
    @GetMapping("/dane/sql/{name}/{sql}")
    public List<Object> getTableName(@PathVariable String name,@PathVariable String sql) throws SQLException {
        return repository.getSQL(name,sql);
    }
    @GetMapping("/dane/all/{name}/{sql}")
    public List<Map<String, Object>> retrieveMultipleRowsColumns(@PathVariable String name, @PathVariable String sql) throws SQLException {
        return repository.retrieveMultipleRowsColumns(name,sql);
    }

}
