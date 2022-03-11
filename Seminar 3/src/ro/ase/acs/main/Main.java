package ro.ase.acs.main;

import ro.ase.acs.database.Database;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, String> data = new HashMap<>();
        data.put("name", "Popescu Ion");
        data.put("address", "Bucharest");
        data.put("salary", "4000");

        Database database = new SQL();
        database.createDatabase();
        database.createTable("employees");
        database.insertData(data, "employees");
        database.readData("employees");

    }
}
