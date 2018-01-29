package csvGenerator;

public class ProveMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String testString	=	"prova \" ";
		String testRegex	=	"koiojkok1232344";
		System.out.println(testRegex.replaceAll("[^\\d.]", ""));
		System.out.println(testString);
		int value	=	Utils.countOcc('\"', testString);
		System.out.println(value);
	}

}
