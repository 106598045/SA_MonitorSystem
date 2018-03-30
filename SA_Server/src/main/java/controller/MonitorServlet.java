package controller;

import Storage.HostRepository;
import bean.Host;
import bean.Monitor;
import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class MonitorServlet {
    private HostRepository hostRepository = HostRepositoryBuilder.Build();
    private Gson gson = new Gson();
    Monitor m = new Monitor();
    public String Monitoing(){
        List<Host> list = hostRepository.getHost();
        for(int i=0;i<list.size();i++){
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(Calendar.getInstance().getTime());
            list.get(i).setLastCheck(timeStamp);
            if(m.ping(list.get(i).getHostIp())){
                list.get(i).setStatus("OK");
            }else{
                list.get(i).setStatus("ERROR");
            }
        }
        String resultJSON = "{ \"result\" : "+gson.toJson(list)+" }";
        return resultJSON;
    }
}
