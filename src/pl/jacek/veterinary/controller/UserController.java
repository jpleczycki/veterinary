package pl.jacek.veterinary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        if (request.getUserPrincipal() != null) {
            request.getRequestDispatcher("WEB-INF/user.jsp").forward(request, response);
        } else {
            response.sendError(403);
        }
    }
}