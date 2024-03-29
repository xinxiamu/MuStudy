﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>jQuery ligerUI</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link
	href="${basePath}resources/ligerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" id="mylink" />
<script
	src="${basePath}resources/ligerUI/lib/jquery/jquery-1.3.2.min.js"
	type="text/javascript"></script>
<script src="${basePath}resources/ligerUI/lib/ligerUI/js/ligerui.all.js"
	type="text/javascript"></script>
<script
	src="${basePath}resources/ligerUI/lib/ligerUI/js/plugins/ligerTab.js"></script>
<script src="${basePath}resources/ligerUI/lib/jquery.cookie.js"></script>
<script src="${basePath}resources/ligerUI/lib/json2.js"></script>
<script src="${basePath}resources/ligerUI/indexdata.js"
	type="text/javascript"></script>
<script type="text/javascript">
	var tab = null;
	var accordion = null;
	var tree = null;
	var tabItems = [];
	$(function() {

		//布局
		$("#layout1").ligerLayout({
			leftWidth : 190,
			height : '100%',
			heightDiff : -34,
			space : 4,
			onHeightChanged : f_heightChanged
		});

		var height = $(".l-layout-center").height();

		//Tab
		$("#framecenter").ligerTab({
			height : height,
			showSwitchInTab : true,
			showSwitch : true,
			onAfterAddTabItem : function(tabdata) {
				tabItems.push(tabdata);
				saveTabStatus();
			},
			onAfterRemoveTabItem : function(tabid) {
				for (var i = 0; i < tabItems.length; i++) {
					var o = tabItems[i];
					if (o.tabid == tabid) {
						tabItems.splice(i, 1);
						saveTabStatus();
						break;
					}
				}
			},
			onReload : function(tabdata) {
				var tabid = tabdata.tabid;
				addFrameSkinLink(tabid);
			}
		});

		//面板
		$("#accordion1").ligerAccordion({
			height : height - 24,
			speed : null
		});

		$(".l-link").hover(function() {
			$(this).addClass("l-link-over");
		}, function() {
			$(this).removeClass("l-link-over");
		});
		//树
		$("#tree1").ligerTree({
			data : indexdata,
			checkbox : false,
			slide : false,
			nodeWidth : 120,
			attribute : [ 'nodename', 'url' ],
			onSelect : function(node) {
				if (!node.data.url)
					return;
				var tabid = $(node.target).attr("tabid");
				if (!tabid) {
					tabid = new Date().getTime();
					$(node.target).attr("tabid", tabid)
				}
				f_addTab(tabid, node.data.text, node.data.url);
			}
		});

		tab = liger.get("framecenter");
		accordion = liger.get("accordion1");
		tree = liger.get("tree1");
		$("#pageloading").hide();

		css_init();
		pages_init();
	});
	function f_heightChanged(options) {
		if (tab)
			tab.addHeight(options.diff);
		if (accordion && options.middleHeight - 24 > 0)
			accordion.setHeight(options.middleHeight - 24);
	}
	function f_addTab(tabid, text, url) {
		tab.addTabItem({
			tabid : tabid,
			text : text,
			url : url,
			callback : function() {
				addShowCodeBtn(tabid);
				addFrameSkinLink(tabid);
			}
		});
	}
	function addShowCodeBtn(tabid) {
		var viewSourceBtn = $('<a class="viewsourcelink" href="javascript:void(0)">查看源码</a>');
		var jiframe = $("#" + tabid);
		viewSourceBtn.insertBefore(jiframe);
		viewSourceBtn.click(function() {
			showCodeView(jiframe.attr("src"));
		}).hover(function() {
			viewSourceBtn.addClass("viewsourcelink-over");
		}, function() {
			viewSourceBtn.removeClass("viewsourcelink-over");
		});
	}
	function showCodeView(src) {
		$.ligerDialog.open({
			title : '源码预览',
			url : 'dotnetdemos/codeView.aspx?src=' + src,
			width : $(window).width() * 0.9,
			height : $(window).height() * 0.9
		});

	}
	function addFrameSkinLink(tabid) {
		var prevHref = getLinkPrevHref(tabid) || "";
		var skin = getQueryString("skin");
		if (!skin)
			return;
		skin = skin.toLowerCase();
		attachLinkToFrame(tabid, prevHref + skin_links[skin]);
	}
	var skin_links = {
		"aqua" : "${basePath}resources/ligerUI/lib/ligerUI/skins/Aqua/css/ligerui-all.css",
		"gray" : "${basePath}resources/ligerUI/lib/ligerUI/skins/Gray/css/all.css",
		"silvery" : "${basePath}resources/ligerUI/lib/ligerUI/skins/Silvery/css/style.css",
		"gray2014" : "${basePath}resources/ligerUI/lib/ligerUI/skins/gray2014/css/all.css"
	};
	function pages_init() {
		var tabJson = $.cookie('liger-home-tab');
		if (tabJson) {
			var tabitems = JSON2.parse(tabJson);
			for (var i = 0; tabitems && tabitems[i]; i++) {
				f_addTab(tabitems[i].tabid, tabitems[i].text, tabitems[i].url);
			}
		}
	}
	function saveTabStatus() {
		$.cookie('liger-home-tab', JSON2.stringify(tabItems));
	}
	function css_init() {
		var css = $("#mylink").get(0), skin = getQueryString("skin");
		$("#skinSelect").val(skin);
		$("#skinSelect").change(function() {
			if (this.value) {
				location.href = "index.htm?skin=" + this.value;
			} else {
				location.href = "index.htm";
			}
		});

		if (!css || !skin)
			return;
		skin = skin.toLowerCase();
		$('body').addClass("body-" + skin);
		$(css).attr("href", skin_links[skin]);
	}
	function getQueryString(name) {
		var now_url = document.location.search.slice(1), q_array = now_url
				.split('&');
		for (var i = 0; i < q_array.length; i++) {
			var v_array = q_array[i].split('=');
			if (v_array[0] == name) {
				return v_array[1];
			}
		}
		return false;
	}
	function attachLinkToFrame(iframeId, filename) {
		if (!window.frames[iframeId])
			return;
		var head = window.frames[iframeId].document
				.getElementsByTagName('head').item(0);
		var fileref = window.frames[iframeId].document.createElement("link");
		if (!fileref)
			return;
		fileref.setAttribute("rel", "stylesheet");
		fileref.setAttribute("type", "text/css");
		fileref.setAttribute("href", filename);
		head.appendChild(fileref);
	}
	function getLinkPrevHref(iframeId) {
		if (!window.frames[iframeId])
			return;
		var head = window.frames[iframeId].document
				.getElementsByTagName('head').item(0);
		var links = $("link:first", head);
		for (var i = 0; links[i]; i++) {
			var href = $(links[i]).attr("href");
			if (href && href.toLowerCase().indexOf("ligerui") > 0) {
				return href.substring(0, href.toLowerCase().indexOf(
						"${basePath}resources/ligerUI/lib"));
			}
		}
	}
</script>
<style type="text/css">
body, html {
	height: 100%;
}

body {
	padding: 0px;
	margin: 0;
	overflow: hidden;
}

.l-link {
	display: block;
	height: 26px;
	line-height: 26px;
	padding-left: 10px;
	text-decoration: underline;
	color: #333;
}

.l-link2 {
	text-decoration: underline;
	color: white;
	margin-left: 2px;
	margin-right: 2px;
}

.l-layout-top {
	background: #102A49;
	color: White;
}

.l-layout-bottom {
	background: #E5EDEF;
	text-align: center;
}

#pageloading {
	position: absolute;
	left: 0px;
	top: 0px;
	background: white url('loading.gif') no-repeat center;
	width: 100%;
	height: 100%;
	z-index: 99999;
}

.l-link {
	display: block;
	line-height: 22px;
	height: 22px;
	padding-left: 16px;
	border: 1px solid white;
	margin: 4px;
}

.l-link-over {
	background: #FFEEAC;
	border: 1px solid #DB9F00;
}

.l-winbar {
	background: #2B5A76;
	height: 30px;
	position: absolute;
	left: 0px;
	bottom: 0px;
	width: 100%;
	z-index: 99999;
}

.space {
	color: #E7E7E7;
}
/* 顶部 */
.l-topmenu {
	margin: 0;
	padding: 0;
	height: 50px;
	line-height: 31px;
	/* background: url('${basePath}resources/ligerUI/lib/images/top.jpg') repeat-x bottom; */
	background: #2F4F4F;
	position: relative;
	border-top: 1px solid #1D438B;
}

.l-topmenu-logo {
	position:absolute;
	color: #E7E7E7;
	padding-left: 35px;
	line-height: 26px;
	top: 10px;
	background: url('${basePath}resources/ligerUI/lib/images/topicon.gif')
		no-repeat 10px 5px;
}

.l-topmenu-welcome {
	position: absolute;
	height: 24px;
	line-height: 24px;
	right: 30px;
	top: 10px;
	color: #070A0C;
}

.l-topmenu-welcome a {
	color: #E7E7E7;
	text-decoration: underline
}

.body-gray2014 #framecenter {
	margin-top: 3px;
}

.viewsourcelink {
	background: #B3D9F7;
	display: block;
	position: absolute;
	right: 10px;
	top: 3px;
	padding: 6px 4px;
	color: #333;
	text-decoration: underline;
}

.viewsourcelink-over {
	background: #81C0F2;
}

.l-topmenu-welcome label {
	color: white;
}

#skinSelect {
	margin-right: 6px;
}
</style>
</head>
<body style="padding:0px;background:#EAEEF5;">
	<div id="pageloading"></div>
	<!-- 头部横条 -->
	<div id="topmenu" class="l-topmenu">
		<div class="l-topmenu-logo"><h2>物流应用后台</h2></div>
		<div class="l-topmenu-welcome">
			<label> 皮肤切换：</label> <select id="skinSelect">
				<option value="aqua">默认</option>
				<option value="silvery">Silvery</option>
				<option value="gray">Gray</option>
				<option value="gray2014">Gray2014</option>
			</select> <a href="index.aspx" class="l-link2">服务器版本</a> <span class="space">|</span>
			<a href="https://me.alipay.com/daomi" class="l-link2" target="_blank">捐赠</a>
			<span class="space">|</span> <a href="http://bbs.ligerui.com"
				class="l-link2" target="_blank">论坛</a>
		</div>
	</div>
	<div id="layout1" style="width:99.2%; margin:0 auto; margin-top:4px; ">
		<div position="left" title="主要菜单" id="accordion1">
			<div title="功能列表" class="l-scroll">
				<ul id="tree1" style="margin-top:3px;">
			</div>
			<div title="应用场景">
				<div style=" height:7px;"></div>
				<a class="l-link"
					href="javascript:f_addTab('listpage','列表页面','demos/case/listpage.htm')">列表页面</a>
				<a class="l-link"
					href="javascript:f_addTab('listpage','列表页面','demos/case/listpage2.htm')">列表页面2</a>
				<a class="l-link" href="demos/dialog/win7.htm" target="_blank">模拟Window桌面</a>
				<a class="l-link"
					href="javascript:f_addTab('week','工作日志','demos/case/week.htm')">工作日志</a>
			</div>
			<div title="实验室">
				<div style=" height:7px;"></div>
				<a class="l-link" href="lab/generate/index.htm" target="_blank">表格表单设计器</a>
				<a class="l-link" href="lab/formdesign/index.htm" target="_blank">可视化表单设计</a>
			</div>
		</div>
		<div position="center" id="framecenter">
			<div tabid="home" title="我的主页" style="height:300px">
				<iframe frameborder="0" name="home" id="home" src="welcome.htm"></iframe>
			</div>
		</div>

	</div>
	<div style="height:32px; line-height:32px;text-align:center;">
		Copyright © 2011-2014 www.ligerui.com</div>
	<div style="display:none"></div>
</body>
</html>
