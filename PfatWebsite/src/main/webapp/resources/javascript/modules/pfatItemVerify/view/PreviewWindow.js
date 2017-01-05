Ext.define('PfatItemVerifyModule.view.PreviewWindow', {
	extend: 'Ext.window.Window',
	alias: 'widget.previewwindow',
	
	title: '在线预览',
    iconCls: 'feed-icon',
    resizable: false,
    modal: true,
    maximized: true,
    closeAction: 'destroy',
    layout: 'fit',
    items: {
    	id: 'preview',
    	html: '<iframe src="preview" width="100%" height="100%" scrolling="no"></iframe>'
    }
});