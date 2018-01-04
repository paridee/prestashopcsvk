package csvGenerator.model;

import java.util.HashMap;
import java.util.List;

public abstract class SupplierProductsLoader {
	public String separator;
	public HashMap<String,Integer> itemMap;
	public HashMap<String,String>  categoryMap;
	public abstract List<Product> loadProducts();
	public Product loadProduct(String s){
		String[] splitted	=	s.split(separator);
		Product p 			=	new Product();
		p.name				=	splitted[itemMap.get("name")];
		//p.brand				=	splitted[itemMap.get("brand")];
		//p.refCode			=	splitted[itemMap.get("refCode")];
		p.ean				=	Long.parseLong(splitted[itemMap.get("ean")]);
		p.description		=	splitted[itemMap.get("description")];
		return p;
	}
}
