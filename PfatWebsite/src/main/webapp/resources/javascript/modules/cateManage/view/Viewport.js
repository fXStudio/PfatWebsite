Ext.define('CateManageModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: ['CateManageModule.view.CateManageGrid'],

    layout: 'fit',
    items: {
        xtype: 'catemanagegrid'
    }
});
