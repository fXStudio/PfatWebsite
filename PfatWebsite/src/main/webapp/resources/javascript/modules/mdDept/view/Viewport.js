Ext.define('MdDeptModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: ['MdDeptModule.view.MdDeptGrid'],

    layout: 'fit',
    items: {
        xtype: 'mddeptgrid'
    }
});
