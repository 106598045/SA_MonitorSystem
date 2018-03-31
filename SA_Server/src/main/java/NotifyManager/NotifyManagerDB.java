package NotifyManager;

import model.Host;
import model.NotifyPair;

public class NotifyManagerDB extends NotifyManager {
    public void Notify(Host host) {
        for(NotifyPair pair : notifyPairList){
            if(pair.getHost() == host) {
                //將訊息存入DB
            }
        }
    }
}
