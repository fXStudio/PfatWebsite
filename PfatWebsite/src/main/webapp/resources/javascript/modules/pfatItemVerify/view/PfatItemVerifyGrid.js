Ext.define('PfatItemVerifyModule.view.PfatItemVerifyGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.pfatitemverifygrid',
	
    columns: [{ 
    	xtype: 'rownumberer',
    	align: 'center',
        header: '序号',
    	width: 50
	},{
        text: '',
        width: 30,
        menuDisabled: true,
        xtype: 'actioncolumn',
        align: 'center',
        iconCls: 'fail'
    },{
        text: '',
        width: 30,
        menuDisabled: true,
        xtype: 'actioncolumn',
        align: 'center',
        iconCls: 'return'
    },{
        text: '',
        width: 30,
        menuDisabled: true,
        xtype: 'actioncolumn',
        align: 'center',
        iconCls: 'commit'
    },{
        text: '',
        width: 30,
        menuDisabled: true,
        xtype: 'actioncolumn',
        align: 'center',
        iconCls: 'postil',
        tooltip: '未通过原因',
        isDisabled: function(view, rowIndex, colIndex, item, record){
            return !record.get('postil');
        }
    }, {
        header: '状态',
        width: 120,
        dataIndex: 'status',
        align: 'center',
        renderer: function(val){
        	switch(val){
        	case 1:
        		return "<span class='pfat-label verify-pfatitem'>申请审核</span>";
        	}
        }
    }, {
        header: '名称',
        width: 160,
        dataIndex: 'itemName'
    }, {
        header: '完成时限',
        width: 100,
        dataIndex: 'compDate'
    }, {
        header: '分值',
        width: 50,
        dataIndex: 'itemScore'
    }, {
        header: '数据材料',
        width: 160,
        dataIndex: 'docName'
    }, {
        header: '负责处室',
        width: 120,
        dataIndex: 'officeName'
    }, {
        header: '负责人',
        width: 90,
        dataIndex: 'personName'
    }, {
        header: '办公电话',
        width: 100,
        dataIndex: 'telPhone'
    }, {
        header: '备注',
        width: 300,
        dataIndex: 'remark'
    }],

    /**
     * Component Init
     */
    initComponent: function() {
        // Create Store Object
        var store = Ext.create('PfatItemVerifyModule.store.PfatItemStore');
        var me = this;

        // Copy properties to Origin Object
        Ext.apply(this, {
            store: store,
            bbar: ['->', '查询考核项目',{
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