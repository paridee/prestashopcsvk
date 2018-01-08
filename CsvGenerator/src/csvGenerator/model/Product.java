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