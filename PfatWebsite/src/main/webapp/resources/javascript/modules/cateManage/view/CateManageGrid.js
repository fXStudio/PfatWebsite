Ext.define('CateManageModule.view.CateManageGrid', {
  extend: 'Ext.tree.Panel',
  alias: 'widget.catemanagegrid',
	
  requires: [
       'Ext.data.*',
       'Ext.grid.*',
       'Ext.tree.*',
       'CateManageModule.model.Task'
   ],    
   xtype: 'tree-grid',
   
   title: '维护分类信息',
   height: 300,
   useArrows: true,
   rootVisible: false,
   multiSelect: true,
   singleExpand: true,
   
   initComponent: function() {
       this.width = 600;
       
       Ext.apply(this, {
           store: new Ext.data.TreeStore({
               model: CateManageModule.model.Task,
               proxy: {
                   type: 'ajax',
                   url: 'javascript/treegrid.json'
               },
               folderSort: true
           }),
           columns: [{
               xtype: 'treecolumn', //this is so we know which column will show the tree
               text: 'Task',
               flex: 2,
               sortable: true,
               dataIndex: 'task'
           },{
               //we must use the templateheader component so we can use a custom tpl
               xtype: 'templatecolumn',
               text: 'Duration',
               flex: 1,
               sortable: true,
               dataIndex: 'duration',
               align: 'center',
               //add in the custom tpl for the rows
               tpl: Ext.create('Ext.XTemplate', '{duration:this.formatHours}', {
                   formatHours: function(v) {
                       if (v < 1) {
                           return Math.round(v * 60) + ' mins';
                       } else if (Math.floor(v) !== v) {
                           var min = v - Math.floor(v);
                           return Math.floor(v) + 'h ' + Math.round(min * 60) + 'm';
                       } else {
                           return v + ' hour' + (v === 1 ? '' : 's');
                       }
                   }
               })
           },{
               text: 'Assigned To',
               flex: 1,
               dataIndex: 'user',
               sortable: true
           }, {
               header: 'Done',
               dataIndex: 'done',
               width: 55,
               stopSelection: false,
               menuDisabled: true
           }]
       });
       this.callParent();
   }
});