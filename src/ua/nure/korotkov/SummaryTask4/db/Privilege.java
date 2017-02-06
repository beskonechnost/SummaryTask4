package ua.nure.korotkov.SummaryTask4.db;

import ua.nure.korotkov.SummaryTask4.db.entity.User;

/**
 * Created by Андрей on 07.01.2017.
 */
public enum Privilege {
    A, ADMIN, USER;

    public static Privilege getPrivilege(User user) {
        int privilegeId = user.getPrivilegeId();
        return Privilege.values()[privilegeId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
