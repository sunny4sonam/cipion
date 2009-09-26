package org.palaciego.cipion.util;

public class CipionUtil {

	  public static double redondear( double numero, int decimales ) {
		    return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
		  }

}
