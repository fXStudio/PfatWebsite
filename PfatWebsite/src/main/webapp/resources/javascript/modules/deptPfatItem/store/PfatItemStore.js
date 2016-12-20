Ext.define('DeptPfatItemModule.store.PfatItemStore', {
    extend: 'Ext.data.Store',
    model: 'DeptPfatItemModule.model.PfatItem',
    
    autoLoad: false,
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        api: {
            read: 'services/deptitemList'
        },
        reader: {
            type: 'json',
            root: 'items',
            idProperty: 'id',
            totalProperty: 'totalCount'
        }
    }
});