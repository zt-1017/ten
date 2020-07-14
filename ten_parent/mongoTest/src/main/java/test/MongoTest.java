package test;


import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @ClsaaName MongoTest
 * Version information 1.0
 * @Date 2020/7/11 18:39
 */
public class MongoTest {

    private MongoClient mongoClient;

    private MongoCollection<Document> comment;

    @Before
    public void init(){
        //1.新建MongoDB客户端
        mongoClient = new MongoClient("192.168.204.129");
        //2.选择数据库
        MongoDatabase commentdb = mongoClient.getDatabase("commentdb");
        //3.获取集合
        comment = commentdb.getCollection("comment");
    }

    @Test
    public void test1(){

        //4.得到查询结果
        FindIterable<Document> documents = comment.find();
        //5.解析结果
        for (Document document : documents) {
            System.out.println("id : " + document.get("_id"));
        }
    }

    @Test
    public void test2(){
        BasicDBObject bson = new BasicDBObject("_id","1");
        FindIterable<Document> documents = comment.find(bson);
        for (Document document : documents) {
            System.out.println("id : " + document.get("_id"));
        }
    }

    @After
    public void after(){
        //6.释放资源
        mongoClient.close();
    }


}
