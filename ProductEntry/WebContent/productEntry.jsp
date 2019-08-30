<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="./css/style.css">
<title>商品登録画面</title>
</head>
<body>
	<script type="text/javascript" src="./js/style.js"></script>

	<!-- 大分類 -->

	<table>
		<s:if test="#session.transitionFlag == true">
				<tr>
					<th><s:label value="大分類登録" /></th>
					<th><s:label value="No" /></th>
					<th><s:label value="名称" /></th>
				</tr>
		</s:if>
		<s:if test="#session.productList.size() > 1">
			<s:form id="productForm">
			List2tu

				<s:iterator value="%{#session.productList}" status="st">
					<tr>
						<td align="center"></td>
					</tr>
					<tr>
						<td></td>
						<td align="center"><s:textfield name="xRowNo"
								value="%{rowNo}" size="1" /> <s:hidden name="xProductId"
								value="%{productId}" /></td>
						<td align="center"><s:textfield name="xProductName"
								value="%{productName}" /></td>
						<td><a
							href='<s:url action="ProductDetalisAction"><s:param name="productId" value="%{productId}" />
					<s:param name="parentName" value="%{productName}" /><s:param name="transitionFlag" value="%{#session.transitionFlag}" />
					</s:url>'><input
								type="button" value="詳細" onclick="goProductDetailsAction()" /></a></td>

						<td><input type="submit" value="変更"
							onclick="goProductChangeAction('<s:property value="#st.index" />')" /></td>

						<td><input type="submit" value="削除"
							onclick="goProductDeleteAction('<s:property value="#st.index" />')" /></td>
					</tr>
				</s:iterator>
				<s:hidden name="productId" />
				<s:hidden name="productName" />
				<s:hidden name="rowNo" />
			</s:form>
		</s:if>
		<s:elseif test="#session.productList.size() <= 1">
			<s:form id="productForm">
		List１つ
			<s:iterator value="%{#session.productList}" status="st">
					<tr>
						<td align="center"></td>
					</tr>
					<tr>

						<td></td>
						<td align="center"><s:textfield name="rowNo" value="%{rowNo}" size="1" /><s:hidden name="productId" value="%{productId}" /></td>
						<td align="center"><s:textfield name="productName" value="%{productName}" /><s:hidden name="parentName" value="%{productName}" />
						<td><a href='<s:url action="ProductDetalisAction"><s:param name="productId" value="%{productId}" /><s:param name="productName" value="%{productName}" />
						<s:param name="parentName" value="%{productName}" /><s:param name="parentName" value="%{parentName}" />
						</s:url>'><input type="submit" value="詳細" onclick="goProductDetailsBigAction()" /></a></td>

						<td><input type="submit" value="変更"
							onclick="goProductChangeBigAction()" /></td>

						<td><input type="submit" value="削除"
							onclick="goProductDeleteBigAction()" /></td>
					</tr>
				</s:iterator>
				<s:hidden name="transitionFlag" value="%{#session.transitionFlag}" />
				</s:form>
		</s:elseif>
		<!---------------------------------- 中分類委員会 ------------------------------------------->


		<s:if test="#session.mediumFlag == true">
			<tr>
				<th><s:label value="中分類登録" /></th>
				<th><s:label value="No" /></th>
				<th><s:label value="名称" /></th>
			</tr>
			<tr>
				<td align="center"><a
					href='<s:url action="GoBackAction"></s:url>'>大分類</a></td>
			</tr>
			<tr>
				<td align="center"><s:textfield name="parentName"
						value="%{#session.parentName}" readonly="true" size="8"
						cssClass="txt" /></td>
			</tr>
		</s:if>



			<s:if test="#session.productListMedium.size() > 1">
	<s:form id="productForm">
			List２つ以上hii

			<s:iterator value="%{#session.productListMedium}" status="st">
					<tr>
						<td align="center"></td>
						<!-- 親分類productkey -->
					</tr>
					<tr>
						<td></td>
						<td align="center"><s:textfield name="xRowNo"
								value="%{rowNo}" size="1" /> <s:hidden name="xProductId"
								value="%{productId}" /></td>
						<td align="center"><s:textfield name="xProductName"
								value="%{productName}" /></td>

<!-- productIdがListでとってきた値parentIdが前ページからとってきたproductId -->
						<td><a href='<s:url action="ProductDetalisSmallAction">
						<s:param name="productId" value="%{productId}" /><s:param name="parentId" value="%{#session.parentId}" />
					<s:param name="parentMiddleName" value="%{productName}" /><s:param name="parentName" value="%{#session.parentName}" />
					<s:param name="parentName" value="%{parentName}" /></s:url>'><input
								type="button" value="詳細" onclick="goProductDetailsSmallAction()" /></a></td>

						<td><input type="submit" value="変更"
							onclick="goProductChangeAction('<s:property value="#st.index" />')" /></td>

						<td><input type="submit" value="削除"
							onclick="goProductDeleteAction('<s:property value="#st.index" />')" /></td>
					</tr>
				</s:iterator>
				<s:hidden name="mediumFlag" value="%{#session.mediumFlag}"/>
				<s:hidden name="parentId" value="%{#session.parentId}" />
				<s:hidden name="parentName" value="%{#session.parentName}" />
				<s:hidden name="productId" />
				<s:hidden name="productName" />
				<s:hidden name="rowNo" />
				</s:form>
			</s:if>
			<s:elseif test="#session.productListMedium.size() <= 1">
			<s:form id="productForm">
		List１つ
			<s:iterator value="%{#session.productListMedium}" status="st">
					<tr>
						<td align="center"></td>
					</tr>
					<tr>
						<td></td>
						<td align="center"><s:textfield name="rowNo" value="%{rowNo}"
								size="1" /> <s:hidden name="productId" value="%{productId}" /></td><%-- <s:hidden name="xParentId" value="%{#session.parentId}" /> --%>
						<td align="center"><s:textfield name="productName"
								value="%{productName}" /> <s:hidden name="parentMiddleName"
								value="%{productName}" /> <s:hidden name="parentName"
								value="%{#session.parentName}" /> <s:hidden name="parentId"
								value="%{#session.parentId}" />
						<td><a
							href='<s:url action="ProductDetalisSmallAction"><s:param name="productId" value="%{productId}" /><s:param name="parentId" value="%{#session.parentId}" />
					<s:param name="parentMiddleName" value="%{productName}" /><s:param name="parentName" value="%{#session.parentName}" />
					<s:param name="parentName" value="%{parentName}" /></s:url>'><input
								type="button" value="詳細" onclick="goProductDetailsSmallAction()" /></a></td>


						<td><input type="submit" value="変更"
							onclick="goProductChangeMediumAction()" /></td>

						<td><input type="submit" value="削除"
							onclick="goProductDeleteMediumAction()" /></td>
					</tr>
				</s:iterator>
		</s:form>
			</s:elseif>





		<!------------------------ 	小分類委員会 ------------------------------------------------------>

			<s:if test="#session.smallFlag == true">


				<tr>
					<th><s:label value="小分類登録" /></th>
					<th><s:label value="No" /></th>
					<th><s:label value="名称" /></th>
				</tr>
				<tr>
					<td align="center"><a
						href='<s:url action="GoBackAction">
				</s:url>'>大分類</a></td>
				</tr>
				<tr>
					<td align="center"><s:textfield name="parentName"
							value="%{#session.parentName}" readonly="true" size="8"
							cssClass="txt" /></td>
				</tr>
				<tr>
					<td align="center"><a
						href='<s:url action="BackAction"><s:param name="parentName" value="%{#session.parentName}" />
				<s:param name="parentId" value="%{#session.parentId}" /></s:url>'>中分類</a></td>
				</tr>
				<tr>
					<td align="center"><s:textfield
							name="parentMiddleName"
							value="%{#session.parentMiddleName}" readonly="true" size="8"
							cssClass="txt" /></td>
				</tr>
			</s:if>



			<s:if test="#session.productListSmall.size() > 1">
<s:form id="productSmallForm2">
List２つ以上
			<s:iterator value="#session.productListSmall" status="st">
					<tr>
						<td align="center"></td>
					</tr>
					<tr>
						<td></td>
						<td align="center"><s:textfield name="xRowNo"
								value="%{rowNo}" size="1" /><s:hidden name="xProductId"
								value="%{productId}" /></td>
						<td align="center"><s:textfield name="xProductName"
								value="%{productName}" /></td>
						<td><input type="button" value="詳細" /></td>

						<td><input type="submit" value="変更"
							onclick="goProductChangeSmallAction('<s:property value="%{#st.index}" />')" /></td>

						<td><input type="submit" value="削除"
							onclick="goProductDeleteSmallAction('<s:property value="#st.index" />')" /></td>
					</tr>
				</s:iterator>
				<s:hidden name="smallFlag" value="%{#session.smallFlag}"/>
				<%-- <s:hidden name="productId" value="%{#session.productId}"/> --%>
				<s:hidden name="parentId" value="%{#session.parentId2}"/>
				<s:hidden name="productId" />
				<s:hidden name="productName" />
				<s:hidden name="rowNo" />
			</s:form>
			</s:if>

		<s:elseif test="#session.productListSmall.size() <= 1">
			<s:form id="productSmallForm">
List１つ
			<s:iterator value="%{#session.productListSmall}" status="st">
					<tr>
						<td align="center"></td>
					</tr>
					<tr>
						<td></td>
						<td align="center"><s:textfield name="rowNo" value="%{rowNo}"
								size="1" /> <s:hidden name="productId" value="%{productId}" />
								<s:hidden name="parentId" value="%{#session.parentId2}" /></td>
						<td align="center"><s:textfield name="productName"
								value="%{productName}" /></td>
						<td><input type="button" value="詳細" /></td>

						<td><s:submit value="変更"
								onclick="goProductChangeSmallAction2()" /></td>

						<td><s:submit value="削除"
								onclick="goProductDeleteSmallAction2()" /></td>
					</tr>
				</s:iterator>
			</s:form>
		</s:elseif>
<!-- value="%{productEntryRowNo}" -->
		<s:form id="productForm2">
			<tr>
				<!-- 追加はtextnameとrowNo productIdは自動 -->
				<td></td>
				<td><s:textfield name="rowNo"  value=""
						size="1" /></td>
				<td><s:textfield name="productName" value="" /></td>
				<td><s:submit value="追加" onclick="goProductEntryAction()" /></td>
				<td><s:hidden name="productId" value="%{#session.productId}" />

				<s:if test="#session.smallFlag == true">
				<s:hidden name="parentId" value="%{#session.parentId2}" />
				</s:if><s:else>
				<s:hidden name="parentId" value="%{#session.parentId}" />
				</s:else>
				</td>

			</tr>
		</s:form>
	</table>
	<div class="error">
		<br>
		<s:if test="!rowNoError.isEmpty()">
			<s:iterator value="rowNoError">
				<s:property />
			</s:iterator>
		</s:if>
		<br>
		<s:if test="!productNameError.isEmpty()">
			<s:iterator value="productNameError">
				<s:property />
			</s:iterator>
		</s:if>
		<br>
		<s:if test="!errorDeleteMessage.isEmpty()">
			<s:iterator value="errorDeleteMessage">
				<s:property />
			</s:iterator>
		</s:if>
		<br>
		<s:if test="!rowNoErrorMessage.isEmpty()">
			<s:iterator value="rowNoErrorMessage">
				<s:property />
			</s:iterator>
		</s:if>
		<br>
		<s:if test="!entrySuccessMessage.isEmpty()">
			<s:iterator value="entrySuccessMessage">
				<s:property />
			</s:iterator>
		</s:if>
		<br>
		<s:if test="!changeSuccessMessage.isEmpty()">
			<s:iterator value="changeSuccessMessage">
				<s:property />
			</s:iterator>
		</s:if>
		<br>
		<s:if test="!deleteSuccessMessage.isEmpty()">
			<s:iterator value="deleteSuccessMessage">
				<s:property />
			</s:iterator>
		</s:if>
	</div>
	<div class="hid"></div>
	<s:debug></s:debug>
</body>
</html>