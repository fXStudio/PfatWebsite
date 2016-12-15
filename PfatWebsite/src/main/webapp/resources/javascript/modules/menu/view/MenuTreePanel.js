Ext.define('MenuModule.view.MenuTreePanel', {
	extend: 'Ext.tree.TreePanel',
	alias: 'widget.menutreepanel',
	
    useArrows: true,
    autoScroll: true,
    animate: false,
    rootVisible: false,
    frame: false,
    border:false,
    height: 240,
    store: Ext.create('MenuModule.store.Tree')
})