Ext.define('CateManageModule.view.CateManageForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.catemanageform',
	
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
        name: 'id',
        hidden: true,
        hideLabel: true
    }, {
        name: 'level',
        hidden: true,
        hideLabel: true
    },{
        name: 'parentId',
        hidden: true,
        hideLabel: true
    },{
        id: 'catename',
        fieldLabel: '分类名称',
        name: 'cateName',
        maxLength: 30,
        selectOnFocus: true,
        allowBlank: false
    }, {
        fieldLabel: '分数',
        name: 'cateScore',
        maxLength: 3,
        regex: new RegExp("^[1-9]\\d{0,2}$"),
        regexText: '只能输入大于1的3位有效数字',
        allowBlank: false,
        selectOnFocus: true
    }, {
        fieldLabel: '备注',
        name: 'remark',
        maxLength: 255,
        selectOnFocus: true,
        enableKeyEvents: true
    }]
});