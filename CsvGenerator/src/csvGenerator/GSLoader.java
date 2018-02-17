package csvGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;

import csvGenerator.model.Product;

public class GSLoader extends SupplierProductsLoader {
	
	public String filename	=	"";
	
	public GSLoader(String fileName){
		super();
		this.supplier	=	1004;
		this.separator	=	"\";\"";
		this.itemMap	=	new HashMap<String, Integer>();
		itemMap.put("name", 3);
		itemMap.put("ean", 4);
		itemMap.put("description", 3);
		itemMap.put("stock", 5);
		itemMap.put("category", 0);
		itemMap.put("price", 6);
		itemMap.put("refCode", 1);
		itemMap.put("brand", 2);
		this.images.add(7);
		this.filename	=	fileName;
		this.margin	=	0.15;
		
		
		this.categoryMap.put("TELEFONIA FISSA/CORDED","Telefonia#Telefonia Fissa#Telefoni con cavo".split("#"));
		this.categoryMap.put("TELEFONIA FISSA/CORDLESS","Telefonia#Telefonia Fissa#Telefoni cordless".split("#"));
		this.categoryMap.put("OROLOGI/SVEGLIE","Casa e piccoli elettrodomestici#Orologi#Sveglie".split("#"));
		this.categoryMap.put("ACCESSORI/CELLULARI","Telefonia#Accessori telefonia".split("#"));
		this.categoryMap.put("INFORMATICA/ACCESSORI","Elettronica e HiFi#Informatica#Accessori".split("#"));
		this.categoryMap.put("ACCESSORI/BORSE COMPUTER","Elettronica e HiFi#Informatica#Accessori".split("#"));
		this.categoryMap.put("CASALINGHI/PER LA CUCINA","Casa e piccoli elettrodomestici#Casalinghi".split("#"));
		this.categoryMap.put("CASALINGHI/PER LA PERSONA","Articoli per la persona".split("#"));
		this.categoryMap.put("ELETTRODOMESTICI/CURA DELLA PERSONA","Articoli per la persona#Elettrodomestici".split("#"));
		this.categoryMap.put("CASALINGHI/PULIZIA E IGIENE","Casa e piccoli elettrodomestici#Pulizia casa".split("#"));
		this.categoryMap.put("ACCESSORI/CD WALLET","Elettronica e HiFi#Audio Video#Accessori".split("#"));
		this.categoryMap.put("ACCESSORI/CUSTODIE E SCREEN PROTECTOR","Telefonia#Accessori#Custodie e pellicole".split("#"));
		this.categoryMap.put("ACCESSORI","Elettronica e HiFi#Audio Video#Accessori".split("#"));
		this.categoryMap.put("ELETTRODOMESTICI/PER LA CUCINA","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("TELEFONI CELLULARI/ITALIA","Telefonia#Cellulari e Smartphone".split("#"));
		this.categoryMap.put("ACCESSORI/BATTERIE E CARICABATTERIE","Telefonia#Ricambi#Batterie e caricabatterie".split("#"));
		this.categoryMap.put("CASALINGHI","Casa e piccoli elettrodomestici#Casalinghi".split("#"));
		this.categoryMap.put("ACCESSORI/CUFFIE","Elettronica e HiFi#Audio Video#Audio".split("#"));
		this.categoryMap.put("ILLUMINAZIONE","Casa e piccoli elettrodomestici#Casa#Illuminazione".split("#"));
		this.categoryMap.put("INFORMATICA/NOTEBOOK","Elettronica e HiFi#Informatica#Notebook".split("#"));
		this.categoryMap.put("VIDEO DIGITALE/TVC","Elettronica e HiFi#Tv#Televisori".split("#"));
		this.categoryMap.put("TELEFONI CELLULARI/TIM","Telefonia#Cellulari e Smartphone".split("#"));
		this.categoryMap.put("INFORMATICA/PC DESKTOP","Elettronica e HiFi#Informatica#PC Desktop".split("#"));
		this.categoryMap.put("ELETTRODOMESTICI/PED","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("TELEFONI CELLULARI/VODAFONE","Telefonia#Cellulari e Smartphone".split("#"));
		this.categoryMap.put("INFORMATICA/TABLET","Elettronica e HiFi#Informatica#Tablet".split("#"));
		this.categoryMap.put("SCHEDE PREPAGATE","Telefonia#Sim#Prepagate".split("#"));
		this.categoryMap.put("TELEFONI CELLULARI/H3G","Telefonia#Cellulari e Smartphone".split("#"));
		this.categoryMap.put("ELETTRODOMESTICI/PER LA CASA","Casa e piccoli elettrodomestici#Piccoli Elettrodomestici".split("#"));
		this.categoryMap.put("TELEFONI CELLULARI/IMPORT","Telefonia#Cellulari e Smartphone".split("#"));
		this.categoryMap.put("TELEFONI CELLULARI/EUROPA","Telefonia#Cellulari e Smartphone".split("#"));
		this.categoryMap.put("TELEFONI CELLULARI/OPERATORE","Telefonia#Cellulari e Smartphone".split("#"));
		this.categoryMap.put("VIDEO DIGITALE/DECODER DVBT","Elettronica e HiFi#Digitale Terrestre#Decoder".split("#"));
		this.categoryMap.put("VIDEO DIGITALE/SCHEDE SAT RICARICABILI","Elettronica e HiFi#Digitale Satellitare#Schede".split("#"));
		this.categoryMap.put("VIDEO DIGITALE/MEDIASET CARD","Elettronica e HiFi#Digitale Terrestre#Schede".split("#"));
		this.categoryMap.put("INFORMATICA","Elettronica e HiFi#Informatica".split("#"));
		this.categoryMap.put("AUDIO","Elettronica e HiFi#Audio Video#Audio".split("#"));
		this.categoryMap.put("VIDEO DIGITALE/FOTOCAMERE","Elettronica e HiFi#Fotocamere".split("#"));
		this.categoryMap.put("VIDEO DIGITALE/BLURAY","Elettronica e HiFi#Blu Ray".split("#"));
		this.categoryMap.put("CONSOLLE/PLAY STATION/PS4","Elettronica e HiFi#Consolle e giochi#PS4".split("#"));
		
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
	@Override
	public Product loadProduct(String s) {
		s	=	s.substring(1, s.length()-1);
		System.out.println("TEST GS "+s);
		Product p	=	super.loadProduct(s);
		return p;
	}

	
}
