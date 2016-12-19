Ext.define('PfatItemModule.store.DeptStore', {
    extend: 'Ext.data.Store',
    model: 'PfatItemModule.model.Dept',
    
    autoLoad: true,
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        url : 'services/combDeptList',//请求
        reader: {
            type: 'json'
        }
    }
});