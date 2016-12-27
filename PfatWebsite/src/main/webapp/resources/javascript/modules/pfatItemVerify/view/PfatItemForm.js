Ext.define('PfatItemVerifyModule.view.PfatItemForm', {
	extend: 'Ext.form.Panel',
	alias: 'widget.pfatitemform',
	
    border: false, // 不显示边线
    defaults: {
    	xtype: 'textfield'
    },
    items: [{
        name: 'id',
        hidden: true
    },{
        name: 'status',
        hidden: true
    },{
        name: 'itemName',
        hidden: true
    },{
        name: 'cateId',
        hidden: true
    },{
        name: 'compDate',
        hidden: true
    },{
        name: 'itemScore',
        hidden: true
    },{
        name: 'docName',
        hidden: true
    },{
        name: 'officeName',
        hidden: true
    },{
        name: 'personName',
        hidden: true
    },{
        name: 'telPhone',
        hidden: true
    },{
        name: 'remark',
        hidden: true
    },{
        name: 'deptId',
        hidden: true
    }, {
        id: 'postil',
        xtype: 'textarea',
        rows: 30,
        width: 400,
        name: 'postil',
        selectOnFocus: true,
        allowBlank: true
    }]
});