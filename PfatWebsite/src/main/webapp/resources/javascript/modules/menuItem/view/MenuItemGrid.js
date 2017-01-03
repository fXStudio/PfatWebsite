Ext.define('MenuItemModule.view.MenuItemGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.menuitemgrid',
	
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
        header: '菜单项名称',
        width: 240,
        dataIndex: 'itemName'
    }, {
        id: 'itemLink',
        header: '访问路径',
        width: 300,
        dataIndex: 'itemPath'
    }, {
        header: '锁定',
        width: 75,
        renderer: function (value) {return value === '1' ? '是' : '--';},
        dataIndex: 'islock'
    }],

    /**
     * Component Init
     */
    initComponent: function() {
        // Create Store Object
        var store = Ext.create('MenuItemModule.store.MenuItem');

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
            bbar: ['->', '查询菜单项',{
	                xtype: 'textfield',
	                name: 'searchField',
	                selectOnFocus: true,
	                hideLabel: true,
	                width: 200
	           }, '|', {
	          iconCls: 'x-tbar-loading',
	          style: 'margin-right:20px',
	          listeners: {
	              click: function() {
	                  store.reload();
	              }
	          }
	        }]
        });
        // Call Parent Constructor
        this.callParent(arguments);
    }
});