Ext.define('SystemUserModule.controller.DeptTreePanelController', {
    extend: 'Ext.app.Controller',
    
    // Cotroller的业务处理
    init: function() {
	    this.control({
	    	'#deptTree': {
	    		'checkchange': function(node, checked) {
	            	// 如果修改子节点的选中状态，要先判其父节点是否有被选中
	            	checked = node.parentNode.get('checked') | checked;
	            	
	                // 取消掉前面选中的所有
	                Ext.each(node.getOwnerTree().getChecked(), function(_node) {
	                	_node.set('checked', (_node.checked = false));
	                	_node.set('cls', '');
	                });
	                
	                // 设置新的选中规则
	                if(checked) {
	                	node.cascadeBy(function(_node){
	                    	_node.set('checked', (_node.checked = true));
	                    	_node.set('cls', 'complete');
	                	});
	                	node.expand();
	                } else {
	                	node.set('cls', '');
	                }
	            },
	    		// 数据源初始化
	            'load': function(treeStore, node, records){
	            	// 展开被选中的节点
	            	if(node = records[0]){
	            		node.cascadeBy(function(_node){
	            			if(_node.get('checked')){
	            				while((_node = _node.parentNode)){
	            					_node.expand();
	            				}
	            				return;
	            			}
	            		});
	            	}
	            }
	    	}
	    })
    }
});