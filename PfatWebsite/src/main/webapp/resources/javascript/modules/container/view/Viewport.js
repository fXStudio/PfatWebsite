Ext.define('MainModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: [
        'MainModule.view.TreePanel',
        'MainModule.view.TabPanel',
        'MainModule.view.Banner'
    ],

    layout: 'border',
    items: [{
        xtype: 'mainbanner',
        region: 'north',
    }, {
	   xtype: 'maintreepanel',
       region: 'west'
    }, {
       xtype: 'maintabpanel',
       region: 'center'
    }]
});
