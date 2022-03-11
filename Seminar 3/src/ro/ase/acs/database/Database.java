package ro.ase.acs.database;

import java.util.Map;

public interface Database {
    void createDatabase();
    void createTable(String nameTable);
    void insertData(Map<String, String> values, String tableName);
    void readData(String tableName);
}
