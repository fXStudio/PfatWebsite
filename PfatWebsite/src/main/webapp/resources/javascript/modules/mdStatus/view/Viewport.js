Ext.define('MDStatusModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: ['MDStatusModule.view.MDStatusGrid'],

    layout: 'fit',
    items: {
        xtype: 'mdstatusgrid'
    }
});
