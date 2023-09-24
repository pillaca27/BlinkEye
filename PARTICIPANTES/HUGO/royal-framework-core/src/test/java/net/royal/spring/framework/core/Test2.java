package net.royal.spring.framework.core;

import java.util.ArrayList;

public class Test2 {

	public static void main(String[] args) {
		ArrayList<Animal> MyList4=new ArrayList<>();
		MyList4.add(new Tiger());
		
		ArrayList<Hunter> MyList3=new ArrayList<>();
		MyList3.add(new Cat());
		
		ArrayList<Hunter> MyList5=new ArrayList<>();
		MyList5.add(new Tiger());
		
		ArrayList<Tiger> MyList2=new ArrayList<>();
		//MyList2.add(new Cat());
		
		ArrayList<Animal> MyList=new ArrayList<>();
		MyList.add(new Cat());
	}

}
