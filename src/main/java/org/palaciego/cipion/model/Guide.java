package org.palaciego.cipion.model;

import org.palaciego.cipion.model.BaseObject;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;

import java.io.Serializable;

@Entity
@Table(name="guide")
public class Guide extends BaseObject implements Serializable {
	/*Default Serial UID*/
	private static final long serialVersionUID = 1L;
    private Long sid;
    private Club club;
    private Date birthday;
    private String firstname;
    private String idnumber;
    private String lastname;
    private boolean male;
    private byte[] picture;
    private Set<Dog> dogs = new HashSet<Dog>(0);

    @Id @GeneratedValue(strategy = GenerationType.AUTO)     @Column(name="sid", unique=true, nullable=false)    
    public Long getSid() {
        return this.sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_club", nullable=false)
    public Club getClub() {
        return this.club;
    }
    
    public void setClub(Club club) {
        this.club = club;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="birthday", length=0)
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
    
    @Column(name="male", nullable=false)
    public boolean isMale() {
        return this.male;
    }
    
    public void setMale(boolean male) {
        this.male = male;
    }
    
    @Column(name="picture",length=16777215)
    public byte[] getPicture() {
        return this.picture;
    }
    
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="guide")
    public Set<Dog> getDogs() {
        return this.dogs;
    }
    
    public void setDogs(Set<Dog> dogs) {
        this.dogs = dogs;
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
		return getFirstname() + " " + getLastname();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;

        Guide pojo = (Guide) o;

        if (club != null ? !club.equals(pojo.club) : pojo.club != null) return false;
        if (birthday != null ? !birthday.equals(pojo.birthday) : pojo.birthday != null) return false;
        if (firstname != null ? !firstname.equals(pojo.firstname) : pojo.firstname != null) return false;
        if (idnumber != null ? !idnumber.equals(pojo.idnumber) : pojo.idnumber != null) return false;
        if (lastname != null ? !lastname.equals(pojo.lastname) : pojo.lastname != null) return false;
        if (male != pojo.male) return false;
        if (picture != null ? !picture.equals(pojo.picture) : pojo.picture != null) return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result = 0;
	        result = (club != null ? club.hashCode() : 0);
	        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
	        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
	        result = 31 * result + (idnumber != null ? idnumber.hashCode() : 0);
	        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
	        result = 31 * result + (male ? 1 : 0);
	        result = 31 * result + (picture != null ? picture.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("sid").append("='").append(getSid()).append("', ");
        
        sb.append("birthday").append("='").append(getBirthday()).append("', ");
        sb.append("firstname").append("='").append(getFirstname()).append("', ");
        sb.append("idnumber").append("='").append(getIdnumber()).append("', ");
        sb.append("lastname").append("='").append(getLastname()).append("', ");
        sb.append("male").append("='").append(isMale()).append("', ");
        sb.append("picture").append("='").append(getPicture()).append("', ");
        
        sb.append("]");
      
        return sb.toString();
    }

}
