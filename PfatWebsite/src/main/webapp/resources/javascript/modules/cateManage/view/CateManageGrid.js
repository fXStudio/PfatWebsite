Ext.define('CateManageModule.view.CateManageGrid', {
  extend: 'Ext.tree.Panel',
  alias: 'widget.catemanagegrid',
	
   xtype: 'tree-grid',
   useArrows: true,
   multiSelect: false,
   singleExpand: false,
   columns: [{
       xtype: 'treecolumn', 
       text: '类别名称',
       width: 400,
       sortable: false,
       dataIndex: 'cateName'
   },{
       text: '类别分数',
       sortable: false,
       dataIndex: 'cateScore'
   },{
       text: '备注',
       dataIndex: 'remark',
       sortable: false
   }],
   
   initComponent: function() {
       this.width = 600;
       var store = Ext.create('CateManageModule.store.CateStore');
       
       Ext.apply(this, {
           store: store,
           tbar: Ext.create('Ext.toolbar.Toolbar',  {
               items: [{
	                   text: '添加',
	                   iconCls: 'add',
	                   action: 'add'
	               }, {
	                   text: '修改',
	                   iconCls: 'update',
	                   action: 'modify'
	               }, '-', {
	                   text: '删除',
	                   iconCls: 'del',
	                   action: 'del'
               }]
           })
       });
       this.callParent();
   }
});