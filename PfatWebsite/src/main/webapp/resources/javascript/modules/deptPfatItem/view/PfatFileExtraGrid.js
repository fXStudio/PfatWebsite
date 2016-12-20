Ext.define('DeptPfatItemModule.view.PfatFileExtraGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.pfatfileextragrid',
	
	title: '加分文件',
	columns: [{ 
    	xtype: 'rownumberer',
    	align: 'center',
        header: '序号',
    	width: 50
	},  {
        header: '文件名称',
        width: 400,
        dataIndex: 'fileName'
    }, {
        header: '创建时间',
        width: 160,
        dataIndex: 'created'
    },{
        text: '',
        width: 30,
        menuDisabled: true,
        xtype: 'actioncolumn',
        align: 'center',
        iconCls: 'del'
    },{
        text: '',
        width: 30,
        menuDisabled: true,
        xtype: 'actioncolumn',
        align: 'center',
        iconCls: 'download'
    }],
    /**
     * Component Init
     */
    initComponent: function() {
        // Create Store Object
        var store = Ext.create('DeptPfatItemModule.store.PfatFileExtraStore');
        var me = this;

        // Copy properties to Origin Object
        Ext.apply(this, {
            store: store,
            tbar: {
                layout: 'hbox',
            	items: [Ext.create('Ext.form.Panel', {
                    layout: 'hbox',
                    id: 'extraGrid',
                    height: 30,
                    frame: false,
                    bodyStyle: 'padding: 4px;border:none',
                    flex: 2,
	                items: [{
	                    xtype: 'filefield',
	                    labelWidth: 70,
	                    flex: 3,
	                    labelAlign: 'right',
	                    fieldLabel: '加分文件',
	                    regex: /.(((doc|xls)x?)|pdf|jpg|png|bmp|gif|avi|mp4|wmv|flv)$/,
	                    regexText: '文件格式错误，只允许上传word、excel、图片、音视频文件',
	                    allowBlank: false,
	                    name: 'fileStream',
	                    buttonText: '',
	                    buttonConfig: {
	                        iconCls: 'upload'
	                    }
	                }, {
	                	xtype: 'textfield',
	                	name: 'cate',
	                	value: '1',
	                	hidden: true
	                }, {
	                	xtype: 'textfield',
	                	name: 'pfatitemId',
	                	id: 'extra_pfatItemId',
	                	hidden: true
	                }]
	            }), {
                    xtype: 'toolbar',
            		flex: 2,
                    style: 'border:none',
                    items: [{
                        text: '上传文件',
                        handler: function(){
                            var form = Ext.getCmp('extraGrid').getForm();
                            if(form.isValid()){
                                Ext.MessageBox.confirm('确认上传', '文件上传后无法修改，需删除旧文件后重新上传，要继续上传吗?', function(res) {
            	                	if (res === 'yes') {
	            	                	form.submit({
		                                    url: 'services/pfatfileUpload',
		                                    method: 'POST', // 请求方式  
		                                    waitMsg: '文件上传中，请稍后...',
		                                    success: function(form, action) {
		                                    	store.reload();
		                                    },
		                                    failure: function(form, action) { // 添加失败后，提示用户添加异常
		                                    	console.log(action.result.failureReason);
		                                        Ext.Msg.alert('提示', '系统错误，原因：' + action.result.failureReason);
		                                    }
		                                });
            	                	}
            	                });
                            }
                        }
                    },{
                        text: '重置',
                        handler: function() {
                            Ext.getCmp('extraGrid').getForm().reset();
                        }
                    }, '->',  '<span class="warn_msg" style="color:red">* 注意: 文件名称应能够清晰表达目的, 这里文档用户考核加分</span>']
            	}]
            }
        });
        this.callParent(arguments);
    }
});