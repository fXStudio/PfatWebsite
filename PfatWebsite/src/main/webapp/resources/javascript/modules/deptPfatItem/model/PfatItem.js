Ext.define('DeptPfatItemModule.model.PfatItem', {
    extend: 'Ext.data.Model',
    
    fields: [
	    'id',  'itemName',  'cateId',  'compDate', 'itemScore', 'docName',
	    'officeName', 'personName', 'telPhone', 'remark', 'status', 'deptId'
    ]
}); 