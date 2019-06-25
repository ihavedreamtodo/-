var interval;

function startSecond() {//开始倒计时

	//跳转定时器
	interval = window.setInterval("changeSecond()", 1000);

};

function changeSecond() {
	//获取元素
	var second = document.getElementById("second");

	var svalue = second.innerHTML;

	//值减一
	svalue = svalue - 1;

	//当值为0时就跳转页面到首页
	if (svalue == 0) {
		window.clearInterval(interval);
		location.href = "index.jsp";
		return;
	}

	second.innerHTML = svalue;
}

//获取XMLHttpRequest对象
function getXMLHttpRequest() {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for all new browsers
		xmlhttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {// code for IE5 and IE6
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}

	return xmlhttp;

}