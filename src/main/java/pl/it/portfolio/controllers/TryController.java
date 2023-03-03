package pl.it.portfolio.controllers;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class TryController {
    @RequestMapping(path = "/tryh", method = RequestMethod.GET)
    public String sprobojg() {
        return "proba2x";
    }@RequestMapping(path = "/tryn", method = RequestMethod.GET)
    public String sprobojh() {
        return "probax";
    }

    @RequestMapping(path = "/try", method = RequestMethod.GET)
    public String sproboj() {
        return "pomiedzy";
    }

    @RequestMapping(path = "/try", method = RequestMethod.POST)
    public String sproboj2(HttpServletRequest request) {
       // System.out.println(request.getHeaderNames());
       // System.out.println("przyszło");
        try {
            Part file = request.getPart("image");
            String imageFileName = file.getSubmittedFileName();
            String uploadPath = "C:/Users/Michał/IdeaProjects/Wystaw.pl/src/main/resources/static/uploadedPhotos/" + imageFileName;
            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream is = file.getInputStream();
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
        return "nic";
    }
}