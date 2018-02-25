package csvGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import csvGenerator.model.Product;

//wget -O byzancia.csv https://europe-api.extenso-telecom.com/Products/get/?authentification_token=at4XJIXXgz5h3abgkndpPHaJYzbn2Ejv&return_format=csv&country_cc2[]=IT

public class ByzanciaLoader extends SupplierProductsLoader{

	private String filename;

	public ByzanciaLoader(String filename){
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
