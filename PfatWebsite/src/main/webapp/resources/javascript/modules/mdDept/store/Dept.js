Ext.define('MdDeptModule.store.Dept', {
    extend: 'Ext.data.Store',
    model: 'MdDeptModule.model.MdDeptModel',
    
    autoLoad: false,
    autoDestroy: true,
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        url : 'services/mdDeptList',//请求
        reader: {
            type: 'json',
            root: 'items',
            idProperty: 'id',
            totalProperty: 'totalCount'
        }
    }
});