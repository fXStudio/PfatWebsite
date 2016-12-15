Ext.define('UserGroupModule.view.UserGroupWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.usergroupwindow',
	
	requires: [
       "UserGroupModule.view.UserGroupForm",
       "UserGroupModule.view.UserGroupTreePanel"
   ],
	
	title: '维护用户组',
    iconCls: 'feed-icon',
    width: 580,
    resizable: false,
    modal: true,
    y: 100,
    closeAction: 'hide',
    items: [{
    	layout: 'border',
        height: 280,
        items: [{
        	xtype: 'usergroupform',
        	region:'west', 
        	width: 280
    	},  {
            xtype: 'fieldset',
            region: 'center',
            margins: '0 3 0 3',
            frame: false,
            title: '选择要包含的菜单',
            items: {xtype: 'usergrouptreepanel'}
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