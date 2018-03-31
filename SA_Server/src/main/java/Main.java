import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import controller.ClientRequireHandler;

public class Main {
    public static void main(String[] args) {
        ArrayList<DataOutputStream> clientOutputStreams = new ArrayList<DataOutputStream>();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            int port = 8888;
            ServerSocket serverSocket = new ServerSocket(port);//開始監聽port連線請求。
            ThreadGroup threadGroup = new ThreadGroup("clientRequirement");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                if(clientSocket.isConnected()) {
                    try {
                        DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                        clientOutputStreams.add(dos);
                        Thread thread = new Thread(threadGroup,new ClientRequireHandler(clientSocket , clientOutputStreams));
                        thread.start();
                        System.out.println("目前共:"+threadGroup.activeCount()+"個請求");
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}