package servlet;

import controller.BookController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogServlet extends HttpServlet {

    private final BookController bookController = new BookController();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("all", bookController.getAll());

        getServletContext().getRequestDispatcher("/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("all", bookController.getAll());

        getServletContext().getRequestDispatcher("/admin.jsp").forward(req, resp);
    }
}