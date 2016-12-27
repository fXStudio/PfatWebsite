Ext.define('STpfatdataModule.store.STpfatdataStore', {
    extend: 'Ext.data.Store',
    model: 'STpfatdataModule.model.STpfatdataModel',
    
    autoLoad: false,
    autoDestroy: true,
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        url : 'services/stpfatdataList',//请求
        reader: {
            type: 'json',
            root: 'items',
            idProperty: 'id',
            totalProperty: 'totalCount'
        }
    }
});