package org.upiita.spring.testing;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.dao.PostDAO;
import org.upiita.spring.entidades.Post;



//ESTO ES UN ESTATIC IMPORT
import static org.springframework.util.Assert.*;
public class PostDAOTest {
	//HAY DOS TIPOS DE PRUEBAS
	//PRUEBA UNITARIA
	//PRUEBA DE INTEGRACION (BD O CHECAR QUE LA APLICACION WEB FUNCIONA
	
	//Declarar el contexto de spring
	
	private static ApplicationContext contexto;
	private static PostDAO postDAO;
	
	@BeforeClass
	public static void inicializar(){
		
		contexto = new ClassPathXmlApplicationContext("dao-context-testing.xml");
		
		postDAO = (PostDAO)contexto.getBean("postDAO");
		
	}
	@Test
 public  void buscaPostTest(){
	 
	Post post =  postDAO.buscaporId(1);
	
	System.out.println("post titulo" +  post.getTitulo());
	System.out.println("comentarios" + post.getComentarios());
	Assert.assertNotNull("El metodo para buscar post regresa datos vacios",post);
	
	
	 
 }
	@Test
	public void buscaPorTituloTest(){
		
		List<Post> postsEncontrados = postDAO.buscaPorTitulo("posts");
		
		System.out.println("Posts encontrados" + postsEncontrados);
		
         notEmpty(postsEncontrados);
		
		
		
	}
	
	
}
