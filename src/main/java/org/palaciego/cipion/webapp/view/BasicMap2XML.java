package org.palaciego.cipion.webapp.view;

import java.util.Collection;
import java.util.Iterator;


/**
 * @author SEVDC
 */
public class BasicMap2XML
{
	/**
	 * Visitamos el DTOBasic para pintar su contenido en forma de XML.
	 *
	 * @param dto Objeto a pintar
	 * @return Cadena con el contenido del objeto en formato XML
	 */
	public static String visit(BasicMap dto)
	{
		StringBuffer res = new StringBuffer();

		Iterator<String> keys = dto.iterator();

		String etiquetaPadre = dto.getName();

		boolean pintoEtiquetaPadre = (etiquetaPadre != null && !etiquetaPadre.startsWith("#"));

		if(pintoEtiquetaPadre)
			{
			res.append("<").append(etiquetaPadre).append(">");
			}

		String etiquetaHijo = null;

		Object contenidoHijo = null;

		while (keys.hasNext())
		{
			etiquetaHijo = keys.next();
			contenidoHijo = dto.get(etiquetaHijo);

			pintoHijo(res, etiquetaHijo, contenidoHijo);
		}

		if (pintoEtiquetaPadre)
		{
			res.append("</").append(etiquetaPadre).append(">");
		}


		return res.toString();
	}

	/**
	 * @param res resultado
	 * @param etiquetaHijo nombre de la clave para etiqueta hijo
	 * @param contenidoHijo valor para asociada ha la clave
	 */
	private static void pintoHijo(StringBuffer res, String etiquetaHijo, Object contenidoHijo)
	{
		if (contenidoHijo == null)
			{
			return;
			}

		if (contenidoHijo instanceof BasicMap)
		{
			res.append(((BasicMap) contenidoHijo).toString());
		}
		else if (contenidoHijo instanceof Collection)
		{
			Iterator<?> itHijos = ((Collection<?>) contenidoHijo).iterator();

			Object hijo = null;

			while (itHijos.hasNext())
			{
				hijo = itHijos.next();

				pintoHijo(res, etiquetaHijo, hijo);
			}
		}
		else
		{
			boolean pintoEtiquetaHijo = (etiquetaHijo != null && !etiquetaHijo.startsWith("#"));

			if (pintoEtiquetaHijo)
				{
				res.append("<").append(etiquetaHijo).append(">");
				}
			res.append("<![CDATA[");
			res.append(contenidoHijo.toString());
			res.append("]]>");
			if (pintoEtiquetaHijo)
			{
				res.append("</").append(etiquetaHijo).append(">");
			}
		}
	}
}
