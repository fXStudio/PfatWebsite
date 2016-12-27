<!DOCTYPE html>
<html>
    <head>
        <!-- Http Header Infomation -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Language" content="zh-cn" />
        <meta http-equiv="X-UA-Compatible" content="IE=Edge" />
        <meta http-equiv="Expires" content="0">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Cache" content="no-cache">
        <meta name="author" content="fxstudio.com.cn" />
        <meta name="description" content="fx-tech.cn" />
        <meta name="keywords" content="fx-tech.cn,Management,FXStudio"/>
        <meta name="Copyright" content="Copyright fx-tech.cn All Rights Reserved."/>

        <title>FreeWay WebSite</title>
        
        <!-- WebPage Style Desc -->
        <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    
        <!-- Load Javascript lib -->
	    <script type="text/javascript" src="javascript/plugins/FlexPaper/js/jquery.js"></script>
		<script type="text/javascript" src="javascript/plugins/FlexPaper/js/flexpaper_flash.js"></script>
    </head>

    <body>
    	<div style="position:absolute;width:100%;height:100%;top:0px;left:0px;">
            <a id="viewerPlaceHolder" style="width:100%;height:100%;display:block"></a>  
            <script type="text/javascript"> 
                var fp = new FlexPaperViewer(    
                         'javascript/plugins/FlexPaper/swfFiles/FlexPaperViewer', 
                         'viewerPlaceHolder',
                         { config : {
                         SwfFile : 'services/binary',
                         Scale : 1.6, 
                         ZoomTransition : 'easeOut',
                         ZoomTime : 0.5,
                         ZoomInterval : 0.2,
                         FitPageOnLoad : true,
                         FitWidthOnLoad : false,
                         PrintEnabled : false,
                         FullScreenAsMaxWindow : false,
                         ProgressiveLoading : false,
                         MinZoomSize : 0.2,
                         MaxZoomSize : 5,
                         SearchMatchAll : false,
                         InitViewMode : 'Portrait',
                         
                         ViewModeToolsVisible : true,
                         ZoomToolsVisible : true,
                         NavToolsVisible : true,
                         CursorToolsVisible : true,
                         SearchToolsVisible : true,
                           localeChain: 'en_US'
                         }});
            </script>
        </div>
    </body>
</html>
