package net.royal.spring.framework.core;

public class UCharacterTests {
	public static void main(String[] args) {
		String str1="compa\u00f1\u00eda";
		String str2="compa\u00f1\u00eda";
		//	Selecci\u00f3n
		//	compa\u00f1ia
		System.out.println(str1);
		System.out.println(str2);
	}
}
