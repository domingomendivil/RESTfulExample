package com.airport.rest;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import com.airport.domain.BoardingPass;
import com.airport.domain.ETicket;
import com.airport.domain.Passenger;
import com.airport.domain.TicketType;

public class PDFPrinter
{
    
	
	public void printBoardingPass(BoardingPass boardingPass) throws BoardingPassPrinterException{
    	PDDocument document = new PDDocument();
    	PDPage page = new PDPage();
    	document.addPage( page );
    	
    	PDPageContentStream contentStream;
		try {
			contentStream = new PDPageContentStream(document, page);
	    	contentStream.setLineWidth(1);
	    	Color color = Color.BLACK;
	    	drawRect(contentStream, color, new java.awt.Rectangle(20, 600, 500, 130), false);
	    	contentStream.moveTo(100,420);
	    	writeText(contentStream, "BOARDING PASS", 20, 740);
	    	writeText(contentStream, boardingPass.geteTicket().getPassenger().getCompleteName(), 25, 690);
	    	writeText(contentStream, "TERMINAL: "+boardingPass.getBoardingTerminal(), 25, 610);
	    	writeText(contentStream, "GATE: "+boardingPass.getBoardingGate(), 130, 610);
	    	writeText(contentStream, "SEAT: "+boardingPass.getSeat(), 230, 610);
	    	writeText(contentStream, "TYPE: "+boardingPass.geteTicket().getTicketType(), 330, 610);

	    	
	    	
//			contentStream.drawImage(image, 20,20);
	    	contentStream.close();
	    	document.save( "C:/Users/d0178/Desktop/helloworld.pdf");
	    	document.close(); 			
		} catch (IOException e) {
			throw new BoardingPassPrinterException(e);
		}
 	}
	
	
		
	
	
	
	
	public static void main(String[] args) throws  BoardingPassPrinterException
    {
		BoardingPass b = new BoardingPass();
		ETicket eTicket = new ETicket();
		Passenger passenger = new Passenger();
		passenger.setFirstName("DOMINGO");
		passenger.setMiddleName("JAVIER");
		passenger.setLastName1("MENDIVIL");
		passenger.setLastName2("GOYENECHE");
		
		eTicket.setPassenger(passenger);
		b.setBoardingGate("8");
		b.setBoardingTerminal("A");
		b.setSeat("19A");
		eTicket.setTicketType(TicketType.economy);
		b.seteTicket(eTicket);
		new PDFPrinter().printBoardingPass(b);
    }
    
    
    private void writeText(PDPageContentStream contentStream, String text,float x,float y) throws IOException{
    	contentStream.beginText();
    	PDFont font = PDType1Font.HELVETICA_BOLD;
    	contentStream.setFont( font, 12 );
    	contentStream.moveTextPositionByAmount(x,y);
    	contentStream.drawString( text );
    	contentStream.endText();
    	
    }
    
    private void  drawRect(PDPageContentStream content, Color color, Rectangle rect, boolean fill) throws IOException {
        content.addRect(rect.x, rect.y, rect.width, rect.height);
        if (fill) {
            content.setNonStrokingColor(color);
            content.fill();
        } else {
            content.setStrokingColor(color);
            content.stroke();
        }
    }

}