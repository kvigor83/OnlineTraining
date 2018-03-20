package by.kastsiuchenka.onlinetraining.web.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    String MAIN_PAGE ="/jsp/layout/default.jspx";
    //returns next command aftaer execute to do
    String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException;
}
