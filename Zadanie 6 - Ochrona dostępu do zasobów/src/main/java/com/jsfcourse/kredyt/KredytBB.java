package com.jsfcourse.kredyt;

import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

@Named
@ViewScoped
public class KredytBB implements Serializable {
	
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	private Double x;
	private Double y;
	private Double z;
	
	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}
	
	public Double getZ() {
		return z;
	}

	public void setZ(Double z) {
		this.z = z;
	}

	public String calc() {
		FacesContext ctx = FacesContext.getCurrentInstance();		
		double result = x/y/12;
        result = result+result*z/100;
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + result, null));
		return PAGE_STAY_AT_THE_SAME; 
	}
}
