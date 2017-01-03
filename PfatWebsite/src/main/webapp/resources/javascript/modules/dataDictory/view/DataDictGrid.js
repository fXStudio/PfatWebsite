Ext.define('DataDictModule.view.DataDictGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.datadictgrid',
	
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
        header: '字典项名称',
        width: 160,
        dataIndex: 'itemName'
    }, {
        header: '字典项值',
        width: 140,
        dataIndex: 'itemVal'
    }, {
        header: '字典项描述',
        width: 460,
        dataIndex: 'remark'
    }],
    
    /**
     * Component Init
     */
    initComponent: function() {
        // Create Store Object
        var store = Ext.create('DataDictModule.store.DataDict');

        // Copy properties to Origin Object
        Ext.apply(this, {
            store: store,
            tbar: Ext.create('Ext.toolbar.Toolbar', {
                items: [
                   '<span style="color:red;padding-right:5px;">* 数据字典用于维护系统运参数, 修改前需仔细核对说明.</span>', '->', 
                   {
	                    text: '修改',
	                    width: 90,
	                    id: 'updatebtn',
	                    disabled: true,
	                    iconCls: 'update',
	                    action: 'modify'
                }]
            }),
            bbar: ['->', '查询字典项',{
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