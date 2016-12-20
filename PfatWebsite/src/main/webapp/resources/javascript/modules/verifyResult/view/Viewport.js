Ext.define('VerifyResultModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: [
       'VerifyResultModule.view.VerifyResultGrid',
       'VerifyResultModule.view.PfatFileNormalGrid',
       'VerifyResultModule.view.PfatFileExtraGrid'
    ],

    layout: 'border',
    items: [{
        xtype: 'verifyresultgrid',
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
