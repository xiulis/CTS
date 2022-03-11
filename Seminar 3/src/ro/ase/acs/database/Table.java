package ro.ase.acs.database;

public interface Table extends Readable, Insertable{
    void createTable(String nameTable);
}
