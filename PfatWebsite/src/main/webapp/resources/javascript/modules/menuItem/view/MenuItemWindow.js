Ext.define('MenuItemModule.view.MenuItemWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.menuitemwindow',
	
	requires: ["MenuItemModule.view.MenuItemForm"],
	
	title: '维护菜单项',
    iconCls: 'feed-icon',
    resizable: false,
    modal: true,
    width: 360,
    y: 100,
    closeAction: 'hide',
    items: {xtype: 'menuitemform'},
    buttons: [{
        text: '提交',
        action: 'submit'
    }, {
        text: '取消',
        action: 'cancel'
    }]
});