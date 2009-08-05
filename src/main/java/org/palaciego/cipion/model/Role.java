package org.palaciego.cipion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.GrantedAuthority;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * This class is used to represent available roles in the database.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         Version by Dan Kibler dan@getrolling.com
 *         Extended to implement Acegi GrantedAuthority interface
 *         by David Carter david@carter.net
 */
@Entity
@Table(name="role")
@NamedQueries ({
    @NamedQuery(
        name = "findRoleByName",
        query = "select r from Role r where r.name = :name "
        )
})
public class Role extends BaseObject implements Serializable, GrantedAuthority {
    private static final long serialVersionUID = 3690197650654049848L;
    private Long id;
    private String name;
    private String description;

    /**
     * Default constructor - creates a new instance with no values set.
     */
    public Role() {
    }

    /**
     * Create a new instance and set the name.
     * @param name name of the role.
     */
    public Role(final String name) {
        this.name = name;
    }

    @Id  @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * @see org.springframework.security.GrantedAuthority#getAuthority()
     * @return the name property (getAuthority required by Acegi's GrantedAuthority interface)
     */
    @Transient
    public String getAuthority() {
        return getName();
    }

    @Column(length=20)
    public String getName() {
        return this.name;
    }

    @Column(length=64)
    public String getDescription() {
        return this.description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }

        final Role role = (Role) o;

        return !(name != null ? !name.equals(role.name) : role.name != null);

    }

    /**
     * {@inheritDoc}
     */
    public int hashCode() {
        return (name != null ? name.hashCode() : 0);
    }

    /**
     * {@inheritDoc}
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append(this.name)
                .toString();
    }

    public int compareTo(Object o) {
        return (equals(o) ? 0 : -1);
    }
    

    /**
     * devuelve el valor del sid.
     */
	@Override
	@Transient
	public Object getFormIdField() {
		return this.getId();
	}

	/**
	 * Obtiene el tipo de sid.
	 */
	@Override
	@Transient
	public Class<?> getFormIdFieldClass() {
		return Long.class;
	}

	/**
	 * Obtiene el valor del texto que quiere aparecer en el combo.
	 */
	@Override
	@Transient
	public Object getFormLabelField() {
		return this.getName();
	}

	/**
	 * Obtiene el objeto id para una cadena.
	 */
	@Override
	@Transient
	public Serializable getPKForString(String stringPK) {
		return new Long(stringPK);
	}

	/**
	 * Obtiene la cadena de texto de una clave.
	 */
	@Override
	@Transient
	public String getStringForPK() {
		// TODO Auto-generated method stub
		return this.getId().toString();
	}
    
}
