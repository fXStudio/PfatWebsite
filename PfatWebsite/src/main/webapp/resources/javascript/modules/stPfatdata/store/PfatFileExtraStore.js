Ext.define('STpfatdataModule.store.PfatFileExtraStore', {
    extend: 'Ext.data.Store',
    model: 'STpfatdataModule.model.PfatFile',
    
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