package org.upiita.spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.upiita.spring.entidades.Usuario;

@Component("usuarioDAO")
@Transactional
public class HibernateUsuarioDAO implements UsuarioDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see org.upiita.spring.dao.UsuarioDAO#buscaPorId(java.lang.Integer)
	 */
	@Override
	public Usuario buscaPorId(Integer id){
		Usuario usuario = null;
		
		System.out.println("BUSCANDO USUARIO POR ID" + id);
		
		Session sesion = sessionFactory.getCurrentSession();
		//hacemos la consulta a la base con hibernate
		//transaccion declarativa
		usuario = (Usuario) sesion.get(Usuario.class, id);
		
		return usuario;
	}
	
	/* (non-Javadoc)
	 * @see org.upiita.spring.dao.UsuarioDAO#guardar(org.upiita.spring.entidades.Usuario)
	 */
	@Override
	public Integer guardar(Usuario usuario){
		
		Session sesion = sessionFactory.getCurrentSession();
		//------------TRANSACCION PROGRAMATICA (comentada, se deja solo para fines de documentacion)
		//Session sesion = sessionFactory.openSession();		
		//sesion.beginTransaction();
		
		//UNA VEZ INICIADA LA TRANSACCION PODEMOS HACER CONSULTAS
		//O MODIFICAR LA BASE
		//SI HIBERNATE ENCUENTRA EL RENGLON, EL OBJETO SE RELLENA AUTOMATICAENTE
		//SI NO LO ENCUENTRA, HIBERNATE REGRESA NULL
		//ESTE METODO NO REGRESA NADA
		// PERO!!!
		// ACUTALIZA EL OBJETO QUE LE PASAN 
		// EN PARTICULAR EL ID
		sesion.saveOrUpdate(usuario);
		
		//TERMINADO TODO LO QUE NECESITAMOS HACER EN LA BASE
		//CERRAMOS LA SESION DE HIBERNATE
		//SI CAMBIAN DATOS HACER EL COMMIT!!!
		//SI NO SE VEN LOS CAMBIOS
		//sesion.getTransaction().commit();
		//sesion.close();		
		
		return usuario.getId();
	}

}
