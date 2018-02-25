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

import csvGenerator.model.Attribute;
import csvGenerator.model.Product;

public class GriffatiLoader extends SupplierProductsLoader implements ProductWithModelsLoader {

	private char delimiter;
	
	public class GriffatiCategoryParser implements CategoryParser{

		
		HashMap<String,String> dict	=	new HashMap<String,String>();
		public GriffatiCategoryParser(){
			dict.put("Gioiellibigiotteria", "Gioielli e Bigiotteria");
			dict.put("Occhialidasole", "Occhiali da sole");
			dict.put("Accessori-mix", "Accessori e Altro");
		}
		@Override
		public String getCategoryString(String[] splitted) {
			return	splitted[13]+"/"+splitted[14];
		}

		@Override
		public String[] createCategory(String category) {
			String[] cats	=	category.split("/");
			ArrayList<String> catsString	=	new ArrayList<String>();
			catsString.add("Abbigliamento");
			for(String s:cats){
				String nString	=	s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
				if(this.dict.containsKey(nString)){
					nString	=	this.dict.get(nString);
				}
				catsString.add(nString);
			}
			String [] retString	=	new String[catsString.size()];
			for(int i=0;i<catsString.size();i++){
				retString[i]	=	catsString.get(i);
			}
			return retString;
		}
		
	}
	
	public GriffatiLoader(){		
		super();
		this.margin		=	0.3;
		this.vat		=	0.22;
		this.supplier	=	1001;
		this.separator	=	",";
		this.delimiter	=	'\"';
		this.itemMap	=	new HashMap<String, Integer>();
		itemMap.put("name", 3);
		itemMap.put("ean", 16);
		itemMap.put("refCode", 1);
		itemMap.put("description", 8);
		itemMap.put("stock", 18);
		itemMap.put("price", 7);
		itemMap.put("brand", 2);
		this.images.add(10);
		this.images.add(11);
		this.images.add(12);
		this.parse	=	new GriffatiCategoryParser();

	}
	
	@Override
	public List<Product> loadProducts() {
		List <Product> returnList	=	new ArrayList<Product>();
        
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
                    			System.out.println(p.getCsvHeader("|",3,3));
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
            		throw e;
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

	@Override
	public Product loadProduct(String s) {
		Product p	=	super.loadProduct(s);
		Attribute size	=	new Attribute();
		size.name		=	"color";
		String[] splitted	=	Utils.splitWithMerge(",", '"', s);
		if(splitted.length>19){
			size.value		=	splitted[19];
			p.attributeList.add(size);	
		}
		return p;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public Product loadModel(Product p, String line, String separator) {
		
		System.out.println("Loading model from line "+line);
		Product retProd	=	(Product) p.clone();
		String[] attr	=	Utils.splitWithMerge(this.separator, delimiter, line);
		String eanStr	=	attr[itemMap.get("ean")];
		if(!eanStr.equals("")){
			try{
				retProd.ean		=	Long.parseLong(eanStr);	
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		String stockStr	=	attr[itemMap.get("stock")];
		if(!stockStr.equals("")){
			retProd.stock	=	Integer.parseInt(stockStr);
		}
		else{
			retProd.stock	=	0;
		}
		Attribute size	=	new Attribute();
		size.name		=	"Taglia";
		size.value		=	attr[17];
		retProd.attributeList.add(size);
		p.inventoryTag	=	size.value.toUpperCase();
		return retProd;
	}

}
