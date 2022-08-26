package com.example.RentalServiceProject.service;

import com.example.RentalServiceProject.model.AssetBooking;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.alignment.VerticalAlignment;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class GeneratingInvoiceService {

    @Autowired
    AssetBookingService assetBookingService;
    static Font font = new Font(Font.HELVETICA, 12, Font.BOLD);



    public void export(HttpServletResponse response,Long booking_id) throws IOException {
        AssetBooking assetBooking = assetBookingService.getAllAssetBooking().stream().filter(assetBooking1 -> assetBooking1.getId().equals(booking_id)).findAny().get();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());

        float threecol = 190f;
        float twocol = 300f;
        float twocol150 = twocol +50f;
        float twoColumnWidth[] = {twocol150,twocol};
        float fullwidth[] = {threecol};

        document.open();

        Table table = new Table(2);
        table.setBorder(Rectangle.NO_BORDER);
        table.setWidths(twoColumnWidth);
        Paragraph onesp = new Paragraph();

        Table nestedTable = new Table(2);
        nestedTable.setWidths(new float[]{twocol/2,twocol/2});

//                                                                  Cell Number 1
        Cell cell1 = new Cell(new Phrase("Stepway Website",FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE,25,Font.BOLD)));
        cell1.setBorder(Rectangle.NO_BORDER);
        table.addCell(cell1);


//                                              2ND CELL HEADER

        nestedTable.addCell(getHeaderTextCell("Invoice No "));
        nestedTable.addCell(getHeaderTextCellValue("#"+assetBooking.getId()));

        nestedTable.addCell(getHeaderTextCell("Invoice Date "));
        nestedTable.addCell(getHeaderTextCellValue(""+LocalDate.now()));


        nestedTable.setBorder(Rectangle.NO_BORDER);
        table.addCell(new Cell(new Table(nestedTable)));

        Table dividerTable = new Table(1);

        dividerTable.addCell(new Cell());
        dividerTable.setWidths(fullwidth);
        dividerTable.setBorder(Rectangle.TOP);
        dividerTable.setBorderColorTop(Color.DARK_GRAY);
        dividerTable.setBorderWidthTop(2f);

        document.add(table);
        document.add(onesp);
        document.add(dividerTable);
        document.add(onesp);
//                                                                             Booking Details Section
        Table bookingDetailsTable = new Table(2);
        bookingDetailsTable.setBorder(Rectangle.NO_BORDER);
        bookingDetailsTable.setWidths(twoColumnWidth);
        bookingDetailsTable.addCell(getUserAndBookingDetailsCell("Customer Information"));
        bookingDetailsTable.addCell(getUserAndBookingDetailsCell("Booking Information"));
        document.add(bookingDetailsTable);
//                                                                  For Row # 1
        Table userAndBookingDetailsR1 = new Table(2);
        userAndBookingDetailsR1.setBorder(Rectangle.NO_BORDER);
        userAndBookingDetailsR1.setWidths(twoColumnWidth);
        userAndBookingDetailsR1.addCell(getCell10fLeft("Name",true));
        userAndBookingDetailsR1.addCell(getCell10fLeft("Asset Name",true));

        userAndBookingDetailsR1.addCell(getCell10fLeft(""+assetBooking.getUser().getName(),false));
        userAndBookingDetailsR1.addCell(getCell10fLeft(""+assetBooking.getAsset().getName(),false));
        document.add(userAndBookingDetailsR1);
//                                                                  For Row # 2
        Table userAndBookingDetailsR2 = new Table(2);
        userAndBookingDetailsR2.setBorder(Rectangle.NO_BORDER);
        userAndBookingDetailsR2.setWidths(twoColumnWidth);
        userAndBookingDetailsR2.addCell(getCell10fLeft("Email",true));
        userAndBookingDetailsR2.addCell(getCell10fLeft("Location",true));

        userAndBookingDetailsR2.addCell(getCell10fLeft(""+assetBooking.getUser().getEmail(),false));
        userAndBookingDetailsR2.addCell(getCell10fLeft(""+assetBooking.getAsset().getAddress(),false));
        document.add(userAndBookingDetailsR2);
//                                                                  For Row # 3
        Table userAndBookingDetailsR3 = new Table(2);
        userAndBookingDetailsR3.setBorder(Rectangle.NO_BORDER);
        userAndBookingDetailsR3.setWidths(twoColumnWidth);
        userAndBookingDetailsR3.addCell(getCell10fLeft("Customer Cnic",true));
        userAndBookingDetailsR3.addCell(getCell10fLeft("Checkin Date",true));

        userAndBookingDetailsR3.addCell(getCell10fLeft(""+assetBooking.getUser().getCnic(),false));
        userAndBookingDetailsR3.addCell(getCell10fLeft(""+assetBooking.getStartDate(),false));
        document.add(userAndBookingDetailsR3);
//                                                                  For Row # 4
        Table userAndBookingDetailsR4 = new Table(2);
        userAndBookingDetailsR4.setBorder(Rectangle.NO_BORDER);
        userAndBookingDetailsR4.setWidths(twoColumnWidth);
        userAndBookingDetailsR4.addCell(getCell10fLeft("Customer ContactNumber",true));
        userAndBookingDetailsR4.addCell(getCell10fLeft("Checkout Date",true));

        userAndBookingDetailsR4.addCell(getCell10fLeft(""+assetBooking.getUser().getNumber(),false));
        userAndBookingDetailsR4.addCell(getCell10fLeft(""+assetBooking.getEndDate(),false));
        document.add(userAndBookingDetailsR4);
//                                                                  For Row # 5
        Table userAndBookingDetailsR5 = new Table(2);
        userAndBookingDetailsR5.setBorder(Rectangle.NO_BORDER);
        userAndBookingDetailsR5.setWidths(twoColumnWidth);
        userAndBookingDetailsR5.addCell(getCell10fLeft("Customer Address",true));
        userAndBookingDetailsR5.addCell(getCell10fLeft("Price",true));

        userAndBookingDetailsR5.addCell(getCell10fLeft(""+assetBooking.getUser().getAddress(),false));
        userAndBookingDetailsR5.addCell(getCell10fLeft(""+assetBooking.getPrice(),false));
        document.add(userAndBookingDetailsR5);
//                                                                 For Row # 6
        Table userAndBookingDetailsR6 = new Table(2);
        userAndBookingDetailsR6.setBorder(Rectangle.NO_BORDER);
        userAndBookingDetailsR6.setWidths(twoColumnWidth);
        userAndBookingDetailsR6.addCell(getEmptyCell());
        userAndBookingDetailsR6.addCell(getCell10fLeft("ServiceProvider Name",true));

        userAndBookingDetailsR6.addCell(getEmptyCell());
        userAndBookingDetailsR6.addCell(getCell10fLeft(""+assetBooking.getAsset().getUser().getName(),false));
        document.add(userAndBookingDetailsR6);
//                                                                 For Row # 7
        Table userAndBookingDetailsR7 = new Table(2);
        userAndBookingDetailsR7.setBorder(Rectangle.NO_BORDER);
        userAndBookingDetailsR7.setWidths(twoColumnWidth);
        userAndBookingDetailsR7.addCell(getEmptyCell());
        userAndBookingDetailsR7.addCell(getCell10fLeft("ServiceProvider Contact Number",true));

        userAndBookingDetailsR7.addCell(getEmptyCell());
        userAndBookingDetailsR7.addCell(getCell10fLeft(""+assetBooking.getAsset().getUser().getNumber(),false));
        document.add(userAndBookingDetailsR7);


//                                                                Signature Field Table
        Table signatureTable = new Table(2);
        signatureTable.setWidths(twoColumnWidth);
        signatureTable.setBorder(Rectangle.NO_BORDER);
        signatureTable.addCell(getSignatureOfCustomer());
        signatureTable.addCell(getEmptyCell());
//                                                                 Adding gaps
        document.add(addSpace(60));
        document.add(signatureTable);

        document.close();

    }
    private static Paragraph addSpace(int size)
    {

        Font LineBreak = FontFactory.getFont("Arial", size);
        Paragraph paragraph = new Paragraph("\n\n", LineBreak);
        return paragraph;

    }
    static Cell getSignatureOfCustomer(){
        Cell cell = new Cell(new Phrase("Customer Signature  ____________ ",FontFactory.getFont(FontFactory.HELVETICA,10,Font.BOLD)));
        cell.setHorizontalAlignment(HorizontalAlignment.LEFT);
        cell.setBorder(Rectangle.NO_BORDER);
        return  cell;
    }
    static Cell getEmptyCell(){
        Cell cell = new Cell();
        cell.setBorder(Rectangle.NO_BORDER);
        return  cell;
    }
    static Cell getHeaderTextCell(String textValue){
        Cell cell = new Cell(new Phrase(textValue,font));
        cell.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        cell.setBorder(Rectangle.NO_BORDER);
        return  cell;
    }
    static  Cell getHeaderTextCellValue(String textValue){
        Cell cell = new Cell(new Phrase(textValue));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(HorizontalAlignment.LEFT);
        return cell;
    }
    static Cell getUserAndBookingDetailsCell(String textValue){
        Cell cell = new Cell(new Phrase(textValue,font));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setHorizontalAlignment(HorizontalAlignment.LEFT);
        return  cell;
    }

    static Cell getCell10fLeft(String textValue,Boolean isBold){
        Cell cell;
        if(isBold){
            cell = new Cell(new Phrase(textValue,FontFactory.getFont(FontFactory.HELVETICA,10,Font.BOLD)));
            cell.setBorder(Rectangle.NO_BORDER);
        }
        else{
            cell = new Cell(new Phrase(textValue,FontFactory.getFont(FontFactory.HELVETICA,10)));
            cell.setBorder(Rectangle.NO_BORDER);
        }
        return cell;
    }

}
