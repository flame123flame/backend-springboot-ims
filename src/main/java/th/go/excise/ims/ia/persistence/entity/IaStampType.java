
package th.go.excise.ims.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_STAMP_TYPE")
public class IaStampType
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4850261049901639149L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_STAMP_TYPE_GEN")
    @SequenceGenerator(name = "IA_STAMP_TYPE_GEN", sequenceName = "IA_STAMP_TYPE_SEQ", allocationSize = 1)
    @Column(name = "STAMP_TYPE_ID")
    private Long stampTypeId;
    @Column(name = "STAMP_TYPE_NAME")
    private String stampTypeName;

    public Long getStampTypeId() {
        return stampTypeId;
    }

    public void setStampTypeId(Long stampTypeId) {
        this.stampTypeId = stampTypeId;
    }

    public String getStampTypeName() {
        return stampTypeName;
    }

    public void setStampTypeName(String stampTypeName) {
        this.stampTypeName = stampTypeName;
    }

}
