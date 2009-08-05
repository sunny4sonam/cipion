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
@Table(name="country")
public class Country extends BaseObject implements Serializable {
	/*Default Serial UID*/
	private static final long serialVersionUID = 1L;
    private Long sid;
    private byte[] flag;
    private String name;
    private Set<Settings> settingses = new HashSet<Settings>(0);
    private Set<Club> clubs = new HashSet<Club>(0);

    @Id @GeneratedValue(strategy = GenerationType.AUTO)     @Column(name="sid", unique=true, nullable=false)    
    public Long getSid() {
        return this.sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }
    
    @Column(name="flag",length=16777215)
    public byte[] getFlag() {
        return this.flag;
    }
    
    public void setFlag(byte[] flag) {
        this.flag = flag;
    }
    
    @Column(name="name", nullable=false, length=150)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="country")
    public Set<Settings> getSettingses() {
        return this.settingses;
    }
    
    public void setSettingses(Set<Settings> settingses) {
        this.settingses = settingses;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="country")
    public Set<Club> getClubs() {
        return this.clubs;
    }
    
    public void setClubs(Set<Club> clubs) {
        this.clubs = clubs;
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

        Country pojo = (Country) o;

        if (flag != null ? !flag.equals(pojo.flag) : pojo.flag != null) return false;
        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result = 0;
	        result = (flag != null ? flag.hashCode() : 0);
	        result = 31 * result + (name != null ? name.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("sid").append("='").append(getSid()).append("', ");
        sb.append("flag").append("='").append(getFlag()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        
        
        sb.append("]");
      
        return sb.toString();
    }

}
