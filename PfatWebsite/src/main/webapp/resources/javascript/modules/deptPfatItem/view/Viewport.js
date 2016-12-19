Ext.define('DeptPfatItemModule.view.Viewport', {
    extend: 'Ext.container.Viewport',

    requires: ['DeptPfatItemModule.view.DeptPfatItemGrid'],

    layout: 'border',
    items: [{
        xtype: 'deptpfatitemgrid',
        region: 'north',
        height: 200,
        resizable: true
    }, {
    	xtype: 'tabpanel',
    	region: 'center',
    	tabPosition: 'bottom',
    	items: [{
            title: '考核文件',
            tbar: {
                layout: 'hbox',
            	items: [Ext.create('Ext.form.Panel', {
                    layout: 'hbox',
                    id: 'pfat-form',
                    height: 30,
                    frame: false,
                    bodyStyle: 'padding: 4px;border:none',
                    flex: 2,
	                items: [{
	                    xtype: 'textfield',
	                    name: 'fileName',
	                    flex: 2,
	                    fieldLabel: '图片名称',
	                    labelWidth: 60,
	                    labelAlign: 'right'
	                },{
	                    xtype: 'filefield',
	                    labelWidth: 50,
	                    flex: 2,
	                    labelAlign: 'right',
	                    fieldLabel: '文件',
	                    name: 'pfatFile',
	                    buttonText: '',
	                    buttonConfig: {
	                        iconCls: 'upload'
	                    }
	                }]
	            }), {
                    xtype: 'toolbar',
            		flex: 2,
                    style: 'border:none',
                    items: [{
                        text: '上传文件',
                        handler: function(){
                            var form = Ext.getCmp('pfat-form').getForm();
                            if(form.isValid()){
                                form.submit({
                                    url: 'services/pfatItemFileUpload',
                                    method: 'POST', // 请求方式  
                                    waitMsg: '文件上传中，请稍后...',
                                    success: function(fp, o) {
                                    	console.log("文件上传成功");
                                    },
                                    failure: function(form, action) { // 添加失败后，提示用户添加异常
                                        Ext.Msg.alert('提示', '系统错误，原因：' + action.result.failureReason, function(){
                                        	usernameField.focus(true, 100);
                                        });
                                    }
                                });
                            }
                        }
                    },{
                        text: '重置',
                        handler: function() {
                            Ext.getCmp('pfat-form').getForm().reset();
                        }
                    }, '->',  '<span class="warn_msg">* 文件名称为可选项，如果不填写则使用上传文件的默认名</span>']
            	}]
            },
        	xtype: 'deptpfatitemgrid'
        }, {
            title: '加分文件',
        	xtype: 'deptpfatitemgrid'
        }]
    }]
});
