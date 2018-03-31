package Servlet;

import socket.ConnectServer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "EditHostServlet",urlPatterns = {"/EditHostServlet"})
public class EditHostServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String name = request.getParameter("name");
        String ip = request.getParameter("ip");
        String msg = "{\"action\":\""+action+"\",\"hostIp\":\""+ip+"\",\"hostName\":\""+name+"\"}";
        ConnectServer connectServer = new ConnectServer("127.0.0.1",8888);
        connectServer.sendMsgToServer(msg);
        String resultJSON = connectServer.getMsgByServer();
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(resultJSON);
    }
}
