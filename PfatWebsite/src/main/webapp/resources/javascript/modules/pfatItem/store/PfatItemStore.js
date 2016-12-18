Ext.define('PfatItemModule.store.PfatItemStore', {
    extend: 'Ext.data.Store',
    model: 'PfatItemModule.model.PfatItem',
    
    autoLoad: false,
    autoSync: true,
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        api: {
            read: 'services/pfatitemList',
            create: 'services/pfatitemModify',
            update: 'services/pfatitemModify',
            destroy: 'services/delPfatitem'
        },
        reader: {
            type: 'json',
            root: 'items',
            idProperty: 'id',
            totalProperty: 'totalCount'
        }
    }
});