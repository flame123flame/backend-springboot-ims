
package th.go.excise.ims.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_DEPACC_MAS")
public class ExciseDepaccMas extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 772379979926738328L;
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCISE_DEPACC_MAS_GEN")
//	@SequenceGenerator(name = "EXCISE_DEPACC_MAS_GEN", sequenceName = "EXCISE_DEPACC_MAS_SEQ", allocationSize = 1)
	@Column(name = "GF_DEPOSIT_CODE")
	private String gfDepositCode;
	@Column(name = "GF_DEPOSIT_NAME")
	private String gfDepositName;

	public String getGfDepositCode() {
		return gfDepositCode;
	}

	public void setGfDepositCode(String gfDepositCode) {
		this.gfDepositCode = gfDepositCode;
	}

	public String getGfDepositName() {
		return gfDepositName;
	}

	public void setGfDepositName(String gfDepositName) {
		this.gfDepositName = gfDepositName;
	}

}
