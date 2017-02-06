package ua.nure.korotkov.SummaryTask4.db.entity;

/**
 * Created by Андрей on 21.01.2017.
 */
public class SubscriptionAll extends Entity {

    private static final long serialVersionUID = 6588281764176138487L;

    private String loginUser;
    private int editionId;
    private String editionName;
    private String editionTopic;
    private double editionPrice;
    private String editionPublisher;

    public int getEditionId() {
        return editionId;
    }

    public void setEditionId(int editionId) {
        this.editionId = editionId;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    public String getEditionName() {
        return editionName;
    }

    public void setEditionName(String editionName) {
        this.editionName = editionName;
    }

    public String getEditionTopic() {
        return editionTopic;
    }

    public void setEditionTopic(String editionTopic) {
        this.editionTopic = editionTopic;
    }

    public double getEditionPrice() {
        return editionPrice;
    }

    public void setEditionPrice(double editionPrice) {
        this.editionPrice = editionPrice;
    }

    public String getEditionPublisher() {
        return editionPublisher;
    }

    public void setEditionPublisher(String editionPublisher) {
        this.editionPublisher = editionPublisher;
    }
}
