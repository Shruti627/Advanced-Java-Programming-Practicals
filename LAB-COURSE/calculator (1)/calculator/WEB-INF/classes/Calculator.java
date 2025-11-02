import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Calculator extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        try {
            // Get parameters from form
            String num1Str = request.getParameter("num1");
            String num2Str = request.getParameter("num2");
            String operation = request.getParameter("operation");
            
            // Convert strings to numbers
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;
            String operationSymbol = "";
            
            // Perform calculation based on operation
            switch(operation) {
                case "add":
                    result = num1 + num2;
                    operationSymbol = "+";
                    break;
                case "subtract":
                    result = num1 - num2;
                    operationSymbol = "-";
                    break;
                case "multiply":
                    result = num1 * num2;
                    operationSymbol = "×";
                    break;
                case "divide":
                    if(num2 != 0) {
                        result = num1 / num2;
                        operationSymbol = "÷";
                    } else {
                        throw new ArithmeticException("Division by zero!");
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operation");
            }
            
            // Generate HTML response
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Calculator Result</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; max-width: 400px; margin: 50px auto; padding: 20px; background-color: #f5f5f5; }");
            out.println(".result { background: white; padding: 30px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); text-align: center; }");
            out.println("h2 { color: #333; }");
            out.println(".calculation { font-size: 18px; margin: 20px 0; color: #666; }");
            out.println(".answer { font-size: 24px; color: #007bff; font-weight: bold; margin: 20px 0; }");
            out.println("a { display: inline-block; padding: 10px 20px; background-color: #007bff; color: white; text-decoration: none; border-radius: 5px; margin-top: 20px; }");
            out.println("a:hover { background-color: #0056b3; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='result'>");
            out.println("<h2>Calculator Result</h2>");
            out.println("<div class='calculation'>" + num1 + " " + operationSymbol + " " + num2 + " =</div>");
            out.println("<div class='answer'>" + result + "</div>");
            out.println("<a href='calculator.html'>Calculate Again</a>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
        } catch(NumberFormatException e) {
            out.println("<h2>Error: Please enter valid numbers!</h2>");
            out.println("<a href='calculator.html'>Go Back</a>");
        } catch(ArithmeticException e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
            out.println("<a href='calculator.html'>Go Back</a>");
        } catch(Exception e) {
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
            out.println("<a href='calculator.html'>Go Back</a>");
        }
    }
    
    // Handle GET requests by redirecting to the form
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("calculator.html");
    }
}