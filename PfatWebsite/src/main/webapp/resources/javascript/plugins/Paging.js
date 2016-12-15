/**
 * 自定义分页组件
 */
Ext.define('Ext.plugins.Paging', {
	extend: 'Ext.toolbar.Paging',
	alias: 'widget.paging',
	pageSize: 25,
	inputItemWidth: 60,
	displayInfo: true,
	displayMsg: '当前显示 {0} - {1} &nbsp;&nbsp; 共有{2}条记录',
	emptyMsg: "没有相关信息",
	refreshText: "刷新"
})