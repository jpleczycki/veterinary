package pl.jacek.veterinary.controller;


import pl.jacek.veterinary.dao.AnimalDAO;
import pl.jacek.veterinary.dao.DAOFactory;
import pl.jacek.veterinary.model.Animal;
import pl.jacek.veterinary.service.AnimalService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/home")
public class HomeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showAnimals(request);
        if (request.getUserPrincipal() != null) {
            request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
        } else {
            response.sendError(403);
        }
    }

    private void showAnimals(HttpServletRequest request) {
        AnimalService animalService = new AnimalService();
        List<Animal> allAnimals =
                animalService.getAllAnimals();
        request.setAttribute("animals", allAnimals);
    }
}