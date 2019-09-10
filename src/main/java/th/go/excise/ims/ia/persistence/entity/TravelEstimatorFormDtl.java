
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TRAVEL_ESTIMATOR_FORM_DTL")
public class TravelEstimatorFormDtl
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAVEL_ESTIMATOR_FORM_DTL_GEN")
    @SequenceGenerator(name = "TRAVEL_ESTIMATOR_FORM_DTL_GEN", sequenceName = "TRAVEL_ESTIMATOR_FORM_DTL_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "ID_DTL")
    private BigDecimal idDtl;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "GRADE")
    private String grade;
    @Column(name = "PERMISSION_DATE")
    private String permissionDate;
    @Column(name = "WRITE_DATE")
    private String writeDate;
    @Column(name = "DEPARTURE_FROM")
    private String departureFrom;
    @Column(name = "DEPARTURE_DATE")
    private String departureDate;
    @Column(name = "RETURN_DATE")
    private String returnDate;
    @Column(name = "PASSAGE")
    private BigDecimal passage;
    @Column(name = "OTHER_EXPENSES")
    private BigDecimal otherExpenses;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "DEPARTURE_TO")
    private String departureTo;
    @Column(name = "NUMBER_DATE_ALLOWANCE")
    private BigDecimal numberDateAllowance;
    @Column(name = "NUMBER_HOURS_ALLOWANCE")
    private BigDecimal numberHoursAllowance;
    @Column(name = "ALLOWANCE_R")
    private BigDecimal allowanceR;
    @Column(name = "ALLOWANCE_TOTAL")
    private BigDecimal allowanceTotal;
    @Column(name = "NUMBER_DATE_ROOST")
    private BigDecimal numberDateRoost;
    @Column(name = "ROOST_R")
    private BigDecimal roostR;
    @Column(name = "ROOST_TOTAL")
    private BigDecimal roostTotal;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getIdDtl() {
        return idDtl;
    }

    public void setIdDtl(BigDecimal idDtl) {
        this.idDtl = idDtl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPermissionDate() {
        return permissionDate;
    }

    public void setPermissionDate(String permissionDate) {
        this.permissionDate = permissionDate;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }

    public String getDepartureFrom() {
        return departureFrom;
    }

    public void setDepartureFrom(String departureFrom) {
        this.departureFrom = departureFrom;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getPassage() {
        return passage;
    }

    public void setPassage(BigDecimal passage) {
        this.passage = passage;
    }

    public BigDecimal getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(BigDecimal otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDepartureTo() {
        return departureTo;
    }

    public void setDepartureTo(String departureTo) {
        this.departureTo = departureTo;
    }

    public BigDecimal getNumberDateAllowance() {
        return numberDateAllowance;
    }

    public void setNumberDateAllowance(BigDecimal numberDateAllowance) {
        this.numberDateAllowance = numberDateAllowance;
    }

    public BigDecimal getNumberHoursAllowance() {
        return numberHoursAllowance;
    }

    public void setNumberHoursAllowance(BigDecimal numberHoursAllowance) {
        this.numberHoursAllowance = numberHoursAllowance;
    }

    public BigDecimal getAllowanceR() {
        return allowanceR;
    }

    public void setAllowanceR(BigDecimal allowanceR) {
        this.allowanceR = allowanceR;
    }

    public BigDecimal getAllowanceTotal() {
        return allowanceTotal;
    }

    public void setAllowanceTotal(BigDecimal allowanceTotal) {
        this.allowanceTotal = allowanceTotal;
    }

    public BigDecimal getNumberDateRoost() {
        return numberDateRoost;
    }

    public void setNumberDateRoost(BigDecimal numberDateRoost) {
        this.numberDateRoost = numberDateRoost;
    }

    public BigDecimal getRoostR() {
        return roostR;
    }

    public void setRoostR(BigDecimal roostR) {
        this.roostR = roostR;
    }

    public BigDecimal getRoostTotal() {
        return roostTotal;
    }

    public void setRoostTotal(BigDecimal roostTotal) {
        this.roostTotal = roostTotal;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
