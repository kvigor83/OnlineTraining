package by.kastsiuchenka.onlinetraining.web.controller;

import by.kastsiuchenka.onlinetraining.web.command.Command;
import by.kastsiuchenka.onlinetraining.web.command.CommandType;
import by.kastsiuchenka.onlinetraining.web.handler.RequestHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/frontController")
public class FrontController extends HttpServlet {
    private static final String MAIN_PAGE = "/jsp/common/default.jspx";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommandType commandType = RequestHandler.getCommand(req);
        Command command = commandType.getCommand();
        String page = command.execute(req, resp);
        if (page != null) {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath+ "/frontController?command="+ page);
        }else{
            getServletContext().getRequestDispatcher(MAIN_PAGE).forward(req, resp);
        }
    }

}
