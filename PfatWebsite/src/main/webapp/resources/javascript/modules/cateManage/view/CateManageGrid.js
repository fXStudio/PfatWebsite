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
           return record.data.leaf;
       }
   }, {
       text: '',
       width: 30,
       menuDisabled: true,
       xtype: 'actioncolumn',
       align: 'center',
       iconCls: 'update',
       isDisabled: function(view, rowIdx, colIdx, item, record) {
           return record.data.id === 0;
       }
   }, {
       text: '',
       width: 30,
       menuDisabled: true,
       xtype: 'actioncolumn',
       align: 'center',
       iconCls: 'del',
       isDisabled: function(view, rowIdx, colIdx, item, record) {
           return record.data.id === 0
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
            		   var rec = dragData.records[0];
            		   
                       return rec.childNodes.length == 0 || (rec.childNodes.length > 0 && targetNode.data.level < 2);
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
            		   var mask = new Ext.LoadMask(me, {msg:"数据处理中请稍后......"});
             		   var rec = data.records[0];
             		   var level = rec.parentNode.get('level') | 0;
             		   
             		   rec.data.level = ++level;
             		   
             		   console.log(data.records.length);
             		   
       	               mask.show();
             		   Ext.Ajax.request({
	                         url: 'services/categoryModify',
	                         params: rec.data,
	                         method: 'POST',
	                         success: function(response, options) {
	                             var obj = Ext.decode(response.responseText);
	
	                             if (obj.success) {// 根据不同的删除状态，做不同的提示
	                             	store.reload();
	                             	store.on({
	                             		'load': function(){
	                        	              mask.hide();
	                             		}
	                             	})
	                             } else {
	                  	             mask.hide();
	                                 Ext.MessageBox.alert('失败', '删除失败, 原因：' + obj.failureReason);
	                             }
	                         },
	                         failure: function(response, options) {
	              	             mask.hide();
	                             Ext.MessageBox.alert('失败', '请求超时或网络故障, 错误编号：' + response.status);
	                         }
	                   });
                   }
               }
           }
       });
       this.callParent();
   }
});