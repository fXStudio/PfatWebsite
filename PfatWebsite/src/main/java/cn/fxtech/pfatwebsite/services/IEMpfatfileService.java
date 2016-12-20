package cn.fxtech.pfatwebsite.services;

import java.util.List;

import cn.fxtech.pfatwebsite.messages.FeedBackMessage;
import cn.fxtech.pfatwebsite.models.EMpfatfile;

/**
 * 系统菜单项管理
 * 
 * @author FXStudio.Ajaxfan
 */
public interface IEMpfatfileService {
	public FeedBackMessage add(EMpfatfile file);
	public FeedBackMessage del(Integer id);
	public List<EMpfatfile> findRecords(EMpfatfile file) ;
	public EMpfatfile findRecordById(Integer id);
}
