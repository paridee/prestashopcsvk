package csvGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import csvGenerator.model.Attribute;
import csvGenerator.model.Product;

public class BigBuyLoader extends SupplierProductsLoader {
	
	public class BigBuyCategoryParser implements CategoryParser{

		@Override
		public String getCategoryString(String[] splitted) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String[] createCategory(String category) {
			String[] retCat		=	new String[3];
			String[] splitted	=	category.split(">");
			if(splitted[0].toLowerCase().contains("piccoli")||splitted[0].toLowerCase().contains("cucina")||splitted[0].toLowerCase().contains("casa")){
				retCat[0]	=	"Casa e Piccoli Elettrodomestici";
			}
			else if(splitted[0].toLowerCase().contains("regali")){
				retCat[0]	=	"Articoli da Regalo";
			}
			else if(splitted[0].toLowerCase().contains("outlet")){
				retCat[0]	=	"Outlet";
			}
			else if(category.toLowerCase().contains("moda")){
				retCat[0]	=	"Abbigliamento";
				if(category.toLowerCase().contains("reggisen")){
					retCat[1]	=	"Donna";
					retCat[2]	=	"Intimo";
				}
			}
			else if(category.toLowerCase().contains("telefonia")){
				retCat[0]	=	"Telefonia";
				if(category.toLowerCase().contains("fissi")){
					retCat[0]	=	"Telefoni Fissi";
				}
			}
			else if(category.toLowerCase().contains("> cellulari")){
				retCat[0]	=	"Telefonia";
				retCat[1]	=	"Smartphone e Cellulari";
				retCat[2]	=	"Altri marchi";
			}
			else if(category.toLowerCase().contains("suono")){
				retCat[0]	=	"Elettronica e HiFi";
			}
			else if(category.toLowerCase().contains("viso")||category.toLowerCase().contains("capelli")||category.toLowerCase().contains("personale")){
				retCat[0]	=	"Articoli per la Persona";
			}
			else{
				retCat[0]	=	"Vari";
			}
			if(category.toLowerCase().contains("cucina")){
				retCat[1]	=	"Cucina";
			}
			else if(category.toLowerCase().contains("pulizia")){
				retCat[1]	=	"Pulizia";
			}
			else{
				retCat[1]	=	"Vari";
			}
			retCat[2]	=	"Vari";
			
			return retCat;
		}
		
	}

	public BigBuyLoader(){
		super();
		this.supplier	=	1003;
		this.separator	=	";";
		this.itemMap	=	new HashMap<String, Integer>();
		itemMap.put("name", 2);
		itemMap.put("ean", 11);
		itemMap.put("description", 5);
		itemMap.put("price", 6);
		itemMap.put("refCode", 0);
		itemMap.put("category", 1);
		this.images.add(16);
		this.images.add(17);
		this.images.add(18);
		this.images.add(19);
		this.images.add(20);
		this.images.add(21);
		this.images.add(22);
		//this.images.add(23);
	}
	@Override
	public List<Product> loadProducts() {
		BigBuyCategoryParser parser	=	new BigBuyCategoryParser();
		ArrayList<Product> prods	=	new ArrayList<Product>();
		String file	=	FtpFileDownloader.downloadFile("bbuEDB3fKMPb","pu9kMbkg7U",21,"www.dropshippers.com.es","bigbuy_it.csv");
		String[] lines = file.split("\n");
		int count	=	0;
		for(String s:lines){
			s	=	s.replace('|', ' ');
			if(!s.isEmpty()&&count>1){
				System.out.println(s);
				System.out.println("Row: "+count);
				if(s.split(separator).length>20){
					if(s.split(separator)[13].equals("Sí")){
						String[] splitted	=	s.split(separator);
						Product p	=	this.loadProduct(s);
						p.category	=	parser.createCategory(splitted[1]);
						p.stock		=	10;
						prods.add(p);	
						Attribute attr	=	new Attribute();
						attr.name		=	"Prima caratteristica";
						attr.value		=	splitted[3];
						Attribute attr2 =	new Attribute();
						attr2.name		=	"Seconda caratteristica";
						attr2.value		=	splitted[4];
						p.attributeList.add(attr);
						p.attributeList.add(attr2);
						p.inventoryTag	=	attr.value+"#"+attr2.value;
					}		
				}
				
			}
			count++;
		}
		return prods;
	}

}
