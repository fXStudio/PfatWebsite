Ext.define('CateManageModule.view.CateManageWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.catemanagewindow',
	
	requires: ["CateManageModule.view.CateManageForm"],
	
	title: '维护分类信息',
    iconCls: 'feed-icon',
    resizable: false,
    modal: true,
    width: 360,
    y: 100,
    closeAction: 'hide',
    items: {xtype: 'catemanageform'},
    buttons: [{
        text: '提交',
        action: 'submit'
    }, {
        text: '取消',
        action: 'cancel'
    }]
});