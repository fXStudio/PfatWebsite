Ext.define('DeptPfatItemModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: [
       'DeptPfatItemModule.view.DeptPfatItemGrid',
       'DeptPfatItemModule.view.PfatFileNormalGrid',
       'DeptPfatItemModule.view.PfatFileExtraGrid'
    ],

    layout: 'border',
    items: [{
        xtype: 'deptpfatitemgrid',
        region: 'north',
        height: 200,
        resizable: true
    }, {
    	xtype: 'tabpanel',
    	region: 'center',
    	tabPosition: 'bottom',
    	items: [{xtype:'pfatfilenormalgrid'}, {xtype: 'pfatfileextragrid'}]
    }]
});
