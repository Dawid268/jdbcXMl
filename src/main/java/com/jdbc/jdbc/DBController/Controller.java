package com.jdbc.jdbc.DBController;

import com.jdbc.jdbc.Repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin
public class Controller {

    @Autowired
    Repository repository;


    @GetMapping
    public String check(){
        return "Hello from my api";
    }


//    @GetMapping("/dane/{dbname}")
//    public List<Object> getUserNames(@PathVariable String dbname){
//            return repository.getUserNames(dbname);
//    }
    @GetMapping("/dane/{dbname}/{data}")
    public List<Object> getDatas(@PathVariable String dbname,@PathVariable String data){
            return repository.getData(dbname,data);
    }

    //Lista tabel w bazie
    @GetMapping("/info")
    public List<String> getDBNAMES() throws SQLException {
        return repository.getConn();
    }
    //Nazwy pól w tabeli
    @GetMapping("/table/{name}")
    public List<String> getTableName(@PathVariable String name) throws SQLException {
        return repository.getTablesName(name);
    }
    //Jedna wartość
    @GetMapping("/dane/sql/{name}/{sql}")
    public List<Map<String, Object>> getTableName(@PathVariable String name,@PathVariable String sql) throws SQLException, FileNotFoundException {
        return repository.getSQL(name,sql);
    }
    //pobiera kilka wartości
    @GetMapping("/dane/all/{name}/{sql}")
    public List<Map<String, Object>> retrieveMultipleRowsColumns(@PathVariable String name, @PathVariable String sql) throws SQLException, FileNotFoundException {
        return repository.retrieveMultipleRowsColumns(name,sql);
    }

}
