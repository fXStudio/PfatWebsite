/**
 * 系统登录
 * 
 * @Author Ajaxfan
 */
Ext.application({
    name: 'LoginModule',
    appFolder: 'javascript/modules/login', 
    views: ['Window'],// 加载视图控件
    controllers: ['LoginController'],// 加载控制器控件
    
    launch: function() {// 应用程序执行器
        var win = Ext.create('widget.loginWindow');// 创建窗体对象
        win.show();// 显示窗体对象
        win.center();// 居中显示
        
        Ext.getCmp('username').focus(false, 100);// 设置默认的焦点
        
   		// 设置登录窗体的居中状态
	    Ext.EventManager.onWindowResize(function() { win.center(); });
    }
});

