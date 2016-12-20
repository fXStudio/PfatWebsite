Ext.define('VerifyResultModule.store.PfatFileNormalStore', {
    extend: 'Ext.data.Store',
    model: 'VerifyResultModule.model.PfatFile',
    
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