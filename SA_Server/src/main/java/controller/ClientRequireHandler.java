package controller;

import bean.Monitor;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
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
    DataInputStream dis;
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

    //EOFException 段開連線問題
    public void run() {
        try {
            while (true) {
                if(this.clientSocket.isClosed()) return;
                String msg = dis.readUTF();
                Map<String,String> map = new HashMap<String,String>();
                Map<String,String> clientMsg = (Map<String,String>) gson.fromJson(msg, map.getClass());
                String response ="";
                String action = clientMsg.get("action");
                if(action.equals("monitor")){
                    MonitorServlet monitorServlet = new MonitorServlet();
                    response =  monitorServlet.Monitoing();
                }else if(action.equals("create") ||action.equals("delete")){
                    EditHostServlet editHostServlet = new EditHostServlet();
                    response = editHostServlet.Edit(action,clientMsg.get("hostIp"),clientMsg.get("hostName"));
                }
                sendMsgToClient(response);
            }
        } catch (IOException e) {
            System.out.println("run : "+e.toString());
        }
    }

    public void sendMsgToClient(String message) {
        DataOutputStream writer = null;
        try {
            writer = new DataOutputStream(clientSocket.getOutputStream());
            writer.writeUTF(message);
            writer.flush();
        } catch (Exception e) {
            System.out.println("broadCast : "+e.toString());
            clientOutputStreams.remove(writer);
        }
    }
}
