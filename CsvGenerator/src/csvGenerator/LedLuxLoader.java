package csvGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import csvGenerator.model.Product;

public class LedLuxLoader extends SupplierProductsLoader {
	
	private String filename;

	public LedLuxLoader(String filename){
		super();
		this.supplier	=	1006;
		this.separator	=	"\\|";
		this.itemMap	=	new HashMap<String, Integer>();
		itemMap.put("name", 0);
		itemMap.put("ean", 16);
		itemMap.put("description", 3);
		itemMap.put("price", 4);
		itemMap.put("refCode", 2);
		itemMap.put("brand", 14);
		this.images.add(9);
		this.images.add(10);
		this.images.add(11);
		this.margin	=	0.2;
		this.filename	=	filename;
	}

	@Override
	public List<Product> loadProducts() {
		ArrayList<Product> retList	=	new ArrayList<Product>();
		int title	=	0;
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				if(title==0){
					title++;
				}
				else{
					retList.add(this.loadProduct(sCurrentLine));
				}				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return retList;
	}

}
