Ext.define('MDStatusModule.store.MDStatus', {
    extend: 'Ext.data.Store',
    model: 'MDStatusModule.model.MDStatusModel',
    
    autoLoad: false,
    autoDestroy: true,
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        url : 'services/statusList',//请求
        reader: {
            type: 'json',
            root: 'items',
            idProperty: 'id',
            totalProperty: 'totalCount'
        }
    }
});