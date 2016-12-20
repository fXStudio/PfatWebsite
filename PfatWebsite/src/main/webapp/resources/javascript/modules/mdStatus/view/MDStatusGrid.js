Ext.define('MDStatusModule.view.MDStatusGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.mdstatusgrid',
	
    requires: ["Ext.plugins.Paging"],
	
    selModel: { checkOnly: true },
    defaults: { sortable: true },
    columns: [{ 
    	xtype: 'rownumberer',
    	align: 'center',
        header: '序号',
    	width: 50
	}, {
        header: '状态名称',
        width: 140,
        dataIndex: 'statusName'
    }, {
        header: '备注',
        width: 300,
        dataIndex: 'remark'
    }, {
        header: '仅管理员可见',
        width: 140,
        renderer: function (value) {return value === '1' ? '是' : '--';},
        dataIndex: 'sPrivilege'
    }],

    /**
     * Component Init
     */
    initComponent: function() {
        // Create Store Object
        var store = Ext.create('MDStatusModule.store.MDStatus');

        // Copy properties to Origin Object
        Ext.apply(this, {
            store: store,
            tbar: Ext.create('Ext.toolbar.Toolbar', {
                items: [{
                    text: '添加',
                    iconCls: 'add',
                    action: 'add'
                }, {
                    text: '修改',
                    iconCls: 'update',
                    action: 'modify'
                }, '-', {
                    text: '删除',
                    iconCls: 'del',
                    action: 'del'
                }]
            }),
            bbar: { // Bottom bar
                xtype: 'paging',
                store: store
            }
        });
        // Call Parent Constructor
        this.callParent(arguments);
    }
});