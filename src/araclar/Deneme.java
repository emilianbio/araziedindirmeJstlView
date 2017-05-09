/**
 * 
 */
package araclar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTable.XWPFBorderType;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;

/**
 * @author Emrah Denizer
 *
 */
public class Deneme {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

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