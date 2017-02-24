var api_key=['dgvRz8kU7IAgILjNkeJJmrYdFyG6Fi0T','e65k1-w14PVwhEUowvFGagNw43yAtQuw'];
var api_secret=['-YLsKW2ygqM55yMfkveE-taxQBe5icdN','V1fqZfOeJ0J26yp87iTZapCuWWI5VL5X'];
var cur=1;
//获取当前视频
var aVideo=document.getElementById('video');
//获取canvas画布
var aCanvas=document.getElementById('canvas');
//得到内容
var ctx=aCanvas.getContext('2d');
var token1 = ['',''];
var token2 = ['',''];

//获取媒体对象（这里指摄像头）
navigator.getUserMedia  = navigator.getUserMedia ||
    navigator.webkitGetUserMedia ||
    navigator.mozGetUserMedia ||
    navigator.msGetUserMedia;
//参数1获取用户打开权限；参数二成功打开后调用，并传一个视频流对象，参数三打开失败后调用，传错误信息
navigator.getUserMedia({video:true}, gotStream, noStream);
//调用成功获得对象流
function gotStream(stream)
{
    video.src = URL.createObjectURL(stream);
    video.onerror = function ()
    {
        stream.stop();
    };
    stream.onended = noStream;//结束
    video.onloadedmetadata = function () {};
}
function noStream(err)
{
    alert(err);
}


function mytest()
{
    ctx.drawImage(aVideo, 0, 0, 640, 480);//将获取视频绘制在画布上


    canvas.width = aVideo.videoWidth;
    canvas.height = aVideo.videoHeight;
    canvas.getContext('2d').drawImage(aVideo, 0, 0);

    var data = canvas.toDataURL('image/jpeg', 1.0);

    newblob = dataURItoBlob(data);


    var formdata = new FormData();
    formdata.append("api_key",api_key[cur]);
    formdata.append("api_secret",api_secret[cur]);
    formdata.append("return_landmark","1");
    formdata.append("return_attributes",'gender,age,smiling,glass,headpose,facequality,blur');
    formdata.append("image_file",newblob);
    cur^=1;


    $.ajax
    ({
        url: 'https://api-cn.faceplusplus.com/facepp/v3/detect',
        data: formdata,
        cache: false,
        contentType: false,
        processData: false,
        dataType:"json",
        type: 'POST',
        success: function (data)
        {
        	
        	var faces=data.faces; 
        	
        	//console.log(data); 
        	if(faces.length>0){
        		updateFaceInfo(data);
        		
        		token2[cur]=faces[0].face_token;
            	if(token1[cur]==""){
            		token1[cur] = token2[cur];
            	}
            	compare(faces);
            	
        	}
          
            
            for(var i=0;i<faces.length;i++)
            {

                var afaces=faces[i];
                if(typeof(afaces) == "undefined")return;

                var face_rectangle=afaces.face_rectangle;
                ctx.strokeStyle="blue";
                ctx.strokeRect(face_rectangle.left,face_rectangle.top,face_rectangle.width,face_rectangle.height);

                var method="updateclassProduct";
                var aclass="1";
                var yaw_angle=afaces.attributes.headpose.yaw_angle;
                var pitch_angle=afaces.attributes.headpose.pitch_angle;
                var roll_angle=afaces.attributes.headpose.roll_angle;
                var smile=afaces.attributes.smile.value;
                var age=afaces.attributes.age.value;
                var male=afaces.attributes.gender.value=="Female"?0:1;
                sendDataOpera(method,yaw_angle,pitch_angle,roll_angle,smile,age,male,aclass);
            }

        }

    });

};


function compare(faces){
	
	
	var formdata = new FormData();
    formdata.append("api_key",api_key[cur]);
    formdata.append("api_secret",api_secret[cur]);
    formdata.append("face_token1",token1[cur]);
    formdata.append("face_token2",token2[cur]);
    //console.log("taken1---"+token1+"-----taken2---"+token2);
    console.log(formdata);
    token1[cur] = token2[cur];
    $.ajax
	({
		url:'https://api-cn.faceplusplus.com/facepp/v3/compare',
		data: formdata,
        cache: false,
        contentType: false,
        processData: false,
        dataType:"json",
        type: 'POST',
        success: function (data){
        	//console.log(data);
        	var confidence = data.confidence;
        	var thresholds = data.thresholds["1e-3"];
        	
        	if(confidence>thresholds){
        		return;
        	}else{
        		var afaces = faces[0];
        		var method="getclassProduct";
        		var yaw_angle=afaces.attributes.headpose.yaw_angle;
                var pitch_angle=afaces.attributes.headpose.pitch_angle;
                var roll_angle=afaces.attributes.headpose.roll_angle;
                var smile=afaces.attributes.smile.value;
                var age=afaces.attributes.age.value;
                var male=afaces.attributes.gender.value=="Female"?0:1;
                sendDataOpera(method,yaw_angle,pitch_angle,roll_angle,smile,age,male);
           	}
        },
        error: function (data){
        	console.log(data);
        }
	});
}


function dataURItoBlob(dataURI, callback)
{
    // convert base64 to raw binary data held in a string
    // doesn't handle URLEncoded DataURIs

    var byteString;
    if (dataURI.split(',')[0].indexOf('base64') >= 0)
    {
        byteString = atob(dataURI.split(',')[1]);
    } else {
        byteString = unescape(dataURI.split(',')[1]);
    }

    // separate out the mime component
    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];

    // write the bytes of the string to an ArrayBuffer
    var ab = new ArrayBuffer(byteString.length);
    var ia = new Uint8Array(ab);
    for (var i = 0; i < byteString.length; i++) {
        ia[i] = byteString.charCodeAt(i);
    }

    var blob=new Blob([ab],{type:mimeString});
    return blob;
}

function getAsJPEGBlob(canvas)
{
    if(canvas.mozGetAsFile) {
        return canvas.mozGetAsFile("foo.jpg", "image/jpeg");
    } else {
        var data = canvas.toDataURL('image/jpeg', 0.7);
        var blob = dataURItoBlob(data);
        return blob;
    }
}


function sendDataOpera(method,yaw_angle,pitch_angle,roll_angle,smile,age,male,aclass)
{
	
    var data=("method="+method);
    data+=("&yaw_angle="+yaw_angle);
    data+=("&pitch_angle="+pitch_angle);
    data+=("&roll_angle="+roll_angle);
    data+=("&smile="+smile);
    data+=("&age="+age);
    data+=("&male="+male);
    data+=("&class="+aclass);
    data+=("&productid="+"1");
    var xmlhttp;
    if (window.XMLHttpRequest)
    {
        // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp = new XMLHttpRequest();
    }
    else
    {   // code for IE6, IE5
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.open("POST","./servlet/getProductServlet",true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.onreadystatechange = function(data)
    {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        {
        	//String productInfo = data;
            //cosole.log(productInfo);
            //if(productInfo.length == 0){
            	//return;
            //}else{
            	//updateProductInfo();
            //}
        	if(method=="getclassProduct"){
        		var result = data.target.responseText;
        		//JSONObject resultJson = JSONObject.fromObject(result);
        		updateHTML(result);
                console.log(result);
        	}
        }
    };
    xmlhttp.send(data);
}

function updateFaceInfo(data) {
    document.getElementById("sex").innerHTML=data.faces[0].attributes.gender.value;
//    console.log(data.faces[0].attributes.gender.value);
    document.getElementById("age").innerHTML=data.faces[0].attributes.age.value;
    if(0<=data.faces[0].attributes.smile.value&&data.faces[0].attributes.smile.value<=20){
        document.getElementById("emoji").innerHTML="1";
    }else if(20<data.faces[0].attributes.smile.value&&data.faces[0].attributes.smile.value<=40){
        document.getElementById("emoji").innerHTML="2";
    }else if(40<data.faces[0].attributes.smile.value&&data.faces[0].attributes.smile.value<=60){
        document.getElementById("emoji").innerHTML="3";
    }else if(60<data.faces[0].attributes.smile.value&&data.faces[0].attributes.smile.value<=80){
        document.getElementById("emoji").innerHTML="4";
    }else if(80<data.faces[0].attributes.smile.value&&data.faces[0].attributes.smile.value<=100){
        document.getElementById("emoji").innerHTML="5";
    }
   document.getElementById("glass").innerHTML=data.faces[0].attributes.glass.value;

   if(-10<=data.faces[0].attributes.headpose.yaw_angle&&data.faces[0].attributes.headpose.yaw_angle<=10){
       document.getElementById("yaw").innerHTML="1";
   }else if((10<data.faces[0].attributes.headpose.yaw_angle&&data.faces[0].attributes.headpose.yaw_angle<=20)
       ||(-20<=data.faces[0].attributes.headpose.yaw_angle&&data.faces[0].attributes.headpose.yaw_angle<-10)){
       document.getElementById("yaw").innerHTML="2";
   }else if((20<data.faces[0].attributes.headpose.yaw_angle&&data.faces[0].attributes.headpose.yaw_angle<=30)
       ||(-30<=data.faces[0].attributes.headpose.yaw_angle&&data.faces[0].attributes.headpose.yaw_angle<-20)){
       document.getElementById("yaw").innerHTML="3";
   }else if((30<data.faces[0].attributes.headpose.yaw_angle&&data.faces[0].attributes.headpose.yaw_angle<=40)
       ||(-40<=data.faces[0].attributes.headpose.yaw_angle&&data.faces[0].attributes.headpose.yaw_angle<-30)){
       document.getElementById("yaw").innerHTML="4";
   }else if((40<data.faces[0].attributes.headpose.yaw_angle&&data.faces[0].attributes.headpose.yaw_angle<=50)
       ||(-50<=data.faces[0].attributes.headpose.yaw_angle&&data.faces[0].attributes.headpose.yaw_angle<-40)){
       document.getElementById("yaw").innerHTML="5";
   }
}

function updateProductInfo(){
	
}
function updateHTML(jsons){
	var arr = eval(jsons);
	for(var i=0; i<arr.length; i++){
		var goodsImg = document.getElementById("goodsImg"+i);
		var goodsName = document.getElementById("goodsName"+i);
		var jsonObj = arr[i]; //获取json对象
		goodsImg.src = "./image/"+jsonObj.imageUrl+".jpg";
		goodsName.innerHTML = jsonObj.name;
	}
}
setInterval(mytest,280);