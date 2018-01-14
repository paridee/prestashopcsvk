package csvGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import csvGenerator.model.Product;

public class ProductRepository {
	HashMap<Long,Product>	repository	=	new HashMap<Long, Product>();
	public void addProduct(Product p){
		if(!repository.containsKey(p.ean)){
			repository.put(p.ean, p);
		}
		else{
			System.out.println("EAN CONFICT, check price...");
			System.out.println(p.name+" "+repository.get(p.ean).name);
			if(p.price<repository.get(p.ean).price){
				repository.put(p.ean, p);
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
		Iterator<Long> it	=	this.repository.keySet().iterator();
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
	
	public List<String> getCsvWithAllFields(){
		ArrayList<String> csv			=	new ArrayList<String>();
		List<String> attributes	=	this.attributesList();
		Iterator<Long> it	=	this.repository.keySet().iterator();
		while(it.hasNext()){
			Long key	=	it.next();
			Product p	=	this.repository.get(key);
			csv.add(p.getCsv(",", attributes));
		}
		return csv;
	}
}
