package storeInfo;

import storeInfo.store.OnlineDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        OnlineDB.instOf().removePost(Integer.parseInt(id));
        for (File file : new File("c\\images\\").listFiles()) {
            String name = file.getName();
            name = name.substring(0, name.indexOf("."));
            if (name.equals(id)) {
                file.delete();
                break;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/posts.do");
    }
}
