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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;

import java.io.Serializable;

@Entity
@Table(name="participants")
public class Participants extends BaseObject implements Serializable {
	/*Default Serial UID*/
	private static final long serialVersionUID = 1L;
    private Long sid;
    private Dog dog;
    private Event event;
    private boolean absent;
    private Long dorsal;
    private boolean heat;
    private Set<Roundresults> roundresultses = new HashSet<Roundresults>(0);

    @Id @GeneratedValue(strategy = GenerationType.AUTO)     @Column(name="sid", unique=true, nullable=false)    
    public Long getSid() {
        return this.sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_dog", nullable=false)
    public Dog getDog() {
        return this.dog;
    }
    
    public void setDog(Dog dog) {
        this.dog = dog;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_event", nullable=false)
    public Event getEvent() {
        return this.event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    
    @Column(name="absent", nullable=false)
    public boolean isAbsent() {
        return this.absent;
    }
    
    public void setAbsent(boolean absent) {
        this.absent = absent;
    }
    
    @Column(name="dorsal", nullable=false)
    public Long getDorsal() {
        return this.dorsal;
    }
    
    public void setDorsal(Long dorsal) {
        this.dorsal = dorsal;
    }
    
    @Column(name="heat", nullable=false)
    public boolean isHeat() {
        return this.heat;
    }
    
    public void setHeat(boolean heat) {
        this.heat = heat;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="participants")
    public Set<Roundresults> getRoundresultses() {
        return this.roundresultses;
    }
    
    public void setRoundresultses(Set<Roundresults> roundresultses) {
        this.roundresultses = roundresultses;
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

        Participants pojo = (Participants) o;

        if (dog != null ? !dog.equals(pojo.dog) : pojo.dog != null) return false;
        if (event != null ? !event.equals(pojo.event) : pojo.event != null) return false;
        if (absent != pojo.absent) return false;
        if (dorsal != null ? !dorsal.equals(pojo.dorsal) : pojo.dorsal != null) return false;
        if (heat != pojo.heat) return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result = 0;
	        result = (dog != null ? dog.hashCode() : 0);
	        result = 31 * result + (event != null ? event.hashCode() : 0);
	        result = 31 * result + (absent ? 1 : 0);
	        result = 31 * result + (dorsal != null ? dorsal.hashCode() : 0);
	        result = 31 * result + (heat ? 1 : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("sid").append("='").append(getSid()).append("', ");
        
        
        sb.append("absent").append("='").append(isAbsent()).append("', ");
        sb.append("dorsal").append("='").append(getDorsal()).append("', ");
        sb.append("heat").append("='").append(isHeat()).append("', ");
        
        sb.append("]");
      
        return sb.toString();
    }

}
