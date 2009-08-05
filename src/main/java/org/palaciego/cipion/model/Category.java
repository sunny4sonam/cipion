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
@Table(name="category")
public class Category extends BaseObject implements Serializable {
	/*Default Serial UID*/
	private static final long serialVersionUID = 1L;
    private Long sid;
    private String description;
    private String name;
    private Set<Dog> dogs = new HashSet<Dog>(0);

    @Id @GeneratedValue(strategy = GenerationType.AUTO)     @Column(name="sid", unique=true, nullable=false)    
    public Long getSid() {
        return this.sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }
    
    @Column(name="description", length=400)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="name", nullable=false, length=200)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="category")
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
		return getName();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category pojo = (Category) o;

        if (description != null ? !description.equals(pojo.description) : pojo.description != null) return false;
        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result = 0;
	        result = (description != null ? description.hashCode() : 0);
	        result = 31 * result + (name != null ? name.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("sid").append("='").append(getSid()).append("', ");
        sb.append("description").append("='").append(getDescription()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        
        sb.append("]");
      
        return sb.toString();
    }

}
