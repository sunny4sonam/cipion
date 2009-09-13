package org.palaciego.cipion.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="round")
public class Round extends BaseObject implements Serializable {
	/*Default Serial UID*/
	private static final long serialVersionUID = 1L;
    private Long sid;
    private Event event;
    private String description;
    private Long number;
    private Grade grade;
    private Category category;
    private int trs;
	private int trm;
	private float velocity;
	private float distance;
	
    private Set<Roundresults> roundresultses = new HashSet<Roundresults>(0);

    @Id @GeneratedValue(strategy = GenerationType.AUTO)     @Column(name="sid", unique=true, nullable=false)    
    public Long getSid() {
        return this.sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_event", nullable=false)
    public Event getEvent() {
        return this.event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }
    
    @Column(name="description", nullable=true, length=400)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="number", nullable=false)
    public Long getNumber() {
        return this.number;
    }
    
    public void setNumber(Long number) {
        this.number = number;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="round")
    public Set<Roundresults> getRoundresultses() {
        return this.roundresultses;
    }
    
    public void setRoundresultses(Set<Roundresults> roundresultses) {
        this.roundresultses = roundresultses;
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
    @JoinColumn(name="fk_grade", nullable=false)
    public Grade getGrade() {
        return this.grade;
    }

    /**
     * setter
     * @param grade
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    /**
     * getter
     * @return
     */
    @Column(name="velocity", nullable=false)
	public float getVelocity() {
		return velocity;
	}

    /**
     * setter
     * @param velocity
     */
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	/**
	 * getter
	 * @return
	 */
    @Column(name="distance", nullable=false)
	public float getDistance() {
		return distance;
	}

    /**
     * setter
     * @param distance
     */
	public void setDistance(float distance) {
		this.distance = distance;
	}

    /**
     * getter.
     * @return
     */
    @Column(name="trs", nullable=false)
    public int getTrs() {
		return this.trs;
	}

    /**
     * setter
     * @param trs
     */
	public void setTrs(int trs) {
		this.trs = trs;
	}

	/**
	 * getter
	 * @return
	 */
    @Column(name="trm", nullable=false)
	public int getTrm() {
		return this.trm;
	}

	/**
	 * setter
	 * @param trm
	 */
	public void setTrm(int trm) {
		this.trm = trm;
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

        Round pojo = (Round) o;

        if (event != null ? !event.equals(pojo.event) : pojo.event != null) return false;
        if (description != null ? !description.equals(pojo.description) : pojo.description != null) return false;
        if (number != null ? !number.equals(pojo.number) : pojo.number != null) return false;
        if (category != null ? !category.equals(pojo.category) : pojo.category != null) return false;
        if (grade != null ? !grade.equals(pojo.grade) : pojo.grade!= null) return false;
        if (trm != pojo.trm) return false;
        if (trs != pojo.trs) return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result = 0;
	        result = (event != null ? event.hashCode() : 0);
	        result = 31 * result + (description != null ? description.hashCode() : 0);
	        result = 31 * result + (number != null ? number.hashCode() : 0);
	        result = 31 * result + (category != null ? category.hashCode() : 0);
	        result = 31 * result + (grade != null ? grade.hashCode() : 0);
	        result = 31 * result + trm;
	        result = 31 * result + trs;

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("sid").append("='").append(getSid()).append("', ");
        
        sb.append("description").append("='").append(getDescription()).append("', ");
        sb.append("number").append("='").append(getNumber()).append("', ");
        sb.append("category").append("='").append(getCategory()).append("'");
        sb.append("grade").append("='").append(getGrade()).append("'");
        sb.append("trm").append("='").append(getTrm()).append("'");
        sb.append("trs").append("='").append(getTrs()).append("'");
        
        sb.append("]");
      
        return sb.toString();
    }

}
