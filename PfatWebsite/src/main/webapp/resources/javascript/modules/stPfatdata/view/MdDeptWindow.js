Ext.define('MdDeptModule.view.MdDeptWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.mddeptwindow',
	
	requires: ["MdDeptModule.view.MdDeptForm"],
	
	title: '维护部门',
    iconCls: 'feed-icon',
    resizable: false,
    modal: true,
    width: 360,
    y: 100,
    closeAction: 'hide',
    items: {xtype: 'mddeptform'},
    buttons: [{
        text: '提交',
        action: 'submit'
    }, {
        text: '取消',
        action: 'cancel'
    }]
});