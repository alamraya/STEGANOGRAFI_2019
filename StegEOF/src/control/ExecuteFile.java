/*
 *--------------------------------------------------------------------------*
 *	Code untuk menjalankan (Run) File rahasia yang didapat dari File stego	*
 *--------------------------------------------------------------------------*
 */

package control;

import java.awt.*;
import java.io.*;

public class ExecuteFile
{   
    public ExecuteFile()
    {
    	new ExecuteFile();
    }
    
    public static void bukaFile(String path)
    {
    	try
    	{
    		Desktop desktop = null;
	        if (Desktop.isDesktopSupported())
	        {
	            desktop = Desktop.getDesktop();
	        }
	        if (!path.equals("")) {
	            desktop.open(new File(path));
	        }
    	}
    	catch(IOException e)
    	{
    		System.out.print(e.getMessage());
    	}
    }
}