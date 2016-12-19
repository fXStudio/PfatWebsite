Ext.define('DeptPfatItemModule.view.DeptPfatItemGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.deptpfatitemgrid',
	
    requires: ["Ext.plugins.Paging"],
	
    selModel: { checkOnly: true },
    defaults: { sortable: true },
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
        		return "<span class='pfat-label commit-review'>申请审核</span>";
        	case 2:
        		return "<span class='pfat-label process-pfatitem'>处理中</span>";
        	case 4:
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
        var store = Ext.create('DeptPfatItemModule.store.PfatItemStore');

        // Copy properties to Origin Object
        Ext.apply(this, {
            store: store
        });
        // Call Parent Constructor
        this.callParent(arguments);
    }
});