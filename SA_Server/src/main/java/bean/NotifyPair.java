package bean;

public class NotifyPair {
    private Host host;
    private Contact contact;

    public NotifyPair(Host newHost, Contact newContact) {
        host = newHost;
        contact = newContact;
    }

    public Host getHost() {
        return host;
    }

    public Contact getContact() {
        return contact;
    }
}
