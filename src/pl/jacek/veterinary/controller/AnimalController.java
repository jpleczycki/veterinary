package pl.jacek.veterinary.controller;

import pl.jacek.veterinary.dao.AnimalDAO;
import pl.jacek.veterinary.model.Animal;
import pl.jacek.veterinary.model.User;
import pl.jacek.veterinary.service.AnimalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/animal")
public class AnimalController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showAnimal(request);
        if (request.getUserPrincipal() != null) {
            request.getRequestDispatcher("WEB-INF/animal.jsp").forward(request, response);
        } else {
            response.sendError(403);
        }
    }

    private void showAnimal(HttpServletRequest request) {
    }
}



