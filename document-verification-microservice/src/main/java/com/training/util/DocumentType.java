package com.training.util;

public enum DocumentType {
AADHAR_CARD('A'),
PAN_CARD('P'),UNDEFINED('-');
	
private char docChar='-';

 DocumentType(char docChar) {
this.docChar=docChar;
}
 public char getDocChar() {
	 return docChar;
 }
public static DocumentType getFromDocChar(char docChar) {
	switch(docChar) {
	case 'A':
		return AADHAR_CARD;
	case 'P':
		return PAN_CARD;
	default:
		return UNDEFINED;
	}
}

	
}




