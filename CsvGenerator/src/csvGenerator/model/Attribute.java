package csvGenerator.model;

public class Attribute implements Cloneable {
	public String name;
	public String value;
	
	public Object clone(){
		Attribute a	=	new Attribute();
		a.name		=	this.name;
		a.value		=	this.value;
		return a;
	}
}
