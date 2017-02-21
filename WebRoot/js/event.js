/**
 * Created by 区区 on 2017/2/16.
 */
//缓存图片
var currImage=2;
var Imgsrc=["img/Advertisement/bsry.jpg","img/Advertisement/shortsleeve.jpg",
    "img/Advertisement/yrf.jpg","img/Advertisement/DanBaiFen.jpg",
    "img/Advertisement/LG_4KTV.jpg","img/Advertisement/fss.jpg",
    "img/Advertisement/IPhone6s.jpg"
];
var Imgjson=["json/hs.json","json/shortsleeve.json",
    "json/yrf.json","json/DanBaiFen.json",
    "json/LG_4KTV.json","json/fss.json",
    "json/IPhone6s.json"
];
var Imgname=["韩束","浅蓝色短袖","羽绒服","蛋白粉","LG电视机","防晒霜","IPhone6s"];

//预先缓冲图片
var Img=new Array(7);
for(var i=0;i<7;i++)
{
    Img[i]=new Image(290,290);
    Img[i].src=Imgsrc[i];
}


var imglrshow=false;


    function Pre()
    {
        currImage--;
        if(currImage<0) currImage=6;
        //注释是否成功
        console.log(currImage);
    }
function Next()
{
    currImage++;
    currImage%=7;
    //注释是否成功
    console.log(currImage);
}
function  ClearChild(parent)
{
    while(parent.hasChildNodes())
    {
        parent.removeChild(parent.firstChild);
    }
}


/*隐藏和突显效果*/
function show(id)
{
    $(id).show();
    $(id).addClass("FROM");
    setTimeout(function(){$(id).addClass("TO");},300);
}
function hide(id)
{
    $(id).removeClass("TO");
}
function  Refle()
{
    hide("#imgLeft");
    hide("#imgRight");
    hide("#imgReturn");
    imglrshow=false;
    setTimeout(function () {
        location.reload();
    },400);
}




function Change(dataroot)
{
    if(!imglrshow)
    {
        imglrshow=true;
        show("#imgLeft");
        show("#imgRight");
        show("#imgReturn");
    }
    $.ajax
    ({
        type: "GET",
        dataType: "json",
        url: dataroot,
        success: function (data)
        {
            //清除面部图
            var parent=document.getElementById("face");
            ClearChild(parent);
            parent.appendChild(Img[currImage]);

            //加载数据图
            parent=document.getElementById('display_graphic');
            ClearChild(parent);

            var myChart = echarts.init(parent);

            option = {
                title: {
                    text: data[0].name+'价格走势图'
                },
                backgroundColor:"rgba(255,255,255,0.6)",
                tooltip: {
                    trigger: 'axis'
                },
                legend:
                {
                    data:data[3].plat
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: data[1].time
                },
                yAxis: {
                    type: 'value',
                    axisLabel :
                    {
                        formatter: '{value} 元'
                    }
                },
                series:data[2].price
            };
            myChart.setOption(option);

            //更改数据信息

            parent=document.getElementById("mytbody");
            var son=parent.getElementsByTagName("tr");


            for(var i=0;i<6;i++)
            {
                ClearChild(son[i]);
            }

            //商品名称
            var td1=document.createElement("td");
            td1.innerHTML="商品名称:  ";
            var td2=document.createElement("td");
            td2.innerHTML=data[0].name;
            son[0].appendChild(td1);
            son[0].appendChild(td2);

            //价格范围
            var td3=document.createElement("td");
            td3.innerHTML="价格范围:  ";
            var td4=document.createElement("td");
            td4.innerHTML=data[4].rangeprice;
            son[1].appendChild(td3);
            son[1].appendChild(td4);


            //推荐平台
            var td5=document.createElement("td");
            td5.innerHTML="推荐平台:  ";
            var td6=document.createElement("td");
            var str="";
            for(var i=0;i<data[3].plat.length;i++)
            {
                str+=data[3].plat[i]+" ";
            }
            td6.innerHTML=str;
            son[2].appendChild(td5);
            son[2].appendChild(td6);

        }
    });

}
