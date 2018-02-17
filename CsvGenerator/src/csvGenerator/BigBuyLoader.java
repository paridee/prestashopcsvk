package csvGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import csvGenerator.model.Attribute;
import csvGenerator.model.Product;

public class BigBuyLoader extends SupplierProductsLoader {

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
		this.margin	=	0.2;
		//this.images.add(23);
		
		this.categoryMap.put("Regali Originali > Giocattoli e Giochi > Giochi Musicali","Articoli da Regalo#Giochi#Giochi musicali".split("#"));
		this.categoryMap.put("Regali Originali > Articoli scherzosi","Articoli da Regalo#Articoli Scherzosi".split("#"));
		this.categoryMap.put("Regali Originali > Gadget > Altri Gadget","Articoli da Regalo#Gadget".split("#"));
		this.categoryMap.put("Piccoli elettrodomestici > Cucina > Robot da Cucina","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("Cucina Gourmet > Utensili da Cucina > Coltelli e Affilacoltelli","Casa e piccoli elettrodomestici#Cucina#Utensili".split("#"));
		this.categoryMap.put("Casa Pulizia > Pulizia > Altri Prodotti Pulizia","Casa e piccoli elettrodomestici#Casalinghi".split("#"));
		this.categoryMap.put("Casa Pulizia > Mobili e Arredamento Interni > Organizer","Casa e piccoli elettrodomestici#Accessori da Interno".split("#"));
		this.categoryMap.put("Casa Pulizia > Climatizzazione > Ventilazione","Casa e piccoli elettrodomestici#Ventilatori".split("#"));
		this.categoryMap.put("Casa Pulizia > Pulizia > Aspirapolvere e Robot","Casa e piccoli elettrodomestici#Aspirapolvere e Robot".split("#"));
		this.categoryMap.put("Salute Bellezza > Igiene Personale > Cura del Viso","Articoli per la Persona#Igiene#Pulizia del Viso".split("#"));
		this.categoryMap.put("Regali Originali > Feste e Celebrazioni","Articoli da Regalo#Feste speciali".split("#"));
		this.categoryMap.put("Regali Originali > Giocattoli e Giochi > Altri Giocattoli","Articoli da Regalo#Giochi".split("#"));
		this.categoryMap.put("Cucina Gourmet > Gourmet > Prosciutto di Jabugo Iberico","Casa e piccoli elettrodomestici#Cucina#Accessori".split("#"));
		this.categoryMap.put("Salute Bellezza > Relax e Benessere > Coperte Batamanta","Casa e piccoli elettrodomestici#Camera da Letto#Coperte".split("#"));
		this.categoryMap.put("Cucina Gourmet > Gourmet > Accessori","Casa e piccoli elettrodomestici#Cucina#Accessori".split("#"));
		this.categoryMap.put("Regali Originali > Fumatori > Ricambi Filtri","Articoli per la Persona#Articoli per Fumatori#Filtri".split("#"));
		this.categoryMap.put("Fitness Sport > Cinture Vibranti","Articoli per la Persona#Fitness".split("#"));
		this.categoryMap.put("Elettronica Informatica > Sveglie e Orologi","Casa e piccoli elettrodomestici#Orologi#Sveglie".split("#"));
		this.categoryMap.put("Regali Originali > Gadget > Salvadanai","Articoli da Regalo#Gadget".split("#"));
		this.categoryMap.put("Cucina Gourmet > Utensili da Cucina > Altri Prodotti per la Casa","Casa e piccoli elettrodomestici#Cucina#Utensili".split("#"));
		this.categoryMap.put("Cucina Gourmet > Preparazione Cibi > Bollitori Acqua","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("Cucina Gourmet > Preparazione Cibi > Spremiagrumi","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("Salute Bellezza > Relax e Benessere > Cuscini","Casa e piccoli elettrodomestici#Camera da Letto#Cuscini".split("#"));
		this.categoryMap.put("Regali Originali > Giocattoli e Giochi > Poker","Articoli da Regalo#Giochi#Giochi da Tavolo".split("#"));
		this.categoryMap.put("Salute Bellezza > Igiene Personale > Cura dei Capelli","Articoli per la Persona#Capelli".split("#"));
		this.categoryMap.put("Cucina Gourmet > Preparazione Cibi","Casa e piccoli elettrodomestici#Cucina".split("#"));
		this.categoryMap.put("Fitness Sport > Attrezzature per Addominali","Articoli per la Persona#Fitness".split("#"));
		this.categoryMap.put("Casa Pulizia > Mobili e Arredamento Interni > Decorazione","Casa e piccoli elettrodomestici#Accessori da Interno".split("#"));
		this.categoryMap.put("Fitness Sport > Muscolazione","Articoli per la Persona#Fitness".split("#"));
		this.categoryMap.put("Casa Pulizia > Terrazza e Giardino","Casa e piccoli elettrodomestici#Esterno".split("#"));
		this.categoryMap.put("Salute Bellezza > Altri Prodotti Benessere","Articoli per la Persona#Altro".split("#"));
		this.categoryMap.put("Piccoli elettrodomestici > Cucina > Grill e Barbecue","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("Casa Pulizia > Pulizia > Moci","Casa e piccoli elettrodomestici#Casalinghi".split("#"));
		this.categoryMap.put("Casa Pulizia > Bagno","Casa e piccoli elettrodomestici#Altro".split("#"));
		this.categoryMap.put("Regali Originali > Articoli da Viaggio","Articoli da Regalo#Articoli da Viaggio".split("#"));
		this.categoryMap.put("Piccoli elettrodomestici > Cucina > Trituatutto e Pelatori","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("Fitness Sport > Accessori sportivi","Articoli per la Persona#Fitness".split("#"));
		this.categoryMap.put("Cucina Gourmet > Gourmet > Bibite","Casa e piccoli elettrodomestici#Cucina".split("#"));
		this.categoryMap.put("Casa Pulizia > Mobili e Arredamento Interni > Altri Prodotti Indoor","Casa e piccoli elettrodomestici#Accessori da Interno".split("#"));
		this.categoryMap.put("Cucina Gourmet > Preparazione Cibi > Frullini elettrici e Frullatori","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("Casa Pulizia > Lampade e Illuminazione","Casa e piccoli elettrodomestici#Esterno#Illuminazione".split("#"));
		this.categoryMap.put("Casa Pulizia > Fai da te > Attrezzi","Casa e piccoli elettrodomestici#Casalinghi".split("#"));
		this.categoryMap.put("Cucina Gourmet > Utensili da Cucina > Contenitori e Tupper","Casa e piccoli elettrodomestici#Cucina#Utensili".split("#"));
		this.categoryMap.put("Salute Bellezza > Igiene Personale > Cura Personale","Articoli per la Persona#Igiene".split("#"));
		this.categoryMap.put("Piccoli elettrodomestici > Cucina > Spillatore Birra","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("Casa Pulizia > Pulizia > Moci e Scope","Casa e piccoli elettrodomestici#Casalinghi".split("#"));
		this.categoryMap.put("Piccoli elettrodomestici > Casa e Pulizia > Pulizia col Vapore","Casa e piccoli elettrodomestici#Pulizia casa#Vapore".split("#"));
		this.categoryMap.put("Piccoli elettrodomestici > Igiene ed Estetica > Manicure e Pedicure","Articoli per la Persona#Igiene#Manicure e Pedicure".split("#"));
		this.categoryMap.put("Elettronica Informatica > Suono > Riproduttori Multimediali","Elettronica e HiFi#Audio Video#Audio".split("#"));
		this.categoryMap.put("Regali Originali > Articoli Erotici","Articoli da Regalo#Articoli Erotici".split("#"));
		this.categoryMap.put("Casa Pulizia > Scacciainsetti","Casa e piccoli elettrodomestici#Scaccia Insetti".split("#"));
		this.categoryMap.put("Salute Bellezza > Relax e Benessere","Articoli per la Persona#Relax".split("#"));
		this.categoryMap.put("Elettronica Informatica > Periferiche Computer","Elettronica e HiFi#Informatica#Accessori".split("#"));
		this.categoryMap.put("Elettronica Informatica > Bascula & Bilancia","Casa e piccoli elettrodomestici#Cucina#Bilance".split("#"));
		this.categoryMap.put("Salute Bellezza > Moda > Accessori","Abbigliamento#Accessori".split("#"));
		this.categoryMap.put("Salute Bellezza > Moda > Reggiseni","Abbigliamento#Donna#Intimo".split("#"));
		this.categoryMap.put("Elettronica Informatica > Suono > Altoparlanti ed Auricolari","Elettronica e HiFi#Audio Video#Audio".split("#"));
		this.categoryMap.put("Salute Bellezza > Moda > Fasce e Coulotte","Abbigliamento#Accessori".split("#"));
		this.categoryMap.put("Casa Pulizia > Amici a Quattro Zampe","Casa e piccoli elettrodomestici#Prodotti per Animali".split("#"));
		this.categoryMap.put("Salute Bellezza > Relax e Benessere > Massaggiatori","Articoli per la Persona#Relax#Massaggiatori".split("#"));
		this.categoryMap.put("Salute Bellezza > Igiene Personale > Cura Piedi e Mani","Articoli per la Persona#Igiene#Manicure e Pedicure".split("#"));
		this.categoryMap.put("Regali Originali > Giocattoli e Giochi > Monopattini","Articoli da Regalo#Giochi".split("#"));
		this.categoryMap.put("Casa Pulizia > Fai da te > Automobile","Casa e piccoli elettrodomestici#Fai da te#Auto".split("#"));
		this.categoryMap.put("Elettronica Informatica > Telefonia e Accessori > Custodie Cellulari","Telefonia#Accessori#Custodie e pellicole".split("#"));
		this.categoryMap.put("Cucina Gourmet > Preparazione Cibi > Tritatutto e Pelatori","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("Salute Bellezza > Relax e Benessere > Altri Prodotti Relax","Articoli per la Persona#Relax".split("#"));
		this.categoryMap.put("Casa Pulizia > Climatizzazione > Riscaldamento","Casa e piccoli elettrodomestici#Riscaldamento".split("#"));
		this.categoryMap.put("Cucina Gourmet > Preparazione Cibi > Altri Accessori Cucina","Casa e piccoli elettrodomestici#Cucina#Accessori".split("#"));
		this.categoryMap.put("Cucina Gourmet > Preparazione Cibi > Robot da Cucina","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("Cucina Gourmet > Utensili da Cucina > Padelle","Casa e piccoli elettrodomestici#Cucina#Padelle e Pentole".split("#"));
		this.categoryMap.put("Casa Pulizia > Pulizia > Magicball","Casa e piccoli elettrodomestici#Casalinghi".split("#"));
		this.categoryMap.put("Salute Bellezza > Puericultura Cura Bebè","Articoli per la Persona#Bebè".split("#"));
		this.categoryMap.put("Cucina Gourmet > Preparazione Cibi > Grill e Barbecue","Casa e piccoli elettrodomestici#Cucina#Grill e Barbecue".split("#"));
		this.categoryMap.put("Casa Pulizia > Pulizia > Vaporelle","Casa e piccoli elettrodomestici#Pulizia casa#Vapore".split("#"));
		this.categoryMap.put("Elettronica Informatica > Telefonia e Accessori > Telefoni Fissi","Telefonia#Telefonia Fissa".split("#"));
		this.categoryMap.put("Casa Pulizia > Terrazza e Giardino > Barbecue","Casa e piccoli elettrodomestici#Cucina#Grill e Barbecue".split("#"));
		this.categoryMap.put("Cucina Gourmet > Preparazione Cibi > Tostapane","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("Cucina Gourmet > Preparazione Cibi > Spillatori Birra","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("Outlet e Promozioni Liquidazioni > Liquidazioni","Outlet#Stock".split("#"));
		this.categoryMap.put("Regali Originali > Fumatori > Posacenere","Articoli per la Persona#Articoli per Fumatori#Posacenere".split("#"));
		this.categoryMap.put("Salute Bellezza > Parrucchieria","Articoli per la Persona#Capelli".split("#"));
		this.categoryMap.put("Cucina Gourmet > Preparazione Cibi > Caffettiere","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("Cucina Gourmet > Preparazione Cibi > Friggitrici","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		this.categoryMap.put("Piccoli elettrodomestici > Igiene ed Estetica > Altri Prodotti Pulizia","Articoli per la Persona#Igiene#Altro".split("#"));
		this.categoryMap.put("Regali Originali > Giocattoli e Giochi > Modellismo Radio Controllo","Articoli da Regalo#Radiocomandi".split("#"));
		this.categoryMap.put("Regali Originali > Materiale Scolastico","Articoli da Regalo#Articoli Scuola".split("#"));
		this.categoryMap.put("Elettronica Informatica > Telefonia e Accessori > Cellulari","Telefonia#Cellulari e Smartphone".split("#"));
		this.categoryMap.put("Regali Originali > Articoli Romantici","Articoli da Regalo#Regali Romantici".split("#"));
		this.categoryMap.put("Regali Originali > Giocattoli e Giochi > Giochi da Tavolo","Articoli da Regalo#Giochi#Giochi da Tavolo".split("#"));
		this.categoryMap.put("Casa Pulizia > Terrazza e Giardino > Altri Prodotti Outdoor","Casa e piccoli elettrodomestici#Esterno e Giardino".split("#"));
		this.categoryMap.put("Casa Pulizia > Altre curiosità","Casa e piccoli elettrodomestici".split("#"));
		this.categoryMap.put("Outlet e Promozioni Liquidazioni > Senza imballaggio","Outlet#Senza Imballaggio".split("#"));
		this.categoryMap.put("Regali Originali > Quadri","Articoli da Regalo#Quadri".split("#"));
		this.categoryMap.put("Regali Originali > Giocattoli e Giochi > Giochi Chupito","Articoli da Regalo#Giochi Alcolici".split("#"));
		this.categoryMap.put("Piccoli elettrodomestici > Casa e Pulizia > Bilancia e Bascula","Casa e piccoli elettrodomestici#Cucina#Elettrodomestici".split("#"));
		
		
	}
	@Override
	public List<Product> loadProducts() {
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
