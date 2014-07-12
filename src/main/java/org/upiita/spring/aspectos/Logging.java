package org.upiita.spring.aspectos;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//Para dar de alta un componente en spring se da de alta con @component
@Component
@Aspect
public class Logging {

	@Before("execution(* org.upiita.spring.dao.UsuarioDAO.buscaPorId(..))")
	public void antesDeInvocar(JoinPoint joinpoint){
		System.out.println("ANTES DE INVOCAR");
		System.out.println("argumentos:" + Arrays.asList(joinpoint.getArgs()));
	}
	
	public void despuesDeInvocar(){
		System.out.println("DESPUES DE INVOCAR");

	}
	
	
	public Object alrededor(ProceedingJoinPoint joinPoint) throws Throwable {
		Object resultado;				
		resultado = joinPoint.proceed();		
		return resultado;
	}

	
	
}



