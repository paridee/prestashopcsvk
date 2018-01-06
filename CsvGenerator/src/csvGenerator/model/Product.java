package csvGenerator.model;

import java.util.ArrayList;
import java.util.List;

public class Product {
	String name;
	String brand;
	String refCode;
	long ean;
	String description;
	List<String> category	=	new ArrayList<String>();
	List<String> photoUrl	=	new ArrayList<String>();
	List<Attribute> attributeList =	new ArrayList<Attribute>();
	String structSchema;
	int stock;

	public String getJson(){
		return Utils.gson.toJson(this);
	}
	
	public String getStructSchema(){
		String retString	=	 "string#name-string#brand-string#refcode-long#ean-string#description";
		for(int i=0;i<photoUrl.size();i++){
			retString	=	retString+"-string#photourl"+i;
		}
		for(int i=0;i<attributeList.size();i++){
			retString	=	retString+"-string#"+attributeList.get(i).name;
		}
		return retString;
	}
	
	public String getCsv(String separator){
		String retString	=	this.name+separator+this.brand+separator+this.refCode+separator+this.ean+separator+this.description;
		for(String s:this.category){
			retString	=	retString+separator+s;
		}
		for(String s:this.photoUrl){
			retString	=	retString+separator+s;
		}
		for(Attribute a:this.attributeList){
			retString	=	retString	+separator+	a.value;
		}
		return retString;
	}
	
	public String getCsvHeader(String separator){
		String header	=	"name"+separator+"brand"+separator+"refcode"+separator+"ean"+separator+"description";
		int i=0;
		for(String s:this.category){
			++i;
			header	=	header+separator+"category"+i;
		}
		for(String s:this.photoUrl){
			++i;
			header	=	header+separator+"foto"+i;
		}
		for(Attribute a:this.attributeList){
			header	=	header	+separator+	a.name;
		}
		return header;
	}
}