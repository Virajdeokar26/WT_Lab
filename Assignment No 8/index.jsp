<%@ page import="java.sql.*"%>
<html>  
<link rel="stylesheet" href="style.css">
<body>  
<h1>Welcome To E-Book Shop</h1>
<div>
    
</div>
<table border='5'>
<tr><th>Book Id</th><th>Book Name</th><th>Author</th><th>Price</th><th>Quantity</th></tr>
<%
try{ 
Class.forName("com.mysql.jdbc.Driver");
 
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/maharashtra","root","");

Statement stmt=con.createStatement();

ResultSet rs=stmt.executeQuery("select * from bookshop"); 
while(rs.next()){    
//writing html in the stream  
out.println("<tr><td>"+rs.getObject(1).toString()+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getInt(5)+"</td></tr>");
}
}catch(Exception e){ out.print(e);} 

%>

<div class="add-book-form">
    <form action="index.jsp" method="post">
        <div>
            Book Id: <input type="text" name="id"><br>
        </div>
        <div>
            Book Name: <input type="text" name="name"><br>
        </div>
        <div>
            Author: <input type="text" name="author"><br>
        </div>
        <div>
            Price: <input type="text" name="price"><br>
        </div>
        <div>
            Quantity: <input type="text" name="quantity"><br>
        </div>
        
        <input type="submit" value="Add Book">
    </form>
</div>

<br><br><br>

<%
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String author = request.getParameter("author");
    String price = request.getParameter("price");
    String quantity = request.getParameter("quantity");

    if (id != null && name != null && author != null && price != null && quantity != null) {
        try {
            int bid = Integer.parseInt(id);
            int bprice = Integer.parseInt(price);
            int bquantity = Integer.parseInt(quantity);

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/maharashtra", "root", "");
            PreparedStatement ps = con.prepareStatement("insert into bookshop values(?,?,?,?,?)");
            ps.setInt(1, bid);
            ps.setString(2, name);
            ps.setString(3, author);
            ps.setInt(4, bprice);
            ps.setInt(5, bquantity);
            int i = ps.executeUpdate();
            if (i > 0) {
                out.println("Record inserted successfully");
            } else {
                out.println("Failed to insert record");
            }
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
%>


</table>
</body>
</html>