Ext.define('SystemUserModule.view.SystemUserGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.systemusergrid',

    selModel: { checkOnly: true },
    defaults: { sortable: true },
	enableColumnHide: false,
	sortableColumns: false,
	enableColumnMove: false,
    columns: [{ 
    	xtype: 'rownumberer',
    	align: 'center',
        header: '序号',
    	width: 50
	}, {
        header: '用户名称',
        width: 160,
        dataIndex: 'username'
    }, {
        header: '创建时间',
        width: 160,
        dataIndex: 'created'
    }, {
        header: '是否锁定',
        width: 95,
        renderer: function (value, p, r) { return value === '1' ? '是' : '--'; },
        dataIndex: 'islock'
    }],
    
    /**
     * Component Init
     */
    initComponent: function() {
        // Create Store Object
        var store = Ext.create('SystemUserModule.store.SystemUser');

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
            })
        });
        // Call Parent Constructor
        this.callParent(arguments);
    }
});