Ext.define('SystemUserModule.view.SystemUserForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.systemuserform',
	
    border: false, // 不显示边线
    defaults: { // 组件的默认样式配置
    	xtype: 'textfield', // 默认的组件类型
        labelWidth: 75, // label的默认宽度
        labelAlign: 'right',
        cls: 'fxstudio-window-inner-margin3',
        labelStyle: 'margin-top:3px;',
        anchor: '95%'
    },
    items: [{
        name: 'id',
        hidden: true,
        hideLabel: true
    }, {
        id: 'depid',
        name: 'deptId',
        hidden: true,
        hideLabel: true
    }, {
        id: 'groupid',
        name: 'groupId',
        hidden: true,
        hideLabel: true
    }, {
        id: 'hidepass',
        hidden: true,
        hideLabel: true
    }, {
        id: 'username',
        fieldLabel: '<span class="must">*</span>用户名称',
        maxLength: 16,
        name: 'username',
        selectOnFocus: true,
        allowBlank: false
    }, {
        fieldLabel: '<span class="must">*</span>用户密码',
        inputType: 'password',
        selectOnFocus: true,
        id: 'password',
        name: 'password',
        allowBlank: false,
        enableKeyEvents: true
    }, {
        xtype: 'checkbox',
        fieldLabel: '是否锁定',
        name: 'islock',
        inputValue:'1'
    }]
});