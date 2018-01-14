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

public class GriffatiLoader extends SupplierProductsLoader implements ProductWithModelsLoader {

	public GriffatiLoader(){
		super();
		this.supplier	=	"002-GR";
		this.separator	=	",";
		this.itemMap	=	new HashMap<String, Integer>();
		itemMap.put("name", 3);
		itemMap.put("ean", 16);
		itemMap.put("description", 8);
		itemMap.put("stock", 18);
		this.categoryMap	=	new HashMap<String, String>();
		this.categoryMap.put("Cables & Adapters", "Cavi e adattatori");
		this.categoryMap.put("Audio & Video", "Audio e video");
	}
	
	@Override
	public List<Product> loadProducts() {
		List <Product> returnList	=	new ArrayList<Product>();
        URL oracle;
        
        int printcount=0;
        
		try {
			URL url = new URL("http://b2b.griffati.it/csvExport.py?gui=0&datatype=prod&go=1&token=548690&apikey=f8dS-sja8-Sgya-5ARt");
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
            while ((inputLine = in.readLine()) != null){
            	//System.out.println("LINE "+inputLine);
            	String[] splitted	=	inputLine.split(",");
            	int i=0;
            	for(String s:splitted){
            		if(i<header.length){
            			System.out.println("ELEMENT"+i+" "+header[i]+": "+s);	
            			i++;
            		}
            	}
            	
            	try{
            		String linetype	=	inputLine.split(",")[0];
            		if(linetype.equals("PRODUCT")){
                    	Product p	=	this.loadProduct(inputLine);
                    	if(p.stock>0){
                    		System.out.println(p.getJson());
                    		//System.out.println(p.getStructSchema());
                    		
                    		if(printcount==0){
                    			System.out.println(p.getCsvHeader("|"));
                    			printcount++;
                    		}
                    		
                    		System.out.println(p.getCsv("|"));
                    		//returnList.add(p);	
                    	}
                    	lastProduct	=	p;
            		}
            		else if(linetype.equals("MODEL")){
            			Product p	=	(Product) lastProduct.clone();
            			returnList.add(this.loadModel(lastProduct, inputLine, ","));
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product loadModel(Product p, String line, String separator) {
		
		System.out.println("Loading model from line "+line);
		Product retProd	=	(Product) p.clone();
		String[] attr	=	line.split(separator);
		String eanStr	=	attr[itemMap.get("ean")];
		if(!eanStr.equals("")){
			retProd.ean		=	Long.parseLong(eanStr);
		}
		String stockStr	=	attr[itemMap.get("stock")];
		if(!stockStr.equals("")){
			retProd.stock	=	Integer.parseInt(stockStr);
		}
		else{
			retProd.stock	=	0;
		}
		return retProd;
	}

}
