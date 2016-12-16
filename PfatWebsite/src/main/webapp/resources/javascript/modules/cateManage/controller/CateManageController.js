Ext.define('CateManageModule.controller.CateManageController', {
    extend: 'Ext.app.Controller',
    refs: [
        {ref: 'gridPanel', selector: 'catemanagegrid'},
        {ref: 'window', selector: 'catemanagewindow'},
        {ref: 'formPanel', selector: 'catemanageform'}
     ],
    	

    /**
     * Module Launch
     */
	onLaunch: function() {
		// 应用启动，先创建窗口对象，避免后面数据的重复拉取
		Ext.create('CateManageModule.view.CateManageWindow');
		
	}
});