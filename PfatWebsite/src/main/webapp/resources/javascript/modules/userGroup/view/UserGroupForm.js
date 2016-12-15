Ext.define('UserGroupModule.view.UserGroupForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.usergroupform',
	
    border: false, // 不显示边线
    defaults: { // 组件的默认样式配置
    	xtype: 'textfield',
        labelWidth: 85, // label的默认宽度
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
        id: 'menus',
        name: 'menus',
        hidden: true,
        hideLabel: true
    }, {
        id: 'groupname',
        fieldLabel: '<span class="must">*</span>用户组名称',
        name: 'groupName',
        allowBlank: false,
        selectOnFocus: true,
        blankText: '用户组名称不能为空'
    }, {
        xtype: 'textarea',
        fieldLabel: '用户组描述',
        selectOnFocus: true,
        name: 'remark'
    }]
});