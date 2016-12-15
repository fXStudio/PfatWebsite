Ext.define('UserGroupModule.store.User', {
    extend: 'Ext.data.Store',
    model: 'UserGroupModule.model.UserModel',
    
    autoLoad: false,
    autoDestroy: true,
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        url : 'services/groupUserList',
        reader: {
            type: 'json',
            root: 'items',
            idProperty: 'id',
            totalProperty: 'totalCount'
        }
    }
});