/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form;

import Entity.Movie;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

enum DialogType {CREATE, UPDATE}

public class frmMovie extends javax.swing.JPanel {
    JFileChooser fileChooser = new JFileChooser();
    JFileChooser videoChooser = new JFileChooser();
    int rowSelected = -1;
    String pathImage = "";
    String pathVideo = "";
    String oldPathImage= "";
    String oldPathVideo = "";
    DialogType dialogType;
    int movieId;
    
    public frmMovie() throws SQLException, SQLException, ClassNotFoundException {
        try {        
            initComponents();
            loadDataToTable();
            ((JSpinner.DefaultEditor) hourInput.getEditor()).getTextField().setEditable(false);
            ((JSpinner.DefaultEditor) minuteInput.getEditor()).getTextField().setEditable(false);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDataToTable() throws SQLException, ClassNotFoundException {
        // Lấy danh sách sách
        List<Movie> movies = DAO.DAO_Movie.getAll();
        //Thêm các tiêu đề cho cột của bảng hiện thị
        DefaultTableModel tableMovie = new DefaultTableModel();
        tableMovie.addColumn("Id");
        tableMovie.addColumn("Name");
        tableMovie.addColumn("Description");
        tableMovie.addColumn("Duration");
        // add lần lượt các hàng
        for (Movie _movie : movies){
            Vector<String> row = new Vector<String>();
            row.addElement(String.valueOf(_movie.getId()));
            row.addElement(_movie.getName());
            row.addElement(_movie.getDescription());
            row.addElement(_movie.getDuration());
            tableMovie.addRow(row);
        }
        this.tableMovie.setModel(tableMovie);
    }
    
    private void loadDataToControl() throws SQLException, ClassNotFoundException, IOException {
        if (tableMovie.getSelectedRow() != -1) {
            int index = tableMovie.getSelectedRow();
            List<Movie> movies = DAO.DAO_Movie.getAll();
            Movie _movie = movies.get(index);
            movieId = _movie.getId();
            nameInput.setText(_movie.getName());
            descriptionInput.setText(_movie.getDescription());
            hourInput.setValue(_movie.getHour());
            minuteInput.setValue(_movie.getMinute());
            BufferedImage img = ImageIO.read(new File(_movie.getImage()));
            Image dimg = img.getScaledInstance(360, 150, Image.SCALE_SMOOTH);
            ImageIcon imageIcon = new ImageIcon(dimg);
            preview.setIcon(imageIcon);
            fileChooser.setSelectedFile(new File(_movie.getImage()));
            oldPathImage = _movie.getImage();
            oldPathVideo = _movie.getVideoTrailer();
        }
    }
    
    private boolean checkNull() {
        if(nameInput.getText().compareTo("") == 0 || descriptionInput.getText().compareTo("") == 0){
            JOptionPane.showMessageDialog(dialogMovie, "Fill the form!", "Error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
    
    private void clearControl() {
        nameInput.setText("");
        descriptionInput.setText("");
        hourInput.setValue(0);
        minuteInput.setValue(0);
        fileChooser.setSelectedFile(new File(""));
        preview.setIcon(new ImageIcon());
        pathImage = "";
        pathVideo = "";
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dialogMovie = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        formTitle = new javax.swing.JLabel();
        nameInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionInput = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        hourInput = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        minuteInput = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        preview = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMovie = new org.jdesktop.swingx.JXTable();
        btnUpdateMovie = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        dialogMovie.setPreferredSize(new java.awt.Dimension(400, 700));
        dialogMovie.setSize(new java.awt.Dimension(400, 700));
        dialogMovie.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                dialogMovieWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                dialogMovieWindowClosing(evt);
            }
        });

        jPanel2.setPreferredSize(new java.awt.Dimension(400, 700));

        formTitle.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        formTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        formTitle.setText("Form Title");

        jLabel3.setText("Name");

        jLabel4.setText("Description");

        descriptionInput.setColumns(20);
        descriptionInput.setRows(5);
        jScrollPane1.setViewportView(descriptionInput);

        jLabel5.setText("Duration");

        hourInput.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));

        jLabel6.setText("hours");

        minuteInput.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));

        jLabel7.setText("minutes");

        jButton2.setText("Select image");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        preview.setPreferredSize(new java.awt.Dimension(400, 120));

        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 102, 102));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Cancel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(formTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameInput)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(hourInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minuteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addComponent(jButton2)
                            .addComponent(jLabel4))
                        .addGap(0, 171, Short.MAX_VALUE))
                    .addComponent(preview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(formTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(hourInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(minuteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(preview, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dialogMovieLayout = new javax.swing.GroupLayout(dialogMovie.getContentPane());
        dialogMovie.getContentPane().setLayout(dialogMovieLayout);
        dialogMovieLayout.setHorizontalGroup(
            dialogMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogMovieLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );
        dialogMovieLayout.setVerticalGroup(
            dialogMovieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogMovieLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setPreferredSize(new java.awt.Dimension(1100, 600));

        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 600));

        jButton1.setText("Create");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tableMovie.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableMovie);

        btnUpdateMovie.setText("Update");
        btnUpdateMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateMovieActionPerformed(evt);
            }
        });

        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdateMovie)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnUpdateMovie)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dialogType = DialogType.CREATE;
        formTitle.setText("Create movie");
        dialogMovie.setVisible(true);
        dialogMovie.setLocationRelativeTo(this);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void dialogMovieWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogMovieWindowClosing
        clearControl();
    }//GEN-LAST:event_dialogMovieWindowClosing

    private void btnUpdateMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateMovieActionPerformed
        if (tableMovie.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Select a record", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            dialogType = DialogType.UPDATE;
            formTitle.setText("Update movie");
            loadDataToControl();
            dialogMovie.setVisible(true);
            dialogMovie.setLocationRelativeTo(this);
        } catch (SQLException ex) {
            Logger.getLogger(frmMovie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmMovie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(frmMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnUpdateMovieActionPerformed

    private void dialogMovieWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dialogMovieWindowClosed
        clearControl();
    }//GEN-LAST:event_dialogMovieWindowClosed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (tableMovie.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Select a record", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int index = tableMovie.getSelectedRow();
            List<Movie> lstMuon = DAO.DAO_Movie.getAll();
            Movie _movie = lstMuon.get(index);
            DAO.DAO_Movie.deleteMovie(_movie.getId());
            new File(_movie.getImage()).delete();
            JOptionPane.showMessageDialog(dialogMovie, "Successfully!");
            loadDataToTable();
        } catch (SQLException ex) {
            Logger.getLogger(frmMovie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(frmMovie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dialogMovie.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (!checkNull()) {
            try {
                if(dialogType == DialogType.CREATE) {
                    DAO.DAO_Movie.createMovie(new Movie(nameInput.getText(), descriptionInput.getText(), pathImage, (Integer) hourInput.getValue(), (Integer) minuteInput.getValue(), pathVideo));
                    ImageIO.write(ImageIO.read(fileChooser.getSelectedFile()), "jpg", new File(pathImage));
                } else {
                    Movie _movie = new Movie(nameInput.getText(), descriptionInput.getText(), pathImage.equals("") ? oldPathImage : pathImage, (Integer) hourInput.getValue(), (Integer) minuteInput.getValue(), pathVideo.equals("") ? oldPathVideo : pathVideo);
                    _movie.setId(movieId);
                    if (!pathImage.equals("")) {
                        new File(oldPathImage).delete();
                        ImageIO.write(ImageIO.read(fileChooser.getSelectedFile()), "jpg", new File(pathImage));
                    }
                    //                    if (!pathVideo.equals("")) {
                        //                        new File(oldPathVideo).delete();
                        //                        ImageIO.write(ImageIO.read(videoChooser.getSelectedFile()), "mav", new File(pathVideo));
                        //                    }
                    DAO.DAO_Movie.updateMovie(_movie);
                }
                JOptionPane.showMessageDialog(dialogMovie, "Successfully!");
                loadDataToTable();
                dialogMovie.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(frmMovie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(frmMovie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(frmMovie.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(frmMovie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image", "jpg", "png");
        fileChooser.setFileFilter(imageFilter);
        fileChooser.setMultiSelectionEnabled(false);

        if (fileChooser.showDialog(dialogMovie, "Select image") == JFileChooser.APPROVE_OPTION) {
            String imageName = fileChooser.getSelectedFile().getName().replace(".", "_" + new Date().getTime() + ".");
            pathImage = "C:/Users/TIEN DUNG/OneDrive/Máy tính/QuanLyDatVe/src/Images/" + imageName;
            BufferedImage img;
            try {
                img = ImageIO.read(new File(fileChooser.getSelectedFile().getAbsolutePath()));
                Image dimg = img.getScaledInstance(preview.getWidth(), preview.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(dimg);
                preview.setIcon(image);
            } catch (IOException ex) {
                Logger.getLogger(frmMovie.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdateMovie;
    private javax.swing.JTextArea descriptionInput;
    private javax.swing.JDialog dialogMovie;
    private javax.swing.JLabel formTitle;
    private javax.swing.JSpinner hourInput;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner minuteInput;
    private javax.swing.JTextField nameInput;
    private javax.swing.JLabel preview;
    private org.jdesktop.swingx.JXTable tableMovie;
    // End of variables declaration//GEN-END:variables
}
