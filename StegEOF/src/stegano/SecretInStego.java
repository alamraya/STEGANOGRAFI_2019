/*
 *--------------------------------------------------------------------------------------------------*
 *	Code untuk pengecekan pertama sebelum melakukan Retrieve File									*
 *	File Stego yang di pilih, akan terlebih dahulu dicek ketersediaan File rahasia					*
 *	Disini akan memberikan Informasi kepada Class EOF_Retrieve mengenai ketersediaan File rahasia	*
 *--------------------------------------------------------------------------------------------------*
 */

package stegano;

import java.io.*;

public class SecretInStego
{
    public static String check,
                         valueCheck;

    public static byte byteArray[];
    public static int  panjangCheck,
                        tanda;

    public SecretInStego()
    {
            new SecretInStego();
    }

    //Method GET hasil variable check
    public static String getCheck()
    {
            return check;
    }

    //Method GET hasil variable tanda
    public static int getTanda()
    {
            return tanda;
    }

    public static boolean setCheck(File StegoF)
    {
    panjangCheck = (int) StegoF.length();

    try
    {
        //Membuat sebuah array byte panjang yang sama dengan ukuran file Stego
        byteArray = new byte[panjangCheck];

        // Membuka dan membaca semua byte array file Stego dalam byteArrayIn
        DataInputStream bacaCheck= new DataInputStream(new FileInputStream(StegoF));
        bacaCheck.read(byteArray, 0, panjangCheck);
        bacaCheck.close();

        //Menampung isi byteArrayIn ke dalam variable value
        valueCheck = new String(byteArray);

        //Kondisi ketersediaan TANDA_BYTE
        if(valueCheck.contains("20-12-93"))
        {
                //Isi variable check dan tanda
                check = "TERSEDIA";
                tanda = valueCheck.length() - valueCheck.indexOf("20-12-93")+8;	
        }
        else
        {
                check = "TIDAK TERSEDIA";
                tanda = 0;	
        }

        }
        catch(EOFException e)
        {
        }
        catch(Exception e)
        {
                e.printStackTrace();
                return false;
        }

        return true;
	}
}