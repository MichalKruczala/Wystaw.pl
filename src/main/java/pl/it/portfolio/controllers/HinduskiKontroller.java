
package pl.it.portfolio.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.springframework.stereotype.Controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serial;
import java.sql.*;

//@MultipartConfig

//@WebServlet("/AddImage")

public class HinduskiKontroller extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;


    public HinduskiKontroller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("In do post method of Add Image servlet.");
        Part file = request.getPart("image");

        String imageFileName = file.getSubmittedFileName();  // get selected image file name
        System.out.println("Selected Image File Name : " + imageFileName);

        String uploadPath = "C:/Users/Michał/IdeaProjects/Wystaw.pl/src/main/resources/static/uploadedPhotos/" + imageFileName;  // upload path where we have to upload our actual image
        System.out.println("Upload Path : " + uploadPath);
        // Uploading our selected image into the images folder
        try {

            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream is = file.getInputStream();

            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
//        //**********************
//
//        //getting database connection (jdbc code)
//        Connection connection=null;
//        try
//        {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/imageTutorial","root","your password");
//            PreparedStatement stmt;
//            String query="insert into image(imageFileName) values(?)";
//            stmt=connection.prepareStatement(query);
//            stmt.setString(1,imageFileName);
//            int row=stmt.executeUpdate(); // it returns no of rows affected.
//
//            if(row>0)
//            {
//                System.out.println("Image added into database successfully.");
//            }
//
//            else
//            {
//                System.out.println("Failed to upload image.");
//            }
//
//
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//        }

    }
}

