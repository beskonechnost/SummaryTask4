package ua.nure.korotkov.SummaryTask4.db.entity;

/**
 * Created by Андрей on 07.01.2017.
 */
public class Edition extends Entity{

    private static final long serialVersionUID = 5352087651905517102L;

    private String name;
    private String topic;
    private double price;
    private String publisher;

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

    @Override
    public String toString() {
        return "Edition{ " + super.toString()+
                ", name='" + name + '\'' +
                ", topic='" + topic + '\'' +
                ", price=" + price +
                ", publisher='" + publisher + '\'' +  "} ";
    }
}
