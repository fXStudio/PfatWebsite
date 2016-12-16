Ext.define('MdDeptModule.view.MdDeptForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.mddeptform',
	
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
        id: 'deptname',
        fieldLabel: '部门名称',
        name: 'deptName',
        maxLength: 30,
        selectOnFocus: true,
        allowBlank: false
    }, {
        fieldLabel: '描述',
        name: 'remark',
        maxLength: 255,
        allowBlank: false,
        selectOnFocus: true,
        enableKeyEvents: true
    }]
});