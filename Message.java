import java.sql.Timestamp;
public class Message {
    private String message;
    private int messageID;
    private static int messageIDCounter = 0;
    private Timestamp timestamp;
    private int AssociatedStoreID;

    public Message(String message, int associatedStoreID) {
        this.message = message;
        messageID = messageIDCounter;
        messageIDCounter ++;
        this.timestamp = new Timestamp(java.lang.System.currentTimeMillis());
        AssociatedStoreID = associatedStoreID;
    }

    public Message(String fileLine) {
        String[] messageContents = fileLine.split("@@@");
        messageID = Integer.parseInt(messageContents[0]);
        this.message = messageContents[1];
        this.timestamp = new Timestamp(Long.parseLong(messageContents[2]));
        AssociatedStoreID = Integer.parseInt(messageContents[3]);;
    }

    public String writeOutMessage() {
        return messageID + "@@@" + message + "@@@" + timestamp.getTime() + "@@@" + AssociatedStoreID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public static int getMessageIDCounter() {
        return messageIDCounter;
    }

    public static void setMessageIDCounter(int messageIDCounter) {
        Message.messageIDCounter = messageIDCounter;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getAssociatedStoreID() {
        return AssociatedStoreID;
    }

    public void setAssociatedStoreID(int associatedStoreID) {
        AssociatedStoreID = associatedStoreID;
    }
}
