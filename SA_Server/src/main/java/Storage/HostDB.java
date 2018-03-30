package Storage;

import bean.Host;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class HostDB implements HostRepository {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> collection;

    public MongoCollection<Document> getDB(){
        mongoClient = new MongoClient("140.124.181.15");
        mongoDatabase = mongoClient.getDatabase( "MonitorSystem" );
        collection = mongoDatabase.getCollection("host");
        return collection;
    }
    public MongoCollection<Document> getMlab(){
        try{
            MongoClientURI uri  = new MongoClientURI("mongodb://Islab:islab1221@ds121189.mlab.com:21189/monitorsystem");
            mongoClient = new MongoClient(uri);
            mongoDatabase = mongoClient.getDatabase(uri.getDatabase());
            collection = mongoDatabase.getCollection("host");
            return collection;
        }
        catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }
    public void addHost(Host host){
        collection = getMlab();
        Document doc =new Document("hostName", host.getHostName())
                .append("hostIp", host.getHostIp());
        collection.insertOne(doc);
        mongoClient.close();
    }
    public List<Host> getHost(){
        List<Host> hostList = new ArrayList<Host>();
        collection = getMlab();

        FindIterable<Document> myDoc = collection.find();
        for (Document document : myDoc) {
            Host host = new Host();
            host.setHostName(document.get("hostName").toString());
            host.setHostIp(document.get("hostIp").toString());
            hostList.add(host);
        }
        mongoClient.close();
        return hostList;
    }

    public void deleteHost(String hostIp){
        collection = getMlab();
        collection.deleteOne(eq("hostIp", hostIp));
        mongoClient.close();
    }

    private String getDateTime(){
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = new Date();
        String strDate = sdFormat.format(date);
        return strDate;
    }
}
