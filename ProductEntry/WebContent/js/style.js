/*詳細：大分類から中分類へ*/
function goProductDetailsAction() {
	document.getElementById("productForm").action = "ProductDetalisAction";
}

/* 詳細：中分類から小分類へ */
function goProductDetalisSmallAction() {
	document.getElementById("productForm").action = "ProductDetalisSmallAction";
}

/* 削除 : 通常 */
function goProductDeleteAction(index) {
	var form = document.forms[0];
	form.productId.value = form.xProductId[index].value;
	form.rowNo.value = form.xRowNo[index].value;
	form.productName.value = form.xProductName[index].value;
	if (!confirm("削除してもよろしいでしょうか？")) {
		document.getElementById("productForm").action = "CancelAction";
	} else {
		document.getElementById("productForm").action = "ProductDeleteAction";
	}
}

/* 追加 : 通常 */
function goProductEntryAction() {
	var let = window.confirm("商品を追加してもよろしいですか？");
	if (let == true) {
		document.getElementById("productForm2").action = "ProductEntryAction";
	} else {
		document.getElementById("productForm2").action = "CancelAction";

	}
}

/* 変更 : 通常 */
function goProductChangeAction(index) {
	var form = document.forms[0];
	form.productId.value = form.xProductId[index].value;
	form.rowNo.value = form.xRowNo[index].value;
	form.productName.value = form.xProductName[index].value;
	if (!confirm("変更してもよろしいでしょうか？")) {
		document.getElementById("productForm").action = "CancelAction";
	} else {
		document.getElementById("productForm").action = "ProductChangeAction";
	}
}

/*詳細：大分類から中分類へ  1個版*/
		   
function goProductDetailsBigAction() {
	document.getElementById("productForm").action = "ProductDetalisAction";
}

/*詳細：大分類 1小判*/
function goProductChangeBigAction() {
	var let = window.confirm("変更してもよろしいでしょうか？");
	if (let == true) {
		document.getElementById("productForm").action = "ProductChangeAction";
	} else {
		document.getElementById("productForm").action = "CancelAction";
	}
}

/* 削除 大分類 1個版 */
function goProductDeleteBigAction() {
	var let = window.confirm("削除してもよろしいでしょうか？");
	if (let == true) {
		document.getElementById("productForm").action = "ProductDeleteAction";
	} else {
		document.getElementById("productForm").action = "CancelAction";
	}
}

/* 詳細 Medium 1個版 */
function goProductDetalisSmallMediumAction() {
	document.getElementById("productForm").action = "ProductDetalisSmallAction";
}

/* 変更 Medium 1個版 */
function goProductChangeMediumAction() {
	var let = window.confirm("変更してもよろしいでしょうか？");
	if (let == true) {
		document.getElementById("productForm").action = "ProductChangeAction";
	} else {
		document.getElementById("productForm").action = "CancelAction";
	}
}

/* 削除 Medium 1個版 */
function goProductDeleteMediumAction() {
	var let = window.confirm("削除してもよろしいでしょうか？");
	if (let == true) {
		document.getElementById("productForm").action = "ProductDeleteAction";
	} else {
		document.getElementById("productForm").action = "CancelAction";
	}
}

/* 変更 small 1個版 */
function goProductChangeSmallAction2() {
	var let = window.confirm("変更してもよろしいでしょうか？");
	if (let == true) {
		document.getElementById("productSmallForm").action = "ProductChangeAction";
	} else {
		document.getElementById("productSmallForm").action = "CancelAction";
	}
}
/* 変更 small 2個版 */
function goProductChangeSmallAction(index) {
	var form = document.forms[0];
	form.productId.value = form.xProductId[index].value;
	form.rowNo.value = form.xRowNo[index].value;
	form.productName.value = form.xProductName[index].value;
	if (!confirm("変更してもよろしいでしょうか？")) {
		document.getElementById("productSmallForm2").action = "CancelAction";
	} else {
		document.getElementById("productSmallForm2").action = "ProductChangeAction";

	}
}

/* 削除 small 1個版 */
function goProductDeleteSmallAction2() {
	var let = window.confirm("削除してもよろしいでしょうか？");
	if (let == true) {
		document.getElementById("productSmallForm").action = "ProductDeleteAction";
	} else {
		document.getElementById("productSmallForm").action = "CancelAction";
	}
}
/* 削除 small 2個版 */
function goProductDeleteSmallAction(index) {
	var form = document.forms[0];
	form.productId.value = form.xProductId[index].value;
	form.rowNo.value = form.xRowNo[index].value;
	form.productName.value = form.xProductName[index].value;
	if (!confirm("削除してもよろしいでしょうか？")) {
		document.getElementById("productSmallForm2").action = "CancelAction";
	} else {
		document.getElementById("productSmallForm2").action = "ProductDeleteAction";
	}
}
