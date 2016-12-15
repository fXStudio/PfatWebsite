package cn.fxtech.pfatwebsite.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.fxtech.pfatwebsite.tree.inters.INode;

/**
 * 树节点的基本类
 *
 * @author Ajaxfan
 */
abstract class BaseNode implements INode {
	/** 子节点列表 */
	protected List<INode> children = new ArrayList<INode>();

	/** 节点ID */
	protected Integer sn;
	/** 节点文本额 */
	protected String text;
	/** 节点URL */
	protected String url;

	/**
	 * 添加子节点
	 *
	 * @param node
	 */
	@Override
	public void addChild(INode node) {
		children.add(node);
	}

	/**
	 * @return 子节点列表
	 */
	@Override
	public List<INode> getChildren() {
		return Collections.unmodifiableList(children);
	}

	/**
	 * @return 单击展开
	 */
	public boolean isSingleClickExpand() {
		return true;
	}

	/**
	 * @return 叶子节点标记
	 */
	public boolean isLeaf() {
		return children.size() == 0;
	}

	/**
	 * @return 菜单项样式
	 */
	public String getCls() {
		return isLeaf() ? "" : "folder";
	}

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
