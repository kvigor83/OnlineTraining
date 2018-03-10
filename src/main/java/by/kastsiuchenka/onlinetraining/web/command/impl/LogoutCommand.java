package by.kastsiuchenka.onlinetraining.web.command.impl;

import by.kastsiuchenka.onlinetraining.web.command.Command;

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
