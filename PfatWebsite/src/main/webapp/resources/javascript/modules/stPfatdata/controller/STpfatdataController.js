Ext.define('STpfatdataModule.controller.STpfatdataController', {
    extend: 'Ext.app.Controller',
    stores: ['STpfatdataStore'],// 存储器
    refs: [
       {ref: 'gridPanel', selector: 'stpfatdatagrid'},
       {ref: 'normalPanel', selector: 'pfatfilenormalgrid'},
       {ref: 'extraPanel', selector: 'pfatfileextragrid'},
       {ref: 'window', selector: 'stpfatdatawindow'}
    ],
    
    // Cotroller的业务处理
    init: function() {
	    this.control({
             'textfield[name=searchField]': {
                 specialkey: function(field, e){
                     if (e.getKey() == e.ENTER) {
                         var gridPanel = this.getGridPanel();
                         gridPanel.getStore().filter({
                             filterFn: function(item) {
                                 return !field.getValue() || 
                                        item.get("itemName") && item.get("itemName").indexOf(field.getValue()) > -1; 
                             }
                         });
                     }
                 }
             },
             'button[name=export2Excel]': {
                 click: function(field, e){
                     
                 }
             },
             'actioncolumn[iconCls=detail]': {
                click: function(grid, rowIndex, colIndex, actionItem, event, record, row){
                    // 获得窗体对象的引用
                    var window = this.getWindow();
                    // 判断窗体对象是否存在, 如果不存在，就创建一个新的窗体对象
                    if(!window){window = Ext.create('STpfatdataModule.view.STpfatdataWindow');}
                    
                    Ext.each([this.getNormalPanel(), this.getExtraPanel()], function(panel, index){
                        panel.getStore().load({params: {pfatitemId: record.get('id'), cate: index}});
                    });
                    
                    window.show(); // 显示窗体
                    window.center();// 窗体居中显示
                }
             },
            'actioncolumn[iconCls=download]': {// 添加分类信息
                click: function(grid, rowIndex, colIndex, actionItem, event, record, row) {
                    window.location.href = "services/pfatfileDownload?id=" + record.data.id;
                 }
             }
        })
    },
    
    /**
     * Module Launch
     */
	onLaunch: function() {
		// 获得数据源对象
	    var gridPanel = this.getGridPanel(), store = gridPanel.getStore();
	    
        // 装载数据
        store.load();

	    // 设置首行选中
        store.on("load", function(store, records, successful, eots) {
            setTimeout(function(){
                gridPanel.merge(gridPanel, 0, 3);
            }, 0.5);
		});
		
		store.on("filterchange", function(store, filters, eOpts ) {
            gridPanel.merge(gridPanel, 0, 3);
        })
	}
});