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
	public Product loadProduct(String s){
		String[] splitted	=	s.split(separator);
		Product p 			=	new Product();
		p.name				=	splitted[itemMap.get("name")];
		//p.brand				=	splitted[itemMap.get("brand")];
		//p.refCode			=	splitted[itemMap.get("refCode")];
		p.ean				=	Long.parseLong(splitted[itemMap.get("ean")].replace("\"", ""));
		p.description		=	splitted[itemMap.get("description")];
		p.stock				=	Integer.parseInt(splitted[itemMap.get("stock")].replace("\"", ""));
		return p;
	}
	
}
