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
		this.supplier	=	"003-LIN";
		this.separator	=	"\t";
		this.itemMap	=	new HashMap<String, Integer>();
		itemMap.put("name", 1);
		itemMap.put("ean", 12);
		itemMap.put("description", 3);
		itemMap.put("stock", 22);
		this.categoryMap	=	new HashMap<String, String>();
		this.categoryMap.put("Cables & Adapters", "Cavi e adattatori");
		this.categoryMap.put("Audio & Video", "Audio e video");
	}

	
	
	@Override
	public Product loadProduct(String s) {
		Product p			=	 super.loadProduct(s);
		p.name				=	p.name.substring(1, p.name.length()-1);
		p.description		=	p.description.substring(1, p.description.length()-1);
		String category		=	s.split(this.separator)[7].replace("\"", "");
		System.out.println("CATEGORY STRING "+category);
		String[] categorySplit	=	category.split("\\\\");
		for(String cat:categorySplit){
			if(this.categoryMap.containsKey(cat)){
				cat	=	this.categoryMap.get(cat);
			}
			else{
				cat="Varie";
			}
			System.out.println("CATEGORY "+cat);
			p.category.add(cat);
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
                			System.out.println(p.getCsvHeader("|"));
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
