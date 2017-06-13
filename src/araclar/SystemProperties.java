/**
 * 
 */
package araclar;

import java.util.Properties;

/**
 * @author Emrah Denizer
 *
 */
public class SystemProperties {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties properties = System.getProperties();
		String[] part = properties.toString().split(",");

		for (int i = 0; i < part.length; i++)

			System.out.println(part[i]);
	}

}
