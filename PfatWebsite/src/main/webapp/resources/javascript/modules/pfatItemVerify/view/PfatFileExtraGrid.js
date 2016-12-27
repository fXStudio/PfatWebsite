Ext.define('PfatItemVerifyModule.view.PfatFileExtraGrid', {
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
        width: 400,
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
        tooltip: '下载',
        iconCls: 'download'
    },{
        text: '',
        width: 30,
        menuDisabled: true,
        xtype: 'actioncolumn',
        align: 'center',
        tooltip: '预览',
        iconCls: 'preview',
        isDisabled: function(view, rowIndex, colIndex, item, record){
            return !record.get('fileName').match(/\.(doc|docx|xls|xlsx)$/);
        }
    }],
    /**
     * Component Init
     */
    initComponent: function() {
        // Create Store Object
        var store = Ext.create('PfatItemVerifyModule.store.PfatFileExtraStore');
        var me = this;

        // Copy properties to Origin Object
        Ext.apply(this, {
            store: store
        });
        this.callParent(arguments);
    }
});