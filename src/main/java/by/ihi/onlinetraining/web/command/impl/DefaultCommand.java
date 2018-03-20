package by.ihi.onlinetraining.web.command.impl;

import by.ihi.onlinetraining.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
//        do nothing
return null;
    }
}
