package by.ihi.onlinetraining.web.handler;

import by.ihi.onlinetraining.web.command.CommandType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
            session.setAttribute("pageName", CommandType.HOME.getPageName());
            session.setAttribute("pagePath", CommandType.HOME.getPagePath());
        }
        return type;
    }
}
