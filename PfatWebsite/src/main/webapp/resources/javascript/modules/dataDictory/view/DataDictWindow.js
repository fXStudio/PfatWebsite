Ext.define('DataDictModule.view.DataDictWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.datadictwindow',
	
	requires: ["DataDictModule.view.DataDictForm"],
	
	title: '维护字典项',
    iconCls: 'feed-icon',
    resizable: false,
    modal: true,
    width: 400,
    y: 100,
    closeAction: 'hide',
    items: {xtype: 'datadictform'},
    buttons: [{
        text: '提交',
        action: 'submit'
    }, {
        text: '取消',
        action: 'cancel'
    }]
});