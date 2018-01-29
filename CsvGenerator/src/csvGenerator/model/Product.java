package csvGenerator.model;

import java.util.ArrayList;
import java.util.List;

import csvGenerator.Utils;

public class Product {
	public String name	=	"";
	public String brand	=	"";
	public String refCode	=	"";
	public long ean;
	public String description	=	"";
	public String[] category	=	new String[0];
	public List<String> photoUrl	=	new ArrayList<String>();
	public List<Attribute> attributeList =	new ArrayList<Attribute>();
	public String structSchema;
	public int stock;
	public double price;
	public String supplier;
	public String inventoryTag;
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
	
	public String getCsv(String separator, List<String> attributes,int photos,int catDepth){
		String retString	=	this.name+separator+this.brand+separator+this.refCode+separator+this.ean+separator+this.description;

		for(int i=0;i<catDepth;i++){
			if(i>=this.category.length){
				retString	=	retString+separator+"";
			}
			else{
				retString	=	retString+separator+this.category[i];	
			}
		}
		
		for(int i=0;i<photos;i++){
			if(i>=this.photoUrl.size()){
				retString	=	retString+separator+"";
			}
			else{
				retString	=	retString+separator+this.photoUrl.get(i);	
			}
		}
		for(String a:attributes){
			String value	=	"";
			List<String> myAttrs	=	this.getAttributes();
			if(myAttrs.contains(a)){
				value	=	this.attributeList.get(myAttrs.indexOf(a)).value.toString();
			}
			retString	=	retString	+separator+	value;
		}
		retString	=	retString+separator+this.stock+separator+this.price+separator+this.supplier;
		return retString;
	}
	
	public String getCsvHeader(String separator, List<String> attributes,int photos, int catDepth){
		String header	=	"name"+separator+"brand"+separator+"refcode"+separator+"ean"+separator+"description";
		for(int j=0;j<catDepth;j++){
			header	=	header+separator+"category"+j;
		}
		for(int j=0;j<photos;j++){
			header	=	header+separator+"foto"+j;
		}
		for(String a:attributes){
			header	=	header	+separator+	a;
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
		if(this.category!=null){
			cloned.category		=	new String[this.category.length];	
			for(int i=0;i<category.length;i++){
				cloned.category[i]	=	this.category[i];
			}
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

	public String getCsvHeader(String string,int photos, int catDepth) {
		return this.getCsvHeader(string, new ArrayList<String>(),photos, catDepth);
	}
}