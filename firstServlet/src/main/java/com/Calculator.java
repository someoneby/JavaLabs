package com;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Calculator extends HttpServlet {

    private String x1,x2;
    private char operation;
    private double result;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.sendRedirect("http://localhost:8080/FirstServlet/Result.jsp");
        request.getRequestDispatcher("/efwf.html").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter print = response.getWriter();

        x1 = request.getParameter("x1");
        x2 = request.getParameter("x2");
        operation = request.getParameter("operation").charAt(0);

        try {
            switch (operation){
                case '+':{
                    result = Double.parseDouble(x1) + Double.parseDouble(x2);
                    break;
                }
                case '-':{
                    result = Double.parseDouble(x1) - Double.parseDouble(x2);
                    break;
                }
                case '*':{
                    result = Double.parseDouble(x1) * Double.parseDouble(x2);
                    break;
                }
                case '/':{
                    result = Double.parseDouble(x1) / Double.parseDouble(x2);
                    break;
                }
            }
            request.setAttribute("result", result);
        }finally {
            print.close();
        }
        request.getRequestDispatcher("/efwf.html").forward(request,response);

        doGet(request,response);
    }
}
