Ext.define('DeptPfatItemModule.controller.PfatfileController', {
    extend: 'Ext.app.Controller',
    stores: ['PfatItemStore'],// 存储器
    refs: [
       {ref: 'gridPanel', selector: 'deptpfatitemgrid'},
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
	    	'actioncolumn[iconCls=del]': {// 添加分类信息
	    		click: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
	                Ext.MessageBox.confirm('确认修改', '确定要删除文件【' + record.get('fileName') + '】吗?', function(res) {
	                	if (res === 'yes') {
	                		Ext.Ajax.request({
	                            url: 'services/delPfatfile',
	                            params: { id: record.get('id') },
	                            method: 'POST',
	                            success: function(response, options) {
	                            	grid.getStore().reload();
	                            },
	                            failure: function(response, options) {
	                                Ext.MessageBox.alert('失败', '文件删除失败: 文件不存在或不可编辑');
	                            }
	                        });
	                	}
        		    });
	    		 }
	    	 },
	    	'actioncolumn[iconCls=commit]': {// 添加分类信息
	    		click: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
	                Ext.MessageBox.confirm('确认提交', '项目【' + record.get('itemName') + '】提交后将无法变更，要继续吗?', function(res) {
	                	if (res === 'yes') {
	                		record.data.status = 1;
	                		
	                		Ext.Ajax.request({
	                            url: 'services/pfatitemModify',
	                            method: 'POST',
	                            params: record.data,
	                            success: function(response, options) {
	                            	grid.getStore().reload();
	                            },
	                            failure: function(response, options) {
	                                Ext.MessageBox.alert('失败', '文件删除失败: 文件不存在或不可编辑');
	                            }
	                        });
	                	}
        		    });
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
        store.load({params: {status: "0,3"}});

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
		if(record){
			Ext.getCmp('extra_pfatItemId').setValue(record.data.id);
			Ext.getCmp('normal_pfatItemId').setValue(record.data.id);
		}
		Ext.getComp('normal_upload').setDisabled(record);
		Ext.getComp('extra_upload').setDisabled(record);
	}
});