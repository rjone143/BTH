package com.rachel;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CostServlet extends HttpServlet {
	
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
		out.println("<title>Beartooth Hiking Company</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action=\"cost\" method=\"post\" bgcolor=\"#FFAD00\">");
		out.println("<label>Select a Trail</label>");
		out.println("<br>");
		out.println("<input type=\"radio\" id=\"hell\" name=\"trail\" value=\"1\">");
		out.println("<label for=\"hell\">HellRoaring</label>");
		out.println("<input type=\"radio\" id=\"gardiner\" name=\"trail\" value=\"2\">");
		out.println("<label for=\"gardiner\">Gardiner</label>");
		out.println("<input type=\"radio\" id=\"beaten\" name=\"trail\" value=\"3\">");
		out.println("<label for=\"beaten\">Beaten</label>");
		out.println("<br>");
		out.println("<label for=\"duration\">Enter duration</label>");
		out.println("<select id=\\\"duration\\\" name=\\\"duration\\\"multiple size= \\\"1\\\" ");
		out.println("<option value =\\\"2\\\" ></option>");
		out.println("<option value =\\\"3\\\" ></option>");
		out.println("<option value =\\\"4\\\" ></option>");
		out.println("<option value =\\\"5\\\" ></option>");
		out.println("<option value =\\\"6\\\" ></option>");
		out.println("<option value =\\\"7\\\" ></option>");
		out.println("</select>");
		out.println("<br>");
		out.println("<label>Enter Start Date</label>");
		out.println("<br>");
		out.println("<h1>Month : </h1>");
		out.println("<select name=\"month\" multiple size= \\\"1\\\" ");
		out.println("<option value =\\\"1\\\" ></option>");
		out.println("<option value =\\\"2\\\" ></option>");
		out.println("<option value =\\\"3\\\" ></option>");
		out.println("<option value =\\\"4\\\" ></option>");
		out.println("<option value =\\\"5\\\" ></option>");
		out.println("<option value =\\\"6\\\" ></option>");
		out.println("<option value =\\\"7\\\" ></option>");
		out.println("<option value =\\\"8\\\" ></option>");
		out.println("<option value =\\\"9\\\" ></option>");
		out.println("<option value =\\\"10\\\" ></option>");
		out.println("<option value =\\\"11\\\" ></option>");
		out.println("<option value =\\\"12\\\" ></option>");
		out.println("</select>");
		
		out.println("<h1>Day :");
		out.println("<select name=\"day\" multiple size= \\\"1\\\" ");
		out.println("<option value =\\\"1\\\" ></option>");
		out.println("<option value =\\\"2\\\" ></option>");
		out.println("<option value =\\\"3\\\" ></option>");
		out.println("<option value =\\\"4\\\" ></option>");
		out.println("<option value =\\\"5\\\" ></option>");
		out.println("<option value =\\\"6\\\" ></option>");
		out.println("<option value =\\\"7\\\" ></option>");
		out.println("<option value =\\\"8\\\" ></option>");
		out.println("<option value =\\\"9\\\" ></option>");
		out.println("<option value =\\\"10\\\" ></option>");
		out.println("<option value =\\\"11\\\" ></option>");
		out.println("<option value =\\\"12\\\" ></option>");
		out.println("<option value =\\\"13\\\" ></option>");
		out.println("<option value =\\\"14\\\" ></option>");
		out.println("<option value =\\\"15\\\" ></option>");
		out.println("<option value =\\\"16\\\" ></option>");
		out.println("<option value =\\\"17\\\" ></option>");
		out.println("<option value =\\\"18\\\" ></option>");
		out.println("<option value =\\\"19\\\" ></option>");
		out.println("<option value =\\\"20\\\" ></option>");
		out.println("<option value =\\\"21\\\" ></option>");
		out.println("<option value =\\\"22\\\" ></option>");
		out.println("<option value =\\\"23\\\" ></option>");
		out.println("<option value =\\\"24\\\" ></option>");
		out.println("<option value =\\\"25\\\" ></option>");
		out.println("<option value =\\\"26\\\" ></option>");
		out.println("<option value =\\\"27\\\" ></option>");
		out.println("<option value =\\\"28\\\" ></option>");
		out.println("<option value =\\\"29\\\" ></option>");
		out.println("<option value =\\\"30\\\" ></option>");

		out.println("</select>");
		out.println("<br>");
		out.println("<h1> Year: </h1>");
		out.println("<input type=\"text\" name =\"year\" maxlength= \"4\" value = \"2020\">");
		out.println("<br>");
		out.println("<button type=\"submit\">Get Cost</button>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//Writer object 
		PrintWriter writer = response.getWriter();
		/*Getting parameter values from post request*/
		try {
			int hikeID = Integer.parseInt(request.getParameter("trail"));
			int duration = Integer.parseInt(request.getParameter("duration"));
			int day = Integer.parseInt(request.getParameter("day"));
			int month = Integer.parseInt(request.getParameter("month"));
			int year = Integer.parseInt(request.getParameter("year"));
			
			Rates rate;
			if(hikeID==1){
				rate = new Rates(Rates.HIKE.HELLROARING);
			}
			else if(hikeID==2)
			{
				rate = new Rates(Rates.HIKE.GARDINER);
			}
			else{
				rate = new Rates(Rates.HIKE.BEATEN);
			}
			//
			BookingDay start = new BookingDay(year,month,day);
			if(start.isValidDate()){
				rate.setBeginDate(start);
				if(rate.setDuration(duration))
				{
					if(rate.isValidDates()){
					 	
					 	writer.println("Estimated cost is "+ rate.getCost());
						
					 }
					 else{
					 	writer.println("The start date and duration combination you selected is not within the tour season! Please Try again.");
					 }
				}
				else{
					writer.println("You selected an invalid Trip Duration!");
				}
			}
			else {
				writer.println("The start date you selected is not within the valid range.");
			}
			
		}
		catch(NumberFormatException e){
			writer.println("Invalid data format, please enter valid data and try again! ");
		}
		
		//
		
		
	}
}
