package com.example.finalproject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/otpManage")

public class OtpManage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        System.out.println("called do post");
        String otp= req.getParameter("otp");
        DatabaseManagement dbManage = new DatabaseManagement();
       boolean chk = dbManage.checkOtp(otp);
        String email =req.getSession().getAttribute("otpEmail").toString();
        System.out.println("your email:"+email);
        if (chk){
            System.out.println("verified your otp");
          dbManage.clearOtp("_",email);
            req.getRequestDispatcher("login.jsp").include(req,resp);
        }else {
            PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>" +"wrong otp"+ "</h1>");
        out.println("</body></html>");
            req.getRequestDispatcher("otp.jsp").include(req,resp);
        }
    }
}
