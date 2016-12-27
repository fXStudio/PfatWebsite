Ext.define('CateManageModule.view.CateManageGrid', {
  extend: 'Ext.tree.Panel',
  alias: 'widget.catemanagegrid',
  
   requires: [
	  "CateManageModule.ex.ViewDropZone",
	  "CateManageModule.ex.TreeViewDragDrop"
   ],
	
   xtype: 'tree-grid',
   useArrows: true,
   multiSelect: false, 
   rootVisible: true,
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
   }, {
       text: '',
       width: 30,
       menuDisabled: true,
       xtype: 'actioncolumn',
       align: 'center',
       iconCls: 'add',
       isDisabled: function(view, rowIdx, colIdx, item, record) {
           return !record.data.depth || record.data.depth === 3;
       }
   }, {
       text: '',
       width: 30,
       menuDisabled: true,
       xtype: 'actioncolumn',
       align: 'center',
       iconCls: 'update',
       isDisabled: function(view, rowIdx, colIdx, item, record) {
           return record.data.depth === 0;
       }
   }, {
       text: '',
       width: 30,
       menuDisabled: true,
       xtype: 'actioncolumn',
       align: 'center',
       iconCls: 'del',
       isDisabled: function(view, rowIdx, colIdx, item, record) {
           return record.data.depth === 0
       }
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
       var store = Ext.create('CateManageModule.store.CateStore');
       var me = this;
       
       Ext.apply(this, {
           store: store,
           bbar: ['<span class="warn_msg">* 当前版本绩效考核分类至多可以创建三层&nbsp; (V 1.0)</span>',
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
        			  me.view.refresh();
        		  }
        	  }
           }],
           viewConfig: {
               plugins: {
                  ptype: 'fxtreeviewdragdrop',
                  dragText : '调整绩效考核分类结构'
               },
               listeners: {
            	   nodedragover: function(targetNode, position, dragData){
            		   var targetDepth = targetNode.data.depth, res = true;
            		   if("append" === position) {// 如果是添加操作，则深度需要加1
            			   targetDepth++;
            		   }
            		   
					  (function(recs, depth){
            			   var fn = arguments.callee;
            			   Ext.each(recs, function(rec, index){
            				  if(depth > 3) {// 数据的转移最多只能达到三层
            					  return res = false;
            				  }
            				  fn(rec.childNodes, depth + 1);
            			   });
            		   })(dragData.records, targetDepth);
					  
					  return res;
                   },
            	   beforedrop: function(node, data, overModel, dropPosition, dropHandlers) {
            		    dropHandlers.wait = true;// 挂起拖动事件
            		    Ext.MessageBox.confirm('调整节点位置', '节点位置发生变更，可能造成已维护的绩效数据丢失，确定要执行此操作吗?', function(btn){
            		        if (btn === 'yes') {
            		            dropHandlers.processDrop();
            		        } else {
            		            dropHandlers.cancelDrop();
            		        }
            		    });
            	   },
            	   drop: function(node, data, overModel, dropPosition, eOpts){
            		   var mask = new Ext.LoadMask(me, {msg:"数据处理中请稍后......"}), arr = [];
       	               mask.show();
            		   
            		   Ext.each(store.getUpdatedRecords(), function(rec, index){
            			   arr.push(rec.data);
            		   });
            		   
             		   Ext.Ajax.request({
	                         url: 'services/categoryAdjust',
	                         params: {cates: JSON.stringify(arr)},
	                         method: 'POST',
	                         success: function(response, options) {
                             	store.reload();
                             	store.on({
                             		'load': function(){
	                         			  me.view.refresh();
	                    	              mask.hide();
                             		}
                             	});
	                         },
	                         failure: function(response, action) {
	              	             mask.hide();
	                             Ext.MessageBox.alert('失败', '操作失败：' + (action.result.failureReason || '系统异常'));
	                         }
	                   });
                   }
               }
           }
       });
       this.callParent();
   }
});