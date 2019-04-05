package softwire.training.myface.models.dbmodels;

public class Post {

    private int id;
    private String sender;
    private String recipient;
    private String content;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
