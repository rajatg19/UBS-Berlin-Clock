package com.ubs.opsit.interviews;

public class UBSBerlinClock implements TimeConverter {
	
	private static final String NEW_LINE = System.getProperty("line.separator");
	
	@Override
	public String convertTime(String aTime) {
		String l_strBerlinClockTime ="";		
		if(aTime != null) 
		{
		    String[] l_arrTime = aTime.split(":", 3);
		    if(l_arrTime.length != 3)
		    	return "Time format is not proper, Enter time in HH:MM:SS format";
		    int l_nHours=0, l_nMinutes=0,l_nSeconds = 0;
            try {
            	l_nHours = Integer.parseInt(l_arrTime[0]);
            	l_nMinutes = Integer.parseInt(l_arrTime[1]);
            	l_nSeconds = Integer.parseInt(l_arrTime[2]);
            } 
            catch (NumberFormatException ex) {
            	return "Time values should be numeric";
            }
            if(l_nHours==24)
            {
            	if(!(l_nMinutes==0 && l_nSeconds==0))
            		return "Entered Time is invalid. Time Should be more than 24:00:00";
            }
            else
            {
            	if (l_nHours < 0 || l_nHours > 23) 
            		return "Hours should be between 0 and 23";
        	
            	if (l_nMinutes < 0 || l_nMinutes > 59)
            		return "Minutes should be between 0 and 59";
            
            	if (l_nSeconds < 0 || l_nSeconds > 59) 
            		return "Seconds should be between 0 and 59";
            }

            l_strBerlinClockTime = fetchBerlinClockTime(l_nHours, l_nMinutes, l_nSeconds);       
		    return l_strBerlinClockTime;
		}
		else
		{
			return "Enter Time in HH:MM:SS format";
		}
	}
	
	
	private String fetchBerlinClockTime(int f_nHours, int f_nMinutes, int f_nSeconds) {

		StringBuilder l_strBerlinClockTime=new StringBuilder();
        l_strBerlinClockTime.append((f_nSeconds%2 == 0)?"Y":"O");//firstRow
        l_strBerlinClockTime.append(NEW_LINE);
        l_strBerlinClockTime.append(fetchRowData(f_nHours/5, 4, "R","O"));//secondRow
        l_strBerlinClockTime.append(NEW_LINE);
        l_strBerlinClockTime.append(fetchRowData(f_nHours%5, 4, "R","O"));//thirdRow
        l_strBerlinClockTime.append(NEW_LINE);
        l_strBerlinClockTime.append(fetchRowData(f_nMinutes/5, 11, "Y","O").replace("YYY", "YYR"));//fourthRow
        l_strBerlinClockTime.append(NEW_LINE);
        l_strBerlinClockTime.append(fetchRowData(f_nMinutes%5, 4, "Y","O"));////fifthRow        
        return l_strBerlinClockTime.toString();

    }

	
	 private String fetchRowData(int f_nOnLampCountInRow, int f_nTotalLampCountInRow, String f_strOnLampColor,String f_strOffLampColor ) 
	 {
        int l_nOffLampCountInRow = f_nTotalLampCountInRow - f_nOnLampCountInRow;
        StringBuilder l_strRow = new StringBuilder();
        for(int i=0;i<f_nOnLampCountInRow;i++)
        {
        	l_strRow.append(f_strOnLampColor);
        }
        for(int i=0;i<l_nOffLampCountInRow;i++)
        {
        	l_strRow.append(f_strOffLampColor);
        }	       
        return l_strRow.toString();
	 }
	  
}
