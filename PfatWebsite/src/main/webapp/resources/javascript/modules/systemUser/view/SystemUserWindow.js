Ext.define('SystemUserModule.view.SystemUserWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.systemuserwindow',
	
	requires: [
	    "SystemUserModule.view.SystemUserForm",
	    "SystemUserModule.view.SystemUserTabPanel"
	],
	
	title: '维护用户',
    resizable: false,
    modal: true,
    width: 460,
    y: 100,
    closeAction: 'hide',
    items: [{
    	layout: 'border',
        border: false,
        height: 320,
        items: [{	
        	xtype: 'systemuserform',
        	region:'north', 
        	width: 280
    	}, {
    		xtype: 'systemusertabpanel',
        	region: 'center'
        }]
    }],
    buttons: [{
        text: '提交',
        action: 'submit'
    }, {
        text: '取消',
        action: 'cancel'
    }]
});