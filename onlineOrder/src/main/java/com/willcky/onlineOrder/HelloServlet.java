package com.willcky.onlineOrder;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import com.willcky.onlineOrder.entity.Customer;
import org.json.JSONObject;
import org.apache.commons.io.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

//servlet
//1.receive request
//2. parse URL --> certain servlet
//3. parse http method -> which method to call
//4. helloServlet.doGet()/ doPost()...
@WebServlet(name = "helloServlet", value = "/hello-servlet")//value是url --> http://localhost:8080/hello-servlet
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }
    //HttpServletRequest request这样写不用再new， tomcat帮你
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");//data format
//        String username = request.getParameter("username");
//        // Hello
        PrintWriter out = response.getWriter();//handler that can print data into response body
//        out.println("<html><body>");
//        out.println("<h1>" + username + "</h1>");
//        out.println("</body></html>");

        JSONObject obj = new JSONObject();
        obj.put("name", "chen");
        obj.put("email", "123456@qq.com");
        obj.put("age", 20);

        out.println(obj);

        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        Customer customer= new Customer();
        customer.setEmail("sun@laioffer.com");
        customer.setPassword("123456");
        customer.setFirstName("rick");
        customer.setLastName("sun");
        customer.setEnabled(true);

        response.getWriter().print(mapper.writeValueAsString(customer));

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonRequest = new JSONObject(IOUtils.toString(request.getReader()));
        String email = jsonRequest.getString("email");
        String firstName = jsonRequest.getString("first_name");
        String lastName = jsonRequest.getString("last_name");
        int age = jsonRequest.getInt("age");

        System.out.println("Email is: " + email);
        System.out.println("First name is: " + firstName);
        System.out.println("Last name is: " + lastName);
        System.out.println("Age is: " + age);

        //Return status = ok as response body to the client
        response.setContentType("application/json");
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", "ok");
        response.getWriter().print(jsonResponse);
    }
    public void destroy() {
    }
}