package csvGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import csvGenerator.model.Product;

public class LindiLoader extends SupplierProductsLoader {
	
	public LindiLoader(){
		super();
		this.supplier	=	1002;
		this.separator	=	"\t";
		this.itemMap	=	new HashMap<String, Integer>();
		itemMap.put("name", 1);
		itemMap.put("ean", 12);
		itemMap.put("description", 3);
		itemMap.put("stock", 22);
	}

	
	
	@Override
	public Product loadProduct(String s) {
		Product p			=	 super.loadProduct(s);
		p.name				=	p.name.substring(1, p.name.length()-1);
		p.description		=	p.description.substring(1, p.description.length()-1);
		String category		=	s.split(this.separator)[7].replace("\"", "");
		if(this.categoryMap.containsKey(category)){
			p.category	=	this.categoryMap.get(category);
		}
		else{
			p.category	=	Utils.defaultCategory;
		}
		return p;
	}



	@Override
	public List<Product> loadProducts() {
		List <Product> returnList	=	new ArrayList<Product>();
        URL oracle;
        
        int printcount=0;
        
		try {
			oracle = new URL("http://exports.lindy.it/webshopdata.txt");
	        BufferedReader in = new BufferedReader(
            new InputStreamReader(oracle.openStream()));

            String inputLine;
            
            inputLine = in.readLine();
            //System.out.println("HEADER: "+inputLine);
            String[] header	=	inputLine.split("\t");

            while ((inputLine = in.readLine()) != null){
            	//System.out.println("LINE "+inputLine);
            	String[] splitted	=	inputLine.split("\t");
            	int i=0;
            	for(String s:splitted){
            		if(i<header.length){
            			//System.out.println("ELEMENT"+i+" "+header[i]+": "+s);	
            			i++;
            		}
            	}
            	
            	try{            		
                	Product p	=	this.loadProduct(inputLine);
                	if(p.stock>0){
                		System.out.println(p.getJson());
                		//System.out.println(p.getStructSchema());
                		
                		if(printcount==0){
                			System.out.println(p.getCsvHeader("|",3,3));
                			printcount++;
                		}
                		
                		System.out.println(p.getCsv("|"));
                		returnList.add(p);	
                	}
            	}
            	catch(Exception e){
            		e.printStackTrace();
            	}
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
		return returnList;
	}

}
