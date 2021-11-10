package storeInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        File download = null;
        for (File file : Objects.requireNonNull(new File("\\images\\").listFiles())) {
            String fileName = file.getName();
            fileName = fileName.substring(0, fileName.indexOf("."));
            if (id.equals(fileName)) {
                download = file;
                break;
            }
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + download.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(download)) {
             resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}
