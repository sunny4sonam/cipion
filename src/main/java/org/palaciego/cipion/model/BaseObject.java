package org.palaciego.cipion.model;

import java.io.Serializable;

import javax.persistence.Transient;


/**
 * Base class for Model objects. Child objects should implement toString(),
 * equals() and hashCode().
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public abstract class BaseObject implements Serializable {    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets FormIdField name for options
	 */
	@Transient
	public abstract Object getFormIdField();

	/**
	 *
	 **/
	@Transient
	public abstract Class getFormIdFieldClass();

	/**
	 * Gets PK for a concrete String
	 */
	@Transient
	public abstract Serializable getPKForString(String stringPK);

	/**
	 * Gets String representation of PK field
	 */
	@Transient
	public abstract String getStringForPK();

	/**
	 * Gets FormLabelField name for options
	 */
	@Transient
	public abstract Object getFormLabelField();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		return getFormLabelField().toString().trim().toLowerCase().compareTo(
				((BaseObject) o).getFormLabelField().toString().trim()
						.toLowerCase());
	}

	
	
    /**
     * Returns a multi-line String with key=value pairs.
     * @return a String representation of this class.
     */
    public abstract String toString();

    /**
     * Compares object equality. When using Hibernate, the primary key should
     * not be a part of this comparison.
     * @param o object to compare to
     * @return true/false based on equality tests
     */
    public abstract boolean equals(Object o);

    /**
     * When you override equals, you should override hashCode. See "Why are
     * equals() and hashCode() importation" for more information:
     * http://www.hibernate.org/109.html
     * @return hashCode
     */
    public abstract int hashCode();
}
