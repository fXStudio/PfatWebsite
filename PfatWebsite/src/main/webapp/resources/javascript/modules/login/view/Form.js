Ext.define('LoginModule.view.Form' ,{
    extend: 'Ext.panel.Panel',
    alias: 'widget.loginform',
    
    layout: 'border',
    items: [{
    	region: 'north',
    	height: 30,
        split: false,
        border: false,
        bodyStyle: 'background-color:#f5f7f9;'
    }, {
    	xtype: 'form',
    	region: 'north',
        split: false,
    	defaults: {
	    	xtype: 'textfield',
	        labelWidth: 90,
	        labelAlign: 'right',
	        anchor: '98%',
	        allowBlank: false,
	        labelStyle: 'padding-right: 5px;font-size:12pt;padding-top:4pt;'
	    },
	    items: [
	        {
	            id: 'username',
	            name: 'username',
	            fieldLabel: '用户名',
	            style: 'margin-top:10px;',
	            selectOnFocus: true
	        }, {
	            inputType: 'password',
	            name: 'pass',
	            fieldLabel: '密　码',
	            maxLength: 16,
	            enableKeyEvents: true,
	            selectOnFocus: true
	        }, {
	            inputType: 'password',
	            name: 'password',
	            fieldLabel: '要提交到后台的代码',
	            hidden: true
	        }, {
	        	xtype: 'numberfield',
                name: 'createYear',
                editable: false,
                step: 1,
                fieldLabel: '考核年份',
                value: new Date().getFullYear(),
                minValue: 2016,
                maxValue: new Date().getFullYear() + 100,
                allowBlank: false
            }
	    ],
	    buttons: [
	        {text: '重置', action: 'reset'}, 
	        {text: '登录', type: 'submit',action: 'login'}
	    ]
	 }]
});