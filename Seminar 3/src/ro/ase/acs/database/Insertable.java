package ro.ase.acs.database;

import java.util.Map;

public interface Insertable {
    void insertData(Map<String, String> values, String tableName);
}
