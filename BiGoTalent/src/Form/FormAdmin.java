package Form;

import koneksi.Koneksi;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FormAdmin extends javax.swing.JFrame {
    DefaultTableModel model;

    public FormAdmin() {
        initComponents();
        setLocationRelativeTo(null);
        loadDataPendaftar();
    }

    public void loadDataPendaftar() {
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama Siswa");
        model.addColumn("Mata Lomba");
        model.addColumn("Status Tahapan");
        tblPendaftar.setModel(model);
        
        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "SELECT pendaftaran.id, users.username, lomba.nama_lomba, pendaftaran.status " +
                         "FROM pendaftaran " +
                         "JOIN users ON pendaftaran.user_id = users.id " +
                         "JOIN lomba ON pendaftaran.lomba_id = lomba.id";
            ResultSet r = s.executeQuery(sql);
            while (r.next()) {
                model.addRow(new Object[]{
                    r.getString("id"),
                    r.getString("username"),
                    r.getString("nama_lomba"),
                    r.getString("status")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + e.getMessage());
        }
    }

    private void btnSimpanLombaActionPerformed(java.awt.event.ActionEvent evt) {                                               
        try {
            Connection c = Koneksi.configDB();
            Statement s = c.createStatement();
            String sql = "INSERT INTO lomba (nama_lomba) VALUES ('" + txtNamaLomba.getText() + "')";
            s.executeUpdate(sql);
            JOptionPane.showMessageDialog(this, "Mata Lomba berhasil ditambahkan!");
            txtNamaLomba.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal tambah lomba: " + e.getMessage());
        }
    }                                              

    private void btnUbahStatusActionPerformed(java.awt.event.ActionEvent evt) {                                              
        int baris = tblPendaftar.getSelectedRow();
        if (baris == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data siswa di tabel terlebih dahulu!");
            return;
        }
        String idPendaftaran = model.getValueAt(baris, 0).toString();
        String statusBaru = JOptionPane.showInputDialog(this, "Masukkan Status Baru (Contoh: Tahap Briefing / Pelatihan / Selesai):");
        
        if (statusBaru != null && !statusBaru.trim().isEmpty()) {
            try {
                Connection c = Koneksi.configDB();
                Statement s = c.createStatement();
                String sql = "UPDATE pendaftaran SET status='" + statusBaru + "' WHERE id='" + idPendaftaran + "'";
                s.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "Status berhasil diperbarui!");
                loadDataPendaftar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal update status: " + e.getMessage());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtNamaLomba = new javax.swing.JTextField();
        btnSimpanLomba = new javax.swing.JButton();
        btnUbahStatus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPendaftar = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PANEL ADMIN - BI GOT TALENT");

        btnSimpanLomba.setText("Simpan Lomba");

        btnUbahStatus.setText("Ubah Status");

        tblPendaftar.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblPendaftar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamaLomba, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSimpanLomba)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUbahStatus))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNamaLomba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUbahStatus)
                    .addComponent(btnSimpanLomba))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSimpanLomba;
    private javax.swing.JButton btnUbahStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPendaftar;
    private javax.swing.JTextField txtNamaLomba;
    // End of variables declaration//GEN-END:variables
}
