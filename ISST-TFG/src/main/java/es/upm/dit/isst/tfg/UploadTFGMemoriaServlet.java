package es.upm.dit.isst.tfg;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import es.upm.dit.isst.model.TFG;
import es.upm.dit.isst.tfg.dao.TFGDAO;
import es.upm.dit.isst.tfg.dao.TFGDAOImpl;

public class UploadTFGMemoriaServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if (req.getUserPrincipal() != null){

			String autor = req.getParameter("autor");
			TFGDAO dao = TFGDAOImpl.getInstancia();
			TFG tfg = dao.leerPorAutor(autor);
			int estado = tfg.getEstado();
			if (estado == 2){

				Map<String, List<BlobKey>> blobs = BlobstoreServiceFactory.getBlobstoreService().getUploads(req);
				List<BlobKey> blobKeys = blobs.get("file");
				if (blobKeys == null || blobKeys.isEmpty() || blobKeys.get(0) == null) {
					resp.sendError(1200);
				}
				tfg.setMemoria(blobKeys.get(0).getKeyString());
				tfg.setEstado(3);
				dao.actualizarTFG(tfg);
			}
				
		
		
		}
		resp.sendRedirect("/isst_tfg");
	}
	
	
}
