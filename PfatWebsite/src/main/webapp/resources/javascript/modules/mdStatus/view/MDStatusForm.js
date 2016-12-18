Ext.define('MDStatusModule.view.MDStatusForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.mdstatusform',
	
    border: false, // 不显示边线
    defaults: { // 组件的默认样式配置
    	xtype: 'textfield',
        labelWidth: 70, // label的默认宽度
        labelAlign: 'right',
        cls: 'fxstudio-window-inner-margin3',
        labelStyle: 'margin-top:3px;',
        anchor: "98%"
    },
    items: [{
        fieldLabel: '主键',
        name: 'id',
        hidden: true,
        hideLabel: true
    }, {
        id: 'statusname',
        fieldLabel: '状态名称',
        name: 'statusName',
        maxLength: 30,
        selectOnFocus: true,
        allowBlank: false
    }, {
        fieldLabel: '备注',
        name: 'remark',
        maxLength: 255,
        allowBlank: false,
        selectOnFocus: true,
        enableKeyEvents: true
    }, {
        xtype: 'checkbox',
        fieldLabel: '限管理员',
        name: 'sPrivilege',
        inputValue: '1',
        style: 'margin-top: 6px;'
    }]
});