<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<title>SewisePlayer</title>
<link rel="stylesheet"
	href="${basePath}resources/bootstrap/3.3/css/bootstrap.min.css">
<script src="${basePath}resources/js/jquery-1.11.1.min.js"></script>
<script src="${basePath}resources/bootstrap/3.3/js/bootstrap.min.js"></script>
</head>
<body>
	<div style="width: 100%; height: 100%;position: fixed;margin: 0.5px;">
		<script type="text/javascript"
			src="${basePath}resources/sewisePlayer/sewise.player.min.js"></script>
		<script type="text/javascript">
			SewisePlayer
					.setup({
						server : "vod",
						type : "mp4",
						videourl : "${basePath}resources/video/oceans-clip.mp4",
						autostart:"false",
						skin : "vodOrange",
						title : "应用场景演示",
						/* topbardisplay:"disable", */
						lang : 'zh_CN',
						fallbackurls : {
							ogg : "http://jackzhang1204.github.io/materials/mov_bbb.ogg",
							webm : "http://jackzhang1204.github.io/materials/mov_bbb.webm"
						}
					});
			//保存当前用户选取的视频清晰度
			function saveClarityName(name) {
				console.log(name);
			}
		</script>
	</div>
</body>
</html>