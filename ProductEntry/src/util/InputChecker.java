	package util;
	import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

	public class InputChecker {
																											//1				//2
		public List<String> doCheck(String propertyName,String value,int minLength,int maxLength, boolean alphabet,boolean kanji,boolean hiragana,boolean digit,boolean symbols, boolean katakana ,boolean space) {

			List<String> stringList = new ArrayList<String>();
			List<String> characterTypeList = new ArrayList<String>();

			if(StringUtils.isEmpty(value)){
				stringList.add(propertyName + "を入力してください。");
			}
			if (value.length() < minLength || value.length() > maxLength  ) {
				stringList.add(propertyName + "は" + minLength + "文字以上" + maxLength + "文字以下で入力して下さい。");
			}
	///////////入力された文字のタイプから何を判別するか決める//////////
			String exception = "";

			if (alphabet || kanji || hiragana || digit || symbols || katakana || space) {   //1
				exception = "[";
			}
			if (alphabet){  																//2
				exception += "a-zA-Z";
				characterTypeList.add("半角英字");
			}
			if(kanji){																	//3
				exception +="一-龠々";
				characterTypeList.add("漢字");
			}

			if(hiragana){																//4
				exception +="ぁ-んー";
				characterTypeList.add("ひらがな");
			}
			if(digit) {
				exception += "0-9-";
				characterTypeList.add("半角数字");
			}
			if(katakana){
				exception +="ァ-ヺ";
				characterTypeList.add("カタカナ");
			}

			if(space){
				exception +=" 　";
				characterTypeList.add("スペース");
			}

			if(!StringUtils.isEmpty(exception)){
				exception +="]+";
			}
			String characterType = "";
			for(int i = 0; i < characterTypeList.size(); i++){
				if (i == 0) {
					characterType += characterTypeList.get(i).toString();
				}else {
					characterType += "、" + characterTypeList.get(i).toString();
				}
			}
			if (!value.matches(exception)&&!value.equals("")){
				System.out.println(value.matches(exception));
				stringList.add(propertyName + "は" + characterType + "で入力して下さい。");
			}
			return stringList;
		}


	//メールアドレスチェック
		public List<String> checkEmail(String propertyName,String value,int minLength,int maxLength){

			List<String> StringList = new ArrayList<String>();

			if(StringUtils.isEmpty(value)){
				StringList.add(propertyName + "を入力してください。");
			}

			if(value.length() < minLength || value.length() > maxLength) {
				StringList.add(propertyName + "は" +  minLength +"文字以上" + maxLength + "文字以下で入力して下さい。");
			}
			String regularExpression = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.]+(?:\\.[a-zA-Z0-9.]+)*$";

			String errorType = "[半角英字・半角数字・半角記号(_.-)]@[半角英字・半角数字・半角記号(.)]";

			if(!value.matches(regularExpression)&&!value.equals("") ){
				StringList.add(propertyName + "は" + errorType + "で入力して下さい。");
			}
			return StringList;

		}

		//一度目のパスワードと二度目のパスワードが同じかを検証。
			public List<String> doPasswordCheck(String password,String reConfirmationPassword){
				List<String> errorMessage = new ArrayList<>();
				if(!password.equals(reConfirmationPassword)){
					errorMessage.add("新しいパスワードと新しいパスワード(再確認)が一致しません。");
				}
				return errorMessage;
			}
	}
