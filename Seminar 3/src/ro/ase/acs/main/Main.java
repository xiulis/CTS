package ro.ase.acs.main;

import ro.ase.acs.database.Database;
import ro.ase.acs.database.Table;


import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String nameTable ="employees";
        Map<String, String> data = new HashMap<>();
        data.put("name", "Popescu Ion");
        data.put("address", "Bucharest");
        data.put("salary", "4000");

        Database database = new SQL();
        database.createDatabase();

        Table table = (SQL)database;
        table.createTable(nameTable);
        table.insertData(data, nameTable);
        table.readData(nameTable);
    }
}
