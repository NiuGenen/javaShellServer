package ng.servlet.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Restrictions;

import ng.jms.hbm.HDB;
import ng.jms.hbm.Table_user;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public UserLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		
		System.out.println("user " + username + "login. passwd: " + passwd);
		
		DataOutputStream output = new DataOutputStream(response.getOutputStream());
		
		List<Object> res = HDB.getInstance().get(Table_user.class, 
				Restrictions.eq("name", username),
				Restrictions.eq("passwd", passwd));
		if(res != null && res.size() >= 1){
			output.writeBytes("{'login':'1'}");
		}
		else{
			output.writeBytes("{'login':'0'}");
		}
		
		output.close();
		
		//doGet(request, response);
	}

}
