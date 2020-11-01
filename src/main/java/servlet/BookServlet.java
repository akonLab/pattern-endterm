package servlet;

import com.google.gson.Gson;
import controller.BookController;
import models.BookModel;
import models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "BookServlet")
public class BookServlet extends HttpServlet {
    private final BookController bookController = new BookController();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String copy = request.getParameter("copy");

        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("update")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                bookController.update(new BookModel(id, name, author, Integer.parseInt(copy)));

            } else if (action.equals("add")) {
                bookController.add(new BookModel(name, author, Integer.parseInt(copy)));

            }
        }

        request.setAttribute("all", bookController.getAll());

        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null) {
            if (action.equals("update")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("book", bookController.getItemById(id));
                request.getRequestDispatcher("/updateForm/updateBook.jsp").forward(request, response);

            } else if (action.equals("add")) {
                request.getRequestDispatcher("/addForm/addBook.jsp").forward(request, response);

            } else if (action.equals("remove")) {
                Integer id = Integer.parseInt(request.getParameter("id"));
                bookController.remove(id);

            } else if (action.equals("search")) {
                String book = request.getParameter("reader");
                ArrayList<BookModel> books = bookController.serch(book);

                response.setContentType("text/html;charset=UTF-8");
                String json = new Gson().toJson(books);

                response.getWriter().write(json);
                return;
            }
        }

        request.setAttribute("all", bookController.getAll());

        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }
}
