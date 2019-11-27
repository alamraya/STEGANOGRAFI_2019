/*
 *----------------------------------------------------------------------------------*
 *	Code untuk melakukan filter file yang akan dipilih								*
 *	Saat browse File, akan membatasi file-file yang diizinkan untuk di pilih, dan	*
 *	File yang tidak diizinkan tidak akan ditampilkan dalam menu browse				*
 *----------------------------------------------------------------------------------*
 */

package stegano;

import javax.swing.filechooser.FileFilter;
import java.io.*;

public class jenisFile extends FileFilter
{
	public String[] s;
	public jenisFile(String[] sArg)
	{
		s = sArg;
	}
	
	public boolean accept(File fArg)
	{
		if(fArg.isDirectory())
			return true;
		
		for(int i=0; i<s.length; i++)
		{
			if(fArg.getName().toLowerCase().indexOf(s[i].toLowerCase()) > 0)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public String getDescription()
	{
		String temp = "";
		for(int i=0; i<s.length; i++)
		{
			temp += "*"+s[i]+" ";
		}
		return temp;
	}
}