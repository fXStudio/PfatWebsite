package cn.fxtech.pfatwebsite.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 带有Check状态的树节点
 *
 * @author Ajaxfan
 */
@JsonInclude(value = Include.NON_NULL)
public class CheckedNode extends BaseNode {
	/** 节点是否被选中 */
	private boolean checked;

	@Override
	public String getCls() {
		return checked ? "complete" : null;
	}

	@Override
	public boolean isLeaf() {
		return children.size() == 0;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
