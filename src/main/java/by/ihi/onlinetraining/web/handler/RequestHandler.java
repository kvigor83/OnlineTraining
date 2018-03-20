package by.kastsiuchenka.onlinetraining.web.handler;

import by.kastsiuchenka.onlinetraining.web.command.CommandType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import static by.kastsiuchenka.onlinetraining.web.command.CommandType.HOME;

//Command Factory (client)
public class RequestHandler {
    public static CommandType getCommand(HttpServletRequest req) {
        String param = req.getParameter("command");
        if (param == null && "".equals(param)) {
            param = "home";
        }
        CommandType type = CommandType.getByCommandName(param);
        HttpSession session = req.getSession();
        String pageName = (String) session.getAttribute("pageName");
        if (pageName != null) {
            session.setAttribute("prevPage", pageName);
            session.setAttribute("pageName", type.getPageName());
            session.setAttribute("pagePath", type.getPagePath());
        } else {
            session.setAttribute("prevPage", type.getPageName());
            session.setAttribute("pageName", HOME.getPageName());
            session.setAttribute("pagePath", HOME.getPagePath());
        }
        return type;
    }
}
