package csvGenerator;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpFileDownloader {
	public static String downloadFile(String user,String pass, int port, String server ,String file){
		String retString	=	"";
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(server, port);
			ftp.login(user, pass);
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			InputStream in;
			
			
			ftp.changeWorkingDirectory("/");		
			FTPFile[] files	=	ftp.listFiles();
			FTPFile target	=	null;
			for(FTPFile f:files){
				System.out.println(f.getName());
				if(f.getName().equals(file)){
					System.out.println("target found");
					target	=	f;
				}
			}
			
			
			in = ftp.retrieveFileStream(target.getName());
	        Scanner sc = new Scanner(in);
	        int count	=	0;
            while (sc.hasNextLine()){
            	System.out.println("Read rows: "+count++);
	        	retString	=retString+"\n"+sc.nextLine();   
	           }
	           sc.close();
	           in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retString;
	}
	
	public static void main(String[] args) {
		String file	=	downloadFile("bbuEDB3fKMPb","pu9kMbkg7U",21,"www.dropshippers.com.es","bigbuy_it.csv");
		String[] lines = file.split("\n");
		
		System.out.println("\\n? "+file.contains("\n"));
		System.out.println("\\r? "+file.contains("\r"));
		String[] testSplit1	=	file.split("\n");
		System.out.println("Test1 split "+testSplit1.length);
		//for(String line:lines){
		//	System.out.println("Line "+line);
		//}
		//System.out.print(file);
		System.out.println("length "+file.length());
		
	}
}
