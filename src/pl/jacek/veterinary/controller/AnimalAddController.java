package pl.jacek.veterinary.controller;


import pl.jacek.veterinary.model.User;
import pl.jacek.veterinary.service.AnimalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AnimalAddController extends HttpServlet {
    private static final long serialVersionUID = 1L;
//sprawdzenie czy użytkownik jest zalogowany
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getUserPrincipal() != null) {
            request.getRequestDispatcher("WEB-INF/new.jsp").forward(request, response);
        } else {
            response.sendError(403);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("inputName");
        String description = request.getParameter("inputDescription");
        String url = request.getParameter("inputUrl");
        User authenticatedUser = (User) request.getSession().getAttribute("user");
        if(request.getUserPrincipal() != null) {
            AnimalService animalService = new AnimalService();
            animalService.addAnimal(name, description, url, authenticatedUser);
            response.sendRedirect("home");
        } else {
            response.sendError(403);
        }
    }
}