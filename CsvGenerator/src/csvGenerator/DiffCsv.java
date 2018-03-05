package csvGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DiffCsv {
	public String 	oldFile;
	public String 	newFile;
	public int		stockPos;
	public HashMap<String,String>  oldFileItems	=	new HashMap<String, String>();
	
	public DiffCsv(String oldFile, String newFile) {
		super();
		this.oldFile = oldFile;
		this.newFile = newFile;
	}

	public ArrayList<String> getMissing(int idColumn, String separator, String outSeparator){	
		Set<String> idsOld	=	loadIdsFromFile(idColumn, separator, oldFile, true);
		Set<String> idsNew	=	loadIdsFromFile(idColumn, separator, newFile);
		ArrayList<String> diff		=	new ArrayList<String>();
		System.out.println(idsOld.size()+" "+idsNew.size());
		for(String s:idsOld){
			if(!idsNew.contains(s)){
				String[] splitted	=	oldFileItems.get(s).split(separator);
				System.out.println("old value "+splitted[stockPos]);
				splitted[stockPos]	=	0+"";
				String tot			=	"";
				for(int i=0;i<splitted.length;i++){
					tot	=	tot	+	splitted[i];
					if(i<splitted.length-1){
						tot	=	tot+outSeparator;
					}
				}
				diff.add(tot);
			}
		}
		return diff;
	}

	private Set<String> loadIdsFromFile(int idColumn, String separator, String file){
		return loadIdsFromFile(idColumn, separator, file, false);
	}
	
	private Set<String> loadIdsFromFile(int idColumn, String separator, String file, boolean isOld) {
		Set<String> ids	=	new HashSet<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String sCurrentLine	=	br.readLine();
			ArrayList<String> headerList	=	new ArrayList<String>();
			String[] header		=	sCurrentLine.split(separator);
			for(String s:header){
				headerList.add(s);
			}
			this.stockPos	=	headerList.indexOf("price");
			System.out.println("price position "+stockPos);
			while ((sCurrentLine = br.readLine()) != null) {
				String[] splitted	=	sCurrentLine.split(separator);
				ids.add(splitted[idColumn]);
				if(isOld==true){
					//System.out.println("added "+splitted[idColumn]+" "+sCurrentLine);
					oldFileItems.put(splitted[idColumn], sCurrentLine);
				}
				
				System.out.println(sCurrentLine);
				
			}
			System.out.println("-----------------");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ids;
	}
	
	public static void main(String[] args) {
		String oldf	=	"/home/paride/git/paridee/prestashopcsvk/CsvGenerator/output.csv";
		String newf	=	"/home/paride/git/paridee/prestashopcsvk/CsvGenerator/output2.csv";
		DiffCsv diff	=	new DiffCsv(oldf,newf);
		ArrayList<String> diffAl	=	diff.getMissing(2,"\\|",'|'+"");
		for(String s:diffAl){
			System.out.println(s);
		}
		
	}
	
}
