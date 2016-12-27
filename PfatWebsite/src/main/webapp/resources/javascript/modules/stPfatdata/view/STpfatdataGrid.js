Ext.define('STpfatdataModule.view.STpfatdataGrid', {
	extend: 'Ext.grid.Panel',
	alias: 'widget.stpfatdatagrid',
	
	rowLines: true,
	columnLines: true,
	enableColumnHide: false,
	disableSelection: false,
	sortableColumns: false,
	enableColumnMove: false,
	viewConfig: {
		stripeRows: true
	},
    columns: [{
        dataIndex: 'id',
        hidden: true
    }, {
        header: '绩效类别',
        width: 200,
        locked: true,
        align: 'center',
        dataIndex: 'firstCate'
    }, {
        header: '一级指标',
        width: 160,
        locked: true,
        align: 'center',
        dataIndex: 'secondCate'
    }, {
        header: '二级指标',
        width: 160,
        locked: true,
        align: 'center',
        dataIndex: 'thirdCate'
    }, {
        width: 0.1,
        locked: true,
        align: 'center'
    },{
        header: '三级指标',
        width: 240,
        dataIndex: 'itemName'
    }, {
        header: '完成时限',
        width: 90,
        dataIndex: 'compDate'
    },{
        header: '分值',
        width: 60,
        dataIndex: 'itemScore'
    }, {
        header: '数据材料',
        width: 100,
        dataIndex: 'docName'
    },{
        header: '部门名称',
        width: 160,
        dataIndex: 'deptName'
    }, {
        header: '办公室',
        width: 160,
        dataIndex: 'officeName'
    },{
        header: '电话',
        width: 90,
        dataIndex: 'telPhone'
    }, {
        header: '联系人',
        width: 80,
        dataIndex: 'personName'
    }, {
        header: '状态',
        width: 120,
        dataIndex: 'status',
        align: 'center',
        renderer: function(val) {
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
    },{
        text: '文件',
        width: 60,
        menuDisabled: true,
        xtype: 'actioncolumn',
        align: 'center',
        iconCls: 'detail'
    }],

    /**
     * Component Init
     */
    initComponent: function() {
        // Create Store Object
        var store = Ext.create('STpfatdataModule.store.STpfatdataStore');
        var panel = this;

        // Copy properties to Origin Object
        Ext.apply(this, {
        	store: store,
        	merge: function(panel, colstart, colend) {
        		var store = {};// 存放合并开始行信息
        		Ext.each(panel.view.lockedView.getNodes(), function(node, index) {// 递归查询所有的行对象
        			var cols = Ext.get(node).query('td');
        			
        			for(var i = colstart; i < Math.min(colend || 255, cols.length); i++){// 按照Excel的标准，最大列数位255
        				try {//  使用try...catch模块，可以减少过多的校验逻辑
        					var first = Ext.get(store[i].node),  second = Ext.get(cols[i]);
        					
        					if(second && second.query('div')[0].innerHTML != '&nbsp;') {// 空单元格不参与合并
            					if(first.query('div')[0].innerHTML === second.query('div')[0].innerHTML) {// 如果两行数据相同就需要合并
            						first.set({ rowSpan: ++store[i].count});// 设置合并单元格的样式
            						second.setStyle({ 'display': 'none' });// 隐藏需要合并的单元格
            						
            						continue;
            					}
        					}
        				} catch(e) {}
        				// 记录合并开始位置以及dom基本信息
        				store[i] = {node: cols[i], count: 1}; 
        				// 参与合并的列不带背景颜色
                        Ext.get(store[i].node).setStyle({ 'vertical-align': 'middle', 'background-color': 'transparent' });
        			}
        		});
        	},
            bbar: [{
                xtype: 'button',
                name: 'export2Excel',
                text: '导出 Excel',
                iconCls: 'excel'
            },'->', '查询考核项目',{
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