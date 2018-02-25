package csvGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import csvGenerator.model.Product;

public class Dropshipping4youLoader extends SupplierProductsLoader{
	
	public Dropshipping4youLoader(){
		super();
		this.supplier	=	1005;
		this.separator	=	"\";\"";
		this.itemMap	=	new HashMap<String, Integer>();
		itemMap.put("name", 3);
		itemMap.put("ean", 11);
		itemMap.put("description", 4);
		itemMap.put("price", 10);
		itemMap.put("refCode", 0);
		this.images.add(5);
		this.margin	=	0.2;
	}
	
	@Override
	public Product loadProduct(String s) {
		Product p	=	 super.loadProduct(s);
		String[] splitted	=	s.split(this.separator);
		String cat			=	splitted[1]+"#"+splitted[2];
		p.category			=	cat.split("#");
		if(!this.categoryMap.containsKey(cat)){
			p.category	=	Utils.defaultCategory;
			if(!Utils.isKnownCategory(cat)){
				Utils.addCategory((cat),this.getClass());
			}
		}
		return p;
	}


	@Override
	public List<Product> loadProducts() {
		List <Product> returnList	=	new ArrayList<Product>();       
        int printcount=0;
			URL url;
			try {
				url = new URL("http://www.dropship4u.it/__csv/dropship4u2csv.php");
				URLConnection conn = url.openConnection();
				conn.setRequestProperty("User-Agent",
				        "Mozilla/5.0 (Windows NT 5.1; rv:19.0) Gecko/20100101 Firefox/19.0");
				conn.connect();
				
				BufferedReader in = new BufferedReader(
	            new InputStreamReader(conn.getInputStream()));

	            String inputLine;
	            
	            inputLine = in.readLine();
	            //System.out.println("HEADER: "+inputLine);
	            String[] header	=	inputLine.split(",");
	            Product lastProduct = null;
	            int count	=	0;
	            while ((inputLine = in.readLine()) != null){
	            	if(count++>0){
		            	Product p	=	this.loadProduct(inputLine.substring(0, inputLine.length()-1).substring(1));
		            	returnList.add(p);	
	            	}
	            }
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		return returnList;
            
	}

}
