package csvGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;

public class Utils {
	public static Gson gson	=	new Gson();
	public static String[] defaultCategory	=	new String[1];
	private static ArrayList<String> unknownCategories			=	new ArrayList<String>();
	private static ArrayList<String> unknownCategoriesLoader	=	new ArrayList<String>();
	
	static{
		defaultCategory[0]	=	"Varie";
	}
	
	public static void addCategory(String category, Class loader){
		unknownCategories.add(category);
		unknownCategoriesLoader.add(loader.getName());
	}
	
	
	public static boolean isKnownCategory(String category){
		return unknownCategories.contains(category);
	}
	
	public static HashMap<String,String[]> loadCategoriesFromFile(String file){
		HashMap<String,String[]>  categoryMap	=	new HashMap<String, String[]>();
		System.out.println("Reading file "+file);
		int counter	=	0;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				String[] splitted	=	sCurrentLine.split("@@@");
				System.out.print("Loaded category map item key "+splitted[0]+" value ");
				for(String s:splitted[1].split("#")){
					System.out.print(" -> "+s);
				}
				System.out.println("");
				counter++;
				categoryMap.put(splitted[0], splitted[1].split("#"));		
			}
			System.out.println("loaded "+counter+" categories");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return categoryMap;
	}
	
	public static List<String> getUnkn(){
		ArrayList<String> list	=	new ArrayList<String>();
		int i=0;
		for(String s:unknownCategories){
			list.add(unknownCategoriesLoader.get(i)+" -> "+s);
			i++;
		}
		return list;
	}
	
	public static String[] splitWithMerge(String separator,char itemDelimiter,String input){
		String[] splitted	=	input.split(separator);
		//System.out.println("split method "+splitted.length);
		ArrayList<String> retString	=	new ArrayList<String>();
		boolean enabled	=	false;
		String tempString="";
		for(int i=0;i<splitted.length;i++){
			String s	=	splitted[i];
			//System.out.println("managing string "+s);
			if(enabled==false){
				int check	=	countOcc(itemDelimiter,s);
				//System.out.println("split delimiter check "+check+" check "+check%2);
				if(check%2==0){
					retString.add(s);
				}
				else{
					enabled=true;
					tempString	=	s;
				}
			}
			else{
				tempString	=	tempString+separator+s;
				//System.out.println("temp "+s);
				if(s.contains(itemDelimiter+"")){
					enabled	=	false;
					retString.add(tempString);
					//System.out.println("Returned merged string "+tempString);
					tempString="";
				}
			}
		}
		String[] retArray	=	new String[retString.size()];
		for(int i=0;i<retString.size();i++){
			retArray[i]	=	retString.get(i);
		}
		System.out.println("returned array size "+retArray.length);
		return retArray;
	}
	
	public static int countOcc(char c,String s){
		int counter = 0;
		for( int i=0; i<s.length(); i++ ) {
		    if( s.charAt(i) == c ) {
		        counter++;
		    } 
		}
		return counter;
	}
	
}
