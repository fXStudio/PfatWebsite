Ext.define('PfatItemModule.controller.PfatItemController', {
    extend: 'Ext.app.Controller',
    
    refs: [
        {ref: 'gridPanel', selector: 'categrid'},
        {ref: 'pfatItemPanel', selector: 'pfatitemgrid'},
        {ref: 'window', selector: 'pfatitemwindow'},
        {ref: 'formPanel', selector: 'pfatitemform'},
        {ref: 'submitBtn', selector: 'button[action=submit]'}
     ],
     init: function() {
 	    this.control({
	    	'categrid': {
	    		itemclick: function(view, record, item, index, e, eOpts){
	    			var isdisabled = record.data.level < 3;
	    			
	    			Ext.each(['addBtn', 'modifyBtn', 'delBtn'], function(id){
	    				Ext.getCmp(id).setDisabled(isdisabled);
	    			});
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
		Ext.create('PfatItemModule.view.PfatItemWindow');	

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