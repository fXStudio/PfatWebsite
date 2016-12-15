package cn.fxtech.pfatwebsite.tree;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 普通的树节点
 *
 * @author Ajaxfan
 */
@JsonInclude(value = Include.NON_NULL)
public class NormalNode extends BaseNode {
}
