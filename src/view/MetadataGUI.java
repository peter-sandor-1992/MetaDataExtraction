package view;

import control.Controller;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.ArticleHeader;

/**
 * Provides a graphical interface for using the meta data predictor.
 * It has two main functionalities:
 * 1. extracts meta data from a given PDF article
 * 2. saves the data in an XML file
 * @author Peter Sandor
 */
public class MetadataGUI extends javax.swing.JFrame {

    private final Controller controller;
    
    public MetadataGUI() {
        initComponents();
        controller = new Controller(this);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblAuthor = new javax.swing.JLabel();
        lblAffiliation = new javax.swing.JLabel();
        lblPhone = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblAbstract = new javax.swing.JLabel();
        lblWeb = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblKeywords = new javax.swing.JLabel();
        tfTitle = new javax.swing.JTextField();
        tfAuthor = new javax.swing.JTextField();
        tfAffiliation = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        tfAddress = new javax.swing.JTextField();
        tfPhone = new javax.swing.JTextField();
        tfWeb = new javax.swing.JTextField();
        tfDate = new javax.swing.JTextField();
        spAbstract = new javax.swing.JScrollPane();
        taAbstract = new javax.swing.JTextArea();
        tfKeyword = new javax.swing.JTextField();
        lblPDFArticle = new javax.swing.JLabel();
        lblXMLDatabase = new javax.swing.JLabel();
        tfPDFArticle = new javax.swing.JTextField();
        tfXMLDatabase = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        btnPredict = new javax.swing.JButton();
        btnPDFArticle = new javax.swing.JButton();
        btnXMLDatabase = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Meta data extraction");
        setMinimumSize(new java.awt.Dimension(600, 560));

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblTitle.setText("Title");

        lblAuthor.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblAuthor.setText("Author");

        lblAffiliation.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblAffiliation.setText("Affiliation");

        lblPhone.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblPhone.setText("Phone");

        lblAddress.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblAddress.setText("Address");

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblEmail.setText("E-mail");

        lblAbstract.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblAbstract.setText("Abstract");

        lblWeb.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblWeb.setText("Web");

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblDate.setText("Date");

        lblKeywords.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblKeywords.setText("Keywords");

        taAbstract.setColumns(20);
        taAbstract.setLineWrap(true);
        taAbstract.setRows(5);
        taAbstract.setWrapStyleWord(true);
        spAbstract.setViewportView(taAbstract);

        lblPDFArticle.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblPDFArticle.setText("PDF article");

        lblXMLDatabase.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblXMLDatabase.setText("XML database");

        btnSave.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSave.setText("Save to database");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnPredict.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnPredict.setText("Predict header");
        btnPredict.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPredictActionPerformed(evt);
            }
        });

        btnPDFArticle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/browse.png"))); // NOI18N
        btnPDFArticle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFArticleActionPerformed(evt);
            }
        });

        btnXMLDatabase.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/browse.png"))); // NOI18N
        btnXMLDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXMLDatabaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblKeywords, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAbstract, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDate, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblWeb, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblPhone, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAddress, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAuthor, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblAffiliation, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfKeyword)
                            .addComponent(tfAuthor)
                            .addComponent(tfAffiliation)
                            .addComponent(tfTitle)
                            .addComponent(tfEmail)
                            .addComponent(tfAddress)
                            .addComponent(tfPhone)
                            .addComponent(tfWeb)
                            .addComponent(tfDate)
                            .addComponent(spAbstract)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblXMLDatabase)
                            .addComponent(lblPDFArticle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfXMLDatabase, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                            .addComponent(tfPDFArticle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPDFArticle, javax.swing.GroupLayout.PREFERRED_SIZE, 34, Short.MAX_VALUE)
                            .addComponent(btnXMLDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPredict, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAuthor)
                    .addComponent(tfAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAffiliation)
                    .addComponent(tfAffiliation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail)
                    .addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress)
                    .addComponent(tfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhone)
                    .addComponent(tfPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblWeb)
                    .addComponent(tfWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDate)
                    .addComponent(tfDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAbstract)
                    .addComponent(spAbstract, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKeywords)
                    .addComponent(tfKeyword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPDFArticle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPDFArticle)
                        .addComponent(tfPDFArticle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPredict)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblXMLDatabase)
                        .addComponent(tfXMLDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSave))
                    .addComponent(btnXMLDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPredictActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPredictActionPerformed
        controller.predictHeader(tfPDFArticle.getText());
    }//GEN-LAST:event_btnPredictActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        saveArticle();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnPDFArticleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFArticleActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(("PDF files"), "pdf"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            tfPDFArticle.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btnPDFArticleActionPerformed

    private void btnXMLDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXMLDatabaseActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(("XML files"), "xml"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            tfXMLDatabase.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }//GEN-LAST:event_btnXMLDatabaseActionPerformed

    /**
     * Creates an article header using the data in the text fields and sends it
     * to the controller to be written in an XML file.
     */
    public void saveArticle() {
        ArticleHeader header = new ArticleHeader();
        
        header.setValue("title", tfTitle.getText());
        header.setValue("author", tfAuthor.getText());
        header.setValue("affiliation", tfAffiliation.getText());
        header.setValue("email", tfEmail.getText());
        header.setValue("address", tfAddress.getText());
        header.setValue("phone", tfPhone.getText());
        header.setValue("web", tfWeb.getText());
        header.setValue("date", tfDate.getText());
        header.setValue("abstract", taAbstract.getText());
        header.setValue("keyword", tfKeyword.getText());
        
        controller.saveToXML(tfXMLDatabase.getText(), header);
    }
    
    /**
     * Fills the text fields of the GUI by getting the values of an article header.
     * @param header the article header
     */
    public void fillMetadata(ArticleHeader header) {
        tfTitle.setText(header.getValue("title"));
        tfAuthor.setText(header.getValue("author"));
        tfAffiliation.setText(header.getValue("affiliation"));
        tfEmail.setText(header.getValue("email"));
        tfAddress.setText(header.getValue("address"));
        tfPhone.setText(header.getValue("phone"));
        tfWeb.setText(header.getValue("web"));
        tfDate.setText(header.getValue("date"));
        taAbstract.setText(header.getValue("abstract"));
        tfKeyword.setText(header.getValue("keyword"));
    }
    
    /**
     * Displays an error message in a message box. If the problem is fatal,
     * the application will be closed.
     * @param errorMessage the message to be shows
     * @param exitApp if true, the application will be closed
     */
    public void showErrorDialog(String errorMessage, boolean exitApp) {
        javax.swing.JOptionPane.showMessageDialog(this, errorMessage, "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        if (exitApp) {
            System.exit(1);
        }
    }
    
    public static void main(String args[]) throws IOException, ClassNotFoundException {        
        /* Set the Nimbus look and feel 
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println("Exception at look & fell initialization: " + ex);
        }

        LoadingForm loadingForm = new LoadingForm();
        new MetadataGUI().setVisible(true);
        loadingForm.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPDFArticle;
    private javax.swing.JButton btnPredict;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnXMLDatabase;
    private javax.swing.JLabel lblAbstract;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblAffiliation;
    private javax.swing.JLabel lblAuthor;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblKeywords;
    private javax.swing.JLabel lblPDFArticle;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblWeb;
    private javax.swing.JLabel lblXMLDatabase;
    private javax.swing.JScrollPane spAbstract;
    private javax.swing.JTextArea taAbstract;
    private javax.swing.JTextField tfAddress;
    private javax.swing.JTextField tfAffiliation;
    private javax.swing.JTextField tfAuthor;
    private javax.swing.JTextField tfDate;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfKeyword;
    private javax.swing.JTextField tfPDFArticle;
    private javax.swing.JTextField tfPhone;
    private javax.swing.JTextField tfTitle;
    private javax.swing.JTextField tfWeb;
    private javax.swing.JTextField tfXMLDatabase;
    // End of variables declaration//GEN-END:variables
}