Ext.define('MenuModule.store.Tree', {
	extend: 'Ext.data.TreeStore',
    model: 'MenuModule.model.TreeModel',
    
    root: { expanded: true },
    autoLoad: false,
    autoDestroy: true,
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        url : 'services/itemList',//请求  
    }
});