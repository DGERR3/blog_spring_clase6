package org.upiita.spring.dao;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.upiita.spring.entidades.Post;;

@Component(value="postDAO")
@Transactional
public class HibernatePostDAO implements PostDAO {
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Post buscaporId(Integer id){
		 Post elPost = null;
		 //TRANSACCION PROGRAMATICA
		 
		 //Estas lineas se quitan para hacer transaccion declarativa
		 
		// Session sesion = sessionFactory.openSession(); 
		 //sesion.beginTransaction();
		 
		 Session sesion = sessionFactory.getCurrentSession();
		 
		 
		 
		 //UNA VEZ INICIADA LA TRANSACCION PODEMOS HACER CONSULTAS O MODIFICAR LA BASE
		 //SI HIBERNATE ENCUENTRA EL RENGLON, EL OBJETO SE RELLENA AUTOMATICAMENTE
		 //SI NO LO ENCUENTRA , HIBERNATE REGRESA NULL
		elPost= (Post)sesion.get(Post.class, id);
		//LE INDICA A HIBERNATEQUE QUIERES SUS COMENTARIOS ASOCIADOS
		//Hibernate.initialize(elPost.getComentarios());
		//CERRAMOS LA SESION DE HIBERNATE
		//sesion.close();
		//SIMULAMOS LA CONSULTA  AL ABASE
			//NOS REGRESO ESTE OBJETO
			/*elPost = new Post();
			elPost.setTitulo("titulo cambia");
			elPost.setContenido("contenido cambia");*/
		 
		 return elPost;
	}
/* (non-Javadoc)
 * @see org.upiita.spring.dao.PostDAO#guardar(org.upiita.spring.entidades.Post)
 */




public Integer guardar(Post post){
	
	//Session sesion = sessionFactory.openSession(); 
	 //sesion.beginTransaction();
	Session sesion = sessionFactory.getCurrentSession();
	 
	 //ACTUALIZA EL OBJETO QUE LE PASAN EN PARTICULAR EL ID
	sesion.saveOrUpdate(post);
	//sesion.getTransaction().commit();
	//CERRAMOS LA SESION DE HIBERNATE
	//sesion.close();
	return post.getId();
}







public List<Post> buscaPorTitulo(String titulo){
	
	Session sesion = sessionFactory.getCurrentSession();
	//A PARTIR DE LA SESION CREAMOS EL CRITERIO
	//USANDO EL CLASE QUE REPRESENTA A LA TABLA
	Criteria criterio = sesion.createCriteria(Post.class);
	//PRIMER ARGUMENTO DEL LIKE (EN GENERAL DE LA RESTRICCION)
	//ES LA PROPIEDAD DE LA ENTIDAD A BUSCAR
	criterio.add(Restrictions.like("titulo","%" + titulo + "%"));
	//LE DECIMOS A HIBERNATE QUE BUSQUE Y OBTENGA UNA LISTA DE RESULTADOS
	
	List<Post> postsEncontrados = criterio.list();
	return postsEncontrados;
	
}




public List<Post> buscaDiferentesDeTitulo(String titulo){
	
	Session sesion = sessionFactory.getCurrentSession();
	//A PARTIR DE LA SESION CREAMOS EL CRITERIO
	//USANDO EL CLASE QUE REPRESENTA A LA TABLA
	Criteria criterio = sesion.createCriteria(Post.class);
	//PRIMER ARGUMENTO DEL LIKE (EN GENERAL DE LA RESTRICCION)
	//ES LA PROPIEDAD DE LA ENTIDAD A BUSCAR
	criterio.add(Restrictions.not(Restrictions.like("titulo","%" + titulo + "%")));
	//LE DECIMOS A HIBERNATE QUE BUSQUE Y OBTENGA UNA LISTA DE RESULTADOS
	
	List<Post> postsEncontrados = criterio.list();
	return postsEncontrados;
	
}


}
