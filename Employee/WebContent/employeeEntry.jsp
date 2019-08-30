<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<link rel="stylesheet" href="./css/index.css">
<title>従業員登録画面</title>
</head>
<body>
	<script type="text/javascript" src="./js/index.js"></script>
	<s:form id="employeeForm">
		<div class="left">
			<table>
				<tr>
					<td scope="row">No</td>
					<td><s:textfield name="empNo" value="%{session.empMaxNo}" id="text" readonly="false" /></td>
				</tr>
				<tr>
					<td scope="row">名前</td>
					<td scope="row"><s:textfield name="fullName"
							value="%{#session.fullName}" id="text" readonly="false" /> <s:submit value="従業員検索"
							class="btnSerch" onclick="goEmployeeListAction()" /></td>
				</tr>
				<tr>
					<td scope="row">名前(カナ)</td>
					<td><s:textfield name="kanaName" value="%{#session.kanaName}"
							 id="text" readonly="false" /></td>
				</tr>
				<tr>
					<td scope="row">loginId</td>
					<td><s:textfield name="loginId" value="%{#session.loginId}" id="text" readonly="false" /></td>
				</tr>
				<tr>
					<td scope="row">メール</td>
					<td><s:textfield name="mail" value="%{#session.mail}"
							 id="text" readonly="false" /></td>
				</tr>
				<tr>
					<td scope="row">所属</td>
					<td><s:select name="branchId" list="#session.branchList"
							listValue="branchName" listKey="branchId" value="%{#session.branchId}" headerKey="" headerValue=""/></td>
				</tr>
				<tr>
					<td scope="row">部署</td>
					<td><s:select name="departmentId"
							list="#session.departmentList" listValue="departmentName"
							listKey="departmentId" value="%{#session.departmentId}" headerKey="" headerValue="" /></td>
				</tr>
				<tr>
					<td scope="row">上司</td>
					<td><s:textfield name="bossName" value="%{#session.bossName}"
							readonly="true" /> <s:submit value="上司検索" class="btnSerch"
							onclick="goBossListAction()" /></td>
				</tr>
				<tr>
					<!--  value=ブラウザに送信される値 key=サーバーに送信される値-->
					<td scope="row">権限</td>
					<td><s:checkboxlist list="#session.roleList" name="checkList" listKey="roleId" listValue="roleName" />
					<s:hidden name="roleNo" value="%{#session.roleNo}"/></td>


				<tr>
					<td scope="row">パスワード</td>
					<td><s:textfield name="password" value="%{#session.login}"
							readonly="true" type="password" /> <s:submit value="パスワード登録"
							onclick="goPasswordFormAction()" /></td>
				</tr>
				<tr>
					<td scope="row"></td>
					<td><div style="display: inline-block; _display: inline;">
							<s:submit value="登録" class="btnSerch"
								onclick="goEmployeeEntryAction()" />
						</div>
						<div style="display: inline-block; _display: inline;">
							<s:submit value="削除" class="btnSerch" onclick="goDeleteAction()" />
						</div>
						<div style="display: inline-block; _display: inline;">
							<s:submit value="クリア" class="btnSerch" onclick="goCrearAction()" />
						</div></td>
				</tr>
			</table>
			<div class="error">
		<%-- 		<s:if test="!empNoError.isEmpty()">
					<s:iterator value="empNoError">
						<s:property />
					</s:iterator>
				</s:if> --%>
				<br>
				<s:if test="!nameError.isEmpty()">
					<s:iterator value="nameError">
						<s:property />
					</s:iterator>
				</s:if>
				<br>
				<s:if test="!kanaError.isEmpty()">
					<s:iterator value="kanaError">
						<s:property />
					</s:iterator>
				</s:if>
				<br>
				<s:if
					test="!loginIdError.isEmpty()">
					<s:iterator value="loginIdError">
						<s:property />
					</s:iterator>
				</s:if>
				<br>
				<s:if test="!passwordError.isEmpty()">
					<s:iterator value="passwordError">
						<s:property />
					</s:iterator>
				</s:if>
				<br>
				<s:if test="!emailError.isEmpty() ">
					<s:iterator value="emailError">
						<s:property />
					</s:iterator>
				</s:if>
				<s:if test="!exitsError.isEmpty()">
					<s:iterator value="exitsError">
						<s:property />
					</s:iterator>
				</s:if>
				<s:if test="!departmentError.isEmpty() ">
					<s:iterator value="departmentError">
						<s:property />
					</s:iterator>
				</s:if>
				<s:if test="!branchError.isEmpty()">
					<s:iterator value="branchError">
						<s:property />
					</s:iterator>
				</s:if>
				<s:if test="!bossError.isEmpty() ">
					<s:iterator value="bossError">
						<s:property />
					</s:iterator>
				</s:if>

				<s:if test="!bossSerchError.isEmpty() ">
					<s:iterator value="bossSerchError">
						<s:property />
					</s:iterator>
				</s:if>
			</div>
			<br>
			<div class="hid">

				<s:hidden name="empId" value="%{#session.empId}"/><br>
				<s:hidden name="bossId" value="%{#session.bossId}"/><br>
				<s:hidden name="bossFlag" value="%{#session.bossFlag}"/>

			</div>
		</div>
	<s:if test="passArea">
	<div id="formblock">
			<table class="right" id="box">
				<tr>
					<td scope="row">新パスワード</td>
					<td><s:textfield name="loginPass" value="%{#session.loginPass}" class="txt" /></td>
				</tr>
				<tr>
					<td scope="row">確認</td>
					<td><s:textfield name="loginCheck" value="%{#session.loginCheck}" class="txt" /></td>
				</tr>
				<tr>
					<td scope="row">
					<s:submit value="登録" onclick="goPasswordCheckAction()" />
					<s:submit value="閉じる" onclick="goBackAction()"/></td>
				</tr>
				<tr>
				<td>
				<div class="error">

				<s:if test="!passError.isEmpty()">

					<s:iterator value="passError">
						<s:property />
					</s:iterator>

				</s:if>
				<s:if test="!passDuplicateError.isEmpty()">
					<s:iterator value="passDuplicateError">
						<s:property />
					</s:iterator>
				</s:if>
				<s:if test="!passCheckError.isEmpty()">
					<s:iterator value="passCheckError">
						<s:property />
					</s:iterator>
				</s:if>
					</div></td></tr>
		</table>
	</div>
	</s:if>
	 <s:token/>
	</s:form>
	<s:debug></s:debug>
</body>
</html>