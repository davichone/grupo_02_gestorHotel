package vista;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import logica.GestorHotel;
import modelo.Reserva;
import logica.GeneradorHtmlReporte;

public class TablaRegistro extends javax.swing.JFrame {
    
    private GestorHotel gestor;
    private javax.swing.JButton generarDoc;

    public TablaRegistro(GestorHotel gestor) {
        initComponents();
        this.gestor = gestor;
        cargarReservasEnTabla();
    }

    private TablaRegistro() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void cargarReservasEnTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) TablaReserva.getModel();
        modeloTabla.setRowCount(0);

        ArrayList<Reserva> listaDeReservas = gestor.getListaReservas();

        for (Reserva reserva : listaDeReservas) {
            Object[] fila = new Object[5];
            fila[0] = reserva.getCliente().getNombre();
            fila[1] = reserva.getCliente().getDni();
            fila[2] = reserva.getHabitacion().getNumero();
            fila[3] = reserva.getDias();
            fila[4] = reserva.calcularImporteTotal();
            modeloTabla.addRow(fila);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaReserva = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        generarDoc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NOMBRE", "DNI", "TELEFONO", "DIAS", "IMPORTE TOTAL"
            }
        ));
        jScrollPane1.setViewportView(TablaReserva);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 724, 300));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("TABLA DE REGISTRO");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 170, -1));

        generarDoc.setText("Generar Documento");
        generarDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarDocActionPerformed(evt);
            }
        });
        jPanel1.add(generarDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 350, 170, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }

    private void generarDocActionPerformed(java.awt.event.ActionEvent evt) {                                           
        GeneradorHtmlReporte.generarReporte(gestor.getListaReservas());
    }                                          

    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(() -> new TablaRegistro().setVisible(true));
        
    }

    private javax.swing.JTable TablaReserva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
}
