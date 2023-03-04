/**
 * This class validates if user credentials are correct and logs them in
 * default login - username: admin, password - admin
 * @author Promise Osunkwo 22504972
 * @version 1.0
 */ 

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

public class ProcessLoginUserHandler implements HttpHandler {
  public void handle(HttpExchange he) throws IOException {


    he.sendResponseHeaders(200, 0);
    BufferedWriter out = new BufferedWriter(
        new OutputStreamWriter(he.getResponseBody()));

    // Get param from URL
Map<String, String> parms = Util.requestStringToMap(he.getRequestURI().getQuery());


    String username = parms.get("username");
    String password = parms.get("password");

      Boolean isUser = UserDAO.loginUser(username,password); //  checks and compares in database


    //  displays error message logs in
     if(!isUser){
      out.write(
         "<script>alert('Username or Password is incorrect!'); </script>"+
         "<script>history.go(-1); </script>");
    }else{
       out.write(
       "<script>alert('Login Successful!'); </script>"+
       "<script>window.location.replace(\"https://everythingstore-bp.promiseosunkwo.repl.co\");</script>");
       
    }

    out.close();

  }
}
