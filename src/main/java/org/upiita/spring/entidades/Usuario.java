package org.upiita.spring.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name = "usuarios")
public class Usuario {

	@Id
	// SOLUCION EJERCICIO:AGREGAR SECUENCIA GENERADORA PARA EL USUARIO
	@SequenceGenerator(name = "secuenciaUsuario", sequenceName = "usuarios_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "secuenciaUsuario", strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Integer id;

	@Column(name = "password")
	private String password;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "email")
	private String email;

	@OneToOne
	// EN LA ENTIDAD DUENA SIEMPRE VA EL @JoinColumn
	// ENTIDAD DUENA == LA TABLA QUE TIENE LA LLAVE FORANEA DE LA RELACION
	// name= NOMBRE REAL DE LA COLUMNA QUE DEFINE LA LLAVE FORANEA
	@JoinColumn(name = "datos_autor_id")
	private DatosUsuario datosUsuario;

	// SOLUCION EJERCICIO 1-N
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT) //solo cuando se tiene EAGER EN AMBAS ENTIDADES
	private List<Post> posts;

	// SOLUCION EJERCICIO N-N
	// ESTOY SIGUIENDO LA CONVENCION DE QUE la tabla usuarios es LA ENTIDAD DUENA
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT) //solo cuando se tiene EAGER EN AMBAS ENTIDADES
	@JoinTable(name = "departamentos_usuarios", 
	          //joinColumns = EL NOMBRE DE LA COLUMNA EN LA TABLA INTERMEDIA DE LA ENTIDAD DUENA
	          joinColumns = { @JoinColumn(name = "usuario_id") },
	    	  //inverseJoinColumns = EL NOMBRE DE LA COLUMNA EN LA TABLA INTERMEDIA DE LA ENTIDAD QUE NO ES DUENA
	          inverseJoinColumns = { @JoinColumn(name = "departamento_id") })
	private List<Departamento> departamentos;
	
	public List<Departamento> getDepartamentos() {
		return departamentos;
	}
	
	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public DatosUsuario getDatosUsuario() {
		return datosUsuario;
	}

	public void setDatosUsuario(DatosUsuario datosUsuario) {
		this.datosUsuario = datosUsuario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
