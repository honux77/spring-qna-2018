package net.honux.qna.web;

import javax.servlet.http.HttpSession;

public class HttpSessionUtils {
    public static final String SESSION_USER_KEY = "session-user";

    public static User getSessionUser(HttpSession session) {
        return (User) session.getAttribute(SESSION_USER_KEY);
    }

    public static boolean isUserLogin(HttpSession session) {
        return getSessionUser(session) != null;
    }

    public static boolean isSessionAdmin(HttpSession session) {
        return isUserLogin(session) && getSessionUser(session).isAdmin();
    }
}
