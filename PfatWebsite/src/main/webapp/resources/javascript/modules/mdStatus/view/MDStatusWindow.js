Ext.define('MDStatusModule.view.MDStatusWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.mdstatuswindow',
	
	requires: ["MDStatusModule.view.MDStatusForm"],
	
	title: '维护状态信息',
    iconCls: 'feed-icon',
    resizable: false,
    modal: true,
    width: 360,
    y: 100,
    closeAction: 'hide',
    items: {xtype: 'mdstatusform'},
    buttons: [{
        text: '提交',
        action: 'submit'
    }, {
        text: '取消',
        action: 'cancel'
    }]
});