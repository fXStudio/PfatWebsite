Ext.define('PfatItemModule.controller.PfatItemController', {
    extend: 'Ext.app.Controller',
    
    refs: [
        {ref: 'gridPanel', selector: 'categrid'},
        {ref: 'pfatItemPanel', selector: 'pfatitemgrid'},
        {ref: 'cateid', selector: 'textfield[name=cateid]'}
     ],
     init: function() {
 	    this.control({
	    	'categrid': {
	    		itemclick: function(view, record, item, index, e, eOpts){
	    			var isdisabled = !record.data.leaf;
	    			
	    			Ext.each(['addBtn'], function(id){
	    				Ext.getCmp(id).setDisabled(isdisabled);
	    			});
    				this.getCateid().setValue(record.data.id);
    				this.getPfatItemPanel().getStore().load({params: { cateId: record.data.id }});
	    		}
	    	},
 	    	'textfield[name=searchField]': {
	    		 specialkey: function(field, e){
	                 if (e.getKey() == e.ENTER) {
		    			 var gridObj = this.getGridPanel();
		    			 
		    			 gridObj.clearFilter();
		    			 gridObj.filterBy(function(node){
		    				 return node.get('cateName').indexOf(field.getValue()) > -1;
		    			 });
	                 }
                 }
	    	 }
 	    })
     },

     /**
      * Module Launch
      */
	 onLaunch: function() {
		var gridObj = this.getGridPanel();
		var pfatItemPanel = this.getPfatItemPanel();
		gridObj.on({// 节点重新展开后，需要提交过滤条件给组件，否则会出现数据不一致的问题
			'itemexpand': function(){
	   			 gridObj.filterBy(function(node){
	   				 return node.get('cateName').indexOf(Ext.getCmp('searchField').getValue()) > -1;
	   			 });
			} 
		});
		
		gridObj.getStore().on({
			'load': function(){
				Ext.getCmp('searchField').focus();
			}
		})
	 }
});