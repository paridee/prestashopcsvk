package csvGenerator.model;

import java.util.ArrayList;
import java.util.List;

public class Product {
	String name;
	String brand;
	String refCode;
	long ean;
	String description;
	List<String> photoUrl	=	new ArrayList<String>();
	List<Attribute> attributeList =	new ArrayList<Attribute>();
	String structSchema;
}
