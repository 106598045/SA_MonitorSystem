package Servlet;

import socket.ConnectServer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "/MonitorServlet",urlPatterns = {"/MonitorServlet"})
public class MonitorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        String msg = "{\"action\":\"monitor\"}";
        ConnectServer connectServer = new ConnectServer("127.0.0.1",8888);
        connectServer.sendMsgToServer(msg);
        String resultJSON = connectServer.getMsgByServer();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        try {
            response.getWriter().write(resultJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
