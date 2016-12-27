Ext.define('PfatItemVerifyModule.controller.PfatItemVerifyController', {
    extend: 'Ext.app.Controller',
    stores: ['PfatItemStore'],// 存储器
    refs: [
       {ref: 'gridPanel', selector: 'pfatitemverifygrid'},
       {ref: 'normalGrid', selector: 'pfatfilenormalgrid'},
       {ref: 'extraGrid', selector: 'pfatfileextragrid'},
       {ref: 'window', selector: 'pfatitemwindow'},
       {ref: 'previewWindow', selector: 'previewwindow'},
       {ref: 'formPanel', selector: 'pfatitemform'}
    ],
    
    // Cotroller的业务处理
    init: function() {
	    this.control({
	    	'deptpfatitemgrid': {
	    		'itemclick': function(view, record, item, index, e, eOpts){
	    			this.itemClick.call(this, record);
	    		}
	    	},
	    	'actioncolumn[iconCls=commit]': {// 添加分类信息
	    		click: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
	                Ext.MessageBox.confirm('审核通过', '项目【' + record.get('itemName') + '】确认审核通过吗?', function(res) {
	                	if (res === 'yes') {
	                		record.data.status = 1;
	                		record.data.postil = null;
	                		
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
	    	'actioncolumn[iconCls=fail]': {// 添加分类信息
	    		click: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
	            	var me = this;
	    			
	                Ext.MessageBox.confirm('审核通过', '项目【' + record.get('itemName') + '】确认审核失败吗?', function(res) {
	                	if (res === 'yes') {
	    	            	var window = me.getWindow();
	    	            	// 判断窗体对象是否存在, 如果不存在，就创建一个新的窗体对象
	    	            	if(!window){window = Ext.create('PfatItemVerifyModule.view.PfatItemWindow');}
	    	            	
	    	            	record.data.status = 3;
	    	            	record.data.postil = null;// 如果重新提交的请求，依旧没有通过，说明信息不应和上次重复
	    	            	
	    	            	me.getFormPanel().getForm().loadRecord(record); // 加载要编辑的对象
	    	                
	    	                window.show(); // 显示窗体
	    	                window.center();// 窗体居中显示
	                	}
        		    });
	    		 }
	    	 },
	    	'actioncolumn[iconCls=return]': {// 添加分类信息
	    		click: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
	                Ext.MessageBox.confirm('审核通过', '项目【' + record.get('itemName') + '】确认要退回责任部门吗?', function(res) {
	                	if (res === 'yes') {
	                		record.data.status = 0;
	                		record.data.postil = null;
	                		
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
            'actioncolumn[iconCls=postil]': {// 添加分类信息
                click: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
                	if(record.get('postil')){
                        Ext.Msg.alert('未通过原因', record.get('postil'));
                	}
                 }
             },
	    	'actioncolumn[iconCls=download]': {// 添加分类信息
	    		click: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
                    window.location.href = "services/pfatfileDownload?id=" + record.data.id;
	    		 }
	    	 },
	    	 'actioncolumn[iconCls=preview]': {// 添加分类信息
	    		 click: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
	    			 if(record.get('fileName').match(/\.(doc|docx|xls|xlsx)$/)) {
	    				 var mask = new Ext.LoadMask(Ext.getBody(), {msg:"数据处理中请稍后......"}); mask.show();
       	              	 var previewWindow = this.getPreviewWindow();
	    				 
	    				 Ext.Ajax.request({
                            url: 'services/preview',
                            method: 'POST',
                            params: record.data,
                            success: function(response, options) {
              	              	mask.hide();
	     						// 判断窗体对象是否存在, 如果不存在，就创建一个新的窗体对象
	     						if(!previewWindow){previewWindow = Ext.create('PfatItemVerifyModule.view.PreviewWindow');}
	     						
	     						previewWindow.show(); // 显示窗体
	     						previewWindow.center();// 窗体居中显示
                            },
                            failure: function(response, options) {
              	              	mask.hide();
                                Ext.MessageBox.alert('失败', '预览文件生成失败');
                            }
                        });
	    			 }
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
	    	 },
	        'pfatitemwindow button[action=submit]': {
	        	click: function(){
	                var formObj = this.getFormPanel().getForm();
	                var window = this.getWindow();
	                var gridPanel = this.getGridPanel();
	                
	                // 检查表单项的录入是否存在问题
	                if (formObj.isValid()) {
	                    formObj.submit({
	                        waitMsg: '数据正在处理请稍后', // 提示信息  
	                        waitTitle: '提示', // 标题  
                            url: 'services/pfatitemModify',
	                        method: 'POST', // 请求方式  
	                        success: function(form, action) { // 添加数据成功后，重新加载数据源刷新表单 
	                        	gridPanel.getStore().reload();
	                        },
	                        failure: function(form, action) { // 添加失败后，提示用户添加异常
	                            Ext.Msg.alert('失败', '操作未完成，原因：录入信息错误');
	                        }
	                    });
	                    setTimeout(function() {window.hide();}, 100);
	                }
	        	}
	        },
	        'pfatitemwindow button[action=cancel]': {
	        	click: function(){
	        		this.getWindow().hide();
	        	}
	        },
	        'pfatitemwindow': {
	        	show: function(){
    	            Ext.getCmp('postil').focus(true, 100);
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
        store.load({params: {status: "1"}});

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