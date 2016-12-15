Ext.define('MenuItemModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: ['MenuItemModule.view.MenuItemGrid'],

    layout: 'fit',
    items: {
        xtype: 'menuitemgrid'
    }
});
