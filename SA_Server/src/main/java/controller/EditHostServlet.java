package controller;

import Storage.HostRepository;
import bean.Host;

import java.io.IOException;

public class EditHostServlet {
    private HostRepository hostRepository = HostRepositoryBuilder.Build();
    protected String Edit(String action,String ip,String name) {
        if(action.equals("create")){
            Host host = new Host();
            host.setHostIp(ip);
            host.setHostName(name);
            host.setLastCheck("null");
            hostRepository.addHost(host);
        }else if(action.equals("delete")){
            hostRepository.deleteHost(ip);
        }
        String resultJSON = "{ \"result\" : true }";
        return resultJSON;
    }
}
