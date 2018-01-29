package csvGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import csvGenerator.model.Product;

public abstract class SupplierProductsLoader {
	public String separator;
	public HashMap<String,Integer> itemMap;
	public HashMap<String,String[]>  categoryMap	=	new HashMap<String, String[]>();
	public List<Integer> images = new ArrayList<Integer>();
	public CategoryParser parse;
	Gson gson	=	new Gson();
	public abstract List<Product> loadProducts();
	int supplier	=	1000;
	double margin;
	double vat;
	
	public Product loadProduct(String s){
		String[] splitted	=	Utils.splitWithMerge(separator, '\"', s);//s.split(separator);
		System.out.println("Splitted size "+splitted.length);
		Product p 			=	new Product();
		p.supplier			=	this.supplier+"";
		p.name				=	splitted[itemMap.get("name")].trim();
		//p.brand				=	splitted[itemMap.get("brand")];
		//p.refCode			=	splitted[itemMap.get("refCode")];
		
		if(itemMap.containsKey("category")){
			if(splitted.length>itemMap.get("category")-1){
				p.brand				=	splitted[itemMap.get("brand")];
				if(p.brand.length()>1){
					String[] brandItems	=	p.brand.split(" ");
					String finalBrand	=	"";
					for(int i=0;i<brandItems.length;i++){
						String st	=	brandItems[i];
						finalBrand	=	finalBrand+st.substring(0,1).toUpperCase()+st.substring(1).toLowerCase();
						if(i<brandItems.length-1){
							finalBrand	=	finalBrand+" ";
						}
					}
					p.brand	=	finalBrand;
				}
			}
		}
		
		
		if(itemMap.containsKey("category")){
			if(splitted.length>itemMap.get("category")-1){
				String category	= splitted[itemMap.get("category")];
				if(categoryMap.containsKey(category)){
					p.category	=	categoryMap.get(category);
				}
				else{
					p.category	=	Utils.defaultCategory;
					if(!Utils.unknownCategories.contains(category)){
						Utils.unknownCategories.add(category);		
					}
				}
			}
		}
		else if(this.parse!=null){
			String category	= this.parse.getCategoryString(splitted);
			if(categoryMap.containsKey(category)){
				p.category	=	categoryMap.get(category);
			}
			else{
				p.category	=	parse.createCategory(category);
			}
		}
		
		if(itemMap.containsKey("refCode")){
			if(splitted.length>itemMap.get("refCode")-1){
				String refString	=	splitted[itemMap.get("refCode")].replace("\"", "");
				if(!refString.equals("")){
					try{
						p.refCode				=	this.supplier+refString;	
					}
					catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		
		if(splitted.length>itemMap.get("ean")-1){
			String eanString	=	splitted[itemMap.get("ean")].replace("\"", "");
			if(!eanString.equals("")){
				try{
					p.ean				=	Long.parseLong(eanString.replace(" ", ""));	
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			else{
				p.ean	=	Long.parseLong(p.refCode.replaceAll("[^\\d.]", ""));
			}
		}
		if(splitted.length-1>=itemMap.get("description")){
			p.description		=	splitted[itemMap.get("description")];	
		}
		if(itemMap.containsKey("stock")){
			if(splitted.length>itemMap.get("stock")-1){
				if(itemMap.containsKey("stock")){
					String stockString	=	splitted[itemMap.get("stock")].replace("\"", "");
					if(!stockString.equals("")){
						p.stock				=	Integer.parseInt(stockString);
					}
					else{
						p.stock=0;
					}	
				}	
			}	
		}
		if(itemMap.containsKey("price")){
			if(splitted.length>itemMap.get("price")-1){
				p.price	=	Double.parseDouble(splitted[itemMap.get("price")].replace("\"", "").replace(",", ".").replace("'", ""));
				p.price	=	p.price+(p.price*margin);
				//p.price	=	p.price+(p.price*vat);
				System.out.println("price "+p.price);
			}
		}
		for(int i:images){
			if(i<splitted.length){
				p.photoUrl.add(splitted[i]);	
			}
		}
		return p;
	}
	
}
