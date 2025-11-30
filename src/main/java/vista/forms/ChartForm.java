/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista.forms;

import java.awt.Color;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import org.jfree.data.category.DefaultCategoryDataset;
import modelo.dto.DataGraficDTO;
import modelo.servicio.DataGraficService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author drola
 */
public class ChartForm extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ChartForm.class.getName());

    /**
     * Creates new form ChartForm
     */
    public ChartForm() {
        initComponents();
      
    }

    private void generarChartBarrasDias() {
        try {
             DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        //DataGraficDTO objetito = new DataGraficDTO();
        DataGraficService servicio = new DataGraficService();
        List<List<DataGraficDTO>> listas = servicio.getListaDataGrafic();
        if(listas.isEmpty()) {
                } else {
                    System.out.println("objeto guardado");
                }
        for (DataGraficDTO datos : listas.get(0)) {
             
                dataSet.addValue(datos.getDias(), "1 a 5 dias", "");
                
        }
        for (DataGraficDTO datos : listas.get(1)) {
             
                dataSet.addValue(datos.getDias(), "6 a 10 dias", "");
        }
        for (DataGraficDTO datos : listas.get(2)) {
             
                dataSet.addValue(datos.getDias(), "11 a 15 dias", "");
        }
        for (DataGraficDTO datos : listas.get(3)) {
             
                dataSet.addValue(datos.getDias(), "Mas de 15 dias", "");
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Distribución de los dias de estancia", // Título del gráfico
                null, // Etiqueta del eje X
                "Cantidad", // Etiqueta del eje Y
                dataSet, // Los datos
                PlotOrientation.VERTICAL,
                true, true, false
        );
        CategoryPlot plot = chart.getCategoryPlot();


BarRenderer renderer = (BarRenderer) plot.getRenderer();


Color colorAzulMate = new Color(90, 115, 139);

renderer.setSeriesPaint(0, colorAzulMate); 
renderer.setSeriesPaint(1, Color.BLACK); 
renderer.setSeriesPaint(2, Color.PINK);
renderer.setSeriesPaint(3, Color.YELLOW); 

//plot.setBackgroundPaint(Color.WHITE);



      
        ChartPanel chartPanel = new ChartPanel(chart);
        panelPrueba.add(chartPanel);
        panelPrueba.revalidate(); // Le dice al layout manager que recalcule las dimension
        panelPrueba.repaint();    // Le dice a Swing que pinte el panel de nuev
            System.out.println("generacion exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al generar grafico"+e.getMessage());
        }
       
    }
    



private void generarChartPastelDias() {
    try {
        
        DefaultPieDataset dataSet = new DefaultPieDataset();
        
        DataGraficService servicio = new DataGraficService();
        List<List<DataGraficDTO>> listas = servicio.getListaDataGrafic();
        
      
        String[] rangos = {"1 a 5 días", "6 a 10 días", "11 a 15 días", "Más de 15 días"};
        
 
        for (int i = 0; i < listas.size() && i < rangos.length; i++) {
            List<DataGraficDTO> listaActual = listas.get(i);
            String rangoEtiqueta = rangos[i];
            
            
            int totalDiasEnRango = 0;
            for (DataGraficDTO datos : listaActual) {
                
                totalDiasEnRango += datos.getDias(); 
            }
            
          
            dataSet.setValue(rangoEtiqueta, totalDiasEnRango);
        }

      
        JFreeChart chart = ChartFactory.createPieChart(
                "Distribución de los dias de estancia", // Título del gráfico
                dataSet,                               // Los datos
                true,                                  // Mostrar leyenda 
                true,                                  // Mostrar tooltips
                false                                  // No generar URLs
        );
        
        
        PiePlot plot = (PiePlot) chart.getPlot();
        
       
        Color colorAzulMate = new Color(90, 115, 139);
        Color colorNegro = Color.BLACK;
        Color colorRosa = Color.PINK;
        Color colorAmarillo = Color.YELLOW;

       
        plot.setSectionPaint(rangos[0], colorAzulMate);
        plot.setSectionPaint(rangos[1], colorNegro);
        plot.setSectionPaint(rangos[2], colorRosa);
        plot.setSectionPaint(rangos[3], colorAmarillo);

  
        ChartPanel chartPanel = new ChartPanel(chart);
        panelPrueba.removeAll(); 
        panelPrueba.add(chartPanel);
        panelPrueba.revalidate(); 
        panelPrueba.repaint(); 
        
        System.out.println("Generación de gráfico de pastel exitosa");
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al generar gráfico: " + e.getMessage());
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroupDatos = new javax.swing.ButtonGroup();
        bgpanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        raBtnBarras = new javax.swing.JRadioButton();
        raBtnPastel = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        raBtnDias = new javax.swing.JRadioButton();
        raBtnServicioPick = new javax.swing.JRadioButton();
        raBtnEdad = new javax.swing.JRadioButton();
        raBtnSatisfaccion = new javax.swing.JRadioButton();
        btnGenerarChart = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnGenerarChart1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnGenerarChart2 = new javax.swing.JButton();
        panelPrueba = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bgpanel.setBackground(new java.awt.Color(174, 190, 213));

        jPanel2.setBackground(new java.awt.Color(174, 190, 200));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "*", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(174, 190, 200));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Generar Grafico:");

        buttonGroup1.add(raBtnBarras);
        raBtnBarras.setForeground(new java.awt.Color(0, 0, 0));
        raBtnBarras.setText("Barras");

        buttonGroup1.add(raBtnPastel);
        raBtnPastel.setForeground(new java.awt.Color(0, 0, 0));
        raBtnPastel.setText("Tipo pastel");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Datos a utilizar:");

        buttonGroupDatos.add(raBtnDias);
        raBtnDias.setForeground(new java.awt.Color(0, 0, 0));
        raBtnDias.setText("Dias estancia");

        buttonGroupDatos.add(raBtnServicioPick);
        raBtnServicioPick.setForeground(new java.awt.Color(0, 0, 0));
        raBtnServicioPick.setText("Servicio adquirido");

        buttonGroupDatos.add(raBtnEdad);
        raBtnEdad.setForeground(new java.awt.Color(0, 0, 0));
        raBtnEdad.setText("Edad cliente");

        buttonGroupDatos.add(raBtnSatisfaccion);
        raBtnSatisfaccion.setForeground(new java.awt.Color(0, 0, 0));
        raBtnSatisfaccion.setText("Nivel satisfaccion");

        btnGenerarChart.setText("Generar grafico");
        btnGenerarChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarChartActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("**Este recurso");

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ayuda a su toma ");

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("de deciciones ");

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("respecto al negocio.");

        btnGenerarChart1.setText("Atras");
        btnGenerarChart1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarChart1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Cambria", 0, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Operador : David Rolando");

        btnGenerarChart2.setText("Generar PDF");
        btnGenerarChart2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarChart2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(raBtnSatisfaccion)
                                    .addComponent(raBtnServicioPick)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(raBtnDias, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(raBtnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(raBtnPastel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(raBtnBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel7))
                                    .addComponent(btnGenerarChart)
                                    .addComponent(btnGenerarChart2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnGenerarChart1, javax.swing.GroupLayout.Alignment.LEADING))))
                        .addContainerGap(29, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(raBtnBarras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(raBtnPastel)
                .addGap(63, 63, 63)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(raBtnDias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(raBtnEdad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(raBtnServicioPick)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(raBtnSatisfaccion)
                .addGap(43, 43, 43)
                .addComponent(btnGenerarChart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btnGenerarChart2)
                .addGap(38, 38, 38)
                .addComponent(btnGenerarChart1)
                .addGap(45, 45, 45)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        panelPrueba.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelPrueba.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout bgpanelLayout = new javax.swing.GroupLayout(bgpanel);
        bgpanel.setLayout(bgpanelLayout);
        bgpanelLayout.setHorizontalGroup(
            bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
                .addContainerGap())
        );
        bgpanelLayout.setVerticalGroup(
            bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgpanelLayout.createSequentialGroup()
                .addGroup(bgpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(bgpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelPrueba, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarChartActionPerformed
        if(raBtnBarras.isSelected()){
            if (raBtnDias.isSelected()) {
                generarChartBarrasDias();             
            }
        }else if(raBtnPastel.isSelected()){
            if (raBtnDias.isSelected()) {
                generarChartPastelDias();
            }
        }
    }//GEN-LAST:event_btnGenerarChartActionPerformed

    private void btnGenerarChart1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarChart1ActionPerformed
        RegistroClienteForm ventana = new RegistroClienteForm();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnGenerarChart1ActionPerformed

    private void btnGenerarChart2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarChart2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenerarChart2ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ChartForm().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bgpanel;
    private javax.swing.JButton btnGenerarChart;
    private javax.swing.JButton btnGenerarChart1;
    private javax.swing.JButton btnGenerarChart2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroupDatos;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelPrueba;
    private javax.swing.JRadioButton raBtnBarras;
    private javax.swing.JRadioButton raBtnDias;
    private javax.swing.JRadioButton raBtnEdad;
    private javax.swing.JRadioButton raBtnPastel;
    private javax.swing.JRadioButton raBtnSatisfaccion;
    private javax.swing.JRadioButton raBtnServicioPick;
    // End of variables declaration//GEN-END:variables
}
