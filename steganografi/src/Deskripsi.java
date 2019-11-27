
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Acer
 */
public class Deskripsi extends javax.swing.JFrame {

    static String pass = "", msg = "", filename = "";
    JFileChooser fc;
    File file;
    int isCorrectPass = 1;

    public Deskripsi() {
        initComponents();
        fc = new JFileChooser();

        fc.addChoosableFileFilter(new pngFilter());
        fc.setAcceptAllFileFilterUsed(false);

        fc.setFileView(new ImageFileView());

        fc.setAccessory(new ImagePreview(fc));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        OK = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        selectfile = new javax.swing.JTextField();
        cancel = new javax.swing.JButton();
        browse = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPassword = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        preview = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 260, 160));

        OK.setText("OK");
        OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });
        getContentPane().add(OK, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 88, -1));

        jLabel1.setText("Pesan Rahasia Nya Yaitu");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 140, 165, -1));

        selectfile.setEditable(false);
        selectfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectfileActionPerformed(evt);
            }
        });
        getContentPane().add(selectfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 31, 419, 22));

        cancel.setText("Back");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        getContentPane().add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 93, -1));

        browse.setText("Browse");
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });
        getContentPane().add(browse, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 31, 100, -1));

        jLabel3.setText("Masukkan PIN");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 63, -1, -1));

        jLabel4.setText("Pilih Gambar");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("@DuaMasaChannel");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 340, 120, -1));

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 90, -1));
        getContentPane().add(jPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 190, -1));

        jLabel6.setText("Preview Gambar ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 139, 165, -1));

        jScrollPane3.setViewportView(preview);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 250, 160));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, -60, 660, 420));

        jMenu1.setText("File");

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OKActionPerformed
        try {
            // TODO add your handling code here:
            msg = decrypt(code(filename), new BigInteger("3078434453"), new BigInteger("1846993025"));
        } catch (IOException ex) {
            Logger.getLogger(Deskripsi.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (pass.equals(jPassword.getText())) {
            jTextArea1.setText(msg);
        } else {
            JOptionPane.showMessageDialog(this, "You Have Entered a Wrong PIN !");
        }
}//GEN-LAST:event_OKActionPerformed

    private void selectfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectfileActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_selectfileActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        this.setVisible(false);
}//GEN-LAST:event_cancelActionPerformed

    private void browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseActionPerformed
        // TODO add your handling code here:
        int returnVal = fc.showOpenDialog(Deskripsi.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            filename = "" + file;
            selectfile.setText(filename);

            try {
                preview.setIcon(new ImageIcon(ImageIO.read(new File(file.getPath()))));
                String coverpath = file.getPath();
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }

        }

}//GEN-LAST:event_browseActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jPassword.setText("");
        selectfile.setText("");
        jTextArea1.setText("");

    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Deskripsi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OK;
    private javax.swing.JButton browse;
    private javax.swing.JButton cancel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel preview;
    private javax.swing.JTextField selectfile;
    // End of variables declaration//GEN-END:variables
    public static BigInteger toDecimal(BigInteger t) {
        BigInteger s = new BigInteger("0"), rem, two = new BigInteger("2");
        BigInteger i = new BigInteger("1");
        while (t.compareTo(new BigInteger("0")) != 0) {
            rem = t.remainder(new BigInteger("10"));

            s = s.add(rem.multiply(i));

            i = i.multiply(new BigInteger("2"));

            t = t.divide(new BigInteger("10"));
        }
        return s;
    }

    public static String decrypt(String code, BigInteger n, BigInteger d) {
        String t = new String("");
        String binary[] = new String[1000000], msg1 = new String("");
        BigInteger decry[] = new BigInteger[1000000];
        BigInteger encry[] = new BigInteger[1000000];
        int j = -1;
        for (int i = 0; i < code.length(); i++) {
            if (i % 4 == 0) {
                j++;
                binary[j] = "";
            }
            t = Long.toBinaryString((long) (code.charAt(i)));
            for (int k = t.length(); k < 8; k++) {
                t = "0" + t;
            }
            binary[j] = binary[j] + t;
        }
      
        for (int i = 0; i <= j; i++) {
            int a = 5;
            if (binary[i].equals("")) {
                break;
            }
           

            BigInteger t1 = new BigInteger(binary[i], 10);
            encry[i] = toDecimal(t1);
            decry[i] = encry[i].modPow(d, n);
           
            binary[i] = Long.toBinaryString(decry[i].longValue());
            for (int pp = binary[i].length(); pp < 32; pp++) {
                binary[i] = "0" + binary[i];
            }
         
            for (int k = 0; k < 25; k += 8) {
                if (binary[i].substring(k, k + 8).equals("00000000")) {
                    continue;
                }
                msg1 += (char) Integer.parseInt(binary[i].substring(k, k + 8), 2);

            }
       
        }

        return msg1;

    }

    public static int coprime(int n) {
        int i;
        for (i = 2; i <= n / 2; i++) {
            if (n % i != 0) {
                break;
            }
        }
        return (i);
    }

    public static String code(String pngfile) throws IOException {
        BufferedImage image = null;
        try {
            File file = new File(pngfile);
            image = ImageIO.read(file);
        } catch (IIOException e) {
           
        }
        BufferedImage img = null;
        int w = image.getWidth();
        int h = image.getHeight();
        int pixels[] = new int[w * h];
        try {
            PixelGrabber pg = new PixelGrabber(image, 0, 0, w, h, pixels, 0, w);
            pg.grabPixels();
        } catch (Exception es) {
          
        }
        int p = pixels[0];
        int b = 0xffffff & (p);
        String bin = "";
        
        int nh = h;
        if (h % 2 == 0) {
            nh = h - 1;
        }
        int incre_w = coprime(w);
        int incre_h = coprime(nh);
        int k = incre_w;
        int l = incre_h;
        p = pixels[l * w + k];
        int b1 = 0xff & (p);
        k = k + incre_w;
        l = l + incre_h;
        for (int j = 0; j < b; j++) {
            p = pixels[l * w + k];
            int m = 0xff & (p);
            if (m % 2 == 0) {
                bin = bin + '0';
            } else {
                bin = bin + '1';
            }
         
            k = k + incre_w;
            l = l + incre_h;
            if (k >= w) {
                k -= w;
            }
            if (l >= nh) {
                l -= nh;
            }
        }
      
        String coded = "";
        pass = "";
        String bin1 = bin.substring(b - b1, b);
        bin = bin.substring(0, b - b1);
        k = bin.length();
        for (int j = 0; j < k; j += 8) {
            coded = coded + (char) Integer.parseInt(bin.substring(j, j + 8), 2);
        }
        k = bin1.length();
        for (int j = 0; j < k; j += 8) {
            pass = pass + (char) Integer.parseInt(bin1.substring(j, j + 8), 2);
        }
        pass = decrypt(pass, new BigInteger("3078434453"), new BigInteger("1846993025"));
        return (coded);
    }
}
