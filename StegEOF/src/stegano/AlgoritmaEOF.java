/*
 *--------------------------------------------------------------------------------------------------*	
 *	Steganografi Metode End Of File (EOF)															*
 *	Code untuk proses Encode File Cover dan File Secret yang dimasukan ke dalam File Stego, dan		*
 *	Code untuk proses Decode File Stego untuk mendapatkan File Secret								*
 *--------------------------------------------------------------------------------------------------*
 */

package stegano;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.lang.StringUtils;

public class AlgoritmaEOF {    
    
    public static final byte[] TANDA_BYTE = {'2','0','-','1','2','-','9','3'};
    public static String   message,
                           value,
                           path,
                           NamaFile,
                           extensionSecret;
    
    public static int 	PanjangMaster,
                        PanjangOutput,
                        i,
                        j,
                        PanjangPesanRahasia;
    
    public static byte byteArrayIn[];
    public static ByteArrayOutputStream byteOut;
    public static byte[] EXT_FILE;

    //deklarasi File fixSecret
    public static File fixfile = null;

    public AlgoritmaEOF() {
        System.out.println("Steganograph ready...");
    }

    public static String getMessage() {
        return message;
    }

    public static String getPath() {
        path = fixfile.getAbsolutePath();
        return path;
    }
	
    //Method Proses Embed File
    public static boolean EmbedFile(File CoverF, File StegoF, File SecretF) {
    
        //Mengambil panjang data File Cover dan File Secret
        PanjangPesanRahasia = (int) SecretF.length();
        PanjangMaster = (int) CoverF.length();             		
        try {
            
            //Mengambil nama File Secret
            NamaFile = SecretF.getName();

            //Mengambil Extension File pada Nama Secret
            extensionSecret = NamaFile.substring(NamaFile.length()-4, NamaFile.length());

            //Memanggil Method buatArrayExt untuk mengisi nilai EXT_SECRET
            buatArrayExt();

            //Instalasi variable byteOut
            byteOut = new ByteArrayOutputStream();

            //Membuat sebuah array byte panjang yang sama dengan ukuran file Cover
            byteArrayIn = new byte[PanjangMaster];

            // Membuka dan membaca semua byte array file Cover dalam byteArrayIn
            DataInputStream baca= new DataInputStream(new FileInputStream(CoverF));
            baca.read(byteArrayIn, 0, PanjangMaster);
            baca.close();

            //Menulis semua byte array dari file Cover ke dalam byteOut
            byteOut.write(byteArrayIn, 0, PanjangMaster);

            //Menulis dan menambahkan TANDA_BYTE dalam byteOut
            tulisBytes(TANDA_BYTE);
            tulisBytes(EXT_FILE);

            // Membaca data kedalam fileArray
            byte []fileArray= new byte[PanjangPesanRahasia];
            baca= new DataInputStream(new FileInputStream(SecretF));
            baca.read(fileArray, 0, PanjangPesanRahasia);
            baca.close();

            //Menulis dan menambahkan byte dari SecretF dalam byteOut
            tulisBytes(fileArray);

            //Membuat File Stego dari hasil gabungan byte-byte byteArrayIn, TANDA_BYTE, dan fileArray
            DataOutputStream fileStego = new DataOutputStream(new FileOutputStream(StegoF));
            byteOut.writeTo(fileStego);
            fileStego.close();

            AwalNull();
            }catch(EOFException e){
                }catch(Exception e){
                    message= "Oops!!\n Error: "+ e;
                    e.printStackTrace();
                    return false;
            }

            message= "The file '"+SecretF.getName()+"' has been successfully embedded inside file '"+ StegoF.getName()+ "'.";
            return true;
            }
	
    public static boolean RetrieveFile(File OutputF, File dataRahasia) {
        //Mengambil panjang data File Stego
        PanjangOutput = (int) OutputF.length();
        String valueExt="";
	try {
            
            //Instalasi variable byteOut
            byteOut = new ByteArrayOutputStream();

            //Membuat sebuah array byte panjang yang sama dengan ukuran file Stego
            byteArrayIn = new byte[PanjangOutput];

            // Membuka dan membaca semua byte array file Stego dalam byteArrayIn
//            DataInputStream baca= new DataInputStream(new FileInputStream(OutputF));
//            baca.read(byteArrayIn, 0, PanjangOutput);
//            baca.close();

            Path path = Paths.get(OutputF.getPath());
            byteArrayIn = Files.readAllBytes(path);

            //menampung nilai bit array
            String[] arrSecretString = new String[byteArrayIn.length];
            for(int i=0; i<byteArrayIn.length; i++){
                arrSecretString[i] += (char) byteArrayIn[i];
            }
            
            //menampung nilai bit array dan nilai null
            String[] arrSecretStringFix = new String[arrSecretString.length];
            for(int i=0; i<arrSecretString.length; i++){
                arrSecretStringFix[i] = arrSecretString[i].substring(4);
            }
            
            //menggabungkan nilai bit array dengan nilai null
            String SecretString = StringUtils.join(arrSecretStringFix);
            
            //Menampung isi byteArrayIn ke dalam variable value
            value = new String(byteArrayIn);

            //Memanggil Method tulisByteSecret dengan isi byteArrayIn dan mulai dari nilai value.indexOf
//            tulisBytesSecret(byteArrayIn,value.indexOf("20-12-93")+830);//b.jpg
//            tulisBytesSecret(byteArrayIn,value.indexOf("20-12-93")+329);//l7.png
//            tulisBytesSecret(byteArrayIn,value.indexOf("20-12-93")+12+39684+657+26);//panitia.png

            //Mengambil Extension File dari variable value
            String valuesecret = SecretString.substring(SecretString.indexOf("20-12-93")+8+4);
            tulissecret(valuesecret);
            
            //Mengambil Extension File dari variable value
            valueExt = value.substring(value.indexOf("20-12-93")+8, value.indexOf("20-12-93")+8+4);

            String[] extensionS = {"docx","xlsx","pptx",".doc",".xls",".ppt",".pdf"};

            //Looping untuk mencari extension File Secret yang cocok dengan variable valueExt
            for(int l=0; l<7; l++)
            {
                if(valueExt.equalsIgnoreCase(extensionS[l]))
                {
                    if(extensionS[l].substring(0,1).equals(".")){
                        fixfile = new File(dataRahasia.getAbsolutePath()+extensionS[l]);
                    }else{
                        fixfile = new File(dataRahasia.getAbsolutePath()+"."+extensionS[l]);
                }
                }
            }

            //Membuat File Secret dari hasil yang didapat dari byteOut
            DataOutputStream FileRahasia = new DataOutputStream(new FileOutputStream(fixfile));
            byteOut.writeTo(FileRahasia);
            FileRahasia.close();
            AwalNull();
            }		
                catch(EOFException e)
                {
                }
                catch(Exception e)
                {
                    message= "Oops!!\n Error: "+ e;
                    e.printStackTrace();
                    return false;
                }

                message= "The file '"+OutputF.getName()+"' has been successfully retrieved\nwith name '"+dataRahasia.getName()+"'!";
                return true;
    }	
	//Method untuk menulis byte kedalam output array (Array Stego)
	public static void tulisBytes(byte[] bytes)
	{
		int size= bytes.length;

		for(int i=0; i< size; i++)
		{
			byteOut.write(bytes[i]);
		}
	}
	
	//Method untuk menulis byte kedalam output array (Array Secret) dengan awal index byte : variable mulai
	public static void tulisBytesSecret(byte[] bytes,int mulai)
	{
		int size= bytes.length;
		for(int i=mulai; i< size; i++)
		{
			byteOut.write(bytes[i]);
		}
                for(int i=0; i<size; i++){
                    System.out.println(i+" : "+(char)bytes[i]);
                }
	}
	
        //method menulis byte ke dalam array
        public static void tulissecret(String secret){
            char[] CSecret = secret.toCharArray();
            byte[] secretbyte = new byte[CSecret.length];
            
            for(int i=0; i<CSecret.length; i++){
                secretbyte[i] = (byte) CSecret[i];
            }
            //menulis byteOut
            for(int i=0; i<secretbyte.length; i++){
                byteOut.write(secretbyte[i]);
            }
        }
        
	//Method untuk membuat isi array byte EXT_SECRET
	public static void buatArrayExt()
	{
		if(extensionSecret.equalsIgnoreCase("docx"))
		{
			EXT_FILE = new byte[] {'d','o','c','x'};
		}
		else if(extensionSecret.equalsIgnoreCase("xlsx"))
		{
			EXT_FILE = new byte[] {'x','l','s','x'};
		}
		else if(extensionSecret.equalsIgnoreCase("pptx"))
		{
			EXT_FILE = new byte[] {'p','p','t','x'};
		}
		else if(extensionSecret.equalsIgnoreCase(".pdf"))
		{
			EXT_FILE = new byte[] {'.','p','d','f'};
		}
		if(extensionSecret.equalsIgnoreCase(".doc"))
		{
			EXT_FILE = new byte[] {'.','d','o','c'};
		}
		else if(extensionSecret.equalsIgnoreCase(".xls"))
		{
			EXT_FILE = new byte[] {'.','x','l','s'};
		}
		else if(extensionSecret.equalsIgnoreCase(".ppt"))
		{
			EXT_FILE = new byte[] {'.','p','p','t'};
		}
	}
	
	public static void AwalNull()
	{
		PanjangMaster = 0;
		PanjangPesanRahasia = 0;
		PanjangOutput = 0;
		byteArrayIn = null;
		byteOut = null;
	}
}