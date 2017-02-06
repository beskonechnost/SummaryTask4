package ua.nure.korotkov.SummaryTask4.db;

import ua.nure.korotkov.SummaryTask4.db.entity.User;

/**
 * Created by Андрей on 07.01.2017.
 */
public enum Lock {
    L, LOCKED, UNLOCKED;

    public static Lock getLocked(User user) {
        int lockId = user.getLock();
        return Lock.values()[lockId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
