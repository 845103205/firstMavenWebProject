
function shebeijichudangan() {
    var categoryInfos = [];
    var category1 = {};
    var category2 = {};
    category1.name = "光伏机房";
    category1.id = 1;
    category1.type = 1;

    category2.name = "光伏计量";
    category2.id = 2;
    category2.type = 1;

    categoryInfos.push(category1);
    categoryInfos.push(category2);

    var equipments = [];
    var dianchizuCount = 50;//光伏组件个数
    var hlxCount = 20;//汇流箱个数
    var nibianqiCount = 10;//逆变器个数
    var xudianchiCount = 1;//储能电池个数
    var peidianguiCount = 1;//配电柜个数

    var tree = {};
    tree.code = 1000;
    var data = {};
    tree.data = data;
    data.categoryInfos = categoryInfos;
    data.equipments = equipments;
    var sheBeiJiChuDangAnRes = {};//详情
    var sheBeiJiChuDangAnData = {};
    sheBeiJiChuDangAnRes.code = 1000;
    sheBeiJiChuDangAnRes.data = sheBeiJiChuDangAnData;
    for (var i=1;i<=dianchizuCount;i++){//光伏组件
        // {
        //     "classify": 1,
        //     "name": "单晶电池组-1",
        //     "categoryTypeId": 1,
        //     "id": 1,
        //     "categoryInfoGid": 1
        // }
        var equipment = {};
        equipment.classify = 1;
        equipment.name = "光伏组件-"+i;
        equipment.categoryTypeId = 1;
        equipment.id = i;
        equipment.categoryInfoGid = 1;
        equipments.push(equipment);
        var sheBeiJiChuDangAn = {};
        var shebeijichuxinxi = {};
        var shebeicanshu = {};
        sheBeiJiChuDangAn.shebeijichuxinxi = shebeijichuxinxi;
        sheBeiJiChuDangAn.shebeicanshu = shebeicanshu;

        shebeijichuxinxi.shebeileixing = "光伏组件";
        shebeijichuxinxi.pingpai= "隆基";
        shebeijichuxinxi.xinghao= "WF-BU";
        shebeijichuxinxi.shebeimingcheng=equipment.name;
        shebeijichuxinxi.shebeibianhao="DC-20210725000"+i;
        shebeijichuxinxi.chuchangriqi="2020-10-20";
        shebeijichuxinxi.touyongriqi= "2021-07-25";
        shebeijichuxinxi.shebeizhuangtai="在运";
        shebeijichuxinxi.suozaiquyu="中石化工业一区1栋顶层" ;

        shebeicanshu.shebeigonglv = "18V 300W +-3%";
        shebeicanshu.duanludianliu = "16.6A +-3%";
        shebeicanshu.kailudianya = "21.5V +-3%";
        shebeicanshu.gongzuodianliu = "15.5A +-3%";
        shebeicanshu.shebeizhongliang = "16 KG";
        shebeicanshu.zujianshuliang = "4";
        shebeicanshu.jierufangshi = "串连";
        sheBeiJiChuDangAnData[i] = sheBeiJiChuDangAn;

    }
    var jStar= dianchizuCount+1;
    var jend = hlxCount+dianchizuCount;
    for (var j=jStar;j<=jend;j++){//汇流箱
        var equipment = {};
        equipment.classify = 1;
        var index = j - dianchizuCount;
        equipment.name = "直流汇流箱-"+index;
        equipment.categoryTypeId = 1;
        equipment.id = j;
        equipment.categoryInfoGid = 1;
        equipments.push(equipment);
        var sheBeiJiChuDangAn = {};
        var shebeijichuxinxi = {};
        var shebeicanshu = {};
        sheBeiJiChuDangAn.shebeijichuxinxi = shebeijichuxinxi;
        sheBeiJiChuDangAn.shebeicanshu = shebeicanshu;
        sheBeiJiChuDangAnData[j] = sheBeiJiChuDangAn;

        shebeijichuxinxi.shebeileixing = "防雷直流汇流箱";
        shebeijichuxinxi.pingpai = "固德威";
        shebeijichuxinxi.xinghao = "LS1-52";
        shebeijichuxinxi.shebeimingcheng = equipment.name;
        shebeijichuxinxi.shebeibianhao = "HL-20210725000"+j;
        shebeijichuxinxi.chuchangriqi = "2020-10-20";
        shebeijichuxinxi.touyongriqi = "2021-07-25";
        shebeijichuxinxi.shebeizhuangtai = "在运";
        shebeijichuxinxi.suozaiquyu = "中石化工业一区1栋顶层";

        shebeicanshu.shebeigonglv = "单路 DC +-10~32A";
        shebeicanshu.shebeidianya = "1000V";
        shebeicanshu.shurudianlu = "2-8路";
        shebeicanshu.shuchulushu = "1路";
        shebeicanshu.jueyuandianzu = "＞100MΩ";
    }
    var kStar= jend+1;
    var kend = nibianqiCount+jend;

    for (var k=kStar;k<=kend;k++) {//逆变器
        var equipment = {};
        equipment.classify = 1;
        var index = k - jend;
        equipment.name = "逆变器-" + index;
        equipment.categoryTypeId = 1;
        equipment.id = k;
        equipment.categoryInfoGid = 1;
        equipments.push(equipment);
        var sheBeiJiChuDangAn = {};
        var shebeijichuxinxi = {};
        var shebeicanshu = {};
        sheBeiJiChuDangAn.shebeijichuxinxi = shebeijichuxinxi;
        sheBeiJiChuDangAn.shebeicanshu = shebeicanshu;
        sheBeiJiChuDangAnData[k] = sheBeiJiChuDangAn;

        shebeijichuxinxi.yunxingshijian = "1476  （小时）";
        shebeijichuxinxi.shebeileixing = "逆变器";
        shebeijichuxinxi.pingpai = "固德威";
        shebeijichuxinxi.xinghao = "HT-100-136kw";
        shebeijichuxinxi.shebeimingcheng = equipment.name;
        shebeijichuxinxi.shebeibianhao = "NB-20200725000"+k;
        shebeijichuxinxi.chuchangriqi = "2020-10-20";
        shebeijichuxinxi.touyongriqi = "2021-07-25";
        shebeijichuxinxi.shebeizhuangtai = "在运";
        shebeijichuxinxi.suozaiquyu = "中石化工业一区1栋顶层";

        shebeicanshu.zuidashurudianya = "1100 (V)";
        shebeicanshu.MPPTdianyafanwei = "16.6A +-3% (V)";
        shebeicanshu.qidongdianya ="200A";
        shebeicanshu.edingshurudianya = "600 V";
        shebeicanshu.meiluMPPTzuidashurudianliu = "30 A";
        shebeicanshu.meiluMPPTzuidaduanludianliu = "45 A";
        shebeicanshu.MPPTshuliang ="10";
        shebeicanshu.meiluMPPTshuruchuanshu ="2";
        shebeicanshu.edingshuchugonglv ="100";
        shebeicanshu.zuidashuchuyougonggonglv ="110";
        shebeicanshu.zuidashuchushizaigonglv ="110";
        shebeicanshu.edingshuchudianya ="380";
        shebeicanshu.shuchudianyapinglv ="50";
        shebeicanshu.zuidashuchudianliu ="167";

    }
    var aStar= kend+1;
    var aend = xudianchiCount+kend;

    for (var a=aStar;a<=aend;a++) {//储电池
        var equipment = {};
        equipment.classify = 1;
        var index = a - kend;
        equipment.name = "储能电池-" + index;
        equipment.categoryTypeId = 1;
        equipment.id = a;
        equipment.categoryInfoGid = 1;
        equipments.push(equipment);
        var sheBeiJiChuDangAn = {};
        var shebeijichuxinxi = {};
        var shebeicanshu = {};
        sheBeiJiChuDangAn.shebeijichuxinxi = shebeijichuxinxi;
        sheBeiJiChuDangAn.shebeicanshu = shebeicanshu;
        sheBeiJiChuDangAnData[a] = sheBeiJiChuDangAn;
        shebeijichuxinxi.yunxingshijian = "1259  （小时）";
        shebeijichuxinxi.shebeileixing = "储能电池";
        shebeijichuxinxi.pingpai = "固德威";
        shebeijichuxinxi.xinghao = "Lynx Home S";
        shebeijichuxinxi.shebeimingcheng = equipment.name;
        shebeijichuxinxi.shebeibianhao = "CD-20210725000"+a;
        shebeijichuxinxi.chuchangriqi = "2020-10-20";
        shebeijichuxinxi.touyongriqi = "2021-07-25";
        shebeijichuxinxi.shebeizhuangtai = "在运";
        shebeijichuxinxi.suozaiquyu = "中石化工业一区1栋顶层";
        shebeicanshu.edingrongliang = "7.68kWh";
        shebeicanshu.keyongrongliang = "16.6A +-3%";
        shebeicanshu.edingdianya = "153.6V";
        shebeicanshu.gongzuodianyafanwei = "144~172.8V";
        shebeicanshu.mozushumu = "4";
        shebeicanshu.edinggonglv ="7.68kW"
    }
    var sStar= aend+1;
    var send = peidianguiCount+aend;
    for (var s=sStar;s<=send;s++) {//并网柜
        var equipment = {};
        equipment.classify = 1;
        var index = s - aend;
        equipment.name = "并网柜-" + index;
        equipment.categoryTypeId = 1;
        equipment.id = s;
        equipment.categoryInfoGid = 1;
        equipments.push(equipment);
        var sheBeiJiChuDangAn = {};
        var shebeijichuxinxi = {};
        var shebeicanshu = {};
        sheBeiJiChuDangAn.shebeijichuxinxi = shebeijichuxinxi;
        sheBeiJiChuDangAn.shebeicanshu = shebeicanshu;
        sheBeiJiChuDangAnData[s] = sheBeiJiChuDangAn;
        shebeijichuxinxi.shebeileixing = "并网柜";
        shebeijichuxinxi.pingpai = "固德威";
        shebeijichuxinxi.xinghao = "XL-21";
        shebeijichuxinxi.shebeimingcheng = equipment.name;
        shebeijichuxinxi.shebeibianhao = "GFM08900"+s;
        shebeijichuxinxi.chuchangriqi = "2020-10-20";
        shebeijichuxinxi.touyongriqi = "2020-10-20";
        shebeijichuxinxi.shebeizhuangtai = "在运";
        shebeijichuxinxi.suozaiquyu = "中石化工业一区1栋顶层"

        shebeicanshu.zongkai = "nm1-630s/3000";
        shebeicanshu.fenkai = "nm1-125s/3000";
        shebeicanshu.fenkaishu  = "3";
        shebeicanshu.dianliu = "600A";
        shebeicanshu.dianya = "400V"
    }
	debugger
    console.log(JSON.stringify(tree));
    console.log(JSON.stringify(sheBeiJiChuDangAnRes));
}

//生成从minNum到maxNum的随机数
Math.seed = 202111230900;
Math.seededRandom = function(max, min) {
    max = max || 1;
    min = min || 0;
    Math.seed = (Math.seed * 9301 + 49297) % 233280;
    var rnd = Math.seed / 233280.0;
    return min + rnd * (max - min);
};

function getHistoryData(){
    // let month = "2021-08-01,2021-09-01,2021-10-01,2021-11-01";
    //发电
    let fadianParam12 = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,50-75,75-100,100-150,150-250,250-300,300-350,300-350,400-450,450-400,450-400,450-400,450-400,400-300,100-50,100-50,100-50,50-0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    let fadianParam11 = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,50-75,75-100,100-150,150-250,250-300,300-350,300-350,350-400,400-450,450-400,450-400,450-400,450-400,350-300,300-250,250-200,200-150,150-100,100-50,0,0,0,0,0,0,0,0,0,0,0,0,0";
    let fadianParam10 = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,50-75,75-100,100-150,150-200,200-250,250-300,250-300,350-400,400-450,450-500,500-550,550-500,550-500,550-500,550-500,500-350,300-250,250-200,200-150,150-100,100-50,50-0,0,0,0,0,0,0,0,0,0,0,0,0";
    let fadianParam9 = "0,0,0,0,0,0,0,0,0,0,0,0,0,50-75,75-100,100-150,150-200,200-250,250-300,250-300,250-300,350-400,400-450,450-500,500-550,550-500,550-500,550-500,550-500,500-350,300-250,250-200,200-150,150-100,150-100,100-75,100-75,50-0,0,0,0,0,0,0,0,0,0,0";
    let fadianParam8 = "0,0,0,0,0,0,0,0,0,0,0,0,0,50-75,75-100,100-150,150-200,200-250,250-300,250-300,250-300,350-400,400-450,450-500,500-550,550-500,550-500,550-500,550-500,500-350,300-250,250-200,200-150,150-100,150-100,100-75,100-75,50-0,0,0,0,0,0,0,0,0,0,0";
    //用电
    let yongdiangParam12 = "100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250";
    let yongdiangParam11 = "100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250";
    let yongdiangParam10 = "100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250";
    let yongdiangParam9 = "100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250";
    let yongdiangParam8 = "100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,800-1000,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250,100-250";

    //储能功率
    let cunengParam12 = "F550-F500,F550-F500,F450-F400,F450-F400,F450-F400,F450-F400,F250-F200,0,0,0,0,0,0,0,0,0,0,725-900,700-850,650-700,550-700,550-600,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    let cunengParam11 = "F550-F500,F550-F500,F450-F400,F450-F400,F450-F400,F450-F400,F250-F200,0,0,0,0,0,0,0,0,0,0,725-900,700-850,650-700,550-700,550-600,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    let cunengParam10 = "F550-F500,F550-F500,F450-F400,F450-F400,F450-F400,F450-F400,F250-F200,0,0,0,0,0,0,0,0,0,0,725-900,700-850,650-700,550-700,550-600,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    let cunengParam9 = "F550-F500,F550-F500,F450-F400,F450-F400,F450-F400,F450-F400,F250-F200,0,0,0,0,0,0,0,0,0,0,725-900,700-850,650-700,550-700,550-600,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    let cunengParam8 = "F550-F500,F550-F500,F450-F400,F450-F400,F450-F400,F450-F400,F250-F200,0,0,0,0,0,0,0,0,0,0,725-900,700-850,650-700,550-700,550-600,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";



    let countString = "31,30,31,30,31";
    let countArry = countString.split(",");
    let fadian = [];
    let yongdian = [];
    let cuneng = [];
    let result = {};
    for (var i=0;i<countArry.length;i++){
        var month = null;
        var count = countArry[i];
        switch(i){
            case 0:
                month = "2108";
                break;
            case 1:
                month = "2109";
                break;
            case 2:
                month = "2110";
                break;
            case 3:
                month = "2111";
                break;
            case 4:
                month = "2112";
                break;
            default:
                break;
        }
        //发电率
        var fadianParam8Arry = fadianParam8.split(",");
        var fadianParam9Arry = fadianParam9.split(",");
        var fadianParam10Arry = fadianParam10.split(",");
        var fadianParam11Arry = fadianParam11.split(",");
        var fadianParam12Arry = fadianParam12.split(",");

        var yongdiangParam8Arry = yongdiangParam8.split(",");
        var yongdiangParam9Arry = yongdiangParam9.split(",");
        var yongdiangParam10Arry = yongdiangParam10.split(",");
        var yongdiangParam11Arry = yongdiangParam11.split(",");
        var yongdiangParam12Arry = yongdiangParam12.split(",");

        var cunengParam8Arry = cunengParam8.split(",");
        var cunengParam9Arry = cunengParam9.split(",");
        var cunengParam10Arry = cunengParam10.split(",");
        var cunengParam11Arry = cunengParam11.split(",");
        var cunengParam12Arry = cunengParam12.split(",");
        for (var j = 0;j<count;j++){
            var date = month;
            var day = j+1;
            if(day<10){
                date = date+"0"+day;
            }else {
                date = date+day;
            }

            for (var k = 0;k<24;k++){
                var h = k;
                if(k<10){
                    h = "0"+k;
                }
                for (var y=0;y<2;y++) {//0为00 1为30
                    var jsonFadian = "";//发电
                    var jsonYongdian = "";//用电
                    var jsonCuNeng = "";//储能
                    var mm = "";
                    if (y == 0) {
                        mm = "00";
                    } else {
                        mm = "30";
                    }
                    var key = date+h+mm
                    var index00 = k*2;
                    var index30 = (k*2)+1;
                    var randomStrFadian = null;
                    var randomStrYongdian = null;
                    var randomStrCuneng = null;

                    var fadianParamArry = {};
                    var yongdianParamArry = {};
                    var cunengParamArry = {};
                    if(i==0){//8月
                        fadianParamArry = fadianParam8Arry;
                        yongdianParamArry = yongdiangParam8Arry
                        cunengParamArry = cunengParam8Arry;
                    }else if(i==1){
                        fadianParamArry = fadianParam9Arry;
                        yongdianParamArry = yongdiangParam9Arry;
                        cunengParamArry = cunengParam9Arry;
                    }else if(i==2){
                        fadianParamArry = fadianParam10Arry;
                        yongdianParamArry = yongdiangParam10Arry;
                        cunengParamArry = cunengParam10Arry;
                    }else if(i==3){
                        fadianParamArry = fadianParam11Arry;
                        yongdianParamArry = yongdiangParam11Arry;
                        cunengParamArry = cunengParam11Arry;
                    }else if(i==4){
                        fadianParamArry = fadianParam12Arry;
                        yongdianParamArry = yongdiangParam12Arry;
                        cunengParamArry = cunengParam12Arry;
                    }
                    if(y==0){
                        randomStrFadian = fadianParamArry[index00];
                        randomStrYongdian = yongdianParamArry[index00];
                        randomStrCuneng = cunengParamArry[index00];
                    }else if(y==1){
                        randomStrFadian = fadianParamArry[index30];
                        randomStrYongdian = yongdianParamArry[index30];
                        randomStrCuneng = cunengParamArry[index30];
                        if(i==1){
                            if(day==30){//9月30号凌晨要储能
                                if(k>=8){
                                    randomStrCuneng = "0"
                                }
                            }
                        }
                    }
                    if(i==2){//10月
                        if(day==8){//10月8号白天要用
                            if(k<8){
                                randomStrCuneng = "0";
                            }
                        }else if(1<=day && day<8) {
                            randomStrCuneng = "0";
                        }
                        if(1<=day && day<8){
                            randomStrYongdian = "100-250";//如果休息日数据变了需要修改
                        }
                    }
                    if(i==1){//9月
                        if(day==22){//端午 过后24号只是用电
                            if(k<8){
                                randomStrCuneng = "0";
                            }
                        }else if(19<=day && day<22) {
                            randomStrCuneng = "0";
                        }
                        if(19<=day && day<22){
                            randomStrYongdian = "100-250";//如果休息日数据变了需要修改
                        }
                    }
                    if(randomStrFadian.indexOf("-")!=-1){
                        var scopeArry = randomStrFadian.split("-");
                        var minStr = scopeArry[0];
                        var maxStr = scopeArry[1];
                        var min = parseInt(minStr);
                        var max = parseInt(maxStr);
                        var res = Math.seededRandom(max, min);
                        jsonFadian = key +"," + res.toFixed(2);
                    }else{
                        // jsonFadian.v = 0;
                    }
                    if(randomStrYongdian.indexOf("-")!=-1){
                        var scopeArry = randomStrYongdian.split("-");
                        var minStr = scopeArry[0];
                        var maxStr = scopeArry[1];
                        var min = parseInt(minStr);
                        var max = parseInt(maxStr);
                        var res = Math.seededRandom(max, min);
                        jsonYongdian = key +"," +  res.toFixed(2);

                    }else{
                        // jsonYongdian.v = 0;
                    }
                    if(randomStrCuneng.indexOf("-")!=-1){
                        var scopeArry = randomStrCuneng.split("-");
                        var minStr = scopeArry[0].replace("F","-");
                        var maxStr = scopeArry[1].replace("F","-");
                        var min = parseInt(minStr);
                        var max = parseInt(maxStr);
                        var res = Math.seededRandom(max, min);
                        jsonCuNeng = key +"," +  res.toFixed(2);
                    }else{
                        // jsonCuNeng.v = 0;
                    }
                    if(jsonFadian!=""){
                        fadian.push(jsonFadian);
                    }
                    if(jsonYongdian!=""){
                        yongdian.push(jsonYongdian);
                    }
                    if(jsonCuNeng!=""){
                        cuneng.push(jsonCuNeng);
                    }
                }
            }
        }
    }
    result.fadian = fadian;
    result.yongdian = yongdian;
    result.cuneng = cuneng;
	debugger
    console.log(JSON.stringify(result));
}


function getres(str) {
    var scopeArry = str.split("-");
    var minStr = scopeArry[0];
    var maxStr = scopeArry[1];
    var min = parseInt(minStr);
    var max = parseInt(maxStr);
    var res = Math.seededRandom(max, min);
    res = res.toFixed(2);
    return res;
}

function shitu(){
    // let month = "2021-08-01,2021-09-01,2021-10-01,2021-11-01";

    let fadianParam12 = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,50-75,75-100,100-150,150-250,250-300,300-350,300-350,400-450,450-400,450-400,450-400,450-400,400-300,100-50,100-50,100-50,50-0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    let fadianParam11 = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,50-75,75-100,100-150,150-250,250-300,300-350,300-350,350-400,400-450,450-400,450-400,450-400,450-400,350-300,300-250,250-200,200-150,150-100,100-50,0,0,0,0,0,0,0,0,0,0,0,0,0";
    let fadianParam10 = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,50-75,75-100,100-150,150-200,200-250,250-300,250-300,350-400,400-450,450-500,500-550,550-500,550-500,550-500,550-500,500-350,300-250,250-200,200-150,150-100,100-50,50-0,0,0,0,0,0,0,0,0,0,0,0,0";
    let fadianParam9 = "0,0,0,0,0,0,0,0,0,0,0,0,0,50-75,75-100,100-150,150-200,200-250,250-300,250-300,250-300,350-400,400-450,450-500,500-550,550-500,550-500,550-500,550-500,500-350,300-250,250-200,200-150,150-100,150-100,100-75,100-75,50-0,0,0,0,0,0,0,0,0,0,0";
    let fadianParam8 = "0,0,0,0,0,0,0,0,0,0,0,0,0,50-75,75-100,100-150,150-200,200-250,250-300,250-300,250-300,350-400,400-450,450-500,500-550,550-500,550-500,550-500,550-500,500-350,300-250,250-200,200-150,150-100,150-100,100-75,100-75,50-0,0,0,0,0,0,0,0,0,0,0";

    let cunengParam12 = "F550-F500,F550-F500,F450-F400,F450-F400,F450-F400,F450-F400,F250-F200,0,0,0,0,0,0,0,0,0,0,725-900,700-850,650-700,550-700,550-600,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    let cunengParam11 = "F550-F500,F550-F500,F450-F400,F450-F400,F450-F400,F450-F400,F250-F200,0,0,0,0,0,0,0,0,0,0,725-900,700-850,650-700,550-700,550-600,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    let cunengParam10 = "F550-F500,F550-F500,F450-F400,F450-F400,F450-F400,F450-F400,F250-F200,0,0,0,0,0,0,0,0,0,0,725-900,700-850,650-700,550-700,550-600,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    let cunengParam9 = "F550-F500,F550-F500,F450-F400,F450-F400,F450-F400,F450-F400,F250-F200,0,0,0,0,0,0,0,0,0,0,725-900,700-850,650-700,550-700,550-600,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    let cunengParam8 = "F550-F500,F550-F500,F450-F400,F450-F400,F450-F400,F450-F400,F250-F200,0,0,0,0,0,0,0,0,0,0,725-900,700-850,650-700,550-700,550-600,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";

    //电池容量kwh
    let DCRLParam = "78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85";
    //电量%
    let DLParam = "5-15,15-30,35-50,50-65,65-80,80-90,90-100,0,0,0,0,0,0,0,0,0,0,90-100,70-90,70-45,45-30,30-5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
    //配电柜状态
    let peidiangui = "储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,离网模式,离网模式,离网模式,离网模式,离网模式,并网模式,并网模式,并网模式,并网模式,并网模式,并网模式,并网模式,并网模式,并网模式,并网模式,并网模式,并网模式,并网模式,并网模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式,储能模式";
    // 光伏组件
    let DCZgongzuodianya = "30-45";//工作电压
    let DCZgongzuodianliu = "8-10";//工作电流
    let DCZFadiangonglv = "315-400";//发电功率
    //汇流箱
    let HLXshurudianya = "380-420";//输入电压（V）
    let HLXshuruguoliurongduanqi = "10-15"//输入过流熔断器（A）
    let HLXshuchudianya = "380-420";//输出电压
    let HLXshuruguangfuzujianlushu = 6;
    let HLXshuchudianliu = "10-15";//输出电流

    let NBQshuruzhiliudianya = "700-800";//输入直流电压
    let NBQshuruzhiliudianliu = "120-130";//输入直流电流
    let NBQshuchujiaoliugonglv = "84-100";//输出交流功率（KW)
    let NBQshuchudianya = "380";//输出电压（V）
    let NBQshuchudianliu = "90-120";//输出电流（A）

    let CDCshuruzhiliudianya = "380"; //输入直流电压（V）
    let CDCshuchudianya = "380";//输出电压（V）
    let CDCshuchudianliu = "25";//输出电流（A）


    let countString = "31,30,31,30,31";
    let countArry = countString.split(",");
    let result = {};
    let DCZArr = [];
    let HLXArr = [];
    let NBQArr = [];
    let CDCArr = [];
    let PDGArr = [];
    var DCZYunxingshijian = 0.0;
    var HLXYunxingshijian = 0.0;
    var CDCYunxingshijian = 0.0;
    var NBQYunxingshijian = 0.0;
    var cdcs = 0;//储能电池充电次数
    var fdcs = 0;//储能电池放电次数
    for (var i=0;i<countArry.length;i++){
        var month = null;
        var count = countArry[i];
        switch(i){
            case 0:
                month = "2108";
                break;
            case 1:
                month = "2109";
                break;
            case 2:
                month = "2110";
                break;
            case 3:
                month = "2111";
                break;
            case 4:
                month = "2112";
                break;
            default:
                break;
        }
        //发电率
        var fadianParam8Arry = fadianParam8.split(",");
        var fadianParam9Arry = fadianParam9.split(",");
        var fadianParam10Arry = fadianParam10.split(",");
        var fadianParam11Arry = fadianParam11.split(",");
        var fadianParam12Arry = fadianParam12.split(",");

        var cunengParam8Arry = cunengParam8.split(",");
        var cunengParam9Arry = cunengParam9.split(",");
        var cunengParam10Arry = cunengParam10.split(",");
        var cunengParam11Arry = cunengParam11.split(",");
        var cunengParam12Arry = cunengParam12.split(",");
        var peidianguiArr = peidiangui.split(",");
        var DCRLParamArr = DCRLParam.split(",");
        var DLParamArr = DLParam.split(",");
        for (var j = 0;j<count;j++){
            var date = month;
            var day = j+1;
            if(day<10){
                date = date+"0"+day;
            }else {
                date = date+day;
            }

            for (var k = 0;k<24;k++){
                var h = k;
                if(k<10){
                    h = "0"+k;
                }
                for (var y=0;y<2;y++) {//0为00 1为30
                    var mm = "";
                    if (y == 0) {
                        mm = "00";
                    } else {
                        mm = "30";
                    }
                    var key = date+h+mm
                    var index00 = k*2;
                    var index30 = (k*2)+1;
                    var randomStrFadian = null;
                    var randomStrCuneng = null;
                    var randomStrPeidiangui = null;
                    var randomStrDCRL = null;
                    var randomStrDL = null;
                    var fadianParamArry = {};
                    var cunengParamArry = {};
                    if(i==0){//8月
                        fadianParamArry = fadianParam8Arry;
                        cunengParamArry = cunengParam8Arry;
                    }else if(i==1){
                        fadianParamArry = fadianParam9Arry;
                        cunengParamArry = cunengParam9Arry;
                    }else if(i==2){
                        fadianParamArry = fadianParam10Arry;
                        cunengParamArry = cunengParam10Arry;
                    }else if(i==3){
                        fadianParamArry = fadianParam11Arry;
                        cunengParamArry = cunengParam11Arry;
                    }else if(i==4){
                        fadianParamArry = fadianParam12Arry;
                        cunengParamArry = cunengParam12Arry;
                    }
                    if(y==0){
                        randomStrFadian = fadianParamArry[index00];
                        randomStrCuneng = cunengParamArry[index00];
                        randomStrPeidiangui = peidianguiArr[index00];
                        randomStrDCRL = DCRLParamArr[index00];
                        randomStrDL = DLParamArr[index00];

                        if(k==3){
                            //充电完成
                            cdcs = cdcs +1;
                        }
                    }else if(y==1){
                        randomStrFadian = fadianParamArry[index30];
                        randomStrCuneng = cunengParamArry[index30];
                        randomStrPeidiangui = peidianguiArr[index30];
                        randomStrDCRL = DCRLParamArr[index30];
                        randomStrDL = DLParamArr[index30];
                        if(i==1){
                            if(day==30){//9月30号凌晨要储能
                                if(k>=8){
                                    randomStrCuneng = "0"
                                    randomStrPeidiangui = "储能模式";
                                }
                            }
                        }
                        if(k==10){
                            //充电完成
                            fdcs = fdcs +1;
                        }
                    }
                    if(i==2){//10月
                        if(day==8){//10月8号白天要用
                            if(k<8){
                                randomStrCuneng = "0";
                                randomStrPeidiangui = "储能模式";
                            }
                        }else if(1<=day && day<8) {
                            randomStrCuneng = "0";
                            randomStrPeidiangui = "储能模式";
                        }
                    }
                    if(i==1){//9月
                        if(day==22){//端午 过后24号只是用电
                            if(k<8){
                                randomStrCuneng = "0";
                                randomStrPeidiangui = "储能模式";
                            }
                        }else if(19<=day && day<22) {
                            randomStrCuneng = "0";
                            randomStrPeidiangui = "储能模式";
                        }
                    }

                    var DCZ = {};
                    var HLX = {};
                    var CDC = {};
                    var NBQ = {};
                    var PDG = {};
                    if(randomStrFadian.indexOf("-")!=-1){
                        var res = getres(DCZFadiangonglv);
                    var DCZgongzuodianyares = getres(DCZgongzuodianya);
                    var DCZgongzuodianliures = getres(DCZgongzuodianliu);
                    DCZ.t = key;
                    DCZ.s = 1;
                    DCZ.fdgl = res;
                    DCZ.gzdy = DCZgongzuodianyares;
                    DCZ.gzdl = DCZgongzuodianliures;
                    DCZYunxingshijian = DCZYunxingshijian+0.5;
                    DCZ.yxsj = DCZYunxingshijian;
                    DCZArr.push(DCZ.t+","+DCZ.s+","+ DCZ.yxsj+","+DCZ.fdgl+","+DCZ.gzdy+","+DCZ.gzdl);
                    HLX.t = key;
                    HLX.s = 1
                    var HLXshurudianyares = getres(HLXshurudianya);
                    HLXYunxingshijian = HLXYunxingshijian+0.5;
                    HLX.yxsj = HLXYunxingshijian;
                    HLX.srgfzjls = HLXshuruguangfuzujianlushu;
                    HLX.srdy = HLXshurudianyares;
                    var HLXshuruguoliurongduanqires = getres(HLXshuruguoliurongduanqi);
                    HLX.srglrdq = HLXshuruguoliurongduanqires;
                    var HLXshuchudianyares = getres(HLXshuchudianya);
                    HLX.scdy = HLXshuchudianyares;
                    HLX.scdl = getres(HLXshuchudianliu);
                    HLXArr.push(HLX.t+","+HLX.s+","+HLX.yxsj+","+HLX.srgfzjls+","+HLX.srdy+","+HLX.scdy+","+HLX.scdl);
                }else{
                    // jsonFadian.v = 0;
                    var res = 0;
                    var DCZgongzuodianyares = 0;
                    var DCZgongzuodianliures = 0;
                    DCZ.t = key;
                    DCZ.s = 0;
                    DCZ.fdgl = res;
                    DCZ.gzdy = 0;
                    DCZ.gzdl = 0;
                    DCZYunxingshijian = DCZYunxingshijian+0;
                    DCZ.yxsk = 0;
                    DCZ.yxsj = DCZYunxingshijian;
                    DCZArr.push(DCZ.t+","+DCZ.s+","+ DCZ.yxsj+","+DCZ.fdgl+","+DCZ.gzdy+","+DCZ.gzdl);
                    HLX.t = key;
                    HLX.s = 0;
                    var HLXshurudianyares = 0;
                    HLXYunxingshijian = HLXYunxingshijian+0;
                    HLX.srdy = 0;
                    HLX.yxsj = HLXYunxingshijian;
                    HLX.srgfzjls = 0
                    var HLXshuruguoliurongduanqires = 0;
                    HLX.srglrdq = 0;
                    HLX.scdy = 0;
                    HLX.scdl =0;
                    HLXArr.push(HLX.t+","+HLX.s+","+HLX.yxsj+","+HLX.srgfzjls+","+HLX.srdy+","+HLX.scdy+","+HLX.scdl);
                }
                if(randomStrCuneng.indexOf("-")!=-1){
                    CDC.t = key;
                    CDCYunxingshijian = CDCYunxingshijian+0.5;
                    CDC.s = 1;
                    CDC.yxsj = CDCYunxingshijian;
                    var dcrl = getres(randomStrDCRL);
                    var dl = getres(randomStrDL);
                    CDC.dcrl = dcrl;
                    CDC.dl = dl;
                    CDC.cdcs = cdcs;
                    CDC.fdcs = fdcs;
                    CDCArr.push(CDC.t+","+CDC.s+","+CDC.yxsj+","+CDC.dcrl+","+CDC.dl+","+CDC.cdcs+","+CDC.fdcs);
                }else{
                    CDC.t = key;
                    CDCYunxingshijian = CDCYunxingshijian+0;
                    CDC.s = 0;
                    CDC.yxsj = 0;
                    CDC.dcrl = 0;
                    CDC.dl = 0;
                    CDC.cdcs = cdcs;
                    CDC.fdcs = fdcs;
                    CDCArr.push(CDC.t+","+CDC.s+","+CDC.yxsj+","+CDC.dcrl+","+CDC.dl+","+CDC.cdcs+","+CDC.fdcs);
                }
                if("储能模式" == randomStrPeidiangui){
                    PDG.t = key;
                    PDG.v = "2";
                }else if("离网模式" == randomStrPeidiangui){
                    PDG.t = key;
                    PDG.v = "1";
                }else if("并网模式" == randomStrPeidiangui){
                    PDG.t = key;
                    PDG.v = "0";
                }
                PDGArr.push(PDG.t+","+PDG.v);
                //逆变器
                NBQ.t = key;
                NBQ.s = 1;
                NBQYunxingshijian = NBQYunxingshijian + 0.5;
                NBQ.yxsj = NBQYunxingshijian;
                NBQ.srzldy = getres(NBQshuruzhiliudianya);
                NBQ.srzldl = getres(NBQshuruzhiliudianliu);
                NBQ.scjlgl = getres(NBQshuchujiaoliugonglv);
                NBQ.scdy = NBQshuchudianya;
                NBQ.scdl = getres(NBQshuchudianliu);
                NBQArr.push(NBQ.t+","+NBQ.s+","+NBQ.yxsj+","+NBQ.srzldy+","+NBQ.srzldl+","+NBQ.scjlgl+","+NBQ.scdy+","+NBQ.scdl);
            }
        }
    }
}
result.dcz = DCZArr;
result.hlx = HLXArr;
result.nbq = NBQArr;
result.cdc = CDCArr;
result.pdg = PDGArr;
debugger
console.log(JSON.stringify(result));
}

/**
 * 获取from表单所有值，value为空不处理
 * @param fromId
 * @returns {{}}
 */
function getData(fromId){
    let d = {};
    let t = $('#'+fromId).serializeArray();
    $.each(t, function() {
        if (this.value.length>0){
            d[this.name] = this.value;
        }
    });
    return d;
}

function qita() {
    // let month = "2021-08-01,2021-09-01,2021-10-01,2021-11-01";
    //温度
    let wenduParam = "78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85";
    //湿度
    let shiduParam = "10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12";
    let fuzhaoParam = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,50-100,100-150,150-220,220-350,350-400,400-550,550-660,660-750,750-800,800-850,850-950,950-980,980-950,950-850,800-850,750-800,660-750,550-660,400-550,350-400,220-350,150-220,0,0,0,0,0,0,0,0,0,0,0,0";
    let countString = "31,30,31,30,31";
    let countArry = countString.split(",");
    var SDArr = [];
    var WDArr = [];
    var FZDArr = [];
    var result ={};
    for (var i=0;i<countArry.length;i++){
        var month = null;
        var count = countArry[i];
        switch(i){
            case 0:
                month = "2108";
                break;
            case 1:
                month = "2109";
                break;
            case 2:
                month = "2110";
                break;
            case 3:
                month = "2111";
                break;
            case 4:
                month = "2112";
                break;
            default:
                break;
        }
        var wenduArr = wenduParam.split(",");
        var shiduArr = shiduParam.split(",");
        var fuzhaoArr = fuzhaoParam.split(",");
        for (var j = 0;j<count;j++) {
            var date = month;
            var day = j + 1;
            if (day < 10) {
                date = date + "0" + day;
            } else {
                date = date + day;
            }

            for (var k = 0; k < 24; k++) {
                var h = k;
                if (k < 10) {
                    h = "0" + k;
                }
                for (var y = 0; y < 2; y++) {
                    var randomStrWendu = null;
                    var randomStrShidu = null;
                    var randomStrFuzhao = null;
                    var mm = "";
                    if (y == 0) {
                        mm = "00";
                    } else {
                        mm = "30";
                    }
                    var key = date+h+mm
                    var index00 = k*2;
                    var index30 = (k*2)+1;
                    if(y==0){
                        randomStrWendu = wenduArr[index00];
                        randomStrShidu = shiduArr[index00];
                        randomStrFuzhao = fuzhaoArr[index00];
                    }else if(y==1) {
                        randomStrWendu = wenduArr[index30];
                        randomStrShidu = shiduArr[index30];
                        randomStrFuzhao = fuzhaoArr[index30];
                    }
                    var SD = "";
                    var WD = "";
                    var FZD = "";
                    if(randomStrWendu.indexOf("-")!=-1){
                        var res = getres(randomStrWendu);
                        WD = key+","+res;
                        WDArr.push(WD);
                    }

                    if(randomStrShidu.indexOf("-")!=-1){
                        var res = getres(randomStrShidu);
                        SD = key+","+res;
                        SDArr.push(SD);
                    }

                    if(randomStrFuzhao.indexOf("-")!=-1){
                        var res = getres(randomStrFuzhao);
                        FZD = key+","+res;
                        FZDArr.push(FZD);
                    }

                }
            }
        }
    }
    result.WD = SDArr;
    result.SD = WDArr;
    result.FZD = FZDArr;
	debugger
    console.log(JSON.stringify(result));
}


function rlANDdl() {
    // let month = "2021-08-01,2021-09-01,2021-10-01,2021-11-01";

    let DCRLParam = "78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,75-80,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85,78-85";
    let DLParam = "10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,12-15,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12,10-12";
    let countString = "31,30,31,30,31";
    let countArry = countString.split(",");
    var DCRLArr = [];
    var DLArr = [];
    var result ={};
    for (var i=0;i<countArry.length;i++){
        var month = null;
        var count = countArry[i];
        switch(i){
            case 0:
                month = "2108";
                break;
            case 1:
                month = "2109";
                break;
            case 2:
                month = "2110";
                break;
            case 3:
                month = "2111";
                break;
            case 4:
                month = "2112";
                break;
            default:
                break;
        }
        var DCRLParamArr = DCRLParam.split(",");
        var DLParamArr = DLParam.split(",");
        for (var j = 0;j<count;j++) {
            var date = month;
            var day = j + 1;
            if (day < 10) {
                date = date + "0" + day;
            } else {
                date = date + day;
            }

            for (var k = 0; k < 24; k++) {
                var h = k;
                if (k < 10) {
                    h = "0" + k;
                }
                for (var y = 0; y < 2; y++) {
                    var randomStrDCRL = null;
                    var randomStrDL = null;
                    var mm = "";
                    if (y == 0) {
                        mm = "00";
                    } else {
                        mm = "30";
                    }
                    var key = date+h+mm
                    var index00 = k*2;
                    var index30 = (k*2)+1;
                    if(y==0){
                        randomStrDCRL = DCRLParamArr[index00];
                        randomStrDL = DLParamArr[index00];
                    }else if(y==1) {
                        randomStrDCRL = DCRLParamArr[index30];
                        randomStrDL = DLParamArr[index30];
                    }
                    var DCRL = "";
                    var DL = "";
                    if(randomStrDCRL.indexOf("-")!=-1){
                        var res = getres(randomStrDCRL);
                        DCRL = key+","+res;
                        DCRLArr.push(DCRL);
                    }

                    if(randomStrDCRL.indexOf("-")!=-1){
                        var res = getres(randomStrDCRL);
                        DL = key+","+res;
                        DLArr.push(DL);
                    }

                }
            }
        }
    }
    result.DCRL = DCRLArr;
    result.DL = DLArr;
	debugger
    console.log(JSON.stringify(result));
}
