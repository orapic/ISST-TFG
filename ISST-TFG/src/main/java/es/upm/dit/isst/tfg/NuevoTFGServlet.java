package es.upm.dit.isst.tfg;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.TFGDAO;
import es.upm.dit.isst.tfg.dao.TFGDAOImpl;

public class NuevoTFGServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String autor = req.getUserPrincipal().getName();
		String titulo = req.getParameter("titulo");
		String tutor = req.getParameter("tutor");
		String secretario = req.getParameter("secretario");
		TFGDAO dao = TFGDAOImpl.getInstancia();
		dao.crearTFG(autor, titulo, tutor, secretario);
		resp.sendRedirect("/isst_tfg");
	}
}
