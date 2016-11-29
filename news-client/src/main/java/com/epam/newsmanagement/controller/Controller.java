package com.epam.newsmanagement.controller;

import com.epam.newsmanagement.command.Command;
import com.epam.newsmanagement.controller.helper.CommandHelper;
import com.epam.newsmanagement.exception.CommandException;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Processes all incoming requests by redirecting them to the corresponding
 * command.
 */

@WebServlet("/news-client")
public class Controller extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Controller.class);
    private static final String COMMAND_NAME = "command";
    private final CommandHelper commandHelper = new CommandHelper();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
        super.service(arg0, arg1);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        Command command;
        String page;

        try {
            command = commandHelper.getCommand(request.getParameter(COMMAND_NAME));
            page = command.execute(request);
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);

            if (dispatcher != null) {
                dispatcher.forward(request, response);
            } else {
                page = command.execute(request);
                dispatcher = request.getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        } catch (CommandException | ServletException | IOException e) {
            logger.error(e.getMessage());
        }
    }

}
