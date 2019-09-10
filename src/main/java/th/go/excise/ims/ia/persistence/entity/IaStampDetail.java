
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_STAMP_DETAIL")
public class IaStampDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4191411110314036315L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_STAMP_DETAIL_GEN")
	@SequenceGenerator(name = "IA_STAMP_DETAIL_GEN", sequenceName = "IA_STAMP_DETAIL_SEQ", allocationSize = 1)
	@Column(name = "WORK_SHEET_DETAIL_ID")
	private BigDecimal workSheetDetailId;
	@Column(name = "DATE_OF_PAY")
	private Date dateOfPay;
	@Column(name = "STATUS")
	private String status;
	@Column(name = "BOOK_NUMBER_WITHDRAW_STAMP")
	private String bookNumberWithdrawStamp;
	@Column(name = "DATE_WITHDRAW_STAMP")
	private Date dateWithdrawStamp;
	@Column(name = "BOOK_NUMBER_DELIVER_STAMP")
	private String bookNumberDeliverStamp;
	@Column(name = "DATE_DELIVER_STAMP")
	private Date dateDeliverStamp;
	@Column(name = "FIVE_PART_NUMBER")
	private String fivePartNumber;
	@Column(name = "FIVE_PART_DATE")
	private Date fivePartDate;
	@Column(name = "STAMP_CHECK_DATE")
	private Date stampCheckDate;
	@Column(name = "STAMP_CHECKER")
	private String stampChecker;
	@Column(name = "STAMP_TYPE")
	private String stampType;
	@Column(name = "STAMP_BRAND")
	private String stampBrand;
	@Column(name = "NUMBER_OF_BOOK")
	private BigDecimal numberOfBook;
	@Column(name = "NUMBER_OF_STAMP")
	private BigDecimal numberOfStamp;
	@Column(name = "VALUE_OF_STAMP_PRINTED")
	private BigDecimal valueOfStampPrinted;
	@Column(name = "SUM_OF_VALUE")
	private BigDecimal sumOfValue;
	@Column(name = "SERIAL_NUMBER")
	private String serialNumber;
	@Column(name = "TAX_STAMP")
	private BigDecimal taxStamp;
	@Column(name = "STAMP_CODE_START")
	private String stampCodeStart;
	@Column(name = "STAMP_CODE_END")
	private String stampCodeEnd;
	@Column(name = "NOTE")
	private String note;
	@Column(name = "FILE_NAME")
	private String fileName;
	@Column(name = "STAMP_TYPE_ID")
	private BigDecimal stampTypeId;
	@Column(name = "STAMP_BRAND_ID")
	private BigDecimal stampBrandId;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "OFFICE_DESC")
	private String officeDesc;
	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;
	@Column(name = "STAMP_CHECKER_2")
	private String stampChecker2;
	@Column(name = "STAMP_CHECKER_3")
	private String stampChecker3;

	public BigDecimal getWorkSheetDetailId() {
		return workSheetDetailId;
	}

	public void setWorkSheetDetailId(BigDecimal workSheetDetailId) {
		this.workSheetDetailId = workSheetDetailId;
	}

	public Date getDateOfPay() {
		return dateOfPay;
	}

	public void setDateOfPay(Date dateOfPay) {
		this.dateOfPay = dateOfPay;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBookNumberWithdrawStamp() {
		return bookNumberWithdrawStamp;
	}

	public void setBookNumberWithdrawStamp(String bookNumberWithdrawStamp) {
		this.bookNumberWithdrawStamp = bookNumberWithdrawStamp;
	}

	public Date getDateWithdrawStamp() {
		return dateWithdrawStamp;
	}

	public void setDateWithdrawStamp(Date dateWithdrawStamp) {
		this.dateWithdrawStamp = dateWithdrawStamp;
	}

	public String getBookNumberDeliverStamp() {
		return bookNumberDeliverStamp;
	}

	public void setBookNumberDeliverStamp(String bookNumberDeliverStamp) {
		this.bookNumberDeliverStamp = bookNumberDeliverStamp;
	}

	public Date getDateDeliverStamp() {
		return dateDeliverStamp;
	}

	public void setDateDeliverStamp(Date dateDeliverStamp) {
		this.dateDeliverStamp = dateDeliverStamp;
	}

	public String getFivePartNumber() {
		return fivePartNumber;
	}

	public void setFivePartNumber(String fivePartNumber) {
		this.fivePartNumber = fivePartNumber;
	}

	public Date getFivePartDate() {
		return fivePartDate;
	}

	public void setFivePartDate(Date fivePartDate) {
		this.fivePartDate = fivePartDate;
	}

	public Date getStampCheckDate() {
		return stampCheckDate;
	}

	public void setStampCheckDate(Date stampCheckDate) {
		this.stampCheckDate = stampCheckDate;
	}

	public String getStampChecker() {
		return stampChecker;
	}

	public void setStampChecker(String stampChecker) {
		this.stampChecker = stampChecker;
	}

	public String getStampType() {
		return stampType;
	}

	public void setStampType(String stampType) {
		this.stampType = stampType;
	}

	public String getStampBrand() {
		return stampBrand;
	}

	public void setStampBrand(String stampBrand) {
		this.stampBrand = stampBrand;
	}

	public BigDecimal getNumberOfBook() {
		return numberOfBook;
	}

	public void setNumberOfBook(BigDecimal numberOfBook) {
		this.numberOfBook = numberOfBook;
	}

	public BigDecimal getNumberOfStamp() {
		return numberOfStamp;
	}

	public void setNumberOfStamp(BigDecimal numberOfStamp) {
		this.numberOfStamp = numberOfStamp;
	}

	public BigDecimal getValueOfStampPrinted() {
		return valueOfStampPrinted;
	}

	public void setValueOfStampPrinted(BigDecimal valueOfStampPrinted) {
		this.valueOfStampPrinted = valueOfStampPrinted;
	}

	public BigDecimal getSumOfValue() {
		return sumOfValue;
	}

	public void setSumOfValue(BigDecimal sumOfValue) {
		this.sumOfValue = sumOfValue;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public BigDecimal getTaxStamp() {
		return taxStamp;
	}

	public void setTaxStamp(BigDecimal taxStamp) {
		this.taxStamp = taxStamp;
	}

	public String getStampCodeStart() {
		return stampCodeStart;
	}

	public void setStampCodeStart(String stampCodeStart) {
		this.stampCodeStart = stampCodeStart;
	}

	public String getStampCodeEnd() {
		return stampCodeEnd;
	}

	public void setStampCodeEnd(String stampCodeEnd) {
		this.stampCodeEnd = stampCodeEnd;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public BigDecimal getStampTypeId() {
		return stampTypeId;
	}

	public void setStampTypeId(BigDecimal stampTypeId) {
		this.stampTypeId = stampTypeId;
	}

	public BigDecimal getStampBrandId() {
		return stampBrandId;
	}

	public void setStampBrandId(BigDecimal stampBrandId) {
		this.stampBrandId = stampBrandId;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfficeDesc() {
		return officeDesc;
	}

	public void setOfficeDesc(String officeDesc) {
		this.officeDesc = officeDesc;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getStampChecker2() {
		return stampChecker2;
	}

	public void setStampChecker2(String stampChecker2) {
		this.stampChecker2 = stampChecker2;
	}

	public String getStampChecker3() {
		return stampChecker3;
	}

	public void setStampChecker3(String stampChecker3) {
		this.stampChecker3 = stampChecker3;
	}

}
