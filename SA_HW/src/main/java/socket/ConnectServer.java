package socket;

import java.io.*;
import java.net.Socket;

/**
 * Created by gunch on 2018/3/31.
 */
public class ConnectServer {
    Socket con;
    String responseMsg;
    public ConnectServer(String ip, int port){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            con = new Socket(ip, port);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void sendMsgToServer(String msg) {
        try {
            if (con.isConnected()) {
                DataOutputStream dos = new DataOutputStream(con.getOutputStream());//建立DataOutputStream將資料寫至Server
                dos.writeUTF(msg);
                DataInputStream dis = new DataInputStream(con.getInputStream());
                while (responseMsg == null ){
                    responseMsg = dis.readUTF();
                    System.out.println("wait... :"+responseMsg);
                }
                con.close();
            } else {
                System.out.println("Connect fails");
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public String getMsgByServer(){
        return responseMsg;
    }
}
