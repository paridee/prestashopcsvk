package csvGenerator;

import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import csvGenerator.model.Product;

public abstract class SupplierProductsLoader {
	public String separator;
	public HashMap<String,Integer> itemMap;
	public HashMap<String,String>  categoryMap;
	Gson gson	=	new Gson();
	public abstract List<Product> loadProducts();
	String supplier	=	"DEFAULT";
	public Product loadProduct(String s){
		String[] splitted	=	s.split(separator);
		Product p 			=	new Product();
		p.name				=	splitted[itemMap.get("name")].trim();
		//p.brand				=	splitted[itemMap.get("brand")];
		//p.refCode			=	splitted[itemMap.get("refCode")];
		String eanString	=	splitted[itemMap.get("ean")].replace("\"", "");
		if(!eanString.equals("")){
			p.ean				=	Long.parseLong(eanString);	
		}
		p.description		=	splitted[itemMap.get("description")];
		if(itemMap.containsKey("stock")){
			String stockString	=	splitted[itemMap.get("stock")].replace("\"", "");
			if(!stockString.equals("")){
				p.stock				=	Integer.parseInt(stockString);
			}
			else{
				p.stock=0;
			}	
		}
		return p;
	}
	
}
