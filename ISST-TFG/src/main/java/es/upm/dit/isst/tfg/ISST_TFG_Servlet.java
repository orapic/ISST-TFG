package es.upm.dit.isst.tfg;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import es.upm.dit.isst.model.TFG;
import es.upm.dit.isst.tfg.dao.TFGDAO;
import es.upm.dit.isst.tfg.dao.TFGDAOImpl;

public class ISST_TFG_Servlet extends HttpServlet{
	
	
	
	@Override
	public void init() throws ServletException {
	    ObjectifyService.register(TFG.class);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = UserServiceFactory.getUserService();
		String url = userService.createLoginURL(request.getRequestURI());
		String urlLinktext = "Login";
		String user = "";
		
		TFGDAO dao = TFGDAOImpl.getInstancia();
		TFG tfg = null;
		ArrayList<TFG> tfgs = new ArrayList<>();
		
		
		if (request.getUserPrincipal() != null){
			user = request.getUserPrincipal().getName();
			url = userService.createLogoutURL(request.getRequestURI());
			urlLinktext = "Logout";
			tfg = dao.leerPorAutor(user);
			tfgs.addAll(dao.leerPorSecretario(user));
			tfgs.addAll(dao.leerPorTutor(user));
		}

		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("url", url);
		request.getSession().setAttribute("urlLinktext", urlLinktext);
		request.getSession().setAttribute("tfg", tfg);
		request.getSession().setAttribute("tfgs", tfgs);
		
		RequestDispatcher view = request.getRequestDispatcher("TFGVista.jsp");
		view.forward(request, response);
		
		
	}
	
	
}
