Ext.define('LoginModule.controller.LoginController', {
	extend: 'Ext.app.Controller',
    
    // 对于代操作对象的引用
    refs: [
        {ref: 'loginForm',      selector: 'form'}, 
        {ref: 'resetBtn',       selector: 'button[action=reset]'}, 
        {ref: 'loginBtn',       selector: 'button[action=login]'}, 
        {ref: 'usernameField',  selector: 'textfield[name=username]'}, 
        {ref: 'passwordField',  selector: 'textfield[name=pass]'}, 
        {ref: 'hiddenpassField',  selector: 'textfield[name=password]'}
    ],
    
    // Cotroller的业务处理
    init: function() {
        this.control({
            'loginform button[action=reset]': {// 输入项重置
                click: function(){
                    this.getLoginForm().getForm().reset();
                    this.getUsernameField().focus(false, 100);
                }
            },
            'loginform textfield[name=pass]': {// 密码项目的事件处理
                specialkey: function(field, e){
                    if (e.getKey() == e.ENTER) {
                        this.getLoginBtn().getEl().dom.click();
                    }
                }
            },
            'loginform button[action=login]': {// 登录事件
                click: function() {
                    var formObj = this.getLoginForm().getForm();// form表单对象 
                    var usernameField = this.getUsernameField();// 焦点组件
                    var passwordField = this.getHiddenpassField();// 密码函数
                    
                    // 在客户端对数据进行加密
                    passwordField.setValue(hex_md5(this.getPasswordField().getValue()));

                    // 检查表单项的录入是否存在问题
                    if (formObj.isValid()) {
                        // 提交表单
                        formObj.submit({
                            waitMsg: '数据正在处理请稍后', // 提示信息  
                            waitTitle: '提示', // 标题  
                            url: 'services/systemLogin', // 请求的url地址  
                            method: 'POST', // 请求方式  
                            data: {'username': usernameField.getValue(), 'password': passwordField.getValue()},
                            success: function(form, action) { // 添加数据成功后，重新加载数据源刷新表单 
                                window.location.href = "main";
                            },
                            failure: function(form, action) { // 添加失败后，提示用户添加异常
                                Ext.Msg.alert('提示', '系统错误，原因：' + action.result.failureReason, function(){
                                	usernameField.focus(true, 100);
                                });
                            }
                        });
                    }
                }
            }
        });
    }
});