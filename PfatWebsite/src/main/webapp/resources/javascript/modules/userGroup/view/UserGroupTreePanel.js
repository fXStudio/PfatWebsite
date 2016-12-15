Ext.define('UserGroupModule.view.UserGroupTreePanel', {
	extend: 'Ext.tree.TreePanel',
	alias: 'widget.usergrouptreepanel',
	
    useArrows: true,
    autoScroll: true,
    animate: false,
    rootVisible: false,
    frame: false,
    border:false,
    height: 245,
    store: Ext.create('UserGroupModule.store.Tree')
})