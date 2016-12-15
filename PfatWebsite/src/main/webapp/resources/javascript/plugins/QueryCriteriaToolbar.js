/**
 * 基本的查询工具条
 */
Ext.define('Ext.plugins.QueryCriteriaToolbar', {
	extend: 'Ext.container.ButtonGroup',
	alias: 'widget.querycriteriatoolbar',

	stationHidden: true,
	hideBlank: false,
	hideType: true,
	hideAxisum: false,
	hideInput: false,
	convertflag: true,
	cartype: true,
	blankLabel: '过滤空牌',
	cardType: '',
	// 组件初始化函数，在组建初始化的时候，可以进行内部组件绑定
    initComponent: function() {
    	var store = this.store,
    	    el = this;
    	
        // 创建工具条
        Ext.apply(this, {
        	xtype: 'buttongroup',
        	bodyPadding: 5,
    		items: [{
        		fieldLabel: '时间',
        		labelWidth: 35,
        		labelAlign: 'right',
	           	id: 'beginDate',
	   	        xtype: 'datefield',
	   	        format: 'Y/m/d',
	   	        width: 140,
	   	        editable: false,
	   	        value: new Date()
        	}, {
        		fieldLabel: '至 ',
        		labelWidth: 30,
        		labelAlign: 'right',
	   	        id: 'endDate',
	   	        xtype: 'datefield',
	   	        format: 'Y/m/d',
	   	        width: 135,
	   	        editable: false,
	   			value: Ext.Date.add(new Date(), Ext.Date.DAY, 1)
	   	   }, Ext.create('Ext.ux.TreeCombo', {
			   	id: 'dept',
			   	fieldLabel: '收费站',
			   	labelAlign: 'right',
	            renderName: 'buildingTree',
	            editable: false,
	            labelWidth: 55,
				width: 255,
				hidden: this.stationHidden,
	            tpl: "<tpl for='.'><div style='height:200px'><div id='buildingTree'></div></div></tpl>",
	            store: Ext.create('Ext.data.TreeStore', {
	            	autoLoad: true,
	            	autoDestroy: true,
	            	fields: ["sn", "text"],
	                root: { expanded: true },
	                proxy: {
	                    type : 'ajax',
	                    actionMethods: { read: 'POST' },
	                    url : 'services/fsOrgDeptList',//请求  
	                },
	                listeners: {
	                	load: function() {Ext.getDom('searchBtn').click();}
	                }
	            })
		   }), {
	   		   id: 'axisum',
			   xtype: 'combobox',
			   fieldLabel: '轴型',
	           labelWidth: 45,
	           labelAlign: 'right',
			   width: 140,
			   editable: false,
			   store: Ext.create('Ext.data.Store', {
				   fields: ['axisum', 'desc'],
				   data : [
					   {"axisum":"", "desc":"全部"},
				       {"axisum":"2", "desc":"2轴"},
				       {"axisum":"3", "desc":"3轴"},
				       {"axisum":"4", "desc":"4轴"},
				       {"axisum":"5", "desc":"5轴"},
				       {"axisum":"6", "desc":"6轴"},
				       {"axisum":"7", "desc":"6轴以上"}
				   ]
			   }),
			   queryMode: 'local',
			   displayField: 'desc',
			   valueField: 'axisum',
			   allowBlank: true,
			   hidden: this.hideAxisum,
			   listeners: {
				   afterRender: function() {
					   this.setValue("");
					   this.setRawValue("全部");
				   }
			   }
		   }, {
	   		   id: 'invalidtype',
			   xtype: 'combobox',
			   fieldLabel: '分类',
	           labelWidth: 45,
	           labelAlign: 'right',
			   width: 130,
			   editable: false,
			   store: Ext.create('Ext.data.Store', {
				   fields: ['convertflag', 'desc'],
				   data : [
					   {"convertflag":"", "desc":"全部"},
				       {"convertflag":"0", "desc":"超速"},
				       {"convertflag":"1", "desc":"超时"}
				   ]
			   }),
			   queryMode: 'local',
			   displayField: 'desc',
			   valueField: 'convertflag',
			   allowBlank: true,
			   hidden: this.convertflag,
			   listeners: {
				   afterRender: function() {
					   this.setValue("");
					   this.setRawValue("全部");
				   }
			   }
		   }, {
	   		   id: 'cartype',
			   xtype: 'combobox',
			   fieldLabel: '类型',
	           labelWidth: 45,
	           labelAlign: 'right',
			   width: 140,
			   editable: false,
			   store: Ext.create('Ext.data.Store', {
				   fields: ['cartype', 'desc'],
				   data : [
					   {"cartype":"", "desc":"全部"},
				       {"cartype":"0", "desc":"车型不符"},
					   {"cartype":"1", "desc":"车牌不符"}
				   ]
			   }),
			   queryMode: 'local',
			   displayField: 'desc',
			   valueField: 'cartype',
			   allowBlank: true,
			   hidden: this.cartype,
			   listeners: {
				   afterRender: function() {
					   this.setValue("");
					   this.setRawValue("全部");
				   }
			   }
		   }, {
	   		    id: 'hiddenBlank',
		        xtype: 'checkbox',
		        fieldLabel: this.blankLabel,
	            labelWidth: 70,
	            labelAlign: 'right',
		        checked: true,
		        hidden: this.hideBlank
		   }, {
	   		    id: 'hiddenType',
		        xtype: 'checkbox',
		        fieldLabel: '过滤车型',
	            labelWidth: 70,
	            labelAlign: 'right',
		        checked: true,
		        hidden: this.hideType
		   }],
		   buttons: [{
	   		   id: 'queryField',
	   		   xtype: 'textfield',
		       fieldLabel: this.label,
	           labelWidth: 85,
	           labelAlign: 'right',
	   		   width: 185,
	   		   hidden: this.hideInput,
	           enableKeyEvents: true,
	           listeners: {
                   specialkey: function(field, e){
                       if (e.getKey() == e.ENTER) {
                    	   Ext.getDom('searchBtn').click();
                       }
                   }
	           } 
	   	   }, '<span style="margin:0 15;"></span>',{
 	   		   id: 'searchBtn',
	   		   xtype: 'button',
	   		   iconCls: 'search',
	   		   text: '查询',
	   		   listeners: {
	   			   click: function() {
	   				    var proxy = store.getProxy();
	   				    var queryField = Ext.getCmp('queryField');
	   				    var dept = Ext.getCmp('dept');
	   				    var hiddenBlank = Ext.getCmp("hiddenBlank");
	   				    var hiddenType = Ext.getCmp("hiddenType");
	   				    var axisum = Ext.getCmp("axisum");
	   				    var cartype = Ext.getCmp("cartype");
	   				    var invalidtype = Ext.getCmp("invalidtype");
		   		        var beginDate = Ext.util.Format.date(Ext.getCmp("beginDate").getValue(), 'Y/m/d');
		   		        var endDate = Ext.util.Format.date(Ext.getCmp("endDate").getValue(), 'Y/m/d');
		   		        
		   		        proxy.extraParams = store.baseParams || {};
		   		        proxy.extraParams[el.paramName] = queryField.getValue();
		   		        proxy.extraParams["beginDate"] = beginDate;// 开始日期
		   		        proxy.extraParams["endDate"] = endDate;// 结束日期
		   		        proxy.extraParams["stationId"] = dept.getValue();// 收费站
		   		        proxy.extraParams["cartype"] = cartype.getValue();// 车型
		   		        proxy.extraParams["hiddenBlank"] = hiddenBlank.getValue();// 过滤降型
		   		        proxy.extraParams["hiddenType"] = hiddenType.getValue();// 过滤空号牌
		   		        proxy.extraParams["axisum"] = axisum.getValue();// 轴数
		   		        proxy.extraParams["convertflag"] = invalidtype.getValue();// 轴数
		   		        proxy.extraParams["cardType"] = el.cardType;// 轴数
		   		        
		   		        store.reload({
		   		        	start : 0,
		   		            limit : 25
		   		        });
	   			   }
	   		   }
	   	   }]
        });
        
        return this.callParent(arguments);
    }
})