package ro.ase.acs.main;

import ro.ase.acs.database.Database;
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import ro.ase.acs.database.Insertable;
import ro.ase.acs.database.Readable;

import java.util.Map;

public class Mongo implements Database, Insertable, Readable {
    public MongoDatabase mongoDb;

    @Override
    public void createDatabase() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoDb = mongoClient.getDatabase("test");
    }

    @Override
    public void createTable(String nameTable) {
        if(mongoDb.getCollection(nameTable) != null) {
            mongoDb.getCollection(nameTable).drop();
        }
        mongoDb.createCollection(nameTable);
    }

    @Override
    public void insertData(Map<String, String> values, String tableName) {
        Document row=new Document();
        for(Map.Entry el : values.entrySet()){
            row.append((String)el.getKey(),el.getValue());
        }
        MongoCollection<Document> collection = mongoDb.getCollection(tableName);
        collection.insertOne(row);
        System.out.println("Values inserted");
    }

    @Override
    public void readData(String tableName) {
        MongoCollection<Document> collection = mongoDb.getCollection(tableName);
        FindIterable<Document> result = collection.find();
        for(Document doc : result) {
            System.out.println(doc);
        }
    }
}
