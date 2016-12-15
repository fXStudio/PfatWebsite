Ext.define('SystemUserModule.store.SystemUser', {
    extend: 'Ext.data.Store',
    model: 'SystemUserModule.model.SystemUserModel',
    
    autoLoad: false,
    autoDestroy: true,
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        url : 'services/systemUserList',
        reader: {
            type: 'json',
            root: 'items',
            idProperty: 'id',
            totalProperty: 'totalCount'
        }
    }
});