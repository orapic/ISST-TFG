package es.upm.dit.isst.model;

import java.io.Serializable;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@Entity
public class TFG implements Serializable{

	@Id
	private String autor;
	private String titulo;
	@Index
	private String tutor;
	@Index
	private String secretario;
	private String memoria;
	private int estado;
	
	public TFG(String autor, String titulo, String tutor, String secretario) {
		super();
		this.autor = autor;
		this.titulo = titulo;
		this.tutor = tutor;
		this.secretario = secretario;
		this.memoria = null;
		this.estado = 1;
	}
	
	public TFG() {}
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public String getSecretario() {
		return secretario;
	}

	public void setSecretario(String secretario) {
		this.secretario = secretario;
	}

	public String getMemoria() {
		return memoria;
	}

	public void setMemoria(String memoria) {
		this.memoria = memoria;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "TFGjava [autor=" + autor + ", titulo=" + titulo + ", tutor=" + tutor + ", secretario=" + secretario
				+ ", memoria=" + memoria + ", estado=" + estado + "]";
	}
	
	


}
