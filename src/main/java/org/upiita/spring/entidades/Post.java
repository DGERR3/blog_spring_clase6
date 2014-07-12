package org.upiita.spring.entidades;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "posts")
public class Post {

	@Id
	@SequenceGenerator(name = "idPostSecuencia", sequenceName = "post_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "idPostSecuencia", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;

	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "contenido")
	private String contenido;

	// A UN POST LE CORRESPONDEN MUCHOS COMENTARIOS
	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	private List<Comentario> comentarios;

	@ManyToMany(mappedBy = "posts", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	private List<Categoria> categorias;

	// SOLUCION EJERCICIO   1-N
	@ManyToOne()
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public String toString() {

		StringBuilder builder = new StringBuilder();

		builder.append("{");
		builder.append("id:").append(this.id).append(",");
		builder.append("titulo:").append(this.titulo).append(",");
		builder.append("contenido:").append(this.contenido).append(",");
		builder.append("fechaCreacion:").append(this.fechaCreacion);
		builder.append("}");

		return builder.toString();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

}
