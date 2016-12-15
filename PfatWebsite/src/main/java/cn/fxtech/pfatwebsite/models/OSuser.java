package cn.fxtech.pfatwebsite.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 系统用户
 * 
 * @author FXStudio.Ajaxfan
 */
@Table(name = "os_user")
public class OSuser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "user_name")
	private String username;
	@JsonIgnore
	@Column(name = "pass_word")
	private String password;
	@JsonIgnore
	private Integer deptId;
	@JsonIgnore
	private Integer groupId;
	private String islock;
	private Timestamp created = new Timestamp(System.currentTimeMillis());

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id == null ? 0 : id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getIslock() {
		return islock == null ? "0" : islock;
	}

	public void setIslock(String islock) {
		this.islock = islock == null ? "0" : islock;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}
}
