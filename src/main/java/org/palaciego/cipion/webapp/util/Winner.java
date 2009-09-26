package org.palaciego.cipion.webapp.util;

import java.util.Comparator;

import org.palaciego.cipion.model.Calification;
import org.palaciego.cipion.model.Participants;
import org.palaciego.cipion.model.Roundresults;
import org.palaciego.cipion.util.CipionUtil;

public class Winner implements Comparator<Winner>
{
	/**
	 * Penalizaciones por tiempo superado del TRM.
	 */
	public double timeFaultPoints;
	/**
	 * Penalizaciones por faltas y rehuses en recorrido.
	 */
	public double pathFaultPoints;

	/**
	 * Resultados de la ronda.
	 */
	public Roundresults results;

	/**
	 * Resultados de la ronda dos.
	 */
	public Roundresults results2;

	/**
	 * Participante.
	 */
	public Participants participants;

	/**
	 * {@link Calification}
	 */
	public Calification Calification;
	
	/**
	 * Devuelve el total de puntos de falta.
	 * @return double
	 */
	public double getTotalFault()
	{
		return CipionUtil.redondear(timeFaultPoints+pathFaultPoints,2);
	}

	/**
	 * Devuelve el tiempo de la prueba.
	 * @return
	 */
	public double getTime()
	{
		if(results!=null)
		{
			if(results2!=null)
			{
				return (results.getTime()+results2.getTime());
			}
			
			return results.getTime();
		}
		return 0;
	}

	/**
	 * Compara dos winners y dice quién es el menor y quién es el mayor.
	 * @return int
	 */
	public int compare(Winner o1, Winner o2) {
		if(o1.getTotalFault()==o2.getTotalFault())
		{
			if(o1.pathFaultPoints<o2.pathFaultPoints)
			{
				return -1;
			}
			else if(o1.pathFaultPoints>o2.pathFaultPoints)
			{
				return 1;
			}
			
			//si son iguales en faltas por recorrido, discrimino por tiempo
			if(o1.timeFaultPoints<o2.timeFaultPoints)
			{
				return -1;
			}
			else if(o1.timeFaultPoints>o2.timeFaultPoints)
			{
				return 1;
			}

			if(o1.results!=null && o2.results!=null)
			{
				//si son iguale spor faltas de tiempo.. discrimino por el tiempo en si
				if(o1.getTime()<o2.getTime())
				{
					return -1;
				}
				else if(o1.getTime()>o2.getTime())
				{
					return 1;
				}
			}

			//sino... me rindo.
			return 0;
		}
		else
		{
			if(o1.getTotalFault()<o2.getTotalFault())
			{
				return -1;
			}
			return 1;
		}
	}
}
