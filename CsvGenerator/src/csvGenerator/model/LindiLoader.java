package csvGenerator.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class LindiLoader extends SupplierProductsLoader {
	Gson gson	=	new Gson;
	public LindiLoader(){
		super();
		this.separator	=	"\t";
		this.itemMap	=	new HashMap<String, Integer>();
		itemMap.put("name", 0);
		itemMap.put("ean", 11);
		itemMap.put("description", 3);
		this.categoryMap	=	new HashMap<String, String>();
	}

	@Override
	public List<Product> loadProducts() {
        URL oracle;
		try {
			oracle = new URL("http://exports.lindy.it/webshopdata.txt");
	        BufferedReader in = new BufferedReader(
            new InputStreamReader(oracle.openStream()));

            String inputLine;
            
            inputLine = in.readLine();
            System.out.println("HEADER: "+inputLine);
            String[] header	=	inputLine.split("\t");

            while ((inputLine = in.readLine()) != null){
            	System.out.println("LINE "+inputLine);
            	String[] splitted	=	inputLine.split("\t");
            	int i=0;
            	for(String s:splitted){
            		System.out.println("ELEMENT"+i+++" "+header[i]+": "+s);
            	}
            	return null;
            }
                
            in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

}
