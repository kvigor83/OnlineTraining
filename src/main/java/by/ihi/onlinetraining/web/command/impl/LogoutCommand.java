package by.ihi.onlinetraining.web.command.impl;

import by.ihi.onlinetraining.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String commandNext = null;
        req.getSession().invalidate();
        return null;
    }
}
