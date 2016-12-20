Ext.define('VerifyResultModule.store.PfatItemStore', {
    extend: 'Ext.data.Store',
    model: 'VerifyResultModule.model.PfatItem',
    
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