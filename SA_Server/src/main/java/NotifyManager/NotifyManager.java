package NotifyManager;
import model.*;
import java.util.List;

public abstract class NotifyManager {
    protected List<NotifyPair> notifyPairList;

    public void Register(Host host, Contact contact) {
        notifyPairList.add(new NotifyPair(host, contact));
    }

    public void Unregister(Host host, Contact contact){

    }

    protected abstract void Notify(Host host);
}
