/**
 * 用户管理
 * 
 * @Author Renjian
 */
Ext.application({
    name: 'SystemUserModule',
    appFolder: 'javascript/modules/systemUser',
    controllers: [
      'SystemUserController', 
      'DeptTreePanelController'
    ],// 控制器

    autoCreateViewport: true,
    
    /**
     * 防止重复加载TreePanel
     */
    launch: function(){
    	Ext.create('SystemUserModule.view.SystemUserWindow');
    }
});

