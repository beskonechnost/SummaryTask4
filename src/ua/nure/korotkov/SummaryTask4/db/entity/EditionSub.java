package ua.nure.korotkov.SummaryTask4.db.entity;

/**
 * Created by Андрей on 21.01.2017.
 */
public class EditionSub extends Entity {
    private static final long serialVersionUID = 5352087651905517102L;

    private String name;
    private String topic;
    private double price;
    private String publisher;
    private boolean sub = false;

    public EditionSub() {
    }

    public EditionSub(Edition edition) {
        this.setId(edition.getId());
        this.name = edition.getName();
        this.topic = edition.getTopic();
        this.price = edition.getPrice();
        this.publisher = edition.getPublisher();
        this.sub = false;
    }

    public EditionSub(Edition edition, boolean status) {
        this.setId(edition.getId());
        this.name = edition.getName();
        this.topic = edition.getTopic();
        this.price = edition.getPrice();
        this.publisher = edition.getPublisher();
        this.sub = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isSub() {
        return sub;
    }

    public void setSub(boolean sub) {
        this.sub = sub;
    }

    @Override
    public String toString() {
        return "Edition{ " + super.toString()+
                ", name='" + name + '\'' +
                ", topic='" + topic + '\'' +
                ", price=" + price +
                ", publisher='" + publisher + '\'' +
                ", sub='" + sub + '\'' +
                "} ";
    }
}
