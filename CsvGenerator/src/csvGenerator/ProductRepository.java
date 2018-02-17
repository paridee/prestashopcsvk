package csvGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import csvGenerator.model.Product;

public class ProductRepository {
	HashMap<String,Product>	repository	=	new HashMap<String, Product>();
	public void addProduct(Product p){
		if(!repository.containsKey(p.ean+"#"+p.inventoryTag)){
			repository.put(p.ean+"#"+p.inventoryTag, p);
		}
		else{
			System.out.println("EAN CONFICT, check price...");
			System.out.println(p.name+" EAN "+p.ean+" "+repository.get(p.ean+"#"+p.inventoryTag).name);
			if(p.price<repository.get(p.ean+"#"+p.inventoryTag).price){
				repository.put(p.ean+"#"+p.inventoryTag, p);
			}
		}
	}
	public void addProducts(List<Product> pList){
		for(Product p:pList){
			this.addProduct(p);
		}
	}

	public int getNumberOfProducts(){
		return this.repository.size();
	}
	
	public List<String> attributesList(){
		ArrayList<String> attributes	=	new ArrayList<String>();
		Iterator<String> it	=	this.repository.keySet().iterator();
		while(it.hasNext()){
			Product p	=	repository.get(it.next());
			List<String> attrTempList	=	p.getAttributes();
			for(String s:attrTempList){
				if(!attributes.contains(s)){
					attributes.add(s);
				}
			}
		}
		return attributes;
	}
	
	public List<String> getCsvWithAllFields(char separator,double priceTh){
		ArrayList<String> csv			=	new ArrayList<String>();
		List<String> attributes	=	this.attributesList();
		Iterator<String> it	=	this.repository.keySet().iterator();
		boolean printHeader	=	true;
		while(it.hasNext()){
			String key	=	it.next();
			Product p	=	this.repository.get(key);
			if(p.category.length>1){
				String checkMobiles	=	p.category[1].trim();
				if(checkMobiles.toLowerCase().equals("Cellulari e Smartphone".toLowerCase())){
					String[] cats	=	new String[3];
					cats[0]			=	p.category[0];
					cats[1]			=	p.category[1];
					cats[2]			=	p.brand;
					p.category		=	cats;
				}	
			}
			if(printHeader==true){
				csv.add(p.getCsvHeader(separator+"", attributes,3,3));
				printHeader=false;
			}
			if(p.price>=priceTh){
				csv.add(p.getCsv(separator+"", attributes,3,3));	
			}
		}
		return csv;
	}
	
	public List<String> getCategores(){
		Iterator<String> it	=	this.repository.keySet().iterator();
		ArrayList<String> catRecap	=	new ArrayList<String>();
		while(it.hasNext()){
			Product p			=	this.repository.get(it.next());
			String[] cats		=	p.category;
			String catString	=	"";
			for(int i=0;i<cats.length;i++){
				String temp	=	cats[i];
				if(i>0){
					temp	=	"#"+temp;
				}
				catString	=	catString+temp;
			}
			if(!catRecap.contains(catString)){
				catRecap.add(catString);
			}
		}
		return catRecap;
	}
}
