package com.example.finalproject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet ("/RegistrationManage")
public class Registration extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* super.doPost(req, resp);*/
        resp.setContentType("text/html");
        String name = req.getParameter("username");
        String password = req.getParameter("pass");
        String email = req.getParameter("email");
        DatabaseManagement databaseManagement = new DatabaseManagement();
        boolean dbCheck = databaseManagement.registrationCheck(name,email);
        if(dbCheck){
            System.out.println("User already exist");
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<h1>" +"User Already exist"+ "</h1>");
            out.println("</body></html>");
            req.getRequestDispatcher("registration.jsp").include(req,resp);
        }else {
            System.out.println("Registration processing");
            boolean chk = databaseManagement.insertData(name, email, password);

            if (chk) {
                System.out.println("Register now");
                Random random = new Random();
                int rand = random.nextInt(9999);
                String otp = rand+"";
                String subject = "Java Otp verification";
                String message = "Your otp code is "+otp;
                new MyMailer(email, subject, message, new MyMailer.MailerListener() {
                    @Override
                    public void onSuccess(String response) {

                        new DatabaseManagement().saveOtp(otp,email);
                    }
                    @Override
                    public void onFailed(String response) {

                        System.out.println("failed to send otp");
                    }
                });
                req.getSession().setAttribute("otpEmail",email);
                req.getRequestDispatcher("otp.jsp").include(req,resp);

            } else {
                System.out.println("invalid name/email");
            }
        }

    }
}
