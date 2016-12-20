Ext.define('VerifyResultModule.controller.VerifyResultController', {
    extend: 'Ext.app.Controller',
    stores: ['PfatItemStore'],// 存储器
    refs: [
       {ref: 'gridPanel', selector: 'verifyresultgrid'},
       {ref: 'normalGrid', selector: 'pfatfilenormalgrid'},
       {ref: 'extraGrid', selector: 'pfatfileextragrid'}
    ],
    
    // Cotroller的业务处理
    init: function() {
	    this.control({
	    	'deptpfatitemgrid': {
	    		'itemclick': function(view, record, item, index, e, eOpts){
	    			this.itemClick.call(this, record);
	    		}
	    	},
	    	'actioncolumn[iconCls=download]': {// 添加分类信息
	    		click: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
                    window.location.href = "services/pfatfileDownload?id=" + record.data.id;
	    		 }
	    	 },
	    	 'textfield[name=searchField]': {
	    		 specialkey: function(field, e){
	                 if (e.getKey() == e.ENTER) {
	             	     var gridPanel = this.getGridPanel();
	                	 gridPanel.getStore().filter({
	                		 filterFn: function(item) { 
	                			 return item.get("itemName").indexOf(field.getValue()) > -1; 
	                	     }
	                	 });
	                 }
                 }
	    	 }
        })
    },
    
    /**
     * Module Launch
     */
	onLaunch: function() {
		// 获得数据源对象
	    var gridPanel = this.getGridPanel();
	    var normalGrid = this.getNormalGrid();
	    var extraGrid = this.getExtraGrid();
	    var store = gridPanel.getStore();
	    var me = this;
	    
        // 装载数据
        store.load({params: {status: "0,1,2,3"}});

	    // 设置首行选中
        store.on("load", function(){
        	gridPanel.getSelectionModel().select(0);
			me.itemClick.call(me, gridPanel.getSelectionModel().getSelection()[0]);
        })
	},
	itemClick: function(record) {
		Ext.each([this.getNormalGrid(), this.getExtraGrid()], function(item, index){
			item.getStore().load({params: {pfatitemId: record ? record.data.id : 0, cate: index}});
		});
	}
});