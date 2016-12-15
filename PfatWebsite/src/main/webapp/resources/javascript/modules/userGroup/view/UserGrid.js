Ext.define('UserGroupModule.view.UserGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.usergrid',

	tbar: [ '<strong>组内用户列表</strong>' ],
    selModel: { checkOnly: true },
    defaults: { sortable: true },
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
        header: '锁定',
        width: 75,
        sortable: true,
        renderer: function (value, p, r) { return value === '1' ? '是' : '--'; },
        dataIndex: 'isLock'
    }],
    
    /**
     * Component Init
     */
    initComponent: function() {
        // Copy properties to Origin Object
        Ext.apply(this, { store: Ext.create('UserGroupModule.store.User') });
        
        // Call Parent Constructor
        this.callParent(arguments);
    }
});