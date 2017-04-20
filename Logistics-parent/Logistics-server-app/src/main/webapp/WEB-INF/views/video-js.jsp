<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>video.js视频播放器演示</title>

<!-- Chang URLs to wherever Video.js files will be hosted -->
<link href="${basePath}resources/video-js/video-js.css" rel="stylesheet" type="text/css">
<!-- video.js must be in the <head> for older IEs to work. -->
<script src="${basePath}resources/video-js/video.dev.js"></script>

<!-- Unless using the CDN hosted version, update the URL to the Flash SWF -->
<script>
     /* 这里有问题呀呀呀呀 */
	//videojs.options.flash.swf = "${basePath}resources/video-js/video-js.swf";
	videojs.options.flash.swf = "video-js.swf";
</script>

</head>

<body>
	<video id="example_video_1" class="video-js vjs-default-skin" controls
		preload="none" width="640" height="264"
		poster="http://video-js.zencoder.com/oceans-clip.png" data-setup="{}">
	<source src="http://video-js.zencoder.com/oceans-clip.mp4" type='video/mp4' /> 
	<source src="http://video-js.zencoder.com/oceans-clip.webm" type='video/webm' />
	<source src="http://video-js.zencoder.com/oceans-clip.ogv"  type='video/ogg' /> 
	<track kind="captions" src="${basePath}resources/video-js/demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
	<track kind="subtitles" src="${basePath}resources/video-js/demo.captions.vtt" srclang="en" label="English"></track><!-- Tracks need an ending tag thanks to IE9 -->
	<!-- <p class="vjs-no-js">To view this video please enable JavaScript, and consider upgrading to a web browser that <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a></p> -->
	</video>
</body>
</html>
