package storeInfo;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> images = new ArrayList<>();
        for (File file: new File("\\images\\").listFiles()) {
            images.add(file.getName());
        }
        req.setAttribute("images", images);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/upload.jsp");
        req.setAttribute("user", req.getSession().getAttribute("user"));
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext context = this.getServletConfig().getServletContext();
        File repository = (File) context.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
             List<FileItem> items = upload.parseRequest(req);
             File folder = new File("c:\\images\\");
             if (!folder.exists()) {
                 folder.mkdir();
             }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    String name = item.getName();
                    name = id + name.substring(name.indexOf("."));
                   File file = new File(folder + File.separator + name);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                         out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
               e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/posts.do");
     }
}
