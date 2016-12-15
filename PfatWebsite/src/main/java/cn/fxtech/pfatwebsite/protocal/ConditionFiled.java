package cn.fxtech.pfatwebsite.protocal;

/**
 * 数据过滤条件字段
 * 
 * @author FXStudio.Ajaxfan
 */
public class ConditionFiled {
	/** 系统主键 */
	private String sysid;
	/** 用户名 */
	private String username;
	/** 密码 */
	private String password;
	/** 开始页码 */
	private int start;
	/** 显示数量 */
	private int limit;
	/** 登陆用户 */
	private String loginUser = "";

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
}
