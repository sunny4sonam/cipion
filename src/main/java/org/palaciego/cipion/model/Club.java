package org.palaciego.cipion.model;

import org.palaciego.cipion.model.BaseObject;
import org.palaciego.cipion.service.GenericManager;

import java.util.HashSet;
import java.util.List;
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

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;

import java.io.Serializable;

@Entity
@Table(name="club")
public class Club extends BaseObject implements Serializable {
	/*Default Serial UID*/
	private static final long serialVersionUID = 1L;
    private Long sid;
    private Country country;
    private String address;
    private String city;
    private String contactperson;
    private String description;
    private String email;
    private String name;
    private byte[] picture;
    private String region;
    private String state;
    private String telephone;
    private String zipcode;
    private Set<Guide> guides = new HashSet<Guide>(0);

    @Id @GeneratedValue(strategy = GenerationType.AUTO)     @Column(name="sid", unique=true, nullable=false)    
    public Long getSid() {
        return this.sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_country", nullable=false)
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
    
    @Column(name="address", length=300)
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="city", length=300)
    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="contactperson", length=200)
    public String getContactperson() {
        return this.contactperson;
    }
    
    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }
    
    @Column(name="description", length=400)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="email", length=200)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column(name="name", nullable=false, length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="picture",length=16777215)
    public byte[] getPicture() {
        return this.picture;
    }
    
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
    
    @Column(name="region", length=300)
    public String getRegion() {
        return this.region;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }
    
    @Column(name="state", length=300)
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    @Column(name="telephone", length=20)
    public String getTelephone() {
        return this.telephone;
    }
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @Column(name="zipcode", length=10)
    public String getZipcode() {
        return this.zipcode;
    }
    
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="club")
    public Set<Guide> getGuides() {
        return this.guides;
    }
    
    public void setGuides(Set<Guide> guides) {
        this.guides = guides;
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
		return getName();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Club pojo = (Club) o;

        if (country != null ? !country.equals(pojo.country) : pojo.country != null) return false;
        if (address != null ? !address.equals(pojo.address) : pojo.address != null) return false;
        if (city != null ? !city.equals(pojo.city) : pojo.city != null) return false;
        if (contactperson != null ? !contactperson.equals(pojo.contactperson) : pojo.contactperson != null) return false;
        if (description != null ? !description.equals(pojo.description) : pojo.description != null) return false;
        if (email != null ? !email.equals(pojo.email) : pojo.email != null) return false;
        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (picture != null ? !picture.equals(pojo.picture) : pojo.picture != null) return false;
        if (region != null ? !region.equals(pojo.region) : pojo.region != null) return false;
        if (state != null ? !state.equals(pojo.state) : pojo.state != null) return false;
        if (telephone != null ? !telephone.equals(pojo.telephone) : pojo.telephone != null) return false;
        if (zipcode != null ? !zipcode.equals(pojo.zipcode) : pojo.zipcode != null) return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result = 0;
	        result = (country != null ? country.hashCode() : 0);
	        result = 31 * result + (address != null ? address.hashCode() : 0);
	        result = 31 * result + (city != null ? city.hashCode() : 0);
	        result = 31 * result + (contactperson != null ? contactperson.hashCode() : 0);
	        result = 31 * result + (description != null ? description.hashCode() : 0);
	        result = 31 * result + (email != null ? email.hashCode() : 0);
	        result = 31 * result + (name != null ? name.hashCode() : 0);
	        result = 31 * result + (picture != null ? picture.hashCode() : 0);
	        result = 31 * result + (region != null ? region.hashCode() : 0);
	        result = 31 * result + (state != null ? state.hashCode() : 0);
	        result = 31 * result + (telephone != null ? telephone.hashCode() : 0);
	        result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("sid").append("='").append(getSid()).append("', ");
        
        sb.append("address").append("='").append(getAddress()).append("', ");
        sb.append("city").append("='").append(getCity()).append("', ");
        sb.append("contactperson").append("='").append(getContactperson()).append("', ");
        sb.append("description").append("='").append(getDescription()).append("', ");
        sb.append("email").append("='").append(getEmail()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("picture").append("='").append(getPicture()).append("', ");
        sb.append("region").append("='").append(getRegion()).append("', ");
        sb.append("state").append("='").append(getState()).append("', ");
        sb.append("telephone").append("='").append(getTelephone()).append("', ");
        sb.append("zipcode").append("='").append(getZipcode()).append("', ");
        
        sb.append("]");
      
        return sb.toString();
    }

}
