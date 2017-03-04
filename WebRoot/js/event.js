/**
 * Created by 锟斤拷锟斤拷 on 2017/2/16.
 */
//锟斤拷锟斤拷图片
var currImage=0;
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
var Imgname=["锟斤拷锟斤拷","浅锟斤拷色锟斤拷锟斤拷","锟斤拷锟睫凤拷","锟斤拷锟阶凤拷","LG锟斤拷锟接伙拷","锟斤拷晒霜","IPhone6s"];

//预锟饺伙拷锟斤拷图片
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

        //注锟斤拷锟角凤拷晒锟�
        //console.log(currImage);
    }
function Next()
{
    currImage++;
    currImage%=7;

    //注锟斤拷锟角凤拷晒锟�
    //console.log(currImage);
}

function  ClearChild(parent)
{
    while(parent.hasChildNodes())
    {
        parent.removeChild(parent.firstChild);
    }
}


/*锟斤拷锟截猴拷突锟斤拷效锟斤拷*/
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
            //锟斤拷锟斤拷娌客�
            var parent=document.getElementById("face");
            ClearChild(parent);
            parent.appendChild(Img[currImage]);

            //锟斤拷锟斤拷锟斤拷锟酵�
            parent=document.getElementById('display_graphic');
            ClearChild(parent);

            var myChart = echarts.init(parent);

            option = {
                title: {
                    text: data[0].name+'锟桔革拷锟斤拷锟斤拷图'
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

            //锟斤拷锟斤拷锟斤拷锟斤拷息

            parent=document.getElementById("mytbody");
            var son=parent.getElementsByTagName("tr");


            for(var i=0;i<6;i++)
            {
                ClearChild(son[i]);
            }

            //锟斤拷品锟斤拷锟�
            var td1=document.createElement("td");
            td1.innerHTML="锟斤拷品锟斤拷锟�  ";
            var td2=document.createElement("td");
            td2.innerHTML=data[0].name;
            son[0].appendChild(td1);
            son[0].appendChild(td2);

            //锟桔革拷围
            var td3=document.createElement("td");
            td3.innerHTML="锟桔革拷围:  ";
            var td4=document.createElement("td");
            td4.innerHTML=data[4].rangeprice;
            son[1].appendChild(td3);
            son[1].appendChild(td4);


            //锟狡硷拷平台
            var td5=document.createElement("td");
            td5.innerHTML="锟狡硷拷平台:  ";
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
