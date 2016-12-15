Ext.define('MainModule.view.TreePanel' ,{
    extend: 'Ext.tree.Panel',
    alias: 'widget.maintreepanel',
    
    title: '系统功能树',
    useArrows: false,// 展开按钮图标是箭头还是+-
    rootVisible: false,
    split: true,
    collapsible: true,
    autoScroll: true,
    padding: 5,
    width: 240
});