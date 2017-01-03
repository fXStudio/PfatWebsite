Ext.define('DataDictModule.controller.DataDictController', {
    extend: 'Ext.app.Controller',
    refs: [
       {ref: 'gridPanel', selector: 'datadictgrid'},
       {ref: 'window',    selector: 'datadictwindow'},
       {ref: 'formPanel', selector: 'datadictform'},
       {ref: 'submitBtn', selector: 'datadictwindow button[action=submit]'}
    ],
    
    // Cotroller的业务处理
    init: function() {
	    this.control({ 'textfield[name=searchField]': {
	            specialkey: function(field, e){
	                if (e.getKey() == e.ENTER) {
	                    var gridPanel = this.getGridPanel();
	                    gridPanel.getStore().filter({
	                        filterFn: function(item) {
	                            return !field.getValue() || 
	                                   item.get("itemName") && item.get("itemName").indexOf(field.getValue()) > -1; 
	                        }
	                    });
	                }
	            }
	        },
            'textfield[name=itemVal]': {
                specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                        this.getSubmitBtn().getEl().dom.click();
                    }
                }
            },
	        'datadictgrid button[action=modify]': {
	        	click: function(){
	        		var me = this;
	        		
	        		// 确认是否修改
	                Ext.MessageBox.confirm('确认修改', '修改字典项可能会影响到系统稳定性，你确认要进行此操作吗?', function(res) {
	                	if (res === 'yes') { 
			                var sm = me.getGridPanel().getSelectionModel();// 获得grid的SelectionModel
			                var record = sm.getLastSelected();// 当前选中记录
			                
			                if (record) {// 只有存在选中行的时候才显示修改窗口
			                    var menuItemWindow = me.getWindow();
			                	if(!menuItemWindow){
			                		menuItemWindow = Ext.create('DataDictModule.view.DataDictWindow');
			                	}
			                	// 窗体对象
			                    me.getFormPanel().getForm().loadRecord(record); // 加载要编辑的对象
			                    menuItemWindow.show(); // 显示窗体
			                    menuItemWindow.center();
			                }
	                	}
        		    });
	            }
	        },
	        'datadictwindow': {
	        	show: function(){
	        		Ext.getCmp("itemval").focus(100, true);
	        	}
	        },
	        'datadictwindow button[action=submit]': {
	        	click: function() {
	                // form表单对象 
	                var formObj = this.getFormPanel().getForm();
	                var gridPanel = this.getGridPanel();
	                var window = this.getWindow();
	                
	                // 检查表单项的录入是否存在问题
	                if (formObj.isValid()) {
	                    // 提交表单
	                    formObj.submit({
	                        waitMsg: '数据正在处理请稍后', // 提示信息  
	                        waitTitle: '提示', // 标题  
	                        url: 'services/systemDataModify', // 请求的url地址  
	                        method: 'POST', // 请求方式  
	                        success: function(form, action) { // 添加数据成功后，重新加载数据源刷新表单 
	                        	gridPanel.getStore().reload();
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
	        'datadictwindow button[action=cancel]': {
	        	click: function(){
                    this.getWindow().hide();
	        	}
	        }
        })
    },
    
    /**
     * Module Launch
     */
	onLaunch: function() {
		// 获得数据源对象
	    var gridPanel = this.getGridPanel(), store = gridPanel.getStore();
	    
        // 装载数据
        store.load();

	    // 设置首行选中
        store.on("load", function(){
        	gridPanel.getSelectionModel().select(0);
        	var record = gridPanel.getSelectionModel().getLastSelected();
        	
        	Ext.getCmp('updatebtn').setDisabled(!record);
        })
	}
});