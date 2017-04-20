package es.upm.dit.isst.tfg.dao;

import java.util.List;

import es.upm.dit.isst.model.TFG;

public interface TFGDAO {

	
	public TFG crearTFG(String autor, String titulo, String tutor, String secretario);
	public List<TFG> leerTodosTFG();
	public TFG leerPorAutor(String autor);
	public List<TFG> leerPorTutor(String tutor);
	public List<TFG> leerPorSecretario(String secretario);
	public TFG actualizarTFG(TFG tfg);
	public TFG borrarTFG(TFG tfg);
	
}
