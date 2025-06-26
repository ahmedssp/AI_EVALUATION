package Serialization;
import java.util.List;

public class RequestBodyc {
    private String model;
    private List<MessageR> messages;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<MessageR> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageR> messages) {
        this.messages = messages;
    }

}