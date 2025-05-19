import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class Add extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException , ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");

        out.println("<h2 style=\"text-align:center\">Add New Book</h2>");
        out.println("<form action='add' method='post'>");
        out.println("<table align='center' cellpadding='10' style='border: 1px solid #ccc; border-collapse: collapse;'>"); 
        out.println("<tr><td>Book ID:</td><td><input type='text' name='book_id' required></td></tr>"); 
        out.println("<tr><td>Book Title:</td><td><input type='text' name='title' required></td></tr>"); 
        out.println("<tr><td>Author:</td><td><input type='text' name='author' required></td></tr>"); 
        out.println("<tr><td>Price:</td><td><input type='text' name='price' required></td></tr>"); 
        out.println("<tr><td>Quantity:</td><td><input type='text' name='quantity' required></td></tr>"); 
        out.println("<tr><td colspan='2' style='text-align:center;'>"); 
        out.println("<input type='submit' value='Add Book' style='padding: 10px 20px; background-color: blue; color: white; border: none; border-radius: 5px;'>"); 
        out.println("</td></tr>"); 
        out.println("</table>");
        out.println("</form>");

        out.println("</body></html>");
        out.close();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");

        try {
            int bookId = Integer.parseInt(req.getParameter("book_id"));
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            Class.forName("com.mysql.jdbc.Driver");  // use "com.mysql.cj.jdbc.Driver" for MySQL 8+
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/maharashtra", "root", "");
            PreparedStatement pst = con.prepareStatement("INSERT INTO bookshop (book_id, book_name, author, price, quantity) VALUES (?, ?, ?, ?, ?)");

            pst.setInt(1, bookId);
            pst.setString(2, title);
            pst.setString(3, author);
            pst.setDouble(4, price);
            pst.setInt(5, quantity);

            int i = pst.executeUpdate();
            if (i > 0) {
                out.println("<h3 style='color:green; text-align:center;'>Book added successfully!</h3>");
            } else {
                out.println("<h3 style='color:red; text-align:center;'>Failed to add book.</h3>");
            }

            con.close();

        } catch (Exception e) {
            out.println("<h3 style='color:red; text-align:center;'>Error: " + e.getMessage() + "</h3>");
        }

        // Navigation Buttons
        out.println("<div style='text-align: center;'>");
        out.println("<a href='add' style='margin: 10px; padding: 10px 20px; background-color: green; color: white; text-decoration: none; border-radius: 5px;'>Add</a>");
        out.println("<a href='/update' style='margin: 10px; padding: 10px 20px; background-color: orange; color: white; text-decoration: none; border-radius: 5px;'>Update</a>");
        out.println("<a href='delete' style='margin: 10px; padding: 10px 20px; background-color: red; color: white; text-decoration: none; border-radius: 5px;'>Delete</a>");
        out.println("</div>");

        out.println("</body></html>");
        out.close();
    }
}
