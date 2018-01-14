package csvGenerator.model;

import java.util.ArrayList;
import java.util.List;

import csvGenerator.Utils;

public class Product {
	public String name;
	public String brand;
	public String refCode;
	public long ean;
	public String description;
	public List<String> category	=	new ArrayList<String>();
	public List<String> photoUrl	=	new ArrayList<String>();
	public List<Attribute> attributeList =	new ArrayList<Attribute>();
	public String structSchema;
	public int stock;
	public double price;
	public String supplier;
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
		retString	=	retString+"#stock#price#supplier";
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
		retString	=	retString+separator+this.stock+separator+this.price+retString+separator+this.supplier;
		return retString;
	}
	
	public String getCsv(String separator, List<String> attributes){
		String retString	=	this.name+separator+this.brand+separator+this.refCode+separator+this.ean+separator+this.description;
		for(String s:this.category){
			retString	=	retString+separator+s;
		}
		for(String s:this.photoUrl){
			retString	=	retString+separator+s;
		}
		for(String a:attributes){
			String value	=	"";
			List<String> myAttrs	=	this.getAttributes();
			if(myAttrs.contains(a)){
				value	=	this.attributeList.get(myAttrs.indexOf(a)).toString();
			}
			retString	=	retString	+separator+	value;
		}
		retString	=	retString+separator+this.stock+separator+this.price+retString+separator+this.supplier;
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
		header	=	header+separator+"stock";
		header	=	header+separator+"price";
		header	=	header+separator+"supplier";
		return header;
	}
	
	public Object clone(){
		Product cloned		=	new Product();
		cloned.name			=	this.name;
		cloned.brand		=	this.brand;
		cloned.refCode		=	this.refCode;
		cloned.ean			=	this.ean;
		cloned.description	=	this.description;
		for(String s:this.category){
			cloned.category.add(s);
		}
		for(String s:this.photoUrl){
			cloned.photoUrl.add(s);
		}
		for(Attribute a:this.attributeList){
			cloned.attributeList.add((Attribute) a.clone());
		}
		cloned.structSchema	=	this.structSchema;
		cloned.stock		=	this.stock;
		cloned.price		=	this.price;
		cloned.supplier		=	this.supplier;
		return cloned;
	}
	
	public List<String> getAttributes(){
		List<String> attributes	=	new ArrayList<String>();
		for(Attribute att: this.attributeList){
			attributes.add(att.name);
		}
		return attributes;
	}
}