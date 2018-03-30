package Storage;


import bean.Host;
import java.util.List;

public interface HostRepository {
    public void addHost(Host host);
    public List<Host> getHost();
    public void deleteHost(String hostIp);
}
