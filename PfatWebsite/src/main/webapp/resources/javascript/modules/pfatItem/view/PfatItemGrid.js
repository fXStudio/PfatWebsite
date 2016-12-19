Ext.define('PfatItemModule.view.PfatItemGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.pfatitemgrid',

    xtype: 'cell-editing',

    /**
     * Component Init
     */
    initComponent: function() {
        var me = this;
        var store = Ext.create('PfatItemModule.store.PfatItemStore');
        var combobox = Ext.create('PfatItemModule.store.DeptStore');
        var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
        	errorSummary: false,
        	listeners: {
                cancelEdit: function(rowEditing, context) {
                    if (!context.record.data.id) {
                        store.remove(context.record);
                    }
                    me.down('#addBtn').setDisabled(false);
                },
                edit: function (editor, context){
                	context.record.data.cateId = Ext.getCmp('cateid').getValue();
                	context.record.data.compDate = Ext.util.Format.date(context.record.data.compDate, 'Y-m-d');
                	
         		    var mask = new Ext.LoadMask(me, {msg:"数据处理中请稍后......"});
         		    mask.show();
         		    Ext.Ajax.request({
                         url: 'services/pfatitemModify',
                         params: context.record.data,
                         method: 'POST',
                         success: function(response, options) {
                             var obj = Ext.decode(response.responseText);

                             if (obj.success) {// 根据不同的删除状态，做不同的提示
                             	store.reload();
                             	store.on({
                             		'load': function(){
                        	              mask.hide();
                             		}
                             	})
                             } else {
                  	             mask.hide();
                                 Ext.MessageBox.alert('失败', '删除失败, 原因：' + obj.failureReason);
                             }
                         },
                         failure: function(response, options) {
              	             mask.hide();
                             Ext.MessageBox.alert('失败', '请求超时或网络故障, 错误编号：' + response.status);
                         }
                    });
                    me.down('#addBtn').setDisabled(false);
                }
            }
        });

        // Copy properties to Origin Object
        Ext.apply(this, {
        	store: store,
            plugins: [rowEditing],
            columns: [{
                xtype: 'actioncolumn',
                width: '12',
                iconCls: 'del',
                align: 'center',
                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
                    if (!rowEditing.editing) {
    	                Ext.MessageBox.confirm('确认删除', '你确定要删除考核项目名称为【' + record.data.itemName + '】的记录吗?', function(res) {
    	                    if (res === 'yes') { // 用户确认要执行删除操作
                                store.remove(record);
                                store.sync();
    	                    }
    	                });
                    }
                }
            }, {
                xtype: 'actioncolumn',
                width: '12',
                iconCls: 'update',
                align: 'center',
                handler: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
                	if(!rowEditing.editing){
                		rowEditing.startEdit(rowIndex, 0);
                	}
	    		}
            }, {
                header: '名称',
                width: 160,
                dataIndex: 'itemName',
                field: {
                    xtype: 'textfield',
                    allowBlank: false,
                    validator: function(val){
                    	var selItemName = me.getSelectionModel().getLastSelected().get('itemName');
                    	
                    	if(val != selItemName && store.findRecord('itemName', val, 0, false, false, true)){
                    		return "考核项目名称重复";
                    	}
                    	return true;
                    }
                }
            }, {
                header: '责任部门',
                width: 220,
                dataIndex: 'deptId',
                renderer: function(val){
                	try {
                		return combobox.findRecord('id', val, 0, false, false, true).data.deptName;
                	}catch(e){}
                	return val;
                },
                field: {
                    xtype: 'combobox',
                    allowBlank: false,
                    store: combobox,
                    displayField: 'deptName',
                    valueField: 'id',
    	   	        editable: false
                }
            }, {
                header: '完成时限',
                width: 120,
                dataIndex: 'compDate',
                renderer: Ext.util.Format.dateRenderer('Y-m-d'),
                field: {
                    xtype: 'datefield',
                    allowBlank: false,
                    format: 'Y-m-d',
                    submitFormat: 'Y-m-d',
                    minValue: new Date(),
                    editable: false

                }
            }, {
                header: '分值',
                width: 80,
                dataIndex: 'itemScore',
                field: {
                    xtype: 'numberfield',
                    allowBlank: false,
                    editable: false,
                    minValue: 1,
                    maxValue: 100
                }
            }, {
                header: '数据材料',
                width: 160,
                dataIndex: 'docName',
                field: {
                    xtype: 'textfield',
                    allowBlank: false
                }
            }, {
                header: '负责处室',
                width: 120,
                dataIndex: 'officeName',
                field: {
                    xtype: 'textfield'
                }
            }, {
                header: '负责人',
                width: 90,
                dataIndex: 'personName',
                field: {
                    xtype: 'textfield'
                }
            }, {
                header: '办公电话',
                width: 100,
                dataIndex: 'telPhone',
                field: {
                    xtype: 'textfield',
                    regex: /^[0-9]{8,11}$/,
                    regexText: '数据格式错误，只能输入座机号(8位)或手机号(11位)'
                }
            }, {
                id: 'itemLink',
                header: '备注',
                width: 300,
                dataIndex: 'remark',
                field: {
                    xtype: 'textfield'
                }
            }],
            tbar: Ext.create('Ext.toolbar.Toolbar', {
                items: [{
                    text: '添      加',
                    id: 'addBtn',
                    iconCls: 'add',
                    action: 'add',
                    width:120,
                    disabled: true,
                    handler: function() {
                        store.insert(0, new PfatItemModule.model.PfatItem());
                        rowEditing.startEdit(0, 0);
                        this.setDisabled(true);
                    }
                }, {
                	xtype: 'textfield',
                	id: 'cateid',
                	name: 'cateid',
                	hidden: true
                }]
            })
        });
        this.callParent(arguments);
    }
});