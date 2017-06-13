/**
 * 
 */
package araclar;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Emrah Denizer
 *
 */
public class PingToHost {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InetAddress address = null;
		try {
			address = InetAddress.getByName("46.245.167.55");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		boolean reachable = false;
		try {
			reachable = address.isReachable(10000);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Is host reachable? " + reachable);

	}

}
