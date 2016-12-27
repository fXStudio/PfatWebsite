Ext.define('STpfatdataModule.model.STpfatdataModel', {
	extend: 'Ext.data.Model',
	
	fields: [
	    'id', 'firstCate', 'secondCate', 'thirdCate', 'itemName', 'compDate',
	    'itemScore', 'docName', 'deptName', 'officeName', 'telPhone', 
	    'personName', 'status', 'cateId'
    ]
});