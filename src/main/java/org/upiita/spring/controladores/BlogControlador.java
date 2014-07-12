package org.upiita.spring.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.upiita.spring.dao.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.upiita.spring.entidades.Post;
import org.upiita.spring.formas.FormaSesion;
import org.upiita.spring.formas.PostForma;


//ESTO ES ANNOTATION Y LE DICE A SPRING
//QUE ESTA CLASE ES UN CONTROLADOR
@Controller
public class BlogControlador {
	
	@Autowired
	@Qualifier(value="postDAO")
	private PostDAO postDAO;
	@Autowired
	private FormaSesion formaSesion;
	
	//EXPRESION REGULAR:
	//   []  = DEFINIR RANGOS
	//    + = AL MENOS UN ELEMENTO DEL ANTERIOR EXPRESION
	@RequestMapping(value="/blog/{postId:[0-9]+}")
	public String mostrarInicio(@PathVariable(value="postId") Integer postId, @RequestParam(required=false) Integer limite, @RequestParam(required=false) String nombre, Model modelo){
		
		System.out.println("limite:" + limite);
		System.out.println("despachando url /blog/" + postId);
		
		//SIMULAMOS LA CONSULTA  AL ABASE
		//NOS REGRESO ESTE OBJETO
		//Post elPost = new Post();
		//elPost.setTitulo("titulo prueba");
		//elPost.setContenido("contenido prueba");
		
		//HibernatePostDAO postDAO = new HibernatePostDAO();
		Post elPost = postDAO.buscaporId(postId);
		
		modelo.addAttribute("post", elPost);
		modelo.addAttribute("nombre",nombre);
				
		System.out.println("ITEMS COMPRADOS:" + formaSesion.getItemsComprados());
		return "post";
	}
	
	//POR DEFAULT, LAS URLS SON DEL TIPO GET
	@RequestMapping(value="/blog/{postId:[0-9]+}/editar")
	public String mostrarEditor(@RequestParam(required= false)Boolean Actualizado,@PathVariable Integer postId,Model modelo){
		//Si hubo errores al gurdar se ejecuta el if
		if(modelo.containsAttribute("post")){
			
			System.out.println("hubo errores,MODELO:" + modelo);
		}
		//si todo salio bien se ejecuta el else o si es la primera vez que s eejecuta
		else{
		
			Post post=postDAO.buscaporId(postId);
	        modelo.addAttribute("post",post);
	        modelo.addAttribute("Actualizado", Actualizado);
		}
		
		return "post_editar";
	}
	
	@RequestMapping(value="/blog/guardar",method=RequestMethod.POST)
	public String guardarPost(@Valid @ModelAttribute("post") PostForma forma,BindingResult validacion,RedirectAttributes atributos){
		
		String urlRedirect = null;
		System.out.println("id: " + forma.getId() + "titulo:" + forma.getTitulo() + ", contenido:" + forma.getContenido());
		//@TODO: MAS ADELANTE GUARDAMOS ESTOS EN LA BASE DE DATOS, 
		
		//@TODO MAS ADELANTE CAMBIAREMOS ESTO POR EL PATRON
		//POST -REDIRECT -GET
		
		if(validacion.hasErrors()){
		//Disponible en versión 3.1 de spring para preservar datos en el redirect
			atributos.addFlashAttribute("post",forma);
			atributos.addFlashAttribute("org.springframework.validation.BindingResult.post", validacion);
			
			urlRedirect = "redirect:/blog/" + forma.getId() + "/editar";
		}
		else{
			//El else se ejecuta si no hay errores
			Post post = new Post();
			post.setId(forma.getId());
			post.setTitulo(forma.getTitulo());
			post.setContenido(forma.getContenido());
			Integer postIdGuardado = postDAO.guardar(post);
			urlRedirect= "redirect:/blog/" + postIdGuardado + "/editar?Actualizado=true";
		}
 
		return urlRedirect;
	}
	

}
