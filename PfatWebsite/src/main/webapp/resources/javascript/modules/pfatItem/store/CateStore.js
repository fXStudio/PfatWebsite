Ext.define('PfatItemModule.store.CateStore', {
	extend: 'Ext.data.TreeStore',
    model: 'PfatItemModule.model.Cate',
    
    root: { expanded: true },
    autoLoad: false,
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        url: 'services/cateTreeList'//请求  
    }
});