Ext.define('MenuModule.view.MenuWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.menuwindow',
	
	requires: ["MenuModule.view.MenuTreePanel", "MenuModule.view.MenuForm"],
	
	title: '维护菜单',
    iconCls: 'feed-icon',
    resizable: false,
    modal: true,
    width: 580,
    y: 100,
    autoScroll: true,
    closeAction: 'hide',
    items: [{
    	layout: 'border',
    	height: 280,
    	items: [{
        	xtype: 'menuform',
        	region:'west', 
        	width: 280
    	}, {
        	xtype: 'fieldset',
        	region: 'center',
        	margins: '0 3 0 3',
        	frame: false,
        	title: '选择要包含的菜单项',
        	items: {xtype: 'menutreepanel'}
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