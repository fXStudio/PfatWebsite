Ext.define('PfatItemModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: ['PfatItemModule.view.CateGrid', 'PfatItemModule.view.PfatItemGrid'],

    layout: 'border',
    items: [{
        xtype: 'categrid',
        region: 'north',
        height: 240
    }, {
    	xtype: 'pfatitemgrid',
    	title: '考核项目',
    	region: 'center'
    }]
});
