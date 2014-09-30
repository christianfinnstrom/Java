package utility;
import com.sun.jna.Platform;


public class UtilityClass {

	/**
	 * 
	 * @return A String with the DLL filename based on OS and bitness
	 */
	public static String getDLLFileName(){
		if (Platform.isWindows()){
			if (Platform.is64Bit()) return "DynamixelControl64_DLL";
			else return "DynamixelControl32_DLL";
		}
		else if(Platform.isLinux()){
			if (Platform.is64Bit()) return "DynamixelControl64_SO";
			else return "DynamixelControl32_SO";
		}
		else return null;
	}

	/**
	 * 
	 * @return OS name
	 */
	public static String getOS(){
		return System.getProperty("os.name");
	}
	
	/**
	 * 
	 * @return the bitness of the OS
	 */
	public static int getBitness(){
		return Integer.parseInt(System.getProperty("sun.arch.data.model"));
	}
	


}
