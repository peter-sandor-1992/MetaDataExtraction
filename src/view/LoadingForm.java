package view;

/**
 * Displays a un-closable message box.
 * @author Peter Sandor
 */
public class LoadingForm extends javax.swing.JFrame {
    
    public LoadingForm() {
        add(new javax.swing.JLabel("Please wait while the model is being loaded..."));
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Loading");
        setResizable(false);
        setType(Type.UTILITY);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}