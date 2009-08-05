package org.palaciego.cipion.model;

import org.palaciego.cipion.model.BaseObject;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;

import java.io.Serializable;

@Entity
@Table(name="judge")
public class Judge extends BaseObject implements Serializable {
	/*Default Serial UID*/
	private static final long serialVersionUID = 1L;
    private Long sid;
    private String firstname;
    private String idnumber;
    private String lastname;
    private String license;
    private Set<Event> events = new HashSet<Event>(0);

    @Id @GeneratedValue(strategy = GenerationType.AUTO)     @Column(name="sid", unique=true, nullable=false)    
    public Long getSid() {
        return this.sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }
    
    @Column(name="firstname", nullable=false, length=200)
    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    @Column(name="idnumber", length=100)
    public String getIdnumber() {
        return this.idnumber;
    }
    
    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }
    
    @Column(name="lastname", length=200)
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    @Column(name="license", length=100)
    public String getLicense() {
        return this.license;
    }
    
    public void setLicense(String license) {
        this.license = license;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="judge")
    public Set<Event> getEvents() {
        return this.events;
    }
    
    public void setEvents(Set<Event> events) {
        this.events = events;
    }

	/**	
	* Gets FormIdField name for options
	*/
	@Transient
	public Long getFormIdField() {
		return getSid();
	}
	
	/**	
	* Gets FormIdFieldClass
	*/
	@Transient
	public Class getFormIdFieldClass() {
		return Long.class;
	}
	
	/**
	 * Gets PK for a concrete String
	 */
	@Transient
	public Serializable getPKForString(String stringPK){
		return new Long(stringPK);
	}
	
	/**
	 * Gets String representation of PK field
	 */
	@Transient
	public String getStringForPK(){
		return getSid().toString();
	}
	
	/**	
	* Gets FormLabelField name for options 
	*/
	@Transient
	public Object getFormLabelField() {
		return getFirstname() + "," + getLastname();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Judge pojo = (Judge) o;

        if (firstname != null ? !firstname.equals(pojo.firstname) : pojo.firstname != null) return false;
        if (idnumber != null ? !idnumber.equals(pojo.idnumber) : pojo.idnumber != null) return false;
        if (lastname != null ? !lastname.equals(pojo.lastname) : pojo.lastname != null) return false;
        if (license != null ? !license.equals(pojo.license) : pojo.license != null) return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result = 0;
	        result = (firstname != null ? firstname.hashCode() : 0);
	        result = 31 * result + (idnumber != null ? idnumber.hashCode() : 0);
	        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
	        result = 31 * result + (license != null ? license.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("sid").append("='").append(getSid()).append("', ");
        sb.append("firstname").append("='").append(getFirstname()).append("', ");
        sb.append("idnumber").append("='").append(getIdnumber()).append("', ");
        sb.append("lastname").append("='").append(getLastname()).append("', ");
        sb.append("license").append("='").append(getLicense()).append("', ");
        
        sb.append("]");
      
        return sb.toString();
    }

}
