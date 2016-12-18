Ext.define('CateManageModule.store.CateStore', {
	extend: 'Ext.data.TreeStore',
    model: 'CateManageModule.model.Cate',
    
    root: { expanded: true, cateName: '所有绩效管理分类' },
    autoLoad: false,
    defaultRootId: 0,
    clearOnLoad: true,
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        url: 'services/cateTreeList'//请求  
    }
});