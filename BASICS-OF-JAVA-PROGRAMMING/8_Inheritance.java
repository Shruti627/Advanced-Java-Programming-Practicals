class Teacher{

public void teach(){

	System.out.println("Teaching....");
}



}
class Person extends Teacher{

public void listen(){

	System.out.println("Listening to teacher....");
}



}

class InMain{

	public static void main(String []args){
	Person p1=new Person();
	p1.teach();
	p1.listen();

}
}