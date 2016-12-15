Ext.define('LoginModule.view.Window', {
	extend: 'Ext.window.Window',
    alias: 'widget.loginWindow',
    
    requires: ['LoginModule.view.Form'],
    
    header: {
    	title: '用户登陆',
        iconCls: 'login'
    },
    closable: false,
    draggable: false,
    resizable: false,
    width: 400,
    height: 205,
    layout: 'fit',   
    items: {xtype: 'loginform'}
})