(function(a){a.SewisePlayerSkin={version:"1.0.0"};a.SewisePlayer=a.SewisePlayer||{}})(window);(function(a){a.SewisePlayer.ILivePlayer=a.SewisePlayer.ILivePlayer||{live:function(){},play:function(){},pause:function(){},stop:function(){},seek:function(){},changeClarity:function(){},setVolume:function(){},playChannel:function(){},toPlay:function(){},duration:function(){},liveTime:function(){},playTime:function(){},type:function(){},showTextTip:function(){},hideTextTip:function(){},muted:function(){}}})(window);(function(a){a.SewisePlayerSkin.ILiveSkin=a.SewisePlayerSkin.ILiveSkin||{player:function(){},started:function(){},paused:function(){},stopped:function(){},seeking:function(){},buffering:function(){},bufferProgress:function(){},loadedOpen:function(){},loadedComplete:function(){},programTitle:function(){},duration:function(){},playTime:function(){},startTime:function(){},loadSpeed:function(){},initialClarity:function(){},lang:function(){},logo:function(){},timeUpdate:function(){},volume:function(){},
clarityButton:function(){},timeDisplay:function(){},controlBarDisplay:function(){},topBarDisplay:function(){},customStrings:function(){},customDatas:function(){},fullScreen:function(){},noramlScreen:function(){},initialAds:function(){},initialStatistics:function(){}}})(window);(function(){SewisePlayerSkin.Utils={stringer:{time2FigFill:function(a){var b,a=Math.floor(a);10>a?b="0"+a:b=""+a;return b},secondsToHMS:function(a){if(!(0>a)){var b=this.time2FigFill(Math.floor(a/3600)),c=this.time2FigFill(a/60%60),a=this.time2FigFill(a%60);return b+":"+c+":"+a}},secondsToMS:function(a){if(!(0>a)){var b=this.time2FigFill(a/60),a=this.time2FigFill(a%60);return b+":"+a}},dateToTimeString:function(a){var b;b=a?a:new Date;var a=b.getFullYear(),c=b.getMonth()+1,j=b.getDate(),g=this.time2FigFill(b.getHours()),
d=this.time2FigFill(b.getMinutes());b=this.time2FigFill(b.getSeconds());return a+"-"+c+"-"+j+" "+g+":"+d+":"+b},dateToTimeStr14:function(a){var b=a.getFullYear(),c=this.time2FigFill(a.getMonth()+1),j=this.time2FigFill(a.getDate()),g=this.time2FigFill(a.getHours()),d=this.time2FigFill(a.getMinutes()),a=this.time2FigFill(a.getSeconds());return b+c+j+g+d+a},dateToStrHMS:function(a){var b=this.time2FigFill(a.getHours()),c=this.time2FigFill(a.getMinutes()),a=this.time2FigFill(a.getSeconds());return b+
":"+c+":"+a},dateToYMD:function(a){var b=a.getFullYear(),c=this.time2FigFill(a.getMonth()+1),a=this.time2FigFill(a.getUTCDate());return b+"-"+c+"-"+a},hmsToDate:function(a){var a=a.split(":"),b=new Date;b.setHours(a[0]?a[0]:0,a[1]?a[1]:0,a[2]?a[2]:0);return b}},language:{zh_cn:{stop:"\u505c\u6b62\u64ad\u653e",pause:"\u6682\u505c",play:"\u64ad\u653e",fullScreen:"\u5168\u5c4f",normalScreen:"\u6062\u590d",soundOn:"\u6253\u5f00\u58f0\u97f3",soundOff:"\u5173\u95ed\u58f0\u97f3",clarity:"\u6e05\u6670\u5ea6",
titleTip:"\u6b63\u5728\u64ad\u653e\uff1a",claritySetting:"\u6e05\u6670\u5ea6\u8bbe\u7f6e",clarityOk:"\u786e\u5b9a",clarityCancel:"\u53d6\u6d88",backToLive:"\u56de\u5230\u76f4\u64ad",loading:"\u7f13\u51b2",subtitles:"\u5b57\u5e55","default":"\u9ed8\u8ba4"},en_us:{stop:"Stop",pause:"Pause",play:"Play",fullScreen:"Full Screen",normalScreen:"Normal Screen",soundOn:"Sound On",soundOff:"Sound Off",clarity:"Clarity",titleTip:"Playing: ",claritySetting:"Definition Setting",clarityOk:"OK",clarityCancel:"Cancel",
backToLive:"Back To Live",loading:"Loading",subtitles:"Subtitles","default":"Default"},lang:{},init:function(a){switch(a){case "en_US":this.lang=this.en_us;break;case "zh_CN":this.lang=this.zh_cn;break;default:this.lang=this.zh_cn}},getString:function(a){return this.lang[a]}}}})();(function(a){SewisePlayerSkin.BannersAds=function(b,c){function j(){x=SewisePlayerSkin.Utils.stringer.dateToYMD(new Date);l=c[x]||c["0000-00-00"];m=0;if(void 0!=l)r=l.length}function g(){for(var a=(new Date).getTime(),b=0;b<r;b++){var c=SewisePlayerSkin.Utils.stringer.hmsToDate(l[b].time).getTime();if(b<r-1){var j=SewisePlayerSkin.Utils.stringer.hmsToDate(l[b+1].time).getTime();if(a>c&&a<j){m=b;f(m);break}}else if(a>c){m=b;f(m);break}}}function d(){var a=(new Date).getTime();if(m<r-1){var b=SewisePlayerSkin.Utils.stringer.hmsToDate(l[m+
1].time).getTime();a>b&&(m++,f(m))}else SewisePlayerSkin.Utils.stringer.dateToYMD(new Date)!=x&&(j(),g())}function f(a){o=l[a].ads[0].picurl;h=l[a].ads[1].picurl;""==o&&""==h||(""==o&&""!=h?o=h:""!=o&&""==h&&(h=o),z.attr("src",o),A.attr("src",h))}var p=a(' <div style="position:absolute; display:table; width:100%; height:100%;"><div style="display:table-cell; text-align:left; vertical-align:middle;"><img style="pointer-events:auto; cursor:pointer; width: auto; height: auto;"></div></div> ');p.appendTo(b);
var z=p.find("img"),p=a(' <div style="position:absolute; display:table; width:100%; height:100%;"><div style="display:table-cell; text-align:right; vertical-align:middle;"><img style="pointer-events:auto; cursor:pointer; width: auto; height: auto;"></div></div> ');p.appendTo(b);var A=p.find("img"),o,h,x,l,m,r;j();void 0!=l&&(1==r&&""==l[0].time?f(0):(g(),setInterval(d,1E4)),z.click(function(a){a.originalEvent.stopPropagation();a=l[m].ads[0].link_url;window.openAdsLink&&"function"==typeof window.openAdsLink?
window.openAdsLink(a):window.open(a,"_blank")}),A.click(function(a){a.originalEvent.stopPropagation();a=l[m].ads[1].link_url;window.openAdsLink&&"function"==typeof window.openAdsLink?window.openAdsLink(a):window.open(a,"_blank")}))}})(window.jQuery);(function(a){SewisePlayerSkin.AdsContainer=function(b,c){var j=b.$container,g=b.$sewisePlayerUi,d=c.banners;if(d){var f=a("<div class='sewise-player-ads-banner'></div>");f.css({position:"absolute",width:"100%",height:"100%",left:"0px",top:"0px",overflow:"hidden","pointer-events":"none"});f.appendTo(j);g.css("z-index",1);SewisePlayerSkin.BannersAds(f,d)}}})(window.jQuery);(function(a){SewisePlayerSkin.Statistics=function(b){function c(b){var c=b["request-url"],d=b["request-data"];setInterval(function(){a.ajax({type:"post",async:!1,dataType:"json",url:c,data:d,success:function(){},error:function(){console.log("play count ajax request fail!")}})},b["request-data"].intervallen?b["request-data"].intervallen:1E4)}(b=b.playCount)&&c(b)}})(window.jQuery);(function(a){SewisePlayerSkin.ElementObject=function(){this.$sewisePlayerUi=a(".sewise-player-ui");this.$container=this.$sewisePlayerUi.parent();this.$video=this.$container.find("video").get(0);this.$controlbar=this.$sewisePlayerUi.find(".controlbar");this.$playBtn=this.$sewisePlayerUi.find(".controlbar-btns-play");this.$pauseBtn=this.$sewisePlayerUi.find(".controlbar-btns-pause");this.$liveBtn=this.$sewisePlayerUi.find(".controlbar-btns-live");this.$fullscreenBtn=this.$sewisePlayerUi.find(".controlbar-btns-fullscreen");
this.$normalscreenBtn=this.$sewisePlayerUi.find(".controlbar-btns-normalscreen");this.$soundopenBtn=this.$sewisePlayerUi.find(".controlbar-btns-soundopen");this.$soundcloseBtn=this.$sewisePlayerUi.find(".controlbar-btns-soundclose");this.$volumelineVolume=this.$sewisePlayerUi.find(".controlbar-volumeline-volume");this.$volumelineDragger=this.$sewisePlayerUi.find(".controlbar-volumeline-dragger");this.$volumelinePoint=this.$sewisePlayerUi.find(".controlbar-volumeline-point");this.$shareBtn=this.$sewisePlayerUi.find(".controlbar-btns-share");
this.$playtime=this.$sewisePlayerUi.find(".controlbar-playtime");this.$controlBarProgress=this.$sewisePlayerUi.find(".controlbar-progress");this.$progressPlayedLine=this.$sewisePlayerUi.find(".controlbar-progress-playedline");this.$progressPlayedPoint=this.$sewisePlayerUi.find(".controlbar-progress-playpoint");this.$progressLoadedLine=this.$sewisePlayerUi.find(".controlbar-progress-loadedline");this.$progressSeekLine=this.$sewisePlayerUi.find(".controlbar-progress-seekline");this.$logo=this.$sewisePlayerUi.find(".logo");
this.$logoIcon=this.$sewisePlayerUi.find(".logo-icon");this.$topbar=this.$sewisePlayerUi.find(".topbar");this.$programTip=this.$sewisePlayerUi.find(".topbar-program-tip");this.$programTitle=this.$sewisePlayerUi.find(".topbar-program-title");this.$topbarClock=this.$sewisePlayerUi.find(".topbar-clock");this.$buffer=this.$sewisePlayerUi.find(".buffer");this.$bufferTip=this.$sewisePlayerUi.find(".buffer-text-tip");this.$bigPlayBtn=this.$sewisePlayerUi.find(".big-play-btn");this.defStageWidth=this.$container.width();
this.defStageHeight=this.$container.height();this.defLeftValue=parseInt(this.$container.css("left"));this.defTopValue=parseInt(this.$container.css("top"));this.defOffsetX=this.$container.get(0).getBoundingClientRect().left;this.defOffsetY=this.$container.get(0).getBoundingClientRect().top;this.defOverflow=a("body").css("overflow")}})(window.jQuery);(function(a){SewisePlayerSkin.ElementLayout=function(b){var c=b.$container,j=b.$controlBarProgress,g=b.$playtime,d=b.defStageWidth,f=b.defLeftValue,p=b.defTopValue,z=b.defOffsetX,A=b.defOffsetY,o=b.defOverflow,h=parseInt(d)-288;0>h&&(h+=g.width(),g.hide());j.css("width",h);this.fullScreen=function(b){if("not-support"==b){a("body").css("overflow","hidden");var b=a(window).width(),d=a(window).height()-8;c.css("width",b);c.css("height",d);b=a(document).scrollLeft();d=a(document).scrollTop();parseInt(a("body").css("margin-left"));
var h=parseInt(a("body").css("margin-top")),d=p-A+d-h+"px";c.css("left",f-z+b+"px");c.css("top",d)}else c.css("width","100%"),c.css("height","100%");b=parseInt(c.width())-288;0>b?(b+=g.width(),g.hide()):g.show();j.css("width",b)};this.normalScreen=function(){c.css("width","100%");c.css("height","100%");c.css("left",f);c.css("top",p);a("body").css("overflow",o);h=parseInt(d)-288;0>h?(h+=g.width(),g.hide()):g.show();j.css("width",h)};this.resize=function(){d=c.width();c.height();h=parseInt(d)-288;0>
h?(h+=g.width(),g.hide()):g.show();j.css("width",h)}}})(window.jQuery);(function(){SewisePlayerSkin.LogoBox=function(a){var b=a.$logoIcon;b.click(function(a){a.originalEvent.stopPropagation()});var c="http://www.sewise.com/";this.setLogo=function(a){b.css("background","url("+a+") 0px 0px no-repeat");b.attr("href",c)};this.setLink=function(a){c=a;b.attr("href",c)}}})(window.jQuery);(function(){SewisePlayerSkin.TopBar=function(a){var b=a.$topbar,c=a.$programTip,j=a.$programTitle,g=a.$topbarClock;this.setClock=function(a){a=SewisePlayerSkin.Utils.stringer.dateToTimeString(a);g.text(a)};this.setTitle=function(a){j.text(a)};this.show=function(){b.css("visibility","visible")};this.hide=function(){b.css("visibility","hidden")};this.hide2=function(){b.hide()};this.initLanguage=function(){var a=SewisePlayerSkin.Utils.language.getString("titleTip");c.text(a)}}})(window.jQuery);(function(a){SewisePlayerSkin.ControlBar=function(b,c){function j(a){a=s+(a.pageX-E);0<a&&a<B&&(y.css("width",a),q.css("left",a-O/2))}function g(b){H.unbind("mousemove",j);a(document).unbind("mouseup",g);P=b.pageX;E!=P&&(s=y.width(),Q=s/B,h(Q));I=!1}function d(a){e=a.originalEvent;1==e.targetTouches.length&&(e.preventDefault(),a=s+(e.targetTouches[0].pageX-E),0<a&&a<B&&(y.css("width",a),q.css("left",a-O/2)))}function f(a){e=a.originalEvent;q.unbind("touchmove",d);q.unbind("touchend",f);if(1==e.changedTouches.length)P=
e.changedTouches[0].pageX,E!=P&&(s=y.width(),Q=s/B,h(Q));I=!1}function p(a){a=k+(a.pageX-F);0<a&&a<t&&(u.css("width",a),n.css("left",a-C/2))}function z(b){H.unbind("mousemove",p);a(document).unbind("mouseup",z);R=b.pageX;F!=R&&(k=u.width(),w=k/t,i.setVolume(w),r())}function A(a){e=a.originalEvent;1==e.targetTouches.length&&(e.preventDefault(),a=k+(e.targetTouches[0].pageX-F),0<a&&a<t&&(u.css("width",a),n.css("left",a-C/2)))}function o(a){e=a.originalEvent;n.unbind("touchmove",A);n.unbind("touchend",
o);if(1==e.changedTouches.length)R=e.changedTouches[0].pageX,F!=R&&(k=u.width(),w=k/t,i.setVolume(w),r())}function h(a){a=new Date(36E5*Math.floor(i.playTime().getTime()/1E3/3600)+1E3*a*v);a=SewisePlayerSkin.Utils.stringer.dateToTimeStr14(a);i.seek(a)}function x(){null!=document.fullscreenElement||null!=document.msFullscreenElement||null!=document.mozFullScreenElement||null!=document.webkitFullscreenElement?c.fullScreen():c.normalScreen();D.timeUpdate(J)}function l(){c.resize();D.timeUpdate(J)}function m(){c.fullScreen("not-support");
D.timeUpdate(J)}function r(){0<w?(i.muted(!1),K.show(),G.hide()):(i.muted(!0),K.hide(),G.show())}var H=b.$container,$=b.$video,X=b.$controlbar,N=b.$playBtn,L=b.$pauseBtn,aa=b.$liveBtn,U=b.$fullscreenBtn,S=b.$normalscreenBtn,K=b.$soundopenBtn,G=b.$soundcloseBtn,u=b.$volumelineVolume,M=b.$volumelineDragger,n=b.$volumelinePoint,ba=b.$playtime,y=b.$progressPlayedLine,q=b.$progressPlayedPoint,ca=b.$progressLoadedLine,V=b.$progressSeekLine,W=b.$buffer,da=b.$bufferTip,T=b.$bigPlayBtn,D=this,i,v=0.1,J=0,
Y="00:00:00",Z="00:00:00",O=0,I=!1,E=0,P=0,s=0,B=0,Q=0,C=0,t=0,k=0,w=0,F=0,R=0,O=q.width(),B=V.width(),C=n.width(),t=M.width();L.hide();S.hide();G.hide();W.hide();a($).css("pointer-events","none");X.click(function(a){a.originalEvent.stopPropagation()});N.click(function(){i.play()});L.click(function(){i.pause()});aa.click(function(){i.live()});T.click(function(a){a.originalEvent.stopPropagation();i.play()});U.click(function(){D.fullScreen()});S.click(function(){D.noramlScreen()});K.click(function(){i.muted(!0);
K.hide();G.show();u.css("width",0);n.css("left",-C/2)});G.click(function(){i.muted(!1);K.show();G.hide();u.css("width",k);n.css("left",k-C/2)});q.mousedown(function(b){this.blur();I=!0;E=b.pageX;s=y.width();B=V.width();H.bind("mousemove",j);a(document).bind("mouseup",g)});q.bind("touchstart",function(a){e=a.originalEvent;q.blur();a=e.targetTouches[0];I=!0;E=a.pageX;s=y.width();B=V.width();q.bind("touchmove",d);q.bind("touchend",f)});M.mousedown(function(a){k=a.pageX-a.target.getBoundingClientRect().left;
t=M.width();u.css("width",k);n.css("left",k-C/2);w=k/t;i.setVolume(w);r()});n.mousedown(function(b){this.blur();F=b.pageX;k=u.width();t=M.width();H.bind("mousemove",p);a(document).bind("mouseup",z)});n.bind("touchstart",function(a){e=a.originalEvent;n.blur();F=e.targetTouches[0].pageX;k=u.width();t=M.width();n.bind("touchmove",A);n.bind("touchend",o)});document.addEventListener("fullscreenchange",x);document.addEventListener("MSFullscreenChange",x);document.addEventListener("mozfullscreenchange",
x);document.addEventListener("webkitfullscreenchange",x);a(window).bind("resize",l);this.setPlayer=function(a){i=a};this.started=function(){N.hide();L.show();T.hide()};this.paused=function(){N.show();L.hide();T.show()};this.stopped=function(){N.show();L.hide();T.show()};this.setDuration=function(a){v=a};this.timeUpdate=function(){if(i.playTime()&&(Z=SewisePlayerSkin.Utils.stringer.dateToStrHMS(new Date(1E3*Math.ceil(i.playTime().getTime()/1E3/v)*v)),Y=SewisePlayerSkin.Utils.stringer.dateToStrHMS(new Date(1E3*
Math.floor(i.playTime().getTime()/1E3/v)*v)),ba.text(Y+"/"+Z),!I)){s=100*(Math.floor(i.playTime().getTime()/1E3)%v/v)+"%";y.css("width",s);var a=y.width()-O/2;q.css("left",a);a=Math.ceil(i.playTime().getTime()/1E3/3600);a=100*(Math.floor(i.liveTime().getTime()/1E3/3600)>=a?1:Math.floor(i.liveTime().getTime()/1E3)%v/v)+"%";ca.css("width",a)}};this.initVolume=function(a){w=a;k=t*w;u.css("width",k);n.css("left",k-C/2);r()};this.hide2=function(){X.hide()};this.fullScreen=function(){U.hide();S.show();
var b=H.get(0);a(window).unbind("resize",l);b.requestFullscreen?b.requestFullscreen():b.msRequestFullscreen?b.msRequestFullscreen():b.mozRequestFullScreen?b.mozRequestFullScreen():b.webkitRequestFullscreen?b.webkitRequestFullscreen():(c.fullScreen("not-support"),D.timeUpdate(J),a(window).bind("resize",m))};this.noramlScreen=function(){U.show();S.hide();document.exitFullscreen?document.exitFullscreen():document.msExitFullscreen?document.msExitFullscreen():document.mozCancelFullScreen?document.mozCancelFullScreen():
document.webkitCancelFullScreen?document.webkitCancelFullScreen():(c.normalScreen(),D.timeUpdate(J),a(window).unbind("resize",m));a(window).bind("resize",l)};this.showBuffer=function(){W.show()};this.hideBuffer=function(){W.hide()};this.initLanguage=function(){var a=SewisePlayerSkin.Utils.language.getString("loading");"Loading"!=a&&(a="\u6b63\u5728"+a);da.text(a)}}})(window.jQuery);(function(a,b){b(document).ready(function(){var a=SewisePlayer.ILivePlayer,b=new SewisePlayerSkin.ElementObject,g=new SewisePlayerSkin.ElementLayout(b),d=new SewisePlayerSkin.TopBar(b),f=new SewisePlayerSkin.ControlBar(b,g,d);SewisePlayerSkin.ILiveSkin.player=function(b){a=b;f.setPlayer(a)};SewisePlayerSkin.ILiveSkin.started=function(){f.started()};SewisePlayerSkin.ILiveSkin.paused=function(){f.paused()};SewisePlayerSkin.ILiveSkin.stopped=function(){f.stopped()};SewisePlayerSkin.ILiveSkin.duration=
function(a){f.setDuration(a)};SewisePlayerSkin.ILiveSkin.timeUpdate=function(){f.timeUpdate();d.setClock(a.playTime())};SewisePlayerSkin.ILiveSkin.loadedOpen=function(){f.showBuffer()};SewisePlayerSkin.ILiveSkin.loadedComplete=function(){f.hideBuffer()};SewisePlayerSkin.ILiveSkin.programTitle=function(a){d.setTitle(a)};SewisePlayerSkin.ILiveSkin.logo=function(){};SewisePlayerSkin.ILiveSkin.volume=function(a){f.initVolume(a)};SewisePlayerSkin.ILiveSkin.clarityButton=function(){};SewisePlayerSkin.ILiveSkin.timeDisplay=
function(){};SewisePlayerSkin.ILiveSkin.controlBarDisplay=function(a){"enable"!=a&&f.hide2()};SewisePlayerSkin.ILiveSkin.topBarDisplay=function(a){"enable"!=a&&d.hide2()};SewisePlayerSkin.ILiveSkin.customStrings=function(){};SewisePlayerSkin.ILiveSkin.customDatas=function(a){a&&a.logoLink&&logoBox.setLink(a.logoLink)};SewisePlayerSkin.ILiveSkin.fullScreen=function(){f.fullScreen()};SewisePlayerSkin.ILiveSkin.noramlScreen=function(){f.noramlScreen()};SewisePlayerSkin.ILiveSkin.initialAds=function(a){a&&
SewisePlayerSkin.AdsContainer(b,a)};SewisePlayerSkin.ILiveSkin.initialStatistics=function(a){a&&SewisePlayerSkin.Statistics(a)};SewisePlayerSkin.ILiveSkin.lang=function(a){SewisePlayerSkin.Utils.language.init(a);f.initLanguage();d.initLanguage()};try{SewisePlayer.CommandDispatcher.dispatchEvent({type:SewisePlayer.Events.PLAYER_SKIN_LOADED,playerSkin:SewisePlayerSkin.ILiveSkin})}catch(p){console.log("No Main Player")}})})(window,window.jQuery);