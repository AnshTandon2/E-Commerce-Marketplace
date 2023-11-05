import java.io.*;
import java.util.ArrayList;
public class Messages {
    private String FILENAME;
    private int senderID;
    private int recipientID;
    private static String keyphrase;

    private ArrayList<Message> messages;

    public Messages(int senderID, int recipientID) {
        this.senderID = senderID;
        this.recipientID = recipientID;
        FILENAME = senderID + "-" + recipientID;
        messages = new ArrayList<>();
        try {
            File file = new File(FILENAME + ".txt");
            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            try {
                String line = bfr.readLine();
                while (line != null) {
                    Message msg = new Message(line);
                    messages.add(msg);
                    line = bfr.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    bfr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Messages(int senderID, int recipientID, ArrayList<Message> messages) {
        this.senderID = senderID;
        this.recipientID = recipientID;
        FILENAME = senderID + "-" + recipientID;
        for (Message msg : messages) {
            addMessage(msg);
        }
    }

    public void addMessage(Message message) {
        messages.add(message);
        try {
            File file = new File(FILENAME + ".txt");
            FileOutputStream fos = new FileOutputStream(file, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(message.writeOutMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteMessage(Message message) {
        try {
            File file = new File(FILENAME + ".txt");
            FileOutputStream fos = new FileOutputStream(file, false);
            PrintWriter pw = new PrintWriter(fos);
            pw.print("");
            messages.remove(message);
            for (Message msg : messages) {
                addMessage(msg);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteMessage(int messageID) {
        try {
            File file = new File(FILENAME + ".txt");
            FileOutputStream fos = new FileOutputStream(file, false);
            PrintWriter pw = new PrintWriter(fos);
            pw.print("");
            messages.removeIf(msg -> msg.getMessageID() == messageID);
            for (Message msg : messages) {
                addMessage(msg);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public Message getMessage(int messageID) {
        for(Message msg : messages) {
            if (msg.getMessageID() == messageID) return msg;
        }
        return null;
    }

    public String getFILENAME() {
        return FILENAME;
    }

    public void updateFileName() {
        FILENAME = senderID + "-" + recipientID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(int recipientID) {
        this.recipientID = recipientID;
    }

    public static String getKeyphrase() {
        return keyphrase;
    }

    public static void setKeyphrase(String keyphrase) {
        Messages.keyphrase = keyphrase;
    }
}