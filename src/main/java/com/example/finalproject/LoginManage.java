package com.example.finalproject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/loginManage")
public class LoginManage extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String name =req.getParameter("username");
        String password =req.getParameter("pass");
        System.out.println("name "+ name );
        DatabaseManagement dbManage = new DatabaseManagement();
       boolean c= dbManage.check(name,password);
        if(c){
            System.out.println("yes you can log in now");
            Cookie userNameCookie = new Cookie("username", name);
            Cookie passwordCookie = new Cookie("password",password);
            userNameCookie.setMaxAge(60*60*24);
            passwordCookie.setMaxAge(60*60*24);
            resp.addCookie( userNameCookie );
            resp.addCookie( passwordCookie );
            req.getRequestDispatcher("index.jsp").include(req,resp);

        }else {
            System.out.println("you are wrong");
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h1>" +"Wrong Information"+ "</h1>");
            out.println("</body></html>");
            req.getRequestDispatcher("login.jsp").include(req,resp);
        }
    }

}
