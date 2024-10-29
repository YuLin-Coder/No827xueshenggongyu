function setMenu(){
	if(window.sessionStorage.getItem('role') != '管理员'){
		var accountTableName = window.sessionStorage.getItem('accountTableName');
		$('#side-menu').append('<li><a href="javascript:void(0);" data-toggle="collapse" data-target="#app_dr"><div class="pull-left"><i class="zmdi zmdi-apps mr-20"></i><span class="right-nav-text">个人中心</span></div><div class="pull-right"><i class="zmdi zmdi-caret-down"></i></div><div class="clearfix"></div></a><ul id="app_dr" class="collapse collapse-level-1"><li><a href="' + baseUrl + 'jsp/modules/center/updatePassword.jsp" onclick="navClick(this);return false;">修改密码</a></li><li><a href="${pageContext.request.contextPath}/jsp/modules/' + accountTableName + '/add-or-update.jsp" onclick="navClick(this);return false;">个人信息</a></li></ul></li>');
		http(accountTableName+'/session','GET',{},(res)=>{
			if(res.code == 0){
				window.sessionStorage.setItem('updateId',res.data.id);
				window.sessionStorage.setItem('onlyme',true);
			}					
		});			
	} else {
		$('#side-menu').append('<li><a href="javascript:void(0);" data-toggle="collapse" data-target="#app_dr"><div class="pull-left"><i class="zmdi zmdi-apps mr-20"></i><span class="right-nav-text">个人中心</span></div><div class="pull-right"><i class="zmdi zmdi-caret-down"></i></div><div class="clearfix"></div></a><ul id="app_dr" class="collapse collapse-level-1"><li><a href="' + baseUrl + 'jsp/modules/center/updatePassword.jsp" onclick="navClick(this);return false;">修改密码</a></li><li><a href="' + baseUrl + 'jsp/modules/center/info.jsp" onclick="navClick(this);return false;">个人信息</a></li></ul></li>');
	}
	
	var role = window.sessionStorage.getItem('role');
	var menusHtml = '';
	for(var i=0;i<menus.length;i++){
		// 找到当前登录角色的菜单
		if(menus[i].roleName == role){
			var menuHtml = '';
			var menu = menus[i].backMenu;
			for(var j=0;j<menu.length;j++){ 
				// 一级菜单
				menuHtml += '<li><a href="javascript:void(0);" data-toggle="collapse" data-target="#app_m' + j + '"><div class="pull-left"><i class="zmdi zmdi-apps mr-20"></i><span class="right-nav-text">' + menu[j].menu + '</span></div><div class="pull-right"><i class="zmdi zmdi-caret-down"></i></div><div class="clearfix"></div></a><ul id="app_m' + j + '" class="collapse collapse-level-1">';
				
				var menuSubHtml = '';
				var secondMenuName = "";
				var menuChild = menu[j].child;
				for(var k=0;k<menuChild.length;k++){  
					// 二级菜单
					if(secondMenuName == menuChild[k].tableName && secondMenuName != ""){
						menuSubHtml += '<li><a href="${pageContext.request.contextPath}/jsp/modules/' + menuChild[k].tableName + '/graph.jsp" onclick="navClick(this);return false;">' + menuChild[k].menu + '</a></li>';
					}else{
						menuSubHtml += '<li><a href="${pageContext.request.contextPath}/jsp/modules/' + menuChild[k].tableName + '/list.jsp" onclick="navClick(this);return false;">' + menuChild[k].menu + '</a></li>';
						secondMenuName = menuChild[k].tableName;
					}
				}
				menuHtml += menuSubHtml + '</ul></li>';
			}
			menusHtml += menuHtml;
		}
	}
	$('#side-menu').append(menusHtml);
	if(hasMessage != null && hasMessage == '是' && role == '管理员'){
		$('#side-menu').append('<li><a href="javascript:void(0);" data-toggle="collapse" data-target="#app_dr1"><div class="pull-left"><i class="zmdi zmdi-apps mr-20"></i><span class="right-nav-text">留言管理</span></div><div class="pull-right"><i class="zmdi zmdi-caret-down"></i></div><div class="clearfix"></div></a><ul id="app_dr1" class="collapse collapse-level-1"><li><a href="' + baseUrl + 'jsp/modules/messages/list.jsp" onclick="navClick(this);return false;">留言列表</a></li></ul></li>');
	}
}

window.navClick = function(obj, rootNavName, subNavName) {
	$('iframe').attr('src', obj.href);
}