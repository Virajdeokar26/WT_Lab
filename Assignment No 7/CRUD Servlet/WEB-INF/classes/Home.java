import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;

public class Home extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException , ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println(
            "<html>" +
            "<body>");
        out.println(
            "<h1 style=\"text-align:center\">Welcome to the Maharashtra Book Shop</h1>" +
            "<table border='5' align='center'>" +
            "<tr>" +
            "<th>Book ID</th>" +
            "<th>Book Title</th>" +
            "<th>Author</th>" +
            "<th>Price</th>" +
            "<th>Quantity</th>" +
            "</tr> +");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/maharashtra", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM bookshop");
            while (rs.next()) {
                int id = rs.getInt("book_id");
                String title = rs.getString("book_name");
                String author = rs.getString("author");
                Double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                out.println(
                    "<tr>" +
                    "<td>" + id + "</td>" +
                    "<td>" + title + "</td>" +
                    "<td>" + author + "</td>" +
                    "<td>" + price + "</td>" +
                    "<td>" + quantity + "</td>" +
                    "</tr>");
            }

        } catch (Exception e) {
            out.println(e);
        }

        out.println("</table>");
        out.println("<br><br>");
        
        out.println(
            "<div style='text-align: center;'>" +
            "<a href='add' style='margin: 10px; padding: 10px 20px; background-color: green; color: white; text-decoration: none; border-radius: 5px;'>Add</a>" +
            "<a href='update' style='margin: 10px; padding: 10px 20px; background-color: orange; color: white; text-decoration: none; border-radius: 5px;'>Update</a>" +
            "<a href='delete' style='margin: 10px; padding: 10px 20px; background-color: red; color: white; text-decoration: none; border-radius: 5px;'>Delete</a>" +
            "</div>"
        );

        out.println(
            "</body>" +
            "</html>");
        out.close();
        
    }
}