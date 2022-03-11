package ro.ase.acs.database;

import java.util.Map;

public interface Database {
    void createDatabase();
    void createTable(String nameTable);
}
