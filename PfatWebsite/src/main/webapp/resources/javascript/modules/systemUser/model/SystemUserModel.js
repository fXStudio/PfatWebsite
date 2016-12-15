/**
 * 树节点模型对象
 */
Ext.define('SystemUserModule.model.SystemUserModel', {
	extend: 'Ext.data.Model',
	fields: [
	    'id', 'username', 'password', 'created',  'islock', 'deptId', 'groupId'
    ]
});