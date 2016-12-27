Ext.define('STpfatdataModule.view.PfatFileExtraGrid', {
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
        width: 200,
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
        iconCls: 'download'
    }],
    /**
     * Component Init
     */
    initComponent: function() {
        // Create Store Object
        var store = Ext.create('STpfatdataModule.store.PfatFileExtraStore');
        var me = this;

        // Copy properties to Origin Object
        Ext.apply(this, {
            store: store
        });
        this.callParent(arguments);
    }
});