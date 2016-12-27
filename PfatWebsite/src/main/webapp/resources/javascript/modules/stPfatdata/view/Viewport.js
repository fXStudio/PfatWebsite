Ext.define('STpfatdataModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: ['STpfatdataModule.view.STpfatdataGrid'],

    layout: 'fit',
    items: {
        xtype: 'stpfatdatagrid'
    }
});
