/**
 * This class updates a user and can only be done by admin
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */  

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProcessEditUserHandler implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {


    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // Get param from URL
    Map<String, String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());
    
    System.out.println("about to get data");

    String username = parms.get("username");
    String email = parms.get("email");
    String password = parms.get("password1");
    String confirmPassword = parms.get("password2");
    int role = Integer.parseInt(parms.get("isadmin"));
    int id = Integer.parseInt(parms.get("id"));

    if(!password.equals(confirmPassword)){
      out.write(
        "<script>alert('passwords do not match!'); </script>"+
       "<script>history.go(-1); </script>");
      out.close();
    }

    System.out.println("about to update user"); // Debugging message
    User newUser = new User(id, username, email, password, role);
    UserDAO.updateUser(newUser);

    

    out.write(
        "<html>" +
            "<head> <title>Success Page</title> " +
            "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">"
            +
            "</head>" +
            "<body>" +
            "<div class=\"container\">" +
            "<div class=\"alert alert-success\" role=\"alert\">User Updated Successfully</div>" +
            "<table class=\"table table-hover table-striped mt-2\">" +
            "<thead>" +
            "  <tr>" +
            // " <th>ID</th>" +
            "    <th>Username</th>" +
            "    <th>Email</th>" +
            "    <th>Password</th>" +
            "    <th>Role</th>" +

            "  </tr>" +
            "</thead>" +
            "<tbody>");

    out.write(
        "  <tr>" +
            "    <td>" + newUser.getUsername() + "</td>" +
            "    <td>" + newUser.getEmail() + "</td>" +
            "    <td>" + UserDAO.hashPassword(newUser.getPassword()) + "</td>");
      
                 if(newUser.getIsAdmin() == 1){
                    
                  out.write(
                    "    <td>Admin</td>");
                  }else{
                   out.write(
                    "    <td>User</td>");
                  }
            

    out.write(
        "</tr>"+
        "</tbody>" +
            "</table>" +
            "<a href=\"/\" class=\"btn btn-outline-dark mr-3\">Back to List </a>" +
            "<a href=\"/users\" class=\"btn btn-outline-secondary\">All Users </a>" +
            "</div>" +
            "</body>" +
            "</html>");
    out.close();

  }
}
