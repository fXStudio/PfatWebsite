Ext.define('PfatItemModule.view.PfatItemGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.pfatitemgrid',

    xtype: 'cell-editing',
    columns: [{ 
    	xtype: 'rownumberer',
    	align: 'center',
        header: '序号',
    	width: 50
	}, {
        header: '名称',
        width: 160,
        dataIndex: 'itemName',
        field: {
            xtype: 'textfield'
        }
    }, {
        header: '责任部门',
        width: 120,
        dataIndex: 'deptId',
        field: {
            xtype: 'combobox',
            store: [
                ['Shade','Shade'],
                ['Mostly Shady','Mostly Shady'],
                ['Sun or Shade','Sun or Shade'],
                ['Mostly Sunny','Mostly Sunny'],
                ['Sunny','Sunny']
            ]
        }
    }, {
        header: '完成时限',
        width: 120,
        dataIndex: 'compDate',
        renderer: Ext.util.Format.dateRenderer('Y-m-d'),
        field: {
            xtype: 'datefield',
            format: 'Y-m-d',
            minValue: '01/01/06'
        }
    }, {
        header: '分值',
        width: 60,
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
            xtype: 'textfield'
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

    /**
     * Component Init
     */
    initComponent: function() {
        // Create Store Object
        var store = Ext.create('PfatItemModule.store.PfatItemStore');
        var rowEditing = Ext.create('Ext.grid.plugin.RowEditing', {
        	errorSummary: false,
        	listeners: {
                cancelEdit: function(rowEditing, context) {
                    if (!context.record.phantom) {
                        store.remove(context.record);
                    }
                }
            }
        });
        var me = this;

        // Copy properties to Origin Object
        Ext.apply(this, {
        	store: store,
            plugins: [rowEditing],
            tbar: Ext.create('Ext.toolbar.Toolbar', {
                items: [{
                    text: '添加',
                    id: 'addBtn',
                    iconCls: 'add',
                    action: 'add',
                    disabled: true,
                    handler: function(){
                        store.insert(0, new PfatItemModule.model.PfatItem());
                        rowEditing.startEdit(0, 0);
                    }
                }, {
                    text: '修改',
                    id: 'modifyBtn',
                    iconCls: 'update',
                    action: 'modify',
                    disabled: true,
                    handler: function(){
                        var selection = me.getView().getSelectionModel().getSelection()[0];
                        if (selection) {
                            rowEditing.startEdit(0, 0);
                        }
                    }
                }, '-', {
                    text: '删除',
                    id: 'delBtn',
                    iconCls: 'del',
                    action: 'del',
                    disabled: true,
                    handler: function(){
                        var selection = me.getView().getSelectionModel().getSelection()[0];
                        if (selection) {
                            store.remove(selection);
                        }
                    }
                }]
            })
        });
        this.callParent(arguments);
    }
});