Ext.define('MenuItemModule.view.MenuItemForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.menuitemform',
	
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
        id: 'itemname',
        fieldLabel: '菜单名称',
        name: 'itemName',
        maxLength: 30,
        selectOnFocus: true,
        allowBlank: false
    }, {
        fieldLabel: '访问路径',
        name: 'itemPath',
        maxLength: 255,
        allowBlank: false,
        selectOnFocus: true,
        enableKeyEvents: true
    }, {
        xtype: 'checkbox',
        fieldLabel: '是否锁定',
        name: 'islock',
        inputValue: '1',
        style: 'margin-top: 6px;'
    }]
});