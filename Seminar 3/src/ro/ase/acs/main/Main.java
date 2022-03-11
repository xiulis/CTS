package ro.ase.acs.main;

import ro.ase.acs.database.Database;

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
        //Database database = new Mongo();
        database.createDatabase();
        database.createTable(nameTable);
        ((SQL) database).insertData(data, nameTable);
        ((SQL) database).readData(nameTable);

    }
}
