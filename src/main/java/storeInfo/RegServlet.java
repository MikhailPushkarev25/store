package storeInfo;

import storeInfo.model.User;
import storeInfo.store.OnlineDB;
import storeInfo.store.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = OnlineDB.instOf().findByEmail(email);
        if (user != null) {
            req.setAttribute("error", "Не верный пароль или e-mail");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            HttpSession sc = req.getSession();
            User admin = new User();
            admin.setName(name);
            admin.setUsername(surname);
            admin.setEmail(email);
            admin.setPassword(password);
            OnlineDB.instOf().saveUser(admin);
            sc.setAttribute("user", admin);
            resp.sendRedirect(req.getContextPath() + "/posts.do");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }
}
