Ext.define('UserGroupModule.view.UserGroupGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.usergroupgrid',
	
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
        header: '用户组名称',
        width: 160,
        dataIndex: 'groupName'
    }, {
        header: '菜单描述',
        width: 300,
        dataIndex: 'remark'
    }],
    
    /**
     * Component Init
     */
    initComponent: function() {
        // Create Store Object
        var store = Ext.create('UserGroupModule.store.UserGroup');

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