Ext.define('SystemUserModule.view.SystemUserTabPanel', {
	extend: 'Ext.tab.Panel',
	alias: 'widget.systemusertabpanel',
	
	items: [
	    {// 用户所在组的维护功功能
	    	title: '所属用户组',
	    	layout: 'fit',
	    	items: Ext.create('Ext.ux.form.ItemSelector', {
                id: 'itemselector-field',
                imagePath: 'javascript/extjs/ux/css/images/',
                store: Ext.create('Ext.data.Store', {
                	fields: ["groupName", "id", "remark"],
                	autoLoad: false,
                	autoDestroy: true,
                    autoSync: false,
                    root: { expanded: true },
                    proxy: {
                        type : 'ajax',
                        actionMethods: { read: 'POST' },
                        url : 'services/userGroupArray',//请求
                        reader: {
                            type: 'json',
                            root: 'items',
                            idProperty: 'id'
                        }
                    }
                }),
                displayField: 'groupName',
                valueField: 'id',
                allowBlank: true,
                msgTarget: 'side',
                fromTitle: '可分配用户组',
                toTitle: '已分配用户组'
            })
	    }, 
	    
	    // 用户所属部门的树菜单
	    Ext.create('Ext.tree.TreePanel', {
	        id: 'deptTree',
	        title: '所属部门',
	        useArrows: true,
	        autoScroll: true,
	        containerScroll: false,
	        rootVisible: false,
	        frame: false,
	        border:false,
	        height: 230,
	        store: Ext.create('SystemUserModule.store.DeptTree')
	    })
    ]
});