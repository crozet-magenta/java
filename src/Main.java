// MVC based on : http://www.austintek.com/mvc/

public class Main{

	public static final double VERSION = 0.1;

	public static final String APP_TITLE = "Theme Park";


	public static void main(String[] args){

		System.out.println(APP_TITLE + " - V" + VERSION + '\n');

		Park park = Park.getInstance();


	} //main()

} //Main
