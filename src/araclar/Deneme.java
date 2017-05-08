/**
 * 
 */
package araclar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * @author Emrah Denizer
 *
 */
public class Deneme {

	  public static void main(String[] args) throws IOException {
	        XWPFDocument doc = new XWPFDocument();

	        XWPFParagraph p = doc.createParagraph();

	        XWPFRun r = p.createRun();
	        r.setText("Some Text");
	        r.setBold(true);
	        r = p.createRun();
	        r.setText("Goodbye");

	        // create header/footer functions insert an empty paragraph
	        XWPFHeader head = doc.createHeader(HeaderFooterType.DEFAULT);
	        head.createParagraph().createRun().setText("header");
	        
	        XWPFFooter foot = doc.createFooter(HeaderFooterType.DEFAULT);
	        foot.createParagraph().createRun().setText("footer");
	        
	        OutputStream os = new FileOutputStream(new File("header2.docx"));
	        doc.write(os);
	        os.close();
	        doc.close();
	    }
	

	/*
	 * @SuppressWarnings("resource") public static void main(String[] args)
	 * throws IOException { test();
	 * 
	 * // Blank Document XWPFDocument document = new XWPFDocument();
	 * 
	 * // Write the Document in file system FileOutputStream out = new
	 * FileOutputStream(new File("create_table.docx"));
	 * 
	 * XWPFTable tableUstBaslik = document.createTable(5, 5);
	 * tableUstBaslik.setColBandSize(0);
	 * tableUstBaslik.setInsideHBorder(XWPFBorderType.NONE, 10, 5, "1C7331");
	 * tableUstBaslik.setInsideVBorder(XWPFBorderType.NONE, 10, 5, "1C7331");
	 * tableUstBaslik.setRowBandSize(0);
	 * 
	 * XWPFTableRow tableRow = tableUstBaslik.getRow(0);
	 * tableRow.getCell(0).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
	 * tableRow.getCell(1).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
	 * tableRow.getCell(2).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
	 * tableRow.getCell(3).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
	 * tableRow.getCell(4).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
	 * 
	 * XWPFTableRow tableRow2 = tableUstBaslik.getRow(0);
	 * tableRow2.getCell(0).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
	 * tableRow2.getCell(1).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
	 * tableRow2.getCell(2).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
	 * tableRow2.getCell(3).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
	 * tableRow2.getCell(4).setText("GIDA TARIM VE HAYVANCILIK BAKANLIĞI");
	 * 
	 * XWPFParagraph paragraph = document.createParagraph(); XWPFRun run =
	 * paragraph.createRun(); run.addBreak(); run.addBreak(); run.addBreak();
	 * 
	 * // create table XWPFTable table = document.createTable();
	 * 
	 * // create first row XWPFTableRow tableRowOne = table.getRow(0);
	 * tableRowOne.getCell(0).setText("col one, row one");
	 * tableRowOne.addNewTableCell().setText("col two, row one");
	 * tableRowOne.addNewTableCell().setText("col three, row one");
	 * 
	 * // create second row XWPFTableRow tableRowTwo = table.createRow();
	 * tableRowTwo.getCell(0).setText("col one, row two");
	 * tableRowTwo.getCell(1).setText("col two, row two");
	 * tableRowTwo.getCell(2).setText("col three, row two");
	 * 
	 * // create third row XWPFTableRow tableRowThree = table.createRow();
	 * tableRowThree.getCell(0).setText("col one, row three");
	 * tableRowThree.getCell(1).setText("col two, row three");
	 * tableRowThree.getCell(2).setText("col three, row three");
	 * 
	 * document.write(out); out.close();
	 * System.out.println("create_table.docx written successully"); }
	 * 
	 * @SuppressWarnings("static-access") public static void test() throws
	 * IOException {
	 * 
	 * XWPFDocument sampleDoc = new XWPFDocument(); XWPFHeaderFooterPolicy
	 * policy = sampleDoc.getHeaderFooterPolicy(); if (policy.getDefaultHeader()
	 * == null && policy.getFirstPageHeader() == null &&
	 * policy.getDefaultFooter() == null) { // Need to create some new headers
	 * // The easy way, gives a single empty paragraph XWPFHeader headerD =
	 * policy.createHeader(policy.DEFAULT); ((XWPFParagraph)
	 * headerD.getParagraphs()).createRun().setText("Hello Header World!");
	 * 
	 * // Or the full control way CTP ctP1 = CTP.Factory.newInstance(); CTR ctR1
	 * = ctP1.addNewR(); CTText t = ctR1.addNewT();
	 * t.setStringValue("Paragraph in header");
	 * 
	 * XWPFParagraph p1 = new XWPFParagraph(ctP1, sampleDoc); XWPFParagraph[]
	 * pars = new XWPFParagraph[1]; pars[0] = p1;
	 * 
	 * policy.createHeader(policy.FIRST, pars); } else { // Already has a
	 * header, change it }
	 * System.out.println("_test1_header.docx oluşturuldu....");
	 * 
	 * }
	 */
}
