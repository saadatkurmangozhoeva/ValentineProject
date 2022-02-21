package kg.megacom.models;

public class Wish {

    private double id;
    private String text;
    private Subscriber sender;
    private Subscriber receipt;

    public Wish() { }

    public Wish(String text, Subscriber sender, Subscriber receipt) {
        this.id = Math.random();
        this.sender = sender;
        this.receipt = receipt;
        this.text = text;
    }

    public double getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Subscriber getSender() {
        return sender;
    }

    public void setSender(Subscriber sender) {
        this.sender = sender;
    }

    public Subscriber getReceipt() {
        return receipt;
    }

    public void setReceipt(Subscriber receipt) {
        this.receipt = receipt;
    }
}
