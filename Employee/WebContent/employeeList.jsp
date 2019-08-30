<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./css/index.css">
<title>従業員一覧</title>
</head>
<body>
	<script type="text/javascript" src="./js/index.js"></script>
	<s:form id="employeeForm">

		<table>
			<tr>
				<td>
					<div>
						<s:submit value="戻る" class="btnBack" onclick="goBackAction()"/>

					</div>
				</td>
			</tr>
			<tr>
				<td scope="row">名前検索</td>
				<td><s:textfield name="name" /></td>
				<td scope="row">所属</td>
				<td><s:select name="branchId" list="#session.branchList"
							listValue="branchName" listKey="branchId" headerKey="" headerValue="" /></td>
				<td scope="row">部署</td>
				<td><s:select name="departmentId"
							list="#session.departmentList" listValue="departmentName"
							listKey="departmentId" headerValue="" headerKey="" /></td>
				<td scope="row"><s:checkboxlist name="enable" list="#{'1': '削除'}"
						listkey="key" listValue="value" /></td>
				<td>
					<div style="display: inline-block; _display: inline;">
						<s:submit value="検索" class="btn" onclick="goSerchAction()" />
					</div>
				</td>
			</tr>

			<tr>
				<td scope="row"></td>
				<td><h5>(名前、仮名の一部)</h5></td>
			</tr>
		</table>

			<div class="error">
				<s:iterator value="errorMessage">
					<s:property />
				</s:iterator>
			</div>
</s:form>
		<table border=1>

			<tr>
				<th><s:label value="#"/></th>
				<th><s:label value="No"/></th>
				<th><s:label value="従業員"/></th>
				<th><s:label value="所属"/></th>
				<th><s:label value="部署"/></th>
				<th><s:label value="上司"/></th>
			</tr>
			<s:iterator value="employeeList" status="st">
				<tr>
					<td><a href='<s:url action="EmployeeChoiceAction">
					<s:param name="bossFlag" value="%{bossFlag}" />
					<s:param name="empNo" value="%{empNo}"/>
					<s:param name="fullName" value="%{fullName}"/>
					<s:param name="kanaName" value="%{kanaName}"/>
					<s:param name="bossName" value="%{bossName}"/>
					<s:param name="loginId" value="loginId"/>
					<s:param name="password" value="%{password}"/>
					<s:param name="mail" value="%{mail}" />
					<s:param name="branchId" value="%{branchId}"/>
					<s:param name="departmentId" value="%{departmentId}"/>
					<s:param name="bossId" value="%{bossId}"/>
					<s:param name="checkList" value="%{checkList}"/>
					<s:param name="userRole" value="%{roleNo}" />
					<s:param name="enable" value="%{enable}"/>
					</s:url>'>選択</a></td>
					<td><s:property value="empNo" /></td>
					<td><s:property value="fullName" /></td>
					<td><s:property value="branchName" /></td>
					<td><s:property value="departmentName" /></td>
					<td><s:property value="bossName" /></td>
				</tr>


			</s:iterator>
		</table>

<s:debug></s:debug>
</body>
</html>