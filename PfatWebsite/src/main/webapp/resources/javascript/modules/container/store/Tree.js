Ext.define('MainModule.store.Tree', {
    extend: 'Ext.data.TreeStore',
    model: 'MainModule.model.TreeModel',
    
    autoLoad: true,
    autoDestroy: true,
    root: { expanded: true },
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        url : 'services/systemMenuList',//请求  
    }
});