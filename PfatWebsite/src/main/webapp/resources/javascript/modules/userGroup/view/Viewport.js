Ext.define('UserGroupModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: [
       'UserGroupModule.view.UserGroupGrid', 
       'UserGroupModule.view.UserGrid'
    ],
    
    layout: 'border',
    defaults: { split: true },
    items: [{
    	xtype: 'usergroupgrid',
    	region: 'north',
        height: 260
    }, {
    	xtype: 'usergrid',
    	region: 'center'
    }]
});
