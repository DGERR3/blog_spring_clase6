package org.upiita.spring.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsuarioControlador {

	@RequestMapping(value="/Usuario/{userid[0-9]+}/editar")
	
	public String MuestraEditorUsu(){
		
		return "user_edit";
		
	}
	
	
}
