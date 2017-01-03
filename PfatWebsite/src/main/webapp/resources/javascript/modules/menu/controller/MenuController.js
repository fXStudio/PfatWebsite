Ext.define('MenuModule.controller.MenuController', {
    extend: 'Ext.app.Controller',
    refs: [
       {ref: 'gridPanel', selector: 'menugrid'},
       {ref: 'window', selector: 'menuwindow'},
       {ref: 'formPanel', selector: 'menuform'},
       {ref: 'treePanel', selector: 'menutreepanel'}
    ],
    
    // Cotroller的业务处理
    init: function() {
	    this.control({
	    	'menugrid button[action=add]':{
	    		click: function(){
	    			// 获得窗体对象的引用
	            	var menuWindow = this.getWindow();
	            	// 判断窗体对象是否存在, 如果不存在，就创建一个新的窗体对象
	            	if(!menuWindow){menuWindow = Ext.create('MenuModule.view.MenuWindow');}
	            	
	            	// 在执行新增业务的时候，要把窗体的内容清空
	                this.getFormPanel().getForm().reset();
	                this.getTreePanel().getStore().load();
	                menuWindow.show(); // 显示窗体
	                menuWindow.center();// 窗体居中显示
	            }
	        },
	        'menugrid button[action=modify]': {
	        	click: function(){
	                // 获得grid的SelectionModel
	                var sm = this.getGridPanel().getSelectionModel();
	                // 当前选中记录
	                var record = sm.getLastSelected();

	                // 只有存在选中行的时候才显示修改窗口
	                if (record) {
		    			// 获得窗体对象的引用
		            	var menuWindow = this.getWindow();
		            	// 判断窗体对象是否存在, 如果不存在，就创建一个新的窗体对象
		            	if(!menuWindow){menuWindow = Ext.create('MenuModule.view.MenuWindow');}
		            	
	                    this.getFormPanel().getForm().loadRecord(record); // 加载要编辑的对象
		                this.getTreePanel().getStore().load({
		                	params: {id: record.get('id')}
		                });
	                    menuWindow.show(); // 显示窗体
	                    menuWindow.center();
	                }
	            }
	        },
	        'menugrid button[action=del]': {
	        	click: function(){
	            	// 表格对象
	                var gridObj = this.getGridPanel();
	                // 如果选中行无效，则不相应用户的删除操作
	                if (gridObj.getSelectionModel().getSelection().length < 1) {
	                    return false;
	                }
	                // 当前选中行
	                var rowId = gridObj.getSelectionModel().getLastSelected().get('id');

	                // 提示用户确认删除操作
	                Ext.MessageBox.confirm('确认删除', '你确定要删除选中记录吗?', function(res) {
	                    if (res === 'yes') { // 用户确认要执行删除操作
	                        Ext.Ajax.request({
	                            url: 'services/delMenu',
	                            params: { sysid: rowId },
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
	        },
	        'menuwindow button[action=submit]': {
	        	click: function() {
	                // form表单对象 
	                var formObj = this.getFormPanel().getForm();
	                var gridPanel = this.getGridPanel();
	                var window = this.getWindow();
	                
	                // 检查表单项的录入是否存在问题
	                if (formObj.isValid()) {
	                    var items = Ext.getCmp('items');// 用户选择的菜单项列表
	                    
	                    items.setValue('');// 重置文本内容
	                    
	                    // 获得用户选中的可访问菜单ID
	                    Ext.each(this.getTreePanel().getChecked(), function(record) {
	                        var val = items.getValue();

	                        if (val.length > 0) { val += ","; }
	                        items.setValue(val + record.get('sn'));
	                    });
	                	
	                    // 提交表单
	                    formObj.submit({
	                        waitMsg: '数据正在处理请稍后', // 提示信息  
	                        waitTitle: '提示', // 标题  
	                        url: 'services/menuModify', // 请求的url地址  
	                        method: 'POST', // 请求方式  
	                        success: function(form, action) { // 添加数据成功后，重新加载数据源刷新表单 
	                        	gridPanel.getStore().load();
	                        },
	                        failure: function(form, action) { // 添加失败后，提示用户添加异常
	                            Ext.Msg.alert('失败', '操作未完成，原因：录入信息错误');
	                        }
	                    });
	                    // 关闭当前弹出窗
	                    setTimeout(function() { window.hide(); }, 100);
	                }
	            }
	        },
	        'menuwindow button[action=cancel]': {
	        	click: function() {
		        	this.getWindow().hide();
	        	}
	        },
	        'menuwindow': {
	        	show: function(){
	                Ext.getCmp('menuname').focus(true, 100);
	        	}
	        },
	        'menutreepanel': {
	        	checkchange: function(node, checked) {
	                if (checked) {
	                	node.set('cls', 'complete');
	                } else {
	                	node.set('cls', '');
	                }
	            }
	        }
        })
    },
    
    /**
     * Module Launch
     */
	onLaunch: function() {
		// 应用启动，先创建窗口对象，避免后面数据的重复拉取
		Ext.create('MenuModule.view.MenuWindow');
		
		// 获得数据源对象
	    var gridPanel = this.getGridPanel(), store = gridPanel.getStore();
	    
        // 装载数据
        store.load();

	    // 设置首行选中
        store.on("load", function(){
        	gridPanel.getSelectionModel().select(0);
        })
	}
});