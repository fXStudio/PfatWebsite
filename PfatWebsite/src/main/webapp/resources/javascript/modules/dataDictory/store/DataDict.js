Ext.define('DataDictModule.store.DataDict', {
    extend: 'Ext.data.Store',
    model: 'DataDictModule.model.DataDictModel',
    
    autoLoad: false,
    autoDestroy: true,
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        url : 'services/systemDataList',//请求
        reader: {
            type: 'json',
            root: 'items',
            idProperty: 'id',
            totalProperty: 'totalCount'
        }
    }
});