Ext.define('SystemUserModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: ['SystemUserModule.view.SystemUserGrid'],
    
    layout: 'fit',
    items: {xtype: 'systemusergrid'}
});
