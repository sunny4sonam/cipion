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
@Table(name="dog")
public class Dog extends BaseObject implements Serializable {
	/*Default Serial UID*/
	private static final long serialVersionUID = 1L;
    private Long sid;
    private Grade grade;
    private Breed breed;
    private Subcategory subcategory;
    private Category category;
    private Guide guide;
    private String alias;
    private Date birthday;
    private String color;
    private String idnumber;
    private String license;
    private boolean male;
    private String name;
    private byte[] picture;
    private Set<Participants> participantses = new HashSet<Participants>(0);

    @Id @GeneratedValue(strategy = GenerationType.AUTO)     @Column(name="sid", unique=true, nullable=false)    
    public Long getSid() {
        return this.sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_grade", nullable=false)
    public Grade getGrade() {
        return this.grade;
    }
    
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_breed", nullable=false)
    public Breed getBreed() {
        return this.breed;
    }
    
    public void setBreed(Breed breed) {
        this.breed = breed;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_subcategory")
    public Subcategory getSubcategory() {
        return this.subcategory;
    }
    
    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_category", nullable=false)
    public Category getCategory() {
        return this.category;
    }
    
    public void setCategory(Category category) {
        this.category = category;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_guide", nullable=false)
    public Guide getGuide() {
        return this.guide;
    }
    
    public void setGuide(Guide guide) {
        this.guide = guide;
    }
    
    @Column(name="alias", length=100)
    public String getAlias() {
        return this.alias;
    }
    
    public void setAlias(String alias) {
        this.alias = alias;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="birthday", nullable=false, length=0)
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    @Column(name="color", length=100)
    public String getColor() {
        return this.color;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
    
    @Column(name="idnumber", length=100)
    public String getIdnumber() {
        return this.idnumber;
    }
    
    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }
    
    @Column(name="license", length=100)
    public String getLicense() {
        return this.license;
    }
    
    public void setLicense(String license) {
        this.license = license;
    }
    
    @Column(name="male", nullable=false)
    public boolean isMale() {
        return this.male;
    }
    
    public void setMale(boolean male) {
        this.male = male;
    }
    
    @Column(name="name", nullable=false, length=100)
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
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="dog")
    public Set<Participants> getParticipantses() {
        return this.participantses;
    }
    
    public void setParticipantses(Set<Participants> participantses) {
        this.participantses = participantses;
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
		return getSid();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Dog)) {
            return false;
        }

        Dog pojo = (Dog) o;

        if (grade != null ? !grade.equals(pojo.grade) : pojo.grade != null) return false;
        if (breed != null ? !breed.equals(pojo.breed) : pojo.breed != null) return false;
        if (subcategory != null ? !subcategory.equals(pojo.subcategory) : pojo.subcategory != null) return false;
        if (category != null ? !category.equals(pojo.category) : pojo.category != null) return false;
        if (guide != null ? !guide.equals(pojo.guide) : pojo.guide != null) return false;
        if (alias != null ? !alias.equals(pojo.alias) : pojo.alias != null) return false;
        if (birthday != null ? !birthday.equals(pojo.birthday) : pojo.birthday != null) return false;
        if (color != null ? !color.equals(pojo.color) : pojo.color != null) return false;
        if (idnumber != null ? !idnumber.equals(pojo.idnumber) : pojo.idnumber != null) return false;
        if (license != null ? !license.equals(pojo.license) : pojo.license != null) return false;
        if (male != pojo.male) return false;
        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (picture != null ? !picture.equals(pojo.picture) : pojo.picture != null) return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result = 0;
	        result = (grade != null ? grade.hashCode() : 0);
	        result = 31 * result + (breed != null ? breed.hashCode() : 0);
	        result = 31 * result + (subcategory != null ? subcategory.hashCode() : 0);
	        result = 31 * result + (category != null ? category.hashCode() : 0);
	        result = 31 * result + (guide != null ? guide.hashCode() : 0);
	        result = 31 * result + (alias != null ? alias.hashCode() : 0);
	        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
	        result = 31 * result + (color != null ? color.hashCode() : 0);
	        result = 31 * result + (idnumber != null ? idnumber.hashCode() : 0);
	        result = 31 * result + (license != null ? license.hashCode() : 0);
	        result = 31 * result + (male ? 1 : 0);
	        result = 31 * result + (name != null ? name.hashCode() : 0);
	        result = 31 * result + (picture != null ? picture.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("sid").append("='").append(getSid()).append("', ");
        
        
        
        
        
        sb.append("alias").append("='").append(getAlias()).append("', ");
        sb.append("birthday").append("='").append(getBirthday()).append("', ");
        sb.append("color").append("='").append(getColor()).append("', ");
        sb.append("idnumber").append("='").append(getIdnumber()).append("', ");
        sb.append("license").append("='").append(getLicense()).append("', ");
        sb.append("male").append("='").append(isMale()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("picture").append("='").append(getPicture()).append("', ");
        
        sb.append("]");
      
        return sb.toString();
    }

}
