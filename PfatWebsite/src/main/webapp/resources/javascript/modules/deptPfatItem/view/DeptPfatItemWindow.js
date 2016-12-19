Ext.define('DeptPfatItemModule.view.DeptPfatItemWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.deptpfatitemwindow',
	
	requires: ["DeptPfatItemModule.view.DeptPfatItemForm"],
	
	title: '维护菜单项',
    iconCls: 'feed-icon',
    resizable: false,
    modal: true,
    width: 360,
    y: 100,
    closeAction: 'hide',
    items: {xtype: 'deptpfatitemform'},
    buttons: [{
        text: '提交',
        action: 'submit'
    }, {
        text: '取消',
        action: 'cancel'
    }]
});