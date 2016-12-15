package cn.fxtech.pfatwebsite.models;

import javax.persistence.Table;

/**
 * 系统菜单和菜单项对象关系
 * 
 * @author FXStudio.Ajaxfan
 */
@Table(name = "os_menu_menuitem")
public class OSmenumenuitem {
	private Integer menuid;
	private Integer itemid;

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public Integer getItemid() {
		return itemid;
	}

	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
}
