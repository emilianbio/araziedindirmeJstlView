/**
 * 
 */
package controllers;


/**
 * @author Emrah Denizer
 *
 */
public class Deneme {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static int kalanİzinSayisi = 5;
	public static int toplamIzinGunSayisi = 0;

	public static void main(String[] args) {
		System.out.println(s(5));
		System.out.println(f(5));

		// Date yeniYil = new Date();
		// SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
		//
		// String newDate = sdf.format(yeniYil);
		//
		// if (newDate.equals("15/11")) {
		//
		// toplamIzinGunSayisi = kalanİzinSayisi + 20;
		//
		// System.out.println(toplamIzinGunSayisi);
		// } else {
		// System.out.println(newDate);
		// }

	}

	public static Integer kalanİzinGunSayisi(int kullanilanIzinSayisi) {

		kalanİzinSayisi -= kullanilanIzinSayisi;

		return kalanİzinSayisi;

	}

	public static int s(int n) {
		if (n == 0)
			return 0;
		return s(n - 1) + n;
	}

	public static int f(int n) {
		if (n == 0)
			return 1;
		return f(n - 1) * n;
	}
}
