/**
 * 基本的查询工具条
 */
Ext.define('Ext.plugins.SimpleToolbar', {
	extend: 'Ext.toolbar.Toolbar',
	alias: 'widget.simpletoolbar',

	// 组件初始化函数，在组建初始化的时候，可以进行内部组件绑定
    initComponent: function() {
    	var store = this.store,
    	    el = this;
    	
        // 创建工具条
        Ext.apply(this, {
    		items: ['<span style="color:red;padding-right:5px;">* 输入查询条件进行数据过滤.</span>', '->', {
	   		   id: 'queryField',
	   		   xtype: 'textfield',
		       fieldLabel: this.label,
	           labelWidth: 155,
	           labelAlign: 'right',
	   		   width: 260,
	           enableKeyEvents: true,
	           listeners: {
                   specialkey: function(field, e){
                       if (e.getKey() == e.ENTER) {
                    	   Ext.getDom('searchBtn').click();
                       }
                   }
	           } 
	   	   }, {
 	   		   id: 'searchBtn',
	   		   xtype: 'button',
	   		   iconCls: 'search',
	   		   text: '查询',
	   		   width: 90,
	   		   listeners: {
	   			   click: function() {
	   				    var proxy = store.getProxy();
	   				    var queryField = Ext.getCmp('queryField');
		   		        
		   		        proxy.extraParams = store.baseParams || {};
		   		        proxy.extraParams[el.paramName] = queryField.getValue();
		   		        
		   		        store.reload();
	   			   }
	   		   }
	   	   }, '<span style="margin:0 0 0 10px;"></span>']
        });
        
        return this.callParent(arguments);
    }
})