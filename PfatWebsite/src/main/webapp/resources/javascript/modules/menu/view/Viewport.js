Ext.define('MenuModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: ['MenuModule.view.MenuGrid'],

    layout: 'fit',
    items: {
        xtype: 'menugrid'
    }
});
