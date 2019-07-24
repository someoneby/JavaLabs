package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Calculator extends HttpServlet {

    private char operation;
    private double result,x1,x2;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        try {
            x1 = Double.parseDouble(request.getParameter("x1"));
            x2 = Double.parseDouble(request.getParameter("x2"));
        }
        catch (NumberFormatException nfe){
            request.setAttribute("errorMessage", "Неправильный ввод\n");
            request.getRequestDispatcher("calculator.jsp").forward(request,response);
        }

        operation = request.getParameter("operation").charAt(0);

            switch (operation){
                case '+':{
                    result = x1 + x2;
                    break;
                }
                case '-':{
                    result = x1 - x2;
                    break;
                }
                case '*':{
                    result = x1 * x2;
                    break;
                }
                case '/':{
                    result = x1 / x2;
                    break;
                }
            }

            request.setAttribute("result", String.valueOf(result));
            request.getParameterNames().hasMoreElements();

        request.getRequestDispatcher("calculator.jsp").forward(request,response);
    }
}
