Ext.define('VerifyResultModule.view.VerifyResultGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.verifyresultgrid',
	
    requires: ["Ext.plugins.Paging"],
	
    columns: [{ 
    	xtype: 'rownumberer',
    	align: 'center',
        header: '序号',
    	width: 50
	}, {
        header: '状态',
        width: 120,
        dataIndex: 'status',
        align: 'center',
        renderer: function(val){
        	switch(val){
        	case 0:
        		return "<span class='pfat-label new-pfatitem'>新建项目</span>";
        	case 1:
        		return "<span class='pfat-label verify-pfatitem'>申请审核</span>";
        	case 2:
        		return "<span class='pfat-label pass-pfatitem'>审核通过</span>";
        	case 3:
        		return "<span class='pfat-label failure-pfatitem'>审核未通过</span>";
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
        var store = Ext.create('VerifyResultModule.store.PfatItemStore');
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