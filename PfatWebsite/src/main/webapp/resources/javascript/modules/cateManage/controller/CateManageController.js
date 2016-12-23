Ext.define('CateManageModule.controller.CateManageController', {
    extend: 'Ext.app.Controller',
    
    refs: [
        {ref: 'gridPanel', selector: 'catemanagegrid'},
        {ref: 'window', selector: 'catemanagewindow'},
        {ref: 'formPanel', selector: 'catemanageform'},
        {ref: 'submitBtn', selector: 'button[action=submit]'}
     ],
     init: function() {
 	    this.control({
 	    	'actioncolumn[iconCls=add]': {// 添加分类信息
 	    		 click: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
 	    			 if(!record.data.leaf) {// 只能为3级分了以上目录添加子节点
 	    				var rec = record.copy();
 	    				rec.data.id = 0;// 创建新的记录
 	    				rec.data.parentId = record.data.id;
 	    				rec.data.cateName = null;
 	    				rec.data.cateScore = null;
 	    				rec.data.remark = null;
 	    				rec.data.depth = record.data.depth + 1;
 	    				rec.data.index = record.childNodes.length;
	    				
		            	var window = this.getWindow();
		            	
		            	// 判断窗体对象是否存在, 如果不存在，就创建一个新的窗体对象
		            	if(!window){window = Ext.create('CateManageModule.view.CateManageWindow');}
		            	
	                    this.getFormPanel().getForm().loadRecord(rec);
	                    window.show();
	                    window.center();
 	    			 }
    	         }
 	    	 },
 	    	'actioncolumn[iconCls=update]': {// 修改分类信息
	    		 click: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
	    			if(record.data.depth){
		            	var window = this.getWindow();
		            	
		            	// 判断窗体对象是否存在, 如果不存在，就创建一个新的窗体对象
		            	if(!window){window = Ext.create('CateManageModule.view.CateManageWindow');}
		            	
	                    this.getFormPanel().getForm().loadRecord(record);
	                    window.show();
	                    window.center();
	    		 	}
	    		 }
	    	 },
 	    	'actioncolumn[iconCls=del]': {// 删除分信息
	    		 click: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
 	                var gridObj = this.getGridPanel();
 	                
	    			if(record.data.depth){
		                Ext.MessageBox.confirm('确认修改', '删除【' + record.get('cateName') + '】分类将会同时删除其下所有的子类目,确定要执行此操作吗?', function(res) {
		                	if (res === 'yes') {
		                		Ext.Ajax.request({
		                            url: 'services/delCategory',
		                            params: { id: record.get('id') },
		                            method: 'POST',
		                            success: function(response, options) {
	                                	gridObj.getStore().reload();
		                            },
		                            failure: function(response, action) {
		                                Ext.MessageBox.alert('失败', '操作失败：' + action.result.failureReason);
		                            }
		                        });
		                	}
	        		    });
	    		 	}
	    		 }
	    	 },
	    	 'textfield[name=searchField]': {
	    		 specialkey: function(field, e){
	                 if (e.getKey() == e.ENTER) {
		    			 var gridObj = this.getGridPanel();
		    			 
		    			 gridObj.clearFilter();
		    			 gridObj.filterBy(function(node){
		    				 return node.get('cateName').indexOf(field.getValue()) > -1;
		    			 });
	                 }
                 }
	    	 },
	    	 'textfield[name=remark]': {
	    		 specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                        this.getSubmitBtn().getEl().dom.click();
                    }
                 }
	    	 },
	        'button[action=submit]': {
	        	click: function() {
	                var formObj = this.getFormPanel().getForm();
	                var gridPanel = this.getGridPanel();
	            	var window = this.getWindow();
	                
	                // 检查表单项的录入是否存在问题
	                if (formObj.isValid()) {
	                    formObj.submit({
	                        waitMsg: '数据正在处理请稍后', // 提示信息  
	                        waitTitle: '提示', // 标题  
	                        url: 'services/categoryModify', // 请求的url地址  
	                        method: 'POST', // 请求方式  
	                        success: function(form, action) { // 添加数据成功后，重新加载数据源刷新表单 
	                        	gridPanel.getStore().load();
	                        },
	                        failure: function(form, action) { // 添加失败后，提示用户添加异常
	                            Ext.Msg.alert('失败', '操作未完成，原因：' + action.result.failureReason);
	                        }
	                    });
	                    setTimeout(function() { window.hide(); }, 100);
	                }
	            }
	        },
		     'button[action=cancel]': {
	        	click: function() {
		        	this.getWindow().hide();
	        	}
	        },
	        'catemanagewindow': {
	        	show: function(){
	                Ext.getCmp('catename').focus(true, 100);
	        	}
	        }
 	    })
     },

     /**
      * Module Launch
      */
	 onLaunch: function() {
		// 应用启动，先创建窗口对象，避免后面数据的重复拉取
		Ext.create('CateManageModule.view.CateManageWindow');	

		var gridObj = this.getGridPanel();
		gridObj.on({// 节点重新展开后，需要提交过滤条件给组件，否则会出现数据不一致的问题
			'itemexpand': function(){
	   			 gridObj.filterBy(function(node){
	   				 return node.get('cateName').indexOf(Ext.getCmp('searchField').getValue()) > -1;
	   			 });
			} 
		});
		gridObj.getStore().on({
			'load': function(){
				Ext.getCmp('searchField').focus();
			}
		})
	 }
});