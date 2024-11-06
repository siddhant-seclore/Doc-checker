package com.training.util;

public class DocumentUtils {
public static boolean checkifContainsName(String docText, String name) {
	if(docText.toLowerCase().contains(name.toLowerCase()))
		return true;
return false;
}
}
