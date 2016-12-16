Ext.define('DataDictModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: ['DataDictModule.view.DataDictGrid'],

    layout: 'fit',
    items: {
        xtype: 'datadictgrid'
    }
});
