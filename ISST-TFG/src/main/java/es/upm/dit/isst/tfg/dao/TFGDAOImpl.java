package es.upm.dit.isst.tfg.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;

import es.upm.dit.isst.model.TFG;

public class TFGDAOImpl implements TFGDAO{
	
	private static TFGDAOImpl instancia;
    private TFGDAOImpl () {
    }
    public static TFGDAOImpl getInstancia() {
        if (instancia == null)
            instancia= new TFGDAOImpl();
        return instancia;
    }

	
	
	
	@Override
	public TFG crearTFG(String autor, String titulo, String tutor, String secretario) {
		
		TFG tfg = new TFG(autor, titulo , tutor, secretario);
		ofy().save().entity(tfg).now();
		return tfg;
	}

	@Override
	public List<TFG> leerTodosTFG() {
		List<TFG> tfgs = ofy().load().type(TFG.class).list();
		return tfgs;
	}

	@Override
	public TFG leerPorAutor(String autor) {
		TFG tfg = ofy().load().type(TFG.class).filterKey(Key.create(TFG.class, autor)).first().now();
		return tfg;
	}

	@Override
	public List<TFG> leerPorTutor(String tutor) {
		List<TFG> tfgs = ofy().load().type(TFG.class).filter("tutor",tutor).list();
		return tfgs;
	}

	@Override
	public List<TFG> leerPorSecretario(String secretario) {
		// TODO Auto-generated method stub
		List<TFG> tfgs = ofy().load().type(TFG.class).filter("secretario",secretario).list();
		return tfgs;
	}

	@Override
	public TFG actualizarTFG(TFG tfg) {
		
		ofy().save().entity(tfg).now();
		return tfg;
	}

	@Override
	public TFG borrarTFG(TFG tfg) {
		ofy().delete().entity(tfg).now();
		return tfg;
	}

}
