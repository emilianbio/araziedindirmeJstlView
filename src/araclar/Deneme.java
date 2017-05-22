/**
 * 
 */
package araclar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;

import forms.Arac;
import forms.Kullanici;
import forms.UserRoles;
import forms.Yerler;

/**
 * @author Emrah Denizer
 *
 */
public class Deneme {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		UserRoles userRole = new UserRoles("SUPER_ADMIN");
		Kullanici islemyapan = new Kullanici("emrah.denizer", "1234", "Emrah Denizer", "Mühendis", "tarazi edindrme",
				userRole, "2013-152", 5312650065l, "emrah.denizer@tarim.gov.tr", '1', 20);

		Kullanici personel1 = new Kullanici("harun.dogru", "1234", "Harun Doğru", "Mühendis", "tarazi edindrme",
				userRole, "2013-152", 5312650065l, "emrah.denizer@tarim.gov.tr", '1', 20);

		Kullanici personel2 = new Kullanici("muharrem.cerit", "1234", "Muharrem Cerit", "Mühendis", "tarazi edindrme",
				userRole, "2013-152", 5312650065l, "emrah.denizer@tarim.gov.tr", '1', 20);

		Kullanici personel3 = new Kullanici("mehmet.ogultekin", "1234", "Mehmet Oğultekin", "Mühendis",
				"tarazi edindrme", userRole, "2013-152", 5312650065l, "emrah.denizer@tarim.gov.tr", '1', 20);
		Kullanici personel4 = new Kullanici("mehmet", "1234", "Mehmet Oğultekin", "Mühendis", "tarazi edindrme",
				userRole, "2013-152", 5312650065l, "emrah.denizer@tarim.gov.tr", '1', 20);

		Yerler ulke = new Yerler();
		ulke.setIsim("Türkiye");
		ulke.setDurum(true);
		ulke.setEklemezamani(new Date());
		ulke.setTip(null);
		Yerler il = new Yerler();
		il.setIsim("Adana");
		il.setTip(ulke);
		il.setDurum(true);
		il.setEklemezamani(new Date());
		Yerler ilce = new Yerler();
		ilce.setIsim("Seyhan");
		ilce.setTip(il);
		ilce.setDurum(true);
		ilce.setEklemezamani(new Date());
		Yerler mahalle = new Yerler();
		mahalle.setIsim("Yeşiloba");
		mahalle.setTip(ilce);
		mahalle.setDurum(true);
		mahalle.setEklemezamani(new Date());
		List<Kullanici> kullaniciList = new ArrayList<>();
		kullaniciList.add(islemyapan);
		kullaniciList.add(personel1);
		kullaniciList.add(personel2);
		kullaniciList.add(personel3);
		Arac arac1 = new Arac(islemyapan, "2017-05-05", "01R1963", null, ilce, mahalle, "09:30", "15:30", "1 deneme ",
				new Date(), kullaniciList);

		Arac arac2 = new Arac(islemyapan, "2017-05-05", "01R1963", null, ilce, mahalle, "09:30", "15:30", "2 deneme ",
				new Date(), kullaniciList);

		Arac arac3 = new Arac(islemyapan, "2017-05-05", "01R1963", null, ilce, mahalle, "09:30", "15:30", "3 deneme ",
				new Date(), kullaniciList);

		Arac arac4 = new Arac(islemyapan, "2017-05-05", "01R1963", null, ilce, mahalle, "09:30", "15:30", "4 deneme ",
				new Date(), kullaniciList);

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg2.xml").buildSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
//		session.save(userRole);
//		session.save(islemyapan);
//		session.save(personel1);
//		session.save(personel2);
//		session.save(personel3);
//		session.save(personel4);
//		session.save(ulke);
//		session.save(il);
//		session.save(ilce);
//		session.save(mahalle);
//		session.save(arac1);
//		session.save(arac2);
//		session.save(arac3);
//		session.save(arac4);
//		System.out.println("arac1: " + arac1);
//		System.out.println("arac2: " + arac2);
//		System.out.println("arac3: " + arac3);
//		System.out.println("arac4: " + arac4);
		session.getTransaction().commit();
		session.close();

	}

	@SuppressWarnings("resource")
	public static void createDoc() throws IOException {

		// Blank Document
		XWPFDocument document = new XWPFDocument();

		XWPFStyles styles = document.createStyles();

		CTFonts fonts = CTFonts.Factory.newInstance();
		fonts.setEastAsia(null);
		fonts.setHAnsi(null);
		fonts.xgetEastAsia();
		fonts.setNil();

		styles.setDefaultFonts(fonts);

		// Write the Document in file system
		FileOutputStream out = new FileOutputStream(new File("create_table.docx"));

		XWPFTable tableUstBaslik = document.createTable(5, 5);

		tableUstBaslik.setColBandSize(0);
		tableUstBaslik.setInsideHBorder(XWPFBorderType.NONE, 10, 5, "1C7331");
		tableUstBaslik.setInsideVBorder(XWPFBorderType.NONE, 10, 5, "1C7331");
		tableUstBaslik.setRowBandSize(0);

		XWPFTableRow tableRow = tableUstBaslik.getRow(0);
		tableRow.getCell(0).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
		tableRow.getCell(1).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
		tableRow.getCell(2).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
		tableRow.getCell(3).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
		tableRow.getCell(4).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");

		XWPFTableRow tableRow2 = tableUstBaslik.getRow(0);
		tableRow2.getCell(0).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
		tableRow2.getCell(1).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
		tableRow2.getCell(2).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
		tableRow2.getCell(3).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
		tableRow2.getCell(4).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");

		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.addBreak();
		run.addBreak();
		run.addBreak();

		// create table
		XWPFTable table = document.createTable();

		// create first row
		XWPFTableRow tableRowOne = table.getRow(0);
		tableRowOne.getCell(0).setText("col one, row one");
		tableRowOne.addNewTableCell().setText("col two, row one");
		tableRowOne.addNewTableCell().setText("col three, row one");

		// create second row
		XWPFTableRow tableRowTwo = table.createRow();
		tableRowTwo.getCell(0).setText("col one, row two");
		tableRowTwo.getCell(1).setText("col two, row two");
		tableRowTwo.getCell(2).setText("col three, row two");

		// create third row
		XWPFTableRow tableRowThree = table.createRow();
		tableRowThree.getCell(0).setText("col one, row three");
		tableRowThree.getCell(1).setText("col two, row three");
		tableRowThree.getCell(2).setText("col three, row three");

		document.write(out);
		out.close();
		System.out.println("create_table.docx written successully");

	}
}