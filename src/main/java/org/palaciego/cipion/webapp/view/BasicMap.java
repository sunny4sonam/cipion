package org.palaciego.cipion.webapp.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Mapa B�sico en el que se insertan los datos para el DTO. Est� sobrecargado de
 * Hashtable para dar una lista ordenada. Hace de DTO en el ProcessReturn.
 * @author SEVDC
 */
public class BasicMap extends Hashtable<String, Object>
{
	/**
	 * Para el Interfaz Serializable.
	 */
	private static final long	serialVersionUID	= 6555284253722338325L;

	/**
	 * Utilizamos esta variable para guardar las claves de forma ordenada y
	 * luego poder recuperarlas de forma ordenada para que el XML se genere en
	 * el mismo orden en que se introduijeron los elementos. Esto es
	 * imprescindible para las pruebas unitarias.
	 */
	private Collection<String>	mKeys				= new ArrayList<String>();

	/**
	 * Nombre del DTO, ser� el texto de la etiqueta padre del XML generado a
	 * partir de este objeto. P.ej DTOBasic dtoBasic = new DTOBasic("hola");
	 * Generar� el XML <hola></hola>
	 */
	private String				name				= null;

	/**
	 * TODO EA (ADD) SESOA -> FROM SEJMVR. puede ser que no sea el lugar
	 * adecuado poner esta cte en esta clase pero de momento la dejamos aqu�
	 * porque la utilizan tanto en el actionplugins-commons como en el
	 * iguax-input Nombre de la vista que queremos asociar al proceso.
	 * @author SEJMVR
	 */
	public static final String	VIEW_ID				= "viewId";

	/**
	 * TODO EA (ADD) SESOA -> FROM SEJMVR. puede ser que no sea el lugar
	 * adecuado poner esta cte en esta clase pero de momento la dejamos aqu�
	 * porque la utilizan tanto en el actionplugins-commons como en el
	 * iguax-input Mensaje que se va a mostrar al usuario.
	 * @author SEJMVR
	 */
	public static final String	MESSAGE				= "message";

	/**
	 * Construimos el DTO por defecto.
	 */
	public BasicMap()
	{
		super();
	}

	/**
	 * Construimos el DTO pasandole el nombre, que ser� el texto de la etiqueta
	 * padre del XML generado a partir de este objeto. P.ej DTOBasic dtoBasic =
	 * new DTOBasic("hola"); Generar� el XML <hola></hola>
	 * @param name nombre del DTO
	 */
	public BasicMap(String name)
	{
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public final String getName()
	{
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Devuelve un String con el contenido del Objeto convertido a XML.
	 * @return un String con el contenido del Objeto convertido a XML.
	 */
	@Override
	public final String toString()
	{
		return BasicMap2XML.visit(this);
	}

	/**
	 * Si se introduce clave nula -> NullPointerException Si se introduce valor.
	 * nulo -> Se almacena la clave con el valor igual a cadena vacia Si se
	 * introduce una clave duplicada -> Se sobreescribe el valor anterior
	 * @param key el nombre de la clave
	 * @param value el objeto asociado a la clave
	 * @return object retorna al padre la clave y valor
	 */
	@Override
	public final Object put(String key, Object value)
	{
		String key2 = key;
		Object value2 = value;
		if (key == null)
		{
			key2 = "NULL";
		}
		if (value == null)
		{
			value2 = "";
		}
		// Sobreescribmos el metodo para poder meter los elementos de forma
		// ordenada y adem�s si el valor es Null, introducimos cadena vacia, lo
		// cual facilita el pintado.
		if (this.mKeys.contains(key2))
		{
			this.mKeys.remove(key2);
		}

		this.mKeys.add(key2);

		return super.put(key2, value2);
	}

	/**
	 * Devuelve un iterador sobre las claves del mapa en el mismo orden en que
	 * se introdujeron.
	 * @return un iterador sobre las claves del mapa en el mismo orden en que se
	 *         introdujeron
	 */
	public final Iterator<String> iterator()
	{
		return this.mKeys.iterator();
	}
}
