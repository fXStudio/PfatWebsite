Ext.define('PfatItemModule.model.PfatItem', {
    extend: 'Ext.data.Model',
    
    fields: [
	    'id',  'itemName',  'cateId',  'compDate', 'itemScore', 'docName',
	    'deptId', 'officeName', 'personName', 'telPhone', 'remark', 'statusId'
    ]
}); 