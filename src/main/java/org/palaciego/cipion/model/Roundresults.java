package org.palaciego.cipion.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="roundresults")
public class Roundresults extends BaseObject implements Serializable {
	/*Default Serial UID*/
	private static final long serialVersionUID = 1L;
    private Long sid;
    private Participants participants;
    private Round round;
    
    private boolean absent;
    private boolean eliminated;
    private Long fouls;
    private Long reuses;
    private Long startorder;
    private float result;
    private float time;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)     @Column(name="sid", unique=true, nullable=false)    
    public Long getSid() {
        return this.sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_participants", nullable=false)
    public Participants getParticipants() {
        return this.participants;
    }
    
    public void setParticipants(Participants participants) {
        this.participants = participants;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_round", nullable=false)
    public Round getRound() {
        return this.round;
    }
    
    public void setRound(Round round) {
        this.round = round;
    }

    
    @Column(name="absent", nullable=false)
    public boolean isAbsent() {
        return this.absent;
    }
    
    public void setAbsent(boolean absent) {
        this.absent = absent;
    }
    
    @Column(name="eliminated", nullable=false)
    public boolean isEliminated() {
        return this.eliminated;
    }
    
    public void setEliminated(boolean eliminated) {
        this.eliminated = eliminated;
    }
    
    @Column(name="fouls", nullable=false)
    public Long getFouls() {
        return this.fouls;
    }
    
    public void setFouls(Long fouls) {
        this.fouls = fouls;
    }
    
    @Column(name="reuses", nullable=false)
    public Long getReuses() {
        return this.reuses;
    }
    
    public void setReuses(Long reuses) {
        this.reuses = reuses;
    }
    
    @Column(name="startorder", nullable=false)
    public Long getStartorder() {
        return this.startorder;
    }
    
    public void setStartorder(Long startorder) {
        this.startorder = startorder;
    }
    
    @Column(name="result", nullable=false, precision=12, scale=0)
    public float getResult() {
        return this.result;
    }
    
    public void setResult(float result) {
        this.result = result;
    }
    
    @Column(name="time", nullable=false, precision=12, scale=0)
    public float getTime() {
        return this.time;
    }
    
    public void setTime(float time) {
        this.time = time;
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
        if (o == null ) return false;

        Roundresults pojo = (Roundresults) o;

        if (participants != null ? !participants.equals(pojo.participants) : pojo.participants != null) return false;
        if (round != null ? !round.equals(pojo.round) : pojo.round != null) return false;
        if (absent != pojo.absent) return false;
        if (eliminated != pojo.eliminated) return false;
        if (fouls != null ? !fouls.equals(pojo.fouls) : pojo.fouls != null) return false;
        if (reuses != null ? !reuses.equals(pojo.reuses) : pojo.reuses != null) return false;
        if (startorder != null ? !startorder.equals(pojo.startorder) : pojo.startorder != null) return false;
        if (result != pojo.result) return false;
        if (time != pojo.time) return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result = 0;
	        result = (participants != null ? participants.hashCode() : 0);
	        result = 31 * result + (round != null ? round.hashCode() : 0);
	        result = 31 * result + (absent ? 1 : 0);
	        result = 31 * result + (eliminated ? 1 : 0);
	        result = 31 * result + (fouls != null ? fouls.hashCode() : 0);
	        result = 31 * result + (reuses != null ? reuses.hashCode() : 0);
	        result = 31 * result + (startorder != null ? startorder.hashCode() : 0);
	        result = 31 * result + new Double(result).hashCode();
	        result = 31 * result + new Double(time).hashCode();

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("sid").append("='").append(getSid()).append("', ");
        
        
        sb.append("absent").append("='").append(isAbsent()).append("', ");
        sb.append("eliminated").append("='").append(isEliminated()).append("', ");
        sb.append("fouls").append("='").append(getFouls()).append("', ");
        sb.append("reuses").append("='").append(getReuses()).append("', ");
        sb.append("startorder").append("='").append(getStartorder()).append("', ");
        sb.append("result").append("='").append(getResult()).append("', ");
        sb.append("time").append("='").append(getTime()).append("', ");
        sb.append("]");
      
        return sb.toString();
    }


}
