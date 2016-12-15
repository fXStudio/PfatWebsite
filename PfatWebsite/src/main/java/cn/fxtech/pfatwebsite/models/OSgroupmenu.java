package cn.fxtech.pfatwebsite.models;

import javax.persistence.Table;

/**
 * 用户组、菜单关系
 * 
 * @author FXStudio.Ajaxfan
 */
@Table(name = "os_groupmenu")
public class OSgroupmenu {
	private Integer usergroupid;
	private Integer menuid;

	public Integer getUsergroupid() {
		return usergroupid;
	}

	public void setUsergroupid(Integer usergroupid) {
		this.usergroupid = usergroupid;
	}

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
}
