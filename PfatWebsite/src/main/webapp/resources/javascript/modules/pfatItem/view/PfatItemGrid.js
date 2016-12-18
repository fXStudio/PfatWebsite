Ext.define('PfatItemModule.view.PfatItemGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.pfatitemgrid',
	
    selModel: { checkOnly: true },
    defaults: { sortable: true },
    columns: [{ 
    	xtype: 'rownumberer',
    	align: 'center',
        header: '序号',
    	width: 50
	}, {
        header: '名称',
        width: 160,
        dataIndex: 'itemName'
    }, {
        header: '责任部门',
        width: 120,
        dataIndex: 'itemName'
    }, {
        header: '完成时限',
        width: 90,
        dataIndex: 'itemName'
    }, {
        header: '分值',
        width: 60,
        dataIndex: 'itemName'
    }, {
        header: '数据材料',
        width: 160,
        dataIndex: 'itemName'
    }, {
        header: '负责处室',
        width: 120,
        dataIndex: 'itemName'
    }, {
        header: '负责人',
        width: 90,
        dataIndex: 'itemName'
    }, {
        header: '办公电话',
        width: 120,
        dataIndex: 'itemName'
    }, {
        id: 'itemLink',
        header: '备注',
        width: 300,
        dataIndex: 'itemPath'
    }],

    /**
     * Component Init
     */
    initComponent: function() {
        // Create Store Object
        var store = Ext.create('PfatItemModule.store.CateStore');

        // Copy properties to Origin Object
        Ext.apply(this, {
            tbar: Ext.create('Ext.toolbar.Toolbar', {
                items: [{
                    text: '添加',
                    id: 'addBtn',
                    iconCls: 'add',
                    action: 'add',
                    disabled: true
                }, {
                    text: '修改',
                    id: 'modifyBtn',
                    iconCls: 'update',
                    action: 'modify',
                    disabled: true
                }, '-', {
                    text: '删除',
                    id: 'delBtn',
                    iconCls: 'del',
                    action: 'del',
                    disabled: true
                }]
            })
        });
        this.callParent(arguments);
    }
});