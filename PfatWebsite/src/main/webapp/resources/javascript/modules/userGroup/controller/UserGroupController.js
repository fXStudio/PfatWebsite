Ext.define('UserGroupModule.controller.UserGroupController', {
    extend: 'Ext.app.Controller',
    refs: [
       {ref: 'userGridPanel', selector: 'usergrid'},
       {ref: 'formPanel', selector: 'usergroupform'},
       {ref: 'groupGridPanel', selector: 'usergroupgrid'},
       {ref: 'treePanel', selector: 'usergrouptreepanel'},
       {ref: 'window', selector: 'usergroupwindow'}
    ],
    
    // Cotroller的业务处理
    init: function() {
	    this.control({ 'textfield[name=searchField]': {
	            specialkey: function(field, e){
	                if (e.getKey() == e.ENTER) {
	                    var gridPanel = this.getGroupGridPanel();
	                    gridPanel.getStore().filter({
	                        filterFn: function(item) {
	                            return !field.getValue() || 
	                                   item.get("groupName") && item.get("groupName").indexOf(field.getValue()) > -1; 
	                        }
	                    });
	                }
	            }
	        },
	    	'usergroupgrid button[action=add]':{
	    		click: function(){
	    			// 获得窗体对象的引用
	            	var userGroupWindow = this.getWindow();
	            	// 判断窗体对象是否存在, 如果不存在，就创建一个新的窗体对象
	            	if(!userGroupWindow){userGroupWindow = Ext.create('UserGroupModule.view.UserGroupWindow');}
	            	
	            	// 在执行新增业务的时候，要把窗体的内容清空
	                this.getFormPanel().getForm().reset();
	                this.getTreePanel().getStore().load();
	                userGroupWindow.show(); // 显示窗体
	                userGroupWindow.center();// 窗体居中显示
	            }
	        },
	        'usergroupgrid button[action=modify]': {
	        	click: function(){
	                // 获得grid的SelectionModel
	                var sm = this.getGroupGridPanel().getSelectionModel();
	                // 当前选中记录
	                var record = sm.getLastSelected();

	                // 只有存在选中行的时候才显示修改窗口
	                if (record) {
		    			// 获得窗体对象的引用
		            	var userGroupWindow = this.getWindow();
		            	// 判断窗体对象是否存在, 如果不存在，就创建一个新的窗体对象
		            	if(!userGroupWindow){userGroupWindow = Ext.create('UserGroupModule.view.UserGroupWindow');}
		            	
	                	// 窗体对象
	                    this.getFormPanel().getForm().loadRecord(record); // 加载要编辑的对象
		                this.getTreePanel().getStore().load({
		                	params: {groupId: record.get('id')}
		                });
		                userGroupWindow.show(); // 显示窗体
		                userGroupWindow.center();
	                }
	            }
	        },
	        'usergroupgrid button[action=del]': {
	        	click: function(){
	                var gridObj = this.getGroupGridPanel();
	                var sm = gridObj.getSelectionModel();
	                
	                if (sm.getSelection().length < 1) {// 如果选中行无效，则不相应用户的删除操作
	                    return false;
	                }
	                
	                Ext.MessageBox.confirm('确认删除', '你确定要删除选中记录吗?', function(res) {
	                    if (res === 'yes') { // 用户确认要执行删除操作
	                        Ext.Ajax.request({
	                            url: 'services/delUserGroup',
	                            params: {
	                            	id: sm.getLastSelected().get('id')
	                            },
	                            method: 'POST',
	                            success: function(response, options) {
                                    gridObj.getStore().load();
	                            },
	                            failure: function(response, action) {
	                                Ext.MessageBox.alert('失败', '操作失败：' + action.result.failureReason);
	                            }
	                        });
	                    }
	                });
	            }
	        },
	        'usergroupgrid': {
	        	itemclick: function(obj, record, e) {
	            	this.reloadGroupUsers(record);
	            }
	        },
	        'usergroupwindow button[action=submit]': {
	        	click: function() {
	                // form表单对象 
	                var formObj = this.getFormPanel().getForm();
	                var groupGridPanel = this.getGroupGridPanel();
	                var window = this.getWindow();
	                
	                // 检查表单项的录入是否存在问题
	                if (formObj.isValid()) {
	                	// 菜单值
	                    var val = "";
	                    
	                    // 获得用户选中的可访问菜单ID
	                    Ext.each(this.getTreePanel().getChecked(), function(record) {
	                        if (val.length > 0) {
	                            val += ",";
	                        }
	                        val = val + record.get('sn');
	                    });
	                    Ext.getCmp('menus').setValue(val);
	                	
	                    // 提交表单
	                    formObj.submit({
	                        waitMsg: '数据正在处理请稍后', // 提示信息  
	                        waitTitle: '提示', // 标题  
	                        url: 'services/userGroupModify', // 请求的url地址  
	                        method: 'POST', // 请求方式  
	                        success: function(form, action) { // 添加数据成功后，重新加载数据源刷新表单 
	                        	groupGridPanel.getStore().load();
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
	        'usergroupwindow button[action=cancel]': {
	        	click: function() {
		        	this.getWindow().hide();
	        	}
	        },
	        'usergroupwindow': {
	        	show: function(){
	                Ext.getCmp('groupname').focus(true, 100);
	        	}
	        },
	        'usergrouptreepanel': {
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
		// 创建window对象，这样做是为了避免重复提交
		Ext.create('UserGroupModule.view.UserGroupWindow');
		
		// 获得数据源对象
	    var me = this, gridPanel = me.getGroupGridPanel(), store = gridPanel.getStore();
	    
        // 装载数据
        store.load();

	    // 设置首行选中
        store.on("load", function(obj, records){
        	gridPanel.getSelectionModel().select(0);
        	me.reloadGroupUsers(records[0]);
        });
	},
	// 数据集合默认选中第一行
    reloadGroupUsers: function(record) { 
        // 检查记录是否存在
        if (record) {
            this.getUserGridPanel().getStore().load({
                params: {
                	sysid: record.get("id")
                }
            });
        }
    }
});