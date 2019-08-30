
/*登録*/
function goEmployeeEntryAction(){
	document.getElementById("employeeForm").action = "EmployeeEntryAction";
}

/*検索*/
function goSerchAction(){
	document.getElementById("employeeForm").action="EmployeeSerchAction";
}
/*一覧*/
function goEmployeeListAction() {
	document.getElementById("employeeForm").action = "EmployeeListAction";
}

/*新パスワードチェック*/
function goPasswordCheckAction(){
	document.getElementById("employeeForm").action = "PasswordCheckAction";
}

/*戻るボタン*/
function goBackAction() {
	document.getElementById("employeeForm").action = "BackAction";
}

/*削除ボタン*/
function goDeleteAction(){
	var let = window.confirm("選択された従業員を削除してもよろしいですか？");
	if(let == true){
		document.getElementById("employeeForm").action="DeleteAction";
	}else{
		document.getElementById("employeeForm").action="BackAction";
	}
}
/*クリアボタン*/
function goCrearAction(){
	document.getElementById("employeeForm").action="ClearAction";
}

/*選択*/
function goEmployeeChoiceAction(){
	document.getElementById("employeeForm").action = "EmployeeChoiceAction";
}

/*パスワード出現*/
function goPasswordFormAction(){
	document.getElementById("employeeForm").action = "PasswordFormAction";
}
//上司検索
function goBossListAction(){
	document.getElementById("employeeForm").action = "BossListAction";
}

/*$('text').attr('readonly',true).
*/

/*function hideForm(){
	document.getElementById("formblock").style.display = "none";
	}
	function showForm() {
	document.getElementById("formblock").style.display = "block";
	}
	onclick = function(){
	hideForm();
};
*/