package controller;

import bean.Monitor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ClientRequireHandler implements Runnable {
    DataInputStream dis;//宣告一個讀取Client傳送過來的字串物件
    ArrayList<DataOutputStream> clientOutputStreams = new ArrayList<DataOutputStream>();
    Gson gson = new Gson();
    protected Socket clientSocket;

    public ClientRequireHandler(Socket clientSocket, ArrayList<DataOutputStream> clientOutputStreams) {
        this.clientOutputStreams = clientOutputStreams;
        this.clientSocket = clientSocket;
        try {
            dis = new DataInputStream(clientSocket.getInputStream());//宣告一個將server端資料寫出的變數
        } catch (Exception e) {
            System.out.println("ClientRequireHandler : "+e.toString());
        }
    }

    public void run() {
        try {
            while (true) {
                if(this.clientSocket.isClosed()) return;
                String msg = dis.readUTF();
                System.out.println(msg);
                Map<String,String> map = new HashMap<String,String>();
                Map<String,String> clientMsg = (Map<String,String>) gson.fromJson(msg, map.getClass());
                String response ="";
                if(clientMsg.get("action").equals("monitor")){
                    //{"action":"monitor"}
                    MonitorServlet monitorServlet = new MonitorServlet();
                    response =  monitorServlet.Monitoing();
                }else if(clientMsg.get("action").equals("create") || clientMsg.get("action").equals("delete")){
                    //未完成
                    //{"action":"create","info":{"hostIp":"140.124.1.1","hostName":"test"}}
                    //{"action":"delete","info":{"hostIp":"140.124.1.1"}}
                    EditHostServlet editHostServlet = new EditHostServlet();
                    System.out.println(clientMsg.get("info"));
                    //editHostServlet.Edit();
                }
                broadCast(response);
            }
        } catch (IOException e) {
            System.out.println("run : "+e.toString());
        }
    }

    public void broadCast(String message) {
        DataOutputStream writer = null;
        Iterator<DataOutputStream> it = clientOutputStreams.iterator();
        while (it.hasNext()) {
            try {
                writer = it.next();
                writer.writeUTF(message);//將資料寫出
                writer.flush();//清空資料串流。
            } catch (Exception e) {
                System.out.println("broadCast : "+e.toString());
                clientOutputStreams.remove(writer);
            }
        }
    }
}
