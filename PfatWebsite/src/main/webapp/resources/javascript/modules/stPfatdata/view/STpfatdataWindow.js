Ext.define('STpfatdataModule.view.STpfatdataWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.stpfatdatawindow',
	
	requires: [
       'STpfatdataModule.view.PfatFileNormalGrid',
       'STpfatdataModule.view.PfatFileExtraGrid'
    ],
    
    title: '文件列表',
    iconCls: 'feed-icon',
    resizable: false,
    modal: true,
    width: 600,
    height: 400,
    layout: 'fit',
    items: [{
        xtype: 'tabpanel',
        region: 'center',
        tabPosition: 'bottom',
        items: [{xtype:'pfatfilenormalgrid'}, {xtype: 'pfatfileextragrid'}]
    }]
});