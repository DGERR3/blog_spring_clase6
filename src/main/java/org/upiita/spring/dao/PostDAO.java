package org.upiita.spring.dao;

import java.util.List;


import org.upiita.spring.entidades.Post;





	


	public interface PostDAO {


		
		



		public abstract Post buscaporId(Integer id);

		public abstract Integer guardar(Post post);
		
		public List<Post> buscaPorTitulo(String titulo);
		
		public List<Post> buscaDiferentesDeTitulo(String titulo);

	}
    

	

	

	

	

	

	
	
	

