package com.airport.domain;

public class Token {
	private String string;

	public Token(String aString){
		string =aString;
	}
	
	@Override
	public String toString(){
		return string;
	}
}
