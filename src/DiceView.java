
import java.util.LinkedList;
import javax.swing.JSpinner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mikko Paukkonen
 */
public class DiceView extends javax.swing.JFrame {

    DiceModel model;
    
    /**
     * Creates new form DiceView
     */
    public DiceView(DiceModel model) {
        this.model = model;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        first = new javax.swing.JSpinner();
        second = new javax.swing.JSpinner();
        third = new javax.swing.JSpinner();
        four = new javax.swing.JSpinner();
        five = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.FlowLayout());

        first.setModel(new javax.swing.SpinnerNumberModel(1, 1, 6, 1));
        first.setToolTipText("");
        first.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                firstStateChanged(evt);
            }
        });
        getContentPane().add(first);

        second.setModel(new javax.swing.SpinnerNumberModel(1, 1, 6, 1));
        second.setToolTipText("");
        second.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                secondStateChanged(evt);
            }
        });
        getContentPane().add(second);

        third.setModel(new javax.swing.SpinnerNumberModel(1, 1, 6, 1));
        third.setToolTipText("");
        third.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                thirdStateChanged(evt);
            }
        });
        getContentPane().add(third);

        four.setModel(new javax.swing.SpinnerNumberModel(1, 1, 6, 1));
        four.setToolTipText("");
        four.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fourStateChanged(evt);
            }
        });
        getContentPane().add(four);

        five.setModel(new javax.swing.SpinnerNumberModel(1, 1, 6, 1));
        five.setToolTipText("");
        five.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fiveStateChanged(evt);
            }
        });
        getContentPane().add(five);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void update()
    {
        int[] values = new int[5];
        int i = 0;
        
        for(JSpinner spinner : new JSpinner[]{first, second, third, four, five})
            values[i++]= (Integer)spinner.getValue();
                
        model.setDieValues(values);
    }
    
    private void firstStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_firstStateChanged
        update();
    }//GEN-LAST:event_firstStateChanged

    private void secondStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_secondStateChanged
        update();
    }//GEN-LAST:event_secondStateChanged

    private void thirdStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_thirdStateChanged
        update();
    }//GEN-LAST:event_thirdStateChanged

    private void fourStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fourStateChanged
       update();
    }//GEN-LAST:event_fourStateChanged

    private void fiveStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fiveStateChanged
        update();
    }//GEN-LAST:event_fiveStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner first;
    private javax.swing.JSpinner five;
    private javax.swing.JSpinner four;
    private javax.swing.JSpinner second;
    private javax.swing.JSpinner third;
    // End of variables declaration//GEN-END:variables
}
