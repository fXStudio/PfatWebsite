Ext.define('MainModule.view.TabPanel' ,{
	extend: 'Ext.tab.Panel',
	alias: 'widget.maintabpanel',
	
	requires: ["Ext.ux.TabCloseMenu"],
	
    resizeTabs: false,
    enableTabScroll: true,
    items: {
        title: '系统简介',
        iconCls: 'tabs',
        html: '<iframe src="describe" width="100%" height="100%"></iframe>'
    },
    plugins: [{
    	ptype: 'tabclosemenu'
    }]
});