Ext.define('PfatItemVerifyModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: [
       'PfatItemVerifyModule.view.PfatItemVerifyGrid',
       'PfatItemVerifyModule.view.PfatFileNormalGrid',
       'PfatItemVerifyModule.view.PfatFileExtraGrid'
    ],

    layout: 'border',
    items: [{
        xtype: 'pfatitemverifygrid',
        region: 'north',
        height: 300,
        resizable: true
    }, {
    	xtype: 'tabpanel',
    	region: 'center',
    	tabPosition: 'bottom',
    	items: [{xtype:'pfatfilenormalgrid'}, {xtype: 'pfatfileextragrid'}]
    }]
});
