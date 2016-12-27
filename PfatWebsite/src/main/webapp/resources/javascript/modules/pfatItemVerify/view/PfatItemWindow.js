Ext.define('PfatItemVerifyModule.view.PfatItemWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.pfatitemwindow',
	
	requires: ["PfatItemVerifyModule.view.PfatItemForm"],
	
	title: '未通过原因',
    iconCls: 'feed-icon',
    resizable: false,
    modal: true,
    width: 400,
    height: 300,
    y: 100,
    closeAction: 'hide',
    items: {
    	xtype: 'pfatitemform'
    },
    buttons: [{
        text: '提交',
        action: 'submit'
    }, {
        text: '取消',
        action: 'cancel'
    }]
});