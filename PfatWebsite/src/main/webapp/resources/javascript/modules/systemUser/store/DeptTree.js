Ext.define('SystemUserModule.store.DeptTree', {
	extend: 'Ext.data.TreeStore',
	
	autoLoad: false,
	autoDestroy: true,
    autoSync: false,
    root: { expanded: true },
	fields: ["sn", "text"],
    proxy: {
        type : 'ajax',
        actionMethods: { read: 'POST' },
        url : 'services/departmentList',//请求  
    }
});