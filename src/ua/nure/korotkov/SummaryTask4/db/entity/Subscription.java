package ua.nure.korotkov.SummaryTask4.db.entity;

/**
 * Created by Андрей on 07.01.2017.
 */
public class Subscription extends Entity {

    private static final long serialVersionUID = 6439881708976928486L;

    private int userId;
    private int editionId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEditionId() {
        return editionId;
    }

    public void setEditionId(int editionId) {
        this.editionId = editionId;
    }
}
