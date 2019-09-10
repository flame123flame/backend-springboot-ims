
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
@Table(name = "IA_CHART_AND_INC")
public class IaChartAndInc
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2531898420761382556L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_CHART_AND_INC_GEN")
    @SequenceGenerator(name = "IA_CHART_AND_INC_GEN", sequenceName = "IA_CHART_AND_INC_SEQ", allocationSize = 1)
    @Column(name = "CHART_AND_INC_ID")
    private Long chartAndIncId;
    @Column(name = "INC_CODE")
    private String incCode;
    @Column(name = "COA_CODE")
    private String coaCode;

    public Long getChartAndIncId() {
        return chartAndIncId;
    }

    public void setChartAndIncId(Long chartAndIncId) {
        this.chartAndIncId = chartAndIncId;
    }

    public String getIncCode() {
        return incCode;
    }

    public void setIncCode(String incCode) {
        this.incCode = incCode;
    }

    public String getCoaCode() {
        return coaCode;
    }

    public void setCoaCode(String coaCode) {
        this.coaCode = coaCode;
    }

}
