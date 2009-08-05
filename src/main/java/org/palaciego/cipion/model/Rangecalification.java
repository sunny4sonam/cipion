package org.palaciego.cipion.model;

import org.palaciego.cipion.model.BaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;

import java.io.Serializable;

@Entity
@Table(name="rangecalification")
public class Rangecalification extends BaseObject implements Serializable {
	/*Default Serial UID*/
	private static final long serialVersionUID = 1L;
    private Long sid;
    private Calification calification;
    private float frompoint;
    private float topoint;
    private boolean round;

    @Id  @GeneratedValue(strategy = GenerationType.AUTO)     @Column(name="sid", unique=true, nullable=false)    
    public Long getSid() {
        return this.sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_calification", nullable=false)
    public Calification getCalification() {
        return this.calification;
    }
    
    public void setCalification(Calification calification) {
        this.calification = calification;
    }
    
    @Column(name="frompoint", nullable=false, precision=12, scale=0)
    public float getFrompoint() {
        return this.frompoint;
    }
    
    public void setFrompoint(float frompoint) {
        this.frompoint = frompoint;
    }
    
    @Column(name="topoint", nullable=false, precision=12, scale=0)
    public float getTopoint() {
        return this.topoint;
    }
    
    public void setTopoint(float topoint) {
        this.topoint = topoint;
    }
    
    @Column(name="round", nullable=false)
    public boolean isRound() {
        return this.round;
    }
    
    public void setRound(boolean round) {
        this.round = round;
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
        if (o == null || getClass() != o.getClass()) return false;

        Rangecalification pojo = (Rangecalification) o;

        if (calification != null ? !calification.equals(pojo.calification) : pojo.calification != null) return false;
        if (frompoint != pojo.frompoint) return false;
        if (topoint != pojo.topoint) return false;
        if (round != pojo.round) return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result = 0;
	        result = (calification != null ? calification.hashCode() : 0);
	        result = 31 * result + new Double(frompoint).hashCode();
	        result = 31 * result + new Double(topoint).hashCode();
	        result = 31 * result + (round ? 1 : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("sid").append("='").append(getSid()).append("', ");
        
        sb.append("frompoint").append("='").append(getFrompoint()).append("', ");
        sb.append("topoint").append("='").append(getTopoint()).append("', ");
        sb.append("round").append("='").append(isRound()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
