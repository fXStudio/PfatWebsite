Ext.define('MainModule.controller.TreePanelController', {
    extend: 'Ext.app.Controller',

    stores: ['Tree'],
    
    refs: [
        {ref: 'treePanel', selector: 'maintreepanel'},
        {ref: 'tabPanel', selector: 'maintabpanel'}
    ],
	
    /**
     * Module Event Bind
     */
    init: function() {
	    this.control({
	    	'maintreepanel': {
        		'itemclick': function (view, record, item, index, e, option) {
                    if (record.get('leaf')) { 
                    	var tabPanel = this.getTabPanel();// TabPanel对象
                    	var sn = record.get('sn') + '_menuitem';// Node ID
                        var tabToCheck = tabPanel.getChildByElement(sn);// 检查标签是否已经打开
                        
                        // 如果节点已经打开，则只是激活标签，不在重复开启
                        if(tabToCheck){
                        	tabPanel.setActiveTab(sn);
                        } else {// 打开一个新的节点标签
                        	tabPanel.add({
	                            title: record.get('text'),
	                            id: sn,
	                            iconCls: 'tabs',
	                            html: '<iframe src=' + record.get('url') + ' width="100%" height="100%"></iframe>',
	                            closable: true,
	                            listeners: {
	                                beforedestroy: function(comp) { // 删除iframe防止浏览器内存泄露
	                                    var ifra = comp.body.dom.firstChild; // 从Dom树种获得iframe对象
	                                    ifra.src = "about:blank"; // Iframe内容置为空
	                                    comp.body.dom.removeChild(ifra); // 从dom树中移除Iframe对象
	                                    comp = ifra = null; // 引用置空以便垃圾回收器清理资源
	                                }
	                            }
	                        }).show();
                        }
                    } else {
                        if (record.get('expanded')) { view.collapse(record); } else { view.expand(record); }
                    }
                }
        	}
        })
    },
    
    /**
     * Module Launch
     */
	onLaunch: function() {
		// 获得数据源对象
	    var store = this.getTreeStore();
	    
	    // 设置数据源对象
	    this.getTreePanel().bindStore(store);
	    
        // 创建数据源的监听
	    store.on('load', function(records, successful, options) {
        	// 页面加载完成后去除等待窗体
            if (Ext.get('loading')) {
                setTimeout(function() {
                    Ext.get('loading').remove();
                    Ext.get('loading-mask').fadeOut({remove: true});
                }, 1 * 1000);
            }
        });
	}
});