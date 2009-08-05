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
@Table(name="event")
public class Event extends BaseObject implements Serializable {
	/*Default Serial UID*/
	private static final long serialVersionUID = 1L;
    private Long sid;
    private Judge judge;
    private Eventtype eventtype;
    private String description;
    private Date endDate;
    private Date endenrollment;
    private String judgeassistant;
    private String judgefloor1;
    private String judgefloor2;
    private String judgefloor3;
    private String judgefloor4;
    private String judgeparticipant;
    private String name;
    private String secretary1;
    private String secretary2;
    private Date startDate;
    private String timekeeper1;
    private String timekeeper2;
    private Set<Round> rounds = new HashSet<Round>(0);
    private Set<Participants> participantses = new HashSet<Participants>(0);

    @Id @GeneratedValue(strategy = GenerationType.AUTO)     @Column(name="sid", unique=true, nullable=false)    
    public Long getSid() {
        return this.sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_judge", nullable=false)
    public Judge getJudge() {
        return this.judge;
    }
    
    public void setJudge(Judge judge) {
        this.judge = judge;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_type", nullable=false)
    public Eventtype getEventtype() {
        return this.eventtype;
    }
    
    public void setEventtype(Eventtype eventtype) {
        this.eventtype = eventtype;
    }
    
    @Column(name="description", length=400)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="endDate", nullable=false, length=0)
    public Date getEndDate() {
        return this.endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="endenrollment", length=0)
    public Date getEndenrollment() {
        return this.endenrollment;
    }
    
    public void setEndenrollment(Date endenrollment) {
        this.endenrollment = endenrollment;
    }
    
    @Column(name="judgeassistant", length=200)
    public String getJudgeassistant() {
        return this.judgeassistant;
    }
    
    public void setJudgeassistant(String judgeassistant) {
        this.judgeassistant = judgeassistant;
    }
    
    @Column(name="judgefloor1", length=200)
    public String getJudgefloor1() {
        return this.judgefloor1;
    }
    
    public void setJudgefloor1(String judgefloor1) {
        this.judgefloor1 = judgefloor1;
    }
    
    @Column(name="judgefloor2", length=200)
    public String getJudgefloor2() {
        return this.judgefloor2;
    }
    
    public void setJudgefloor2(String judgefloor2) {
        this.judgefloor2 = judgefloor2;
    }
    
    @Column(name="judgefloor3", length=200)
    public String getJudgefloor3() {
        return this.judgefloor3;
    }
    
    public void setJudgefloor3(String judgefloor3) {
        this.judgefloor3 = judgefloor3;
    }
    
    @Column(name="judgefloor4", length=200)
    public String getJudgefloor4() {
        return this.judgefloor4;
    }
    
    public void setJudgefloor4(String judgefloor4) {
        this.judgefloor4 = judgefloor4;
    }
    
    @Column(name="judgeparticipant", length=200)
    public String getJudgeparticipant() {
        return this.judgeparticipant;
    }
    
    public void setJudgeparticipant(String judgeparticipant) {
        this.judgeparticipant = judgeparticipant;
    }
    
    @Column(name="name", length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="secretary1", length=200)
    public String getSecretary1() {
        return this.secretary1;
    }
    
    public void setSecretary1(String secretary1) {
        this.secretary1 = secretary1;
    }
    
    @Column(name="secretary2", length=200)
    public String getSecretary2() {
        return this.secretary2;
    }
    
    public void setSecretary2(String secretary2) {
        this.secretary2 = secretary2;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="startDate", nullable=false, length=0)
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    @Column(name="timekeeper1", length=200)
    public String getTimekeeper1() {
        return this.timekeeper1;
    }
    
    public void setTimekeeper1(String timekeeper1) {
        this.timekeeper1 = timekeeper1;
    }
    
    @Column(name="timekeeper2", length=200)
    public String getTimekeeper2() {
        return this.timekeeper2;
    }
    
    public void setTimekeeper2(String timekeeper2) {
        this.timekeeper2 = timekeeper2;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="event")
    public Set<Round> getRounds() {
        return this.rounds;
    }
    
    public void setRounds(Set<Round> rounds) {
        this.rounds = rounds;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="event")
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
        if (o == null || getClass() != o.getClass()) return false;

        Event pojo = (Event) o;

        if (judge != null ? !judge.equals(pojo.judge) : pojo.judge != null) return false;
        if (eventtype != null ? !eventtype.equals(pojo.eventtype) : pojo.eventtype != null) return false;
        if (description != null ? !description.equals(pojo.description) : pojo.description != null) return false;
        if (endDate != null ? !endDate.equals(pojo.endDate) : pojo.endDate != null) return false;
        if (endenrollment != null ? !endenrollment.equals(pojo.endenrollment) : pojo.endenrollment != null) return false;
        if (judgeassistant != null ? !judgeassistant.equals(pojo.judgeassistant) : pojo.judgeassistant != null) return false;
        if (judgefloor1 != null ? !judgefloor1.equals(pojo.judgefloor1) : pojo.judgefloor1 != null) return false;
        if (judgefloor2 != null ? !judgefloor2.equals(pojo.judgefloor2) : pojo.judgefloor2 != null) return false;
        if (judgefloor3 != null ? !judgefloor3.equals(pojo.judgefloor3) : pojo.judgefloor3 != null) return false;
        if (judgefloor4 != null ? !judgefloor4.equals(pojo.judgefloor4) : pojo.judgefloor4 != null) return false;
        if (judgeparticipant != null ? !judgeparticipant.equals(pojo.judgeparticipant) : pojo.judgeparticipant != null) return false;
        if (name != null ? !name.equals(pojo.name) : pojo.name != null) return false;
        if (secretary1 != null ? !secretary1.equals(pojo.secretary1) : pojo.secretary1 != null) return false;
        if (secretary2 != null ? !secretary2.equals(pojo.secretary2) : pojo.secretary2 != null) return false;
        if (startDate != null ? !startDate.equals(pojo.startDate) : pojo.startDate != null) return false;
        if (timekeeper1 != null ? !timekeeper1.equals(pojo.timekeeper1) : pojo.timekeeper1 != null) return false;
        if (timekeeper2 != null ? !timekeeper2.equals(pojo.timekeeper2) : pojo.timekeeper2 != null) return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result = 0;
	        result = (judge != null ? judge.hashCode() : 0);
	        result = 31 * result + (eventtype != null ? eventtype.hashCode() : 0);
	        result = 31 * result + (description != null ? description.hashCode() : 0);
	        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
	        result = 31 * result + (endenrollment != null ? endenrollment.hashCode() : 0);
	        result = 31 * result + (judgeassistant != null ? judgeassistant.hashCode() : 0);
	        result = 31 * result + (judgefloor1 != null ? judgefloor1.hashCode() : 0);
	        result = 31 * result + (judgefloor2 != null ? judgefloor2.hashCode() : 0);
	        result = 31 * result + (judgefloor3 != null ? judgefloor3.hashCode() : 0);
	        result = 31 * result + (judgefloor4 != null ? judgefloor4.hashCode() : 0);
	        result = 31 * result + (judgeparticipant != null ? judgeparticipant.hashCode() : 0);
	        result = 31 * result + (name != null ? name.hashCode() : 0);
	        result = 31 * result + (secretary1 != null ? secretary1.hashCode() : 0);
	        result = 31 * result + (secretary2 != null ? secretary2.hashCode() : 0);
	        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
	        result = 31 * result + (timekeeper1 != null ? timekeeper1.hashCode() : 0);
	        result = 31 * result + (timekeeper2 != null ? timekeeper2.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("sid").append("='").append(getSid()).append("', ");
        
        
        sb.append("description").append("='").append(getDescription()).append("', ");
        sb.append("endDate").append("='").append(getEndDate()).append("', ");
        sb.append("endenrollment").append("='").append(getEndenrollment()).append("', ");
        sb.append("judgeassistant").append("='").append(getJudgeassistant()).append("', ");
        sb.append("judgefloor1").append("='").append(getJudgefloor1()).append("', ");
        sb.append("judgefloor2").append("='").append(getJudgefloor2()).append("', ");
        sb.append("judgefloor3").append("='").append(getJudgefloor3()).append("', ");
        sb.append("judgefloor4").append("='").append(getJudgefloor4()).append("', ");
        sb.append("judgeparticipant").append("='").append(getJudgeparticipant()).append("', ");
        sb.append("name").append("='").append(getName()).append("', ");
        sb.append("secretary1").append("='").append(getSecretary1()).append("', ");
        sb.append("secretary2").append("='").append(getSecretary2()).append("', ");
        sb.append("startDate").append("='").append(getStartDate()).append("', ");
        sb.append("timekeeper1").append("='").append(getTimekeeper1()).append("', ");
        sb.append("timekeeper2").append("='").append(getTimekeeper2()).append("', ");
        
        
        sb.append("]");
      
        return sb.toString();
    }

}
