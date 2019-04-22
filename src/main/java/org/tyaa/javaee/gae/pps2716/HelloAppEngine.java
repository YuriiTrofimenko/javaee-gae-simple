package org.tyaa.javaee.gae.pps2716;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.googlecode.objectify.ObjectifyService.ofy;

@WebServlet(name = "HelloAppEngine", urlPatterns = { "/user" })
public class HelloAppEngine extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");

		try(PrintWriter out = response.getWriter()) {
			
			if (request.getParameterMap().containsKey("action")) {
				switch (request.getParameter("action")) {
				case "create":
					String name = request.getParameter("name");
					String password = request.getParameter("password");
					User user = new User();
					user.name = name;
					user.password = password;
					ofy().save().entity(user).now();
					out.printf("User %s created", user.id);
					break;
				case "get-all":

					out.println("Users list:");
					List<User> users = ofy().load().type(User.class).list();
					if (users != null) {
						for (User currentUser : users) {
							out.print(currentUser);
						}
					}
					break;
				default:
					break;
				}
			} else {

				out.print("Hello App Engine!");
			}
		} catch (Exception ex) {
			try(PrintWriter out = response.getWriter()) {
				if (ex.getMessage() != null) {
					
					out.print(ex.getMessage());
				} else {
					out.print("Unknown server error");
				}
			}
		}

		
	}
}