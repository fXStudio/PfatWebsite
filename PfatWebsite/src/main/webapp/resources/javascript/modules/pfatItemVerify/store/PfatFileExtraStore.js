Ext.define('PfatItemVerifyModule.store.PfatFileExtraStore', {
    extend: 'Ext.data.Store',
    model: 'PfatItemVerifyModule.model.PfatFile',
    
    autoLoad: false,
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        api: {
            read: 'services/pfatfileList'
        },
        reader: {
            type: 'json',
            root: 'items',
            idProperty: 'id',
            totalProperty: 'totalCount'
        }
    }
});