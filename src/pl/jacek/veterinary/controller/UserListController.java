package pl.jacek.veterinary.controller;

import pl.jacek.veterinary.model.User;
import pl.jacek.veterinary.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/allUsers")
public class UserListController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showUsers(request);
        if (request.getUserPrincipal() != null) {
            request.getRequestDispatcher("WEB-INF/allUsers.jsp").forward(request, response);
        } else {
            response.sendError(403);
        }
    }


    private void showUsers(HttpServletRequest request) {
        UserService userService = new UserService();
        List<User> allUsers =
                userService.getAllUsers();
        request.setAttribute("users", allUsers);
    }
}

