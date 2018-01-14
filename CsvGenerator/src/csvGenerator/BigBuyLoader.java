package csvGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import csvGenerator.model.Product;

public class BigBuyLoader extends SupplierProductsLoader {

	public BigBuyLoader(){
		super();
		this.supplier	=	"001-BB";
		this.separator	=	";";
		this.itemMap	=	new HashMap<String, Integer>();
		itemMap.put("name", 2);
		itemMap.put("ean", 11);
		itemMap.put("description", 5);
		//itemMap.put("stock", 18);
		this.categoryMap	=	new HashMap<String, String>();
		this.categoryMap.put("Cables & Adapters", "Cavi e adattatori");
		this.categoryMap.put("Audio & Video", "Audio e video");
	}
	@Override
	public List<Product> loadProducts() {
		ArrayList<Product> prods	=	new ArrayList<Product>();
		String file	=	FtpFileDownloader.downloadFile("bbuEDB3fKMPb","pu9kMbkg7U",21,"www.dropshippers.com.es","bigbuy_it.csv");
		String[] lines = file.split("\n");
		int count	=	0;
		for(String s:lines){
			if(!s.isEmpty()&&count>1){
				System.out.println(s);
				System.out.println("Row: "+count);
				if(s.split(separator).length>20){
					prods.add(this.loadProduct(s));		
				}
			}
			count++;
		}
		return prods;
	}

}
