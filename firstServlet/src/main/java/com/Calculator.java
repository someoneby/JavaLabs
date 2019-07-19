package com;

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Calculator extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter print = response.getWriter();

        String x1 = request.getParameter("x1");
        String x2 = request.getParameter("x2");
        char operation = request.getParameter("operation").charAt(0);

        try {
            switch (operation){
                case '+':{
                    request.setAttribute("result",(Double.parseDouble(x1) + Double.parseDouble(x2)));
                    break;
                }
                case '-':{
                    request.setAttribute("result",Double.parseDouble(x1) - Double.parseDouble(x2));
                    break;
                }
                case '*':{
                    request.setAttribute("result",Double.parseDouble(x1) * Double.parseDouble(x2));
                    break;
                }
                case '/':{
                    request.setAttribute("result",Double.parseDouble(x1) / Double.parseDouble(x2));
                    break;
                }
            }
            getServletContext().getRequestDispatcher("/Result.jsp").forward(request,response);
        }finally {
            print.close();
        }
    }
}
