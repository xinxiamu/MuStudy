<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html lang="en">
<head>
<title>Accessible HTML5 Video Player</title>
<meta charset="utf-8" />
<meta name="description"
	content="Custom HTML5 video controls and WebVTT captions." />
<meta name="author" content="Dennis Lembree, PayPal" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<!-- PX-VIDEO CSS -->
<link rel="stylesheet" href="${basePath}resources/videoPlayer/px-video.css" />

<style>
/*** for demo only ***/
#wrapper {
	width: 640px;
	margin: 1em auto;
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

#brand a,p a {
	border: 1px #fff dotted;
}

#brand a:focus,p a:focus {
	border: 1px #999 dotted;
	outline: none;
}

p {
	padding-top: 1em;
}
</style>

</head>

<body>

	<div id="wrapper">
		<div class="px-video-container" id="myvid">
			<div class="px-video-img-captions-container">
				<div class="px-video-captions hide" aria-hidden="false"></div>
				<video width="640" height="360"
					poster="${basePath}resources/videoPlayer/media/poster_PayPal_Austin2.jpg"
					controls>
					<!----------- 这里添加视频文件 ----------->
					
					<!-- 本地视频文件 -->
					<source src="${basePath}resources/videoPlayer/media/oceans-clip.mp4"
					type="video/mp4">
					
					<source
						src="https://www.paypalobjects.com/webstatic/mktg/videos/PayPal_AustinSMB_baseline.mp4"
						type="video/mp4" />
					<source
						src="https://www.paypalobjects.com/webstatic/mktg/videos/PayPal_AustinSMB_baseline.webm"
						type="video/webm" />

					<!-- text track file -->
					<track kind="captions" label="English captions"
						src="${basePath}resources/videoPlayer/media/captions_PayPal_Austin_en.vtt"
						srclang="en" default />

					<!-- fallback for browsers that don't support the video element -->
					<%-- <div>
						<a
							href="https://www.paypalobjects.com/webstatic/mktg/videos/PayPal_AustinSMB_baseline.mp4">
							<img src="${basePath}resources/videoPlayer/media/poster_PayPal_Austin2.jpg" width="640"
							height="360" alt="download video" />
						</a>
					</div> --%>
				</video>
				<div class="px-video-controls"></div>
			</div>
			<!-- end container for captions and video -->
		</div>
		<!-- end video container -->
	</div>
	<!-- end page wrapper container -->

	<script src="${basePath}resources/videoPlayer/js/px-video.js"></script>

	<script>
		// Initialize
		new InitPxVideo({
			"videoId" : "myvid",
			"captionsOnDefault" : true,
			"seekInterval" : 20,
			"videoTitle" : "PayPal Austin promo",
			"debug" : true
		});
	</script>

</body>
</html>


