package cn.fxtech.pfatwebsite.models;

import java.sql.Date;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 数据统计
 * 
 * @author Administrator
 */
@Table(name = "pfat_statis_view")
public class STpfatdata {
	private Integer id;
	private String firstCate;
	private String secondCate;
	private String thirdCate;
	private String itemName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date compDate;
	private Integer itemScore;
	private String docName;
	private String deptName;
	private String officeName;
	private String telPhone;
	private String personName;
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstCate() {
		return firstCate;
	}

	public void setFirstCate(String firstCate) {
		this.firstCate = firstCate;
	}

	public String getSecondCate() {
		return secondCate;
	}

	public void setSecondCate(String secondCate) {
		this.secondCate = secondCate;
	}

	public String getThirdCate() {
		return thirdCate;
	}

	public void setThirdCate(String thirdCate) {
		this.thirdCate = thirdCate;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getCompDate() {
		return compDate;
	}

	public void setCompDate(Date compDate) {
		this.compDate = compDate;
	}

	public Integer getItemScore() {
		return itemScore;
	}

	public void setItemScore(Integer itemScore) {
		this.itemScore = itemScore;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
