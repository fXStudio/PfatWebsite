Ext.define('PfatItemModule.view.CateGrid', {
  extend: 'Ext.tree.Panel',
  alias: 'widget.categrid',
	
   xtype: 'tree-grid',
   useArrows: true,
   multiSelect: false, 
   rootVisible: false,
   singleExpand: false,
   columns: [{
       xtype: 'treecolumn', 
       text: '类别名称',
       width: 400,
       sortable: false,
       dataIndex: 'cateName'
   },{
       text: '分数',
       sortable: false,
       width: 50,
       align: 'center',
       dataIndex: 'cateScore'
   },{
       text: '备注',
       dataIndex: 'remark',
       flex:1,
       sortable: false
   }], filterBy: function(fn, scope) {// 自定义数据过滤器
	   var view = this.getView(),
       me = this,
       nodesAndParents = [];
	   
	   // 找到匹配的节点并展开.
       // 添加匹配的节点和他们的父节点到nodesAndParents数组.
       this.getRootNode().cascadeBy(function(tree, view) {
           var currNode = this;

           if (fn.call(me, currNode)) {
               me.expandPath(currNode.getPath());

               (function(node){
            	   node.cascadeBy(function(tree, view){
                       nodesAndParents.push(this.id);
            	   })
               })(currNode);
               while (currNode.parentNode) {
                   nodesAndParents.push(currNode.id);
                   currNode = currNode.parentNode;
               }
           }
       }, null, [me, view]);

       // 将不在nodesAndParents数组中的节点隐藏
       this.getRootNode().cascadeBy(function(tree, view) {
           var uiNode = view.getNodeByRecord(this);

           if (uiNode && !Ext.Array.contains(nodesAndParents, this.id)) {
               Ext.get(uiNode).setDisplayed(false);
           }
       }, null, [me, view]);
   },
   clearFilter: function() {
       var view = this.getView();

       this.getRootNode().cascadeBy(function(tree, view) {
           var uiNode = view.getNodeByRecord(this);

           if (uiNode) {
               Ext.get(uiNode).setDisplayed(true);
           }
       }, null, [this, view]);
   },
   initComponent: function() {
       var store = Ext.create('PfatItemModule.store.CateStore');
       var me = this;
       
       Ext.apply(this, {
           store: store,
           tbar: ['<span class="warn_msg">* 只能在二级绩效指标下创建考核项目</span>',
        	   '->', '查询分类',{
                   id: 'searchField',
        	       xtype: 'textfield',
                   name: 'searchField',
                   selectOnFocus: true,
                   hideLabel: true,
                   width: 200
              }, '|', {
        	  iconCls: 'x-tbar-loading',
        	  style: 'margin-right:20px',
        	  listeners: {
        		  click: function(){
        			  store.reload();
        		  }
        	  }
           }]
       });
       this.callParent();
   }
});