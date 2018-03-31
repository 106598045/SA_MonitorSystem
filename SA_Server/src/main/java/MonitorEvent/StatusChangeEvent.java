package MonitorEvent;

import model.Host;

public class StatusChangeEvent extends MonitorEvent {
    private Host host;
    EventType eventType;

    public StatusChangeEvent(Host h, EventType type){
        host = h;
        eventType = type;
    }

    @Override
    public String toString() {
        return message;
    }

}
