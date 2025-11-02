class Fruits{
	public String name;
	public String color;

	Fruits(String name,String color){
	this.name=name;
	this.color=color;
	}

	public void display(){
	System.out.println("Name : "+name+" color : "+color);
	}

	public void eat(){
	System.out.println(name + "eating...!!");
	}
	public void ripen(){
	System.out.println(name +" is ripening....!!");
	}

}

class Apple extends Fruits{
	
	
	Apple(String name,String color){
	super(name,color);
	
	}
	public void crunch(){
	System.out.println("Crunchy "+name+"...!");
	}
	public void eat(){
	System.out.println("eating "+name);
	}

}
class Orange extends Fruits{
	
	
	Orange(String name,String color){
	super(name,color);
	
	}
	public void peel(){
	System.out.println("peeling "+name+"...!");
	}
	public void eat(){
	System.out.println("eating "+name);
	}

}
class Mango extends Fruits{
	
	
	Mango(String name,String color){
	super(name,color);
	
	}
	public void slice(){
	System.out.println("slicing "+name+"...!");
	}
	public void eat(){
	System.out.println("eating "+name);
	}

}
class Banana extends Fruits{
	
	
	Banana(String name,String color){
	super(name,color);
	
	}
	public void peel(){
	System.out.println("peeling "+ name+"...!");
	}
	public void eat(){
	System.out.println("eating "+name);
	}

}
class FruitsMain{
	public static void main(String []args){
	Apple apple=new Apple("apple","red");
	Orange orange=new Orange("orange","orange");
	Mango mango=new Mango("mango","yellow-orange");
	Banana banana=new Banana("banana","yellow");

	System.out.println();
	System.out.println("Apple methods........");
	apple.crunch();
	apple.eat();
	apple.ripen();
	apple.display();

	System.out.println();

	System.out.println();
	System.out.println("orange methods........");
	orange.peel();
	orange.eat();
	orange.ripen();
	orange.display();

	System.out.println();
	
	System.out.println("mango methods........");
	mango.slice();
	mango.eat();
	mango.ripen();
	mango.display();

	System.out.println();

	System.out.println("banana methods........");
	banana.peel();
	banana.eat();
	banana.ripen();
	banana.display();

	System.out.println();



}

}





