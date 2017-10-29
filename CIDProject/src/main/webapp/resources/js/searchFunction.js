	var newWindow;
	var httpRequest = null;
	function getXMLHttpRequest() {
		if (window.ActiveXObject) {
			try {
				return new ActiveXObject("Msxml2.XMLHTTP");
			} catch(e) {
				try {
					return new ActiveXObject("Microsoft.XMLHTTP");
				} catch(e1) { return null; }
			}
		} else if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else {
			return null;
		}
	}
		console.log(document.getElementById('searchOne'));
		function sendRequest(url,params,callback,method) {
			httpRequest = getXMLHttpRequest();
			var httpMethod = method ? method : 'GET';
			if (httpMethod != 'GET' && httpMethod != 'POST') {
				httpMethod = 'GET';
			}
			var httpParams = (params == null || params == '') ? null : params;
			var httpUrl = url;
			if (httpMethod == 'GET' && httpParams != null) {
				httpUrl = httpUrl + "?" + httpParams;
			}
			console.log(httpUrl);
			httpRequest.open(httpMethod, httpUrl, true);
			httpRequest.setRequestHeader(
				'Content-Type', 'application/x-www-form-urlencoded');
			httpRequest.onreadystatechange = callback;
			httpRequest.send(httpMethod == 'POST' ? httpParams : null);
		}
			
		function searchBarAdd() {
			if(document.getElementById('searchThree').style.display == 'inline'){
				document.getElementById('searchFour').style.display = 'inline' ;
	
			}else if(document.getElementById('searchTwo').style.display == 'inline'){
				document.getElementById('searchThree').style.display = 'inline' ;
	
			}else if(document.getElementById('searchOne').style.display == 'inline'){
				document.getElementById('searchTwo').style.display = 'inline' ;
			}
		}
		function searchBarDelete() {
			if(document.getElementById('searchFour').style.display == 'inline'){
				document.getElementById('searchFour').style.display = 'none' ;
				document.getElementById('op4').value = "cosmeticType";
				document.getElementById('four').value="";
			}else if(document.getElementById('searchThree').style.display == 'inline'){
				document.getElementById('searchThree').style.display = 'none' ;
				document.getElementById('op3').value = "cosmeticType";
				document.getElementById('three').value="";
			}else if(document.getElementById('searchTwo').style.display == 'inline'){
				document.getElementById('searchTwo').style.display = 'none' ;
				document.getElementById('op2').value = "cosmeticType";
				document.getElementById('two').value="";
			}else if(document.getElementById('searchOne').style.display == 'inline'){
				alert('최소 한개의 검색창은 필요합니다.');
			}
		}
		function infoItem(cosmeticName) {
			newWindow = window.open("iteminfo.do?cosmeticName="+cosmeticName,"iteminfo","height=100,widht=100,resizeable=yes");
		}
		function checkSelectValue() {
			op1Val = document.getElementById('op1').value;
			op2Val = document.getElementById('op2').value;
			op3Val = document.getElementById('op3').value;
			op4Val = document.getElementById('op4').value;		
			input1Val = document.getElementById('one').value.trim().length;
			input2Val = document.getElementById('two').value.trim().length;
			input3Val = document.getElementById('three').value.trim().length;
			input4Val = document.getElementById('four').value.trim().length;
			if(input1Val == 0){
				alert('검색할 단어를 입력해 주세요');
				return false;
			}
			if(input2Val > 0 && (op2Val == op1Val || op2Val == op3Val || op2Val == op4Val)){
				alert('검색옵션은 겹치지 않게해주세요 "," 로 성분명 구분이 가능합니다.');
				return false;
			}else if(input3Val > 0 && (op3Val == op1Val || op3Val == op2Val || op3Val == op4Val)){
				alert('검색옵션은 겹치지 않게해주세요 "," 로 성분명 구분이 가능합니다.');
				return false;
			}else if(input4Val > 0 && (op4Val == op1Val || op4Val == op3Val || op4Val == op2Val)){
				alert('검색옵션은 겹치지 않게해주세요 "," 로 성분명 구분이 가능합니다.');
				return false;
			}
		}
		function checkSelectValueAjax() {
			op1Val = document.getElementById('op1').value;
			op2Val = document.getElementById('op2').value;
			op3Val = document.getElementById('op3').value;
			op4Val = document.getElementById('op4').value;		
			input1Val = document.getElementById('one').value.trim().length;
			input2Val = document.getElementById('two').value.trim().length;
			input3Val = document.getElementById('three').value.trim().length;
			input4Val = document.getElementById('four').value.trim().length;
			inputOneVal = document.getElementById('one').value.trim();
			inputTwoVal = document.getElementById('two').value.trim();
			inputThreeVal = document.getElementById('three').value.trim();
			inputFourVal = document.getElementById('four').value.trim();

			if(input1Val == 0){
				alert('검색할 단어를 입력해 주세요');
				return false;
			}
			if(input2Val > 0 && (op2Val == op1Val || op2Val == op3Val || op2Val == op4Val)){
				alert('검색옵션은 겹치지 않게해주세요 "," 로 성분명 구분이 가능합니다.');
				return false;
			}else if(input3Val > 0 && (op3Val == op1Val || op3Val == op2Val || op3Val == op4Val)){
				alert('검색옵션은 겹치지 않게해주세요 "," 로 성분명 구분이 가능합니다.');
				return false;
			}else if(input4Val > 0 && (op4Val == op1Val || op4Val == op3Val || op4Val == op2Val)){
				alert('검색옵션은 겹치지 않게해주세요 "," 로 성분명 구분이 가능합니다.');
				return false;
			}
			var v1 = "op1="+op1Val; 
			var v2 = "one="+inputOneVal;
			var v3 = "op2="+op2Val;
			var v4 = "two="+inputTwoVal;
			var v5 = "op3="+op3Val;
			var v6 = "three="+inputThreeVal;
			var v7 = "op4="+op4Val;
			var v8 = "four="+inputFourVal;
			sendRequest("searchBar.do",v1+"&"+v2+"&"+v3+"&"+v4+"&"+v5+"&"+v6+"&"+v7+"&"+v8,callcosmetic,"GET");
		}
		function callcosmetic() {
			alert('콜백');
		}
		