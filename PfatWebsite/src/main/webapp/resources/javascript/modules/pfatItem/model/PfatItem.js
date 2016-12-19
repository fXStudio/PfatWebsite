Ext.define('PfatItemModule.model.PfatItem', {
    extend: 'Ext.data.Model',
    
    fields: [
	    {name: 'id', type: 'int', defaultValue: 0},  'itemName',  'cateId',  {name: 'compDate', type: 'date', defaultValue: new Date()}, 'itemScore', 'docName',
	    'deptId', 'officeName', 'personName', 'telPhone', 'remark', 'statusId'
    ]
}); 