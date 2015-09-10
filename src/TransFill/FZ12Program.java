/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TransFill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author mats
 */
public class FZ12Program {
    String entireProgram;
    private JTextArea jTAProgramArea;
    private Path currentFilePath;

    public FZ12Program(String entireProgram) {
        this.entireProgram = entireProgram;
    }

    boolean hasFile() {
        return ( entireProgram != null );
    }

    Path getCurrentDir() {
        return currentFilePath.getParent();
    }

    private void buildUsedPlaneListMainProgram() {
        StringReader reader = new StringReader(entireProgram);
        BufferedReader br = new BufferedReader(reader);
        String line;
        try {
            while((line=br.readLine())!=null)
            {
                if ( line.contains("TRANS")) {
                    System.out.println(line);
                }
                if ( line.matches(".*A[0-9.]+.*B[0-9.]+")) {
                    System.out.println(line);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(FZ12Program.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class FormatException extends Exception {
        public FormatException( String message ){
            super( message );
        }
    }
    
    
    public void readFile(String fileName){
        
        if ( fileName != null ) {

            byte[] encoded;
            try {
                encoded = Files.readAllBytes(Paths.get(fileName));
                Charset cs = Charset.forName("ISO_8859_1");
                entireProgram = cs.decode(ByteBuffer.wrap(encoded)).toString();
                jTAProgramArea.setText(entireProgram);
                currentFilePath = Paths.get(fileName);
                System.out.println("Path " + currentFilePath);
            } catch (IOException ex) {
                Logger.getLogger(TransFillMainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    void saveFile() {
        entireProgram = jTAProgramArea.getText();
        boolean okToSave = false;
        if ( Files.exists(currentFilePath) ) {
            int retVal = JOptionPane.showConfirmDialog(null, "Filen finns. Skriva över?", "Filen finns", JOptionPane.YES_NO_OPTION);
            if ( retVal == JOptionPane.YES_OPTION ) {
                okToSave = true;
            }
        } else {
            okToSave = true;
        }
        if ( okToSave ) {
            try {
                Files.write(currentFilePath, entireProgram.getBytes() );
            } catch (IOException ex) {
                JOptionPane.showConfirmDialog( null , "Kunde inte spara filen " + ex.getMessage() , "FEL!", JOptionPane.OK_OPTION);
            }
        }
    }

    void analyseMainProgram() {
        buildUsedPlaneListMainProgram();
    }

    void setTextArea(JTextArea jTAProgramArea) {
        this.jTAProgramArea = jTAProgramArea;
    }
}
