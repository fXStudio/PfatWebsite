Ext.define('CateManageModule.model.Cate', {
    extend: 'Ext.data.Model',
    
    fields: [
	    'id',  'cateName',  'cateScore',  'parentId', 'remark', 'level'
    ]
}); 