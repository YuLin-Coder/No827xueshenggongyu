<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-cn">


<head>
    <%@ include file="../../static/head.jsp" %>
    <!-- font-awesome -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap-select.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<style>

</style>
<body>



<div class="modal fade" id="zichanTableModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <%-- 模态框标题--%>
            <div class="modal-header">
                <h5 class="modal-title">宿舍资产</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%-- 模态框内容 --%>
            <div class="modal-body">
                <%-- 查出当前宿舍的用户 --%>
                <table id="zichanTable" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th >资产名字</th>
                        <th >资产状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="zichanTableTbody">
                    </tbody>
                </table>
            </div>
            <%-- 模态框按钮 --%>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="susheYongTable" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <%-- 模态框标题--%>
            <div class="modal-header">
                <h5 class="modal-title">宿舍人员</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <%-- 模态框内容 --%>
            <div class="modal-body">
                <div class="col-sm-12">
                    <label class="col-sm-10">
                    学生
                    <select id="yonghuSelect" name="yonghuSelect"
                            class="selectpicker form-control"  data-live-search="true"
                            title="请选择" data-header="请选择" data-size="5">
                    </select>
                    </label>
                    <label class="col-sm-1">
                        <button onclick="addYonghu()" type="button" class="btn btn-success 新增">添加</button>
                    </label>
                </div>
                <%-- 查出当前宿舍的用户 --%>
                <table id="yonghuTable" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th >姓名</th>
                        <th >手机号</th>
                        <th >身份证号</th>
                        <th >性别</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody id="yonghuTableTbody">
                    </tbody>
                </table>
            </div>
            <%-- 模态框按钮 --%>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <%--<button type="button" class="btn btn-primary">
                    提交更改
                </button>--%>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>




<!-- Pre Loader -->
<div class="loading">
    <div class="spinner">
        <div class="double-bounce1"></div>
        <div class="double-bounce2"></div>
    </div>
</div>
<!--/Pre Loader -->
<div class="wrapper">
    <!-- Page Content -->
    <div id="content">
        <!-- Top Navigation -->
        <%@ include file="../../static/topNav.jsp" %>
        <!-- Menu -->
        <div class="container menu-nav">
            <nav class="navbar navbar-expand-lg lochana-bg text-white">
                <button class="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarSupportedContent"
                        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="ti-menu text-white"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul id="navUl" class="navbar-nav mr-auto">
                    </ul>
                </div>
            </nav>
        </div>
        <!-- /Menu -->
        <!-- Breadcrumb -->
        <!-- Page Title -->
        <div class="container mt-0">
            <div class="row breadcrumb-bar">
                <div class="col-md-6">
                    <h3 class="block-title">宿舍信息管理</h3>
                </div>
                <div class="col-md-6">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item">
                            <a href="${pageContext.request.contextPath}/index.jsp">
                                <span class="ti-home"></span>
                            </a>
                        </li>
                        <li class="breadcrumb-item">宿舍信息管理</li>
                        <li class="breadcrumb-item active">宿舍信息列表</li>
                    </ol>
                </div>
            </div>
        </div>
        <!-- /Page Title -->

        <!-- /Breadcrumb -->
        <!-- Main Content -->
        <div class="container">

            <div class="row">
                <!-- Widget Item -->
                <div class="col-md-12">
                    <div class="widget-area-2 lochana-box-shadow">
                        <h3 class="widget-title">宿舍信息列表</h3>
                        <div class="table-responsive mb-3">
                            <div class="col-sm-12">
                                                                 
                                        <label>
                                            公寓
                                            <input type="text" id="buildingSearch" style="width: 140px;" class="form-control form-control-sm"
                                                   placeholder="公寓号" aria-controls="tableId">
                                        </label>
                                 
                                        <label>
                                            单元
                                            <input type="text" id="unitSearch" style="width: 140px;" class="form-control form-control-sm"
                                                   placeholder="单元号" aria-controls="tableId">
                                        </label>
                                 
                                        <label>
                                            房间号
                                            <input type="text" id="roomSearch" style="width: 140px;" class="form-control form-control-sm"
                                                   placeholder="房间号" aria-controls="tableId">
                                        </label>
                                                                
                                <button onclick="search()" type="button" class="btn btn-primary">查询</button>
                                <button onclick="add()" type="button" class="btn btn-success 新增">添加</button>
                                <button onclick="deleteMore()" type="button" class="btn btn-danger 删除">批量删除</button>
                                <button onclick="graph()" type="button" class="btn btn-success 报表">报表</button>
                            </div>
                            <table id="tableId" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th class="no-sort" style="min-width: 35px;">
                                        <div class="custom-control custom-checkbox">
                                            <input class="custom-control-input" type="checkbox" id="select-all"
                                                   onclick="chooseAll()">
                                            <label class="custom-control-label" for="select-all"></label>
                                        </div>
                                    </th>

                                    <th >公寓</th>
                                    <th >单元</th>
                                    <th >房间号</th>
                                    <th >已住人员</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody id="thisTbody">
                                </tbody>
                            </table>
                            <div class="col-md-6 col-sm-3">
                                <div class="dataTables_length" id="tableId_length">

                                    <select name="tableId_length" aria-controls="tableId" id="selectPageSize"
                                            onchange="changePageSize()">
                                        <option value="10">10</option>
                                        <option value="25">25</option>
                                        <option value="50">50</option>
                                        <option value="100">100</option>
                                    </select>
                                    条 每页

                                </div>
                            </div>
                            <nav aria-label="Page navigation example">
                                <ul class="pagination justify-content-end">
                                    <li class="page-item" id="tableId_previous" onclick="pageNumChange('pre')">
                                        <a class="page-link" href="#" tabindex="-1">上一页</a>
                                    </li>

                                    <li class="page-item" id="tableId_next" onclick="pageNumChange('next')">
                                        <a class="page-link" href="#">下一页</a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
                <!-- /Widget Item -->
            </div>
        </div>
        <!-- /Main Content -->

    </div>
    <!-- /Page Content -->
</div>
<!-- Back to Top -->
<a id="back-to-top" href="#" class="back-to-top">
    <span class="ti-angle-up"></span>
</a>
<!-- /Back to Top -->
<%@ include file="../../static/foot.jsp" %>

<script language="javascript" type="text/javascript"
        src="${pageContext.request.contextPath}/resources/My97DatePicker/WdatePicker.js"></script>
</script><script type="text/javascript" charset="utf-8"
                 src="${pageContext.request.contextPath}/resources/js/bootstrap-select.js"></script>

<script>
    <%@ include file="../../utils/menu.jsp"%>
            <%@ include file="../../static/setMenu.js"%>
            <%@ include file="../../utils/baseUrl.jsp"%>
            <%@ include file="../../static/getRoleButtons.js"%>
            <%@ include file="../../static/crossBtnControl.js"%>
    var tableName = "sushe";
    var pageType = "list";
    var searchForm = {key: ""};
    var pageIndex = 1;
    var pageSize = 10;
    var totalPage = 0;
    var dataList = [];
    var sortColumn = '';
    var sortOrder = '';
    var ids = [];
    var checkAll = false;

    var susheId = null;


    function init() {
        // 满足条件渲染提醒接口
    }

    // 改变每页记录条数
    function changePageSize() {
        var selection = document.getElementById('selectPageSize');
        var index = selection.selectedIndex;
        pageSize = selection.options[index].value;
        getDataList();
    }



    // 查询
    function search() {
        searchForm = {key: ""};

        <!-- 级联表的级联字典表 -->
    <!-- 本表的查询条件 -->
         
        //公寓
        var buildingSearchInput = $('#buildingSearch');
        if( buildingSearchInput != null){
            if (buildingSearchInput.val() != null && buildingSearchInput.val() != '') {
                searchForm.building = $('#buildingSearch').val();
            }
        }
     
        //单元
        var unitSearchInput = $('#unitSearch');
        if( unitSearchInput != null){
            if (unitSearchInput.val() != null && unitSearchInput.val() != '') {
                searchForm.unit = $('#unitSearch').val();
            }
        }
     
        //房间号
        var roomSearchInput = $('#roomSearch');
        if( roomSearchInput != null){
            if (roomSearchInput.val() != null && roomSearchInput.val() != '') {
                searchForm.room = $('#roomSearch').val();
            }
        }
                getDataList();
    }

    // 获取数据列表
    function getDataList() {
        http("sushe/page", "GET", {
            page: pageIndex,
            limit: pageSize,
            sort: sortColumn,
            order: sortOrder,
            //本表的
            building: searchForm.building,
            unit: searchForm.unit,
            room: searchForm.room,

            //级联表的

    }, (res) => {
            if(res.code == 0)
            {
                clear();
                $("#thisTbody").html("");
                dataList = res.data.list;
                totalPage = res.data.totalPage;
                //var tbody = document.getElementById('tbMain');
                for (var i = 0; i < dataList.length; i++) { //遍历一下表格数据  
                    var trow = setDataRow(dataList[i], i); //定义一个方法,返回tr数据 
                    $('#thisTbody').append(trow);
                }
                pagination(); //渲染翻页组件
                getRoleButtons();// 权限按钮控制
            }
        })
        ;
    }

    // 渲染表格数据
    function setDataRow(item, number) {
        //创建行 
        var row = document.createElement('tr');
        row.setAttribute('class', 'useOnce');
        //创建勾选框
        var checkbox = document.createElement('td');
        var checkboxDiv = document.createElement('div');
        checkboxDiv.setAttribute("class", "custom-control custom-checkbox");
        var checkboxInput = document.createElement('input');
        checkboxInput.setAttribute("class", "custom-control-input");
        checkboxInput.setAttribute("type", "checkbox");
        checkboxInput.setAttribute('name', 'chk');
        checkboxInput.setAttribute('value', item.id);
        checkboxInput.setAttribute("id", number);
        checkboxDiv.appendChild(checkboxInput);
        var checkboxLabel = document.createElement('label');
        checkboxLabel.setAttribute("class", "custom-control-label");
        checkboxLabel.setAttribute("for", number);
        checkboxDiv.appendChild(checkboxLabel);
        checkbox.appendChild(checkboxDiv);
        row.appendChild(checkbox)



        //公寓
        var buildingCell = document.createElement('td');
        buildingCell.innerHTML = item.building;
        row.appendChild(buildingCell);


        //单元
        var unitCell = document.createElement('td');
        unitCell.innerHTML = item.unit;
        row.appendChild(unitCell);


        //房间号
        var roomCell = document.createElement('td');
        roomCell.innerHTML = item.room;
        row.appendChild(roomCell);


        //已住人员
        var susheNumberCell = document.createElement('td');
        susheNumberCell.innerHTML = item.susheNumber;
        row.appendChild(susheNumberCell);



        //每行按钮
        var btnGroup = document.createElement('td');

        //详情按钮
        var detailBtn = document.createElement('button');
        var detailAttr = "detail(" + item.id + ')';
        detailBtn.setAttribute("type", "button");
        detailBtn.setAttribute("class", "btn btn-info btn-sm 查看");
        detailBtn.setAttribute("onclick", detailAttr);
        detailBtn.innerHTML = "查看";
        btnGroup.appendChild(detailBtn);
        //查看人员
        var detailBtn = document.createElement('button');
        var detailAttr = "getId(" + item.id + ')';
        detailBtn.setAttribute("type", "button");
        detailBtn.setAttribute("class", "btn btn-info btn-sm 修改");
        detailBtn.setAttribute("onclick", detailAttr);
        detailBtn.innerHTML = "查看人员信息";
        btnGroup.appendChild(detailBtn);
        //修改按钮
        var editBtn = document.createElement('button');
        var editAttr = 'edit(' + item.id + ')';
        editBtn.setAttribute("type", "button");
        editBtn.setAttribute("class", "btn btn-warning btn-sm 修改");
        editBtn.setAttribute("onclick", editAttr);
        editBtn.innerHTML = "修改房间信息";
        btnGroup.appendChild(editBtn);
        //查看资产
        var detailBtn = document.createElement('button');
        var detailAttr = "getziChan(" + item.id + ')';
        detailBtn.setAttribute("type", "button");
        detailBtn.setAttribute("class", "btn btn-info btn-sm 修改");
        detailBtn.setAttribute("onclick", detailAttr);
        detailBtn.innerHTML = "查看资产信息";
        btnGroup.appendChild(detailBtn);
        //删除按钮
        var deleteBtn = document.createElement('button');
        var deleteAttr = 'remove(' + item.id + ')';
        deleteBtn.setAttribute("type", "button");
        deleteBtn.setAttribute("class", "btn btn-danger btn-sm 删除");
        deleteBtn.setAttribute("onclick", deleteAttr);
        deleteBtn.innerHTML = "删除"
        btnGroup.appendChild(deleteBtn)

        row.appendChild(btnGroup)
        return row;
    }


    // 翻页
    function pageNumChange(val) {
        if (val == 'pre') {
            pageIndex--;
        } else if (val == 'next') {
            pageIndex++;
        } else {
            pageIndex = val;
        }
        getDataList();
    }

    // 下载
    function download(url) {
        window.open(url);
    }

    // 渲染翻页组件
    function pagination() {
        var beginIndex = pageIndex;
        var endIndex = pageIndex;
        var point = 4;
        //计算页码
        for (var i = 0; i < 3; i++) {
            if (endIndex == totalPage) {
                break;
            }
            endIndex++;
            point--;
        }
        for (var i = 0; i < 3; i++) {
            if (beginIndex == 1) {
                break;
            }
            beginIndex--;
            point--;
        }
        if (point > 0) {
            while (point > 0) {
                if (endIndex == totalPage) {
                    break;
                }
                endIndex++;
                point--;
            }
            while (point > 0) {
                if (beginIndex == 1) {
                    break;
                }
                beginIndex--;
                point--
            }
        }
        // 是否显示 前一页 按钮
        if (pageIndex > 1) {
            $('#tableId_previous').show();
        } else {
            $('#tableId_previous').hide();
        }
        // 渲染页码按钮
        for (var i = beginIndex; i <= endIndex; i++) {
            var pageNum = document.createElement('li');
            pageNum.setAttribute('onclick', "pageNumChange(" + i + ")");
            if (pageIndex == i) {
                pageNum.setAttribute('class', 'paginate_button page-item active useOnce');
            } else {
                pageNum.setAttribute('class', 'paginate_button page-item useOnce');
            }
            var pageHref = document.createElement('a');
            pageHref.setAttribute('class', 'page-link');
            pageHref.setAttribute('href', '#');
            pageHref.setAttribute('aria-controls', 'tableId');
            pageHref.setAttribute('data-dt-idx', i);
            pageHref.setAttribute('tabindex', 0);
            pageHref.innerHTML = i;
            pageNum.appendChild(pageHref);
            $('#tableId_next').before(pageNum);
        }
        // 是否显示 下一页 按钮
        if (pageIndex < totalPage) {
            $('#tableId_next').show();
            $('#tableId_next a').attr('data-dt-idx', endIndex + 1);
        } else {
            $('#tableId_next').hide();
        }
        var pageNumInfo = "当前第 " + pageIndex + " 页，共 " + totalPage + " 页";
        $('#tableId_info').html(pageNumInfo);
    }

    // 跳转到指定页
    function toThatPage() {
        //var index = document.getElementById('pageIndexInput').value;
        if (index < 0 || index > totalPage) {
            alert('请输入正确的页码');
        } else {
            pageNumChange(index);
        }
    }

    // 全选/全不选
    function chooseAll() {
        checkAll = !checkAll;
        var boxs = document.getElementsByName("chk");
        for (var i = 0; i < boxs.length; i++) {
            boxs[i].checked = checkAll;
        }
    }

    // 批量删除
    function deleteMore() {
        ids = []
        var boxs = document.getElementsByName("chk");
        for (var i = 0; i < boxs.length; i++) {
            if (boxs[i].checked) {
                ids.push(boxs[i].value)
            }
        }
        if (ids.length == 0) {
            alert('请勾选要删除的记录');
        } else {
            remove(ids);
        }
    }

    // 删除
    function remove(id) {
        var mymessage = confirm("删除该宿舍的话,该宿舍人员随之删除,已产生的宿舍卫生记录无法查看");
        if (mymessage == true) {
            var paramArray = [];
            if (id == ids) {
                paramArray = id;
            } else {
                paramArray.push(id);
            }
            httpJson("sushe/delete", "POST", paramArray, (res) => {
                if(res.code == 0){
                    getDataList();
                    alert('删除成功');
                }
            });
        }
        else {
            alert("已取消操作");
        }
    }

    // 用户登出
    <%@ include file="../../static/logout.jsp"%>

    //修改
    function edit(id) {
        window.sessionStorage.setItem('updateId', id)
        window.location.href = "add-or-update.jsp"
    }

    //清除会重复渲染的节点
    function clear() {
        var elements = document.getElementsByClassName('useOnce');
        for (var i = elements.length - 1; i >= 0; i--) {
            elements[i].parentNode.removeChild(elements[i]);
        }
    }

    //添加
    function add() {
        window.sessionStorage.setItem("addsushe", "addsushe");
        window.location.href = "add-or-update.jsp"
    }

    //报表
    function graph() {
        window.location.href = "graph.jsp"
    }

    // 查看详情
    function detail(id) {
        window.sessionStorage.setItem("updateId", id);
        window.location.href = "info.jsp";
    }


    // 获取宿舍人员列表
    function getId(id) {
        if(id == null || id=="" || id=="null"){
            alert("未知错误,请联系管理员处理");
            return;
        }
        susheId=id;
        $('#susheYongTable').modal('show');
    }

    //模态框打开的时候会执行这个里面的东西
    $('#susheYongTable').on('show.bs.modal', function () {
        //清理表格内容,防止重复回显
        $("#yonghuTableTbody").html("");
        if(susheId ==null){
            alert("获取宿舍id错误,请联系管理员处理");
            $('#susheYongTable').modal('hide');
            return;
        }
        getYonghuDataList();
    });
    //模态框关闭的时候会执行这个里面的东西
    $('#susheYongTable').on('hide.bs.modal', function () {
        $("#yonghuTableTbody").html("");
        susheId=null;
        getDataList();
    });

    function getYonghuDataList() {
        http("susheYonghu/page", "GET", {
            page: 1,
            limit: 10,
            susheId: susheId,
        }, (res) => {
            if(res.code == 0){
                var list = res.data.list;
                $("#yonghuTableTbody").html("");
                for (var i = 0; i < list.length; i++) { //遍历一下表格数据  
                    var trow = setYonghuDataRow(list[i], i); //定义一个方法,返回tr数据 
                    $('#yonghuTableTbody').append(trow);
                }
            }else{
                alert("查询宿舍的绑定人员错误,请联系管理员处理");
                $('#susheYongTable').modal('hide');
                return
            }
    });
    }


    // 渲染用户数据表格
    function setYonghuDataRow(item) {
        //创建行 
        var row = document.createElement('tr');
        //姓名
        var nameCell = document.createElement('td');
        nameCell.innerHTML = item.name;
        row.appendChild(nameCell);


        //手机号
        var phoneCell = document.createElement('td');
        phoneCell.innerHTML = item.phone;
        row.appendChild(phoneCell);


        //身份证号
        var idNumberCell = document.createElement('td');
        idNumberCell.innerHTML = item.idNumber;
        row.appendChild(idNumberCell);


        //性别
        var sexValueCell = document.createElement('td');
        sexValueCell.innerHTML = item.sexValue;
        row.appendChild(sexValueCell);

        //每行按钮
        var btnGroup = document.createElement('td');
        //删除按钮
        var deleteBtn = document.createElement('button');
        var deleteAttr = 'removeYonghu(' + item.id + ')';
        deleteBtn.setAttribute("type", "button");
        deleteBtn.setAttribute("class", "btn btn-danger btn-sm 删除");
        deleteBtn.setAttribute("onclick", deleteAttr);
        deleteBtn.innerHTML = "删除"
        btnGroup.appendChild(deleteBtn)

        row.appendChild(btnGroup)
        return row;
    }

    // 删除
    function removeYonghu(id) {
        var mymessage = confirm("真的要删除吗？");
        if (mymessage == true) {
            var paramArray = [];
            paramArray.push(id);
            httpJson("susheYonghu/delete", "POST", paramArray, (res) => {
                if(res.code == 0){
                    alert('删除成功');
                    getYonghuDataList();
                }else{
                    alert(res.msg);
                }
            });
        } else {
            alert("已取消操作");
        }
    }

    //新增用户
    function addYonghu() {
        var yonghuId = $('#yonghuSelect option:selected').val();//选中的值
        if(yonghuId == null || yonghuId == "" || yonghuId =="null"){
            alert("您没有选择用户");
            return;
        }

        var paramArray = {};
        paramArray["susheId"]=susheId;
        paramArray["yonghuId"]=yonghuId;
        httpJson("susheYonghu/save", "POST", paramArray, (res) => {
            if(res.code == 0){
                alert('新增成功');
                getYonghuDataList();
            }else{
                alert(res.msg);
            }
        });
    }



    //查询所有用户
    function yonghuSelect() {
        //填充下拉框选项
        http("yonghu/page?page=1&limit=100&sort=&order=", "GET", {}, (res) => {
            if(res.code == 0){
                yonghuOptions = res.data.list;
            }
        });
    }


    function initializationyonghuSelect() {
        var yonghuSelect = document.getElementById('yonghuSelect');
        if(yonghuSelect != null && yonghuOptions != null  && yonghuOptions.length > 0 ) {
            yonghuSelect.add(new Option("---请选择---",""));
            for (var i = 0; i < yonghuOptions.length; i++) {
                yonghuSelect.add(new Option(yonghuOptions[i].name+" "+yonghuOptions[i].phone+" "+yonghuOptions[i].idNumber, yonghuOptions[i].id));
            }
        }
    }





    // 获取资产列表
    function getziChan(id) {
        if(id == null || id=="" || id=="null"){
            alert("未知错误,请联系管理员处理");
            return;
        }
        susheId=id;
        $('#zichanTableModal').modal('show');
    }

    //模态框打开的时候会执行这个里面的东西
    $('#zichanTableModal').on('show.bs.modal', function () {
        //清理表格内容,防止重复回显
        $("#zichanTableTbody").html("");
        if(susheId ==null){
            alert("获取宿舍id错误,请联系管理员处理");
            $('#zichanTableTbody').modal('hide');
            return;
        }
        getZiChanDataList();
    });
    //模态框关闭的时候会执行这个里面的东西
    $('#zichanTableModal').on('hide.bs.modal', function () {
        $("#zichanTableTbody").html("");
        susheId=null;
    });

    function getZiChanDataList() {
        http("zichan/page", "GET", {
            page: 1,
            limit: 10,
            susheId: susheId,
        }, (res) => {
            if(res.code == 0){
            var list = res.data.list;
            $("#zichanTableTbody").html("");
            for (var i = 0; i < list.length; i++) { //遍历一下表格数据  
                var trow = setZiChanDataRow(list[i], i); //定义一个方法,返回tr数据 
                $('#zichanTableTbody').append(trow);
            }
        }else{
            alert("查询宿舍的资产错误,请联系管理员处理");
            $('#zichanTableModal').modal('hide');
            return
        }
    });
    }


    // 渲染用户数据表格
    function setZiChanDataRow(item) {
        //创建行 
        var row = document.createElement('tr');
        //资产名字
        var zichanNameCell = document.createElement('td');
        zichanNameCell.innerHTML = item.zichanName;
        row.appendChild(zichanNameCell);


        //资产状态
        var zichanValueCell = document.createElement('td');
        zichanValueCell.innerHTML = item.zichanValue;
        row.appendChild(zichanValueCell);

        var updateTypes =null;
        if(item.zichanTypes ==1){
            updateTypes=2;
        }else if(item.zichanTypes ==2){
            updateTypes=1;
        }else if(item.zichanTypes ==2){
            alert("回显异常,请联系管理员处理");
        }

        //每行按钮
        var btnGroup = document.createElement('td');
        //修改按钮
        var deleteBtn = document.createElement('button');
        var deleteAttr = 'updateZichanTypes(' + item.id +',' + updateTypes + ')';
        deleteBtn.setAttribute("type", "button");
        deleteBtn.setAttribute("class", "btn btn-info btn-sm 修改");
        deleteBtn.setAttribute("onclick", deleteAttr);
        deleteBtn.innerHTML = "修改资产状态";
        btnGroup.appendChild(deleteBtn);
        //删除按钮
        var deleteBtn = document.createElement('button');
        var deleteAttr = 'removeZichan(' + item.id + ')';
        deleteBtn.setAttribute("type", "button");
        deleteBtn.setAttribute("class", "btn btn-danger btn-sm 删除");
        deleteBtn.setAttribute("onclick", deleteAttr);
        deleteBtn.innerHTML = "删除资产";
        btnGroup.appendChild(deleteBtn);

        row.appendChild(btnGroup)
        return row;
    }

    // 删除
    function removeZichan(id) {
        var mymessage = confirm("真的要删除吗？");
        if (mymessage == true) {
            var paramArray = [];
            paramArray.push(id);
            httpJson("zichan/delete", "POST", paramArray, (res) => {
                if(res.code == 0){
                    alert('删除成功');
                    getZiChanDataList();
                }else{
                    alert(res.msg);
                }
            });
        } else {
            alert("已取消操作");
        }
    }


    // 修改资产状态
    function updateZichanTypes(id,zichanTypes) {
        httpJson("zichan/updateZichanTypes?id="+id+"&zichanTypes="+zichanTypes, "GET",null, (res) => {
            if(res.code == 0){
                alert('修改成功');
                getZiChanDataList();
            }else{
                alert("修改资产状态失败,请重试");
            }
        });
    }




    $(document).ready(function () {
        //激活翻页按钮
        $('#tableId_previous').attr('class', 'paginate_button page-item previous')
        $('#tableId_next').attr('class', 'paginate_button page-item next')
        //隐藏原生搜索框
        $('#tableId_filter').hide()
        //设置右上角用户名
        $('.dropdown-menu h5').html(window.sessionStorage.getItem('username'))
        //设置项目名
        $('.sidebar-header h3 a').html(projectName)
        setMenu();
        init();
        getDataList();

        //查询级联表的搜索下拉框
        yonghuSelect();
        //级联表的下拉框赋值
        initializationyonghuSelect();
        $(".selectpicker" ).selectpicker('refresh');
    <%@ include file="../../static/myInfo.js"%>
    });
</script>
</body>

</html>