Ext.define('PfatItemModule.view.PfatItemWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.pfatitemwindow',
	
	requires: ["PfatItemModule.view.PfatItemForm"],
	
	title: '考核项目维护',
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