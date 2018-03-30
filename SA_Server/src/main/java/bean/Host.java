package bean;

public class Host {
    private String hostName;
    private String hostIp;
    private String status;
    private String lastCheck;

    public void setHostName(String name){
        this.hostName = name;
    }

    public String getHostName(){
        return this.hostName;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }

    public void setHostIp(String ip){
        this.hostIp = ip;
    }

    public String getHostIp(){
        return this.hostIp;
    }

    public void setLastCheck(String lastCheck){
        this.lastCheck = lastCheck;
    }

    public String getLastCheck(){
        return this.lastCheck;
    }

}
