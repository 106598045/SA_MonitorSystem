package bean;

import java.net.InetAddress;

public class Monitor {
    public boolean ping(String ip){
        boolean reachable = false;
        try {
            InetAddress address = InetAddress.getByName(ip);
            reachable = address.isReachable(100);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return reachable;
    }
}
