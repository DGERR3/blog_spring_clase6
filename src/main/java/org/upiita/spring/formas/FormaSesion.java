package org.upiita.spring.formas;

import java.io.Serializable;

public class FormaSesion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer itemsComprados = 0;

	public Integer getItemsComprados() {
		return itemsComprados;
	}

	public void setItemsComprados(Integer itemsComprados) {
		this.itemsComprados = itemsComprados;
	}
	
	
	
}
