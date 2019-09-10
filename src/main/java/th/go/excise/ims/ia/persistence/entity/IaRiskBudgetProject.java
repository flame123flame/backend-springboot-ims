
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_RISK_BUDGET_PROJECT")
public class IaRiskBudgetProject
    extends BaseEntity
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 255906958828258825L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_BUDGET_PROJECT_GEN")
    @SequenceGenerator(name = "IA_RISK_BUDGET_PROJECT_GEN", sequenceName = "IA_RISK_BUDGET_PROJECT_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "PROJECTID")
    private String projectid;
    @Column(name = "PROJECTYEAR")
    private String projectyear;
    @Column(name = "PROJECTNAME")
    private String projectname;
    @Column(name = "PROJECTTYPECODE")
    private String projecttypecode;
    @Column(name = "PROJECTTYPENAME")
    private String projecttypename;
    @Column(name = "OWNEROFFICEID")
    private String ownerofficeid;
    @Column(name = "OWNEROFFICENAME")
    private String ownerofficename;
    @Column(name = "EXPENSEBUDGETAMOUNTM")
    private String expensebudgetamountm;
    @Column(name = "EXPENSEBUDGETAMOUNTX")
    private String expensebudgetamountx;
    @Column(name = "EXPENSEBUDGETAMOUNTA")
    private String expensebudgetamounta;
    @Column(name = "APPROVEDBUDGETAMOUNT")
    private String approvedbudgetamount;
    
    

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectyear() {
        return projectyear;
    }

    public void setProjectyear(String projectyear) {
        this.projectyear = projectyear;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjecttypecode() {
        return projecttypecode;
    }

    public void setProjecttypecode(String projecttypecode) {
        this.projecttypecode = projecttypecode;
    }

    public String getProjecttypename() {
        return projecttypename;
    }

    public void setProjecttypename(String projecttypename) {
        this.projecttypename = projecttypename;
    }

    public String getOwnerofficeid() {
        return ownerofficeid;
    }

    public void setOwnerofficeid(String ownerofficeid) {
        this.ownerofficeid = ownerofficeid;
    }

    public String getOwnerofficename() {
        return ownerofficename;
    }

    public void setOwnerofficename(String ownerofficename) {
        this.ownerofficename = ownerofficename;
    }

    public String getExpensebudgetamountm() {
        return expensebudgetamountm;
    }

    public void setExpensebudgetamountm(String expensebudgetamountm) {
        this.expensebudgetamountm = expensebudgetamountm;
    }

    public String getExpensebudgetamountx() {
        return expensebudgetamountx;
    }

    public void setExpensebudgetamountx(String expensebudgetamountx) {
        this.expensebudgetamountx = expensebudgetamountx;
    }

    public String getExpensebudgetamounta() {
        return expensebudgetamounta;
    }

    public void setExpensebudgetamounta(String expensebudgetamounta) {
        this.expensebudgetamounta = expensebudgetamounta;
    }

    public String getApprovedbudgetamount() {
        return approvedbudgetamount;
    }

    public void setApprovedbudgetamount(String approvedbudgetamount) {
        this.approvedbudgetamount = approvedbudgetamount;
    }

}
