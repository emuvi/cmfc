package br.com.pointel.cmfd;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class WizSwing {
    
    public static void showInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void showError(Throwable error) { 
        JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static String getStringOnClipboard() throws Exception {
        Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable clipboardContents = systemClipboard.getContents(null);
        if (clipboardContents != null) {
            try {
                if (clipboardContents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    return (String) clipboardContents.getTransferData(DataFlavor.stringFlavor);
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
    
    public static File selectFolder(File selected) {
        return select(selected, JFileChooser.DIRECTORIES_ONLY);
    }
    
    public static File selectFile(File selected) {
        return select(selected, JFileChooser.FILES_ONLY);
    }
    
    public static File select(File selected) {
        return select(selected, JFileChooser.FILES_AND_DIRECTORIES);
    }
    
    public static File select(File selected, int kind) {
        var chooser = new JFileChooser();
        chooser.setFileSelectionMode(kind);
        if (selected != null) {
            chooser.setSelectedFile(selected);
        }
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }
    
    public static void open(File file) throws Exception {
        Desktop.getDesktop().open(file);
    }
    
}
