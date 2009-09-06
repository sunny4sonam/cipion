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
@Table(name="settings")
public class Settings extends BaseObject implements Serializable {
	/*Default Serial UID*/
	private static final long serialVersionUID = 1L;
    private Long sid;
    private Country country;
    private Eventtype eventtype;
    private Long maxreuses;
    private Long pointspenaltyabsent;
    private Long pointspenaltyfoul;
    private Long pointspenaltymaxreuses;
    private Long pointspenaltyreuse;
    private Long pointspenaltysecond;
    private byte[] reportlogo;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)     @Column(name="sid", unique=true, nullable=false)    
    public Long getSid() {
        return this.sid;
    }
    
    public void setSid(Long sid) {
        this.sid = sid;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_country")
    public Country getCountry() {
        return this.country;
    }
    
    public void setCountry(Country country) {
        this.country = country;
    }
@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="fk_eventtype", nullable=false)
    public Eventtype getEventtype() {
        return this.eventtype;
    }
    
    public void setEventtype(Eventtype eventtype) {
        this.eventtype = eventtype;
    }
    
    @Column(name="maxreuses", nullable=false)
    public Long getMaxreuses() {
        return this.maxreuses;
    }
    
    public void setMaxreuses(Long maxreuses) {
        this.maxreuses = maxreuses;
    }
    
    @Column(name="pointspenaltyabsent", nullable=false)
    public Long getPointspenaltyabsent() {
        return this.pointspenaltyabsent;
    }
    
    public void setPointspenaltyabsent(Long pointspenaltyabsent) {
        this.pointspenaltyabsent = pointspenaltyabsent;
    }
    
    @Column(name="pointspenaltyfoul", nullable=false)
    public Long getPointspenaltyfoul() {
        return this.pointspenaltyfoul;
    }
    
    public void setPointspenaltyfoul(Long pointspenaltyfoul) {
        this.pointspenaltyfoul = pointspenaltyfoul;
    }
    
    @Column(name="pointspenaltymaxreuses", nullable=false)
    public Long getPointspenaltymaxreuses() {
        return this.pointspenaltymaxreuses;
    }
    
    public void setPointspenaltymaxreuses(Long pointspenaltymaxreuses) {
        this.pointspenaltymaxreuses = pointspenaltymaxreuses;
    }
    
    @Column(name="pointspenaltyreuse", nullable=false)
    public Long getPointspenaltyreuse() {
        return this.pointspenaltyreuse;
    }
    
    public void setPointspenaltyreuse(Long pointspenaltyreuse) {
        this.pointspenaltyreuse = pointspenaltyreuse;
    }
    
    @Column(name="pointspenaltysecond", nullable=false)
    public Long getPointspenaltysecond() {
        return this.pointspenaltysecond;
    }
    
    public void setPointspenaltysecond(Long pointspenaltysecond) {
        this.pointspenaltysecond = pointspenaltysecond;
    }

    @Column(name="reportlogo",length=16777215)
    public byte[] getReportlogo() {
        return this.reportlogo;
    }
    
    public void setReportlogo(byte[] picture) {
        this.reportlogo = picture;
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

        Settings pojo = (Settings) o;

        if (country != null ? !country.equals(pojo.country) : pojo.country != null) return false;
        if (eventtype != null ? !eventtype.equals(pojo.eventtype) : pojo.eventtype != null) return false;
        if (maxreuses != null ? !maxreuses.equals(pojo.maxreuses) : pojo.maxreuses != null) return false;
        if (pointspenaltyabsent != null ? !pointspenaltyabsent.equals(pojo.pointspenaltyabsent) : pojo.pointspenaltyabsent != null) return false;
        if (pointspenaltyfoul != null ? !pointspenaltyfoul.equals(pojo.pointspenaltyfoul) : pojo.pointspenaltyfoul != null) return false;
        if (pointspenaltymaxreuses != null ? !pointspenaltymaxreuses.equals(pojo.pointspenaltymaxreuses) : pojo.pointspenaltymaxreuses != null) return false;
        if (pointspenaltyreuse != null ? !pointspenaltyreuse.equals(pojo.pointspenaltyreuse) : pojo.pointspenaltyreuse != null) return false;
        if (pointspenaltysecond != null ? !pointspenaltysecond.equals(pojo.pointspenaltysecond) : pojo.pointspenaltysecond != null) return false;

        return true;
    }

	@Override
    public int hashCode() {
        int result = 0;
	        result = (country != null ? country.hashCode() : 0);
	        result = 31 * result + (eventtype != null ? eventtype.hashCode() : 0);
	        result = 31 * result + (maxreuses != null ? maxreuses.hashCode() : 0);
	        result = 31 * result + (pointspenaltyabsent != null ? pointspenaltyabsent.hashCode() : 0);
	        result = 31 * result + (pointspenaltyfoul != null ? pointspenaltyfoul.hashCode() : 0);
	        result = 31 * result + (pointspenaltymaxreuses != null ? pointspenaltymaxreuses.hashCode() : 0);
	        result = 31 * result + (pointspenaltyreuse != null ? pointspenaltyreuse.hashCode() : 0);
	        result = 31 * result + (pointspenaltysecond != null ? pointspenaltysecond.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("sid").append("='").append(getSid()).append("', ");
        
        
        sb.append("maxreuses").append("='").append(getMaxreuses()).append("', ");
        sb.append("pointspenaltyabsent").append("='").append(getPointspenaltyabsent()).append("', ");
        sb.append("pointspenaltyfoul").append("='").append(getPointspenaltyfoul()).append("', ");
        sb.append("pointspenaltymaxreuses").append("='").append(getPointspenaltymaxreuses()).append("', ");
        sb.append("pointspenaltyreuse").append("='").append(getPointspenaltyreuse()).append("', ");
        sb.append("pointspenaltysecond").append("='").append(getPointspenaltysecond()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
