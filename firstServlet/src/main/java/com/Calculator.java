package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Calculator extends HttpServlet {

    private String x1,x2;
    private char operation;
    private double result;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        x1 = request.getParameter("x1");
        x2 = request.getParameter("x2");
        operation = request.getParameter("operation").charAt(0);

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
            request.getParameterNames().hasMoreElements();

        request.getRequestDispatcher("calculator.jsp").forward(request,response);
    }
}
