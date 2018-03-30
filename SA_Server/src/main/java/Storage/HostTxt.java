package Storage;

import bean.Host;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HostTxt implements HostRepository {
    String path;
    String csvFile;

    public HostTxt(){
        this.path = ""+ HostTxt.class.getClassLoader().getResource("");
        this.csvFile = this.path.substring(6)+"hostList.txt";
    }

    public List<Host> getHost(){
        List<Host> list = new ArrayList<Host>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(this.csvFile));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                Host host = new Host();
                host.setHostName(row[0]);
                host.setHostIp(row[1]);
                host.setLastCheck(row[2]);
                list.add(host);
            }
            br.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addHost(Host host){
        if(existHost(host)) return;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(this.csvFile,true));
            StringBuilder sb = new StringBuilder();
            sb.append(host.getHostName()).append(',').append(host.getHostIp()).append(',').append(host.getLastCheck());
            sb.append('\n');
            out.write(sb.toString());
            out.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void deleteHost(String ip) {
        List<Host> list = getHost();
        clearHostList();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getHostIp().equals(ip)) continue;
            addHost(list.get(i));
        }
    }

    private void clearHostList() {
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(this.csvFile));
            out.flush();
            out.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private boolean existHost(Host host){
        boolean res = false;
        List<Host> list = getHost();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getHostIp().equals(host.getHostIp())) res = true;
        }
        return res;
    }
}
