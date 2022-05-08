package anjocaido.console;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.DropMode;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OutputConsole extends JFrame {
  int threadsUsing = 0;
  
  private JScrollPane jScrollPane1;
  
  private JTextArea jTextArea1;
  
  public OutputConsole() {
    initComponents();
    this.jScrollPane1.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
          public void adjustmentValueChanged(AdjustmentEvent e) {
            e.getAdjustable().setValue(e.getAdjustable().getMaximum());
          }
        });
    pack();
    setVisible(true);
  }
  
  private void initComponents() {
    this.jScrollPane1 = new JScrollPane();
    this.jTextArea1 = new JTextArea();
    setDefaultCloseOperation(2);
    setTitle("Error Console");
    this.jScrollPane1.setAutoscrolls(true);
    this.jScrollPane1.setColumnHeaderView((Component)null);
    this.jScrollPane1.setCursor(new Cursor(0));
    this.jScrollPane1.setDebugGraphicsOptions(-1);
    this.jTextArea1.setColumns(20);
    this.jTextArea1.setEditable(false);
    this.jTextArea1.setRows(5);
    this.jTextArea1.setDropMode(DropMode.INSERT);
    this.jScrollPane1.setViewportView(this.jTextArea1);
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 636, 32767).addContainerGap()));
    layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -1, 227, 32767).addContainerGap()));
    pack();
  }
  
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            (new OutputConsole()).setVisible(true);
          }
        });
  }
  
  public void appendText(String text) {
    this.jTextArea1.append(text);
    this.jTextArea1.selectAll();
    if (this.threadsUsing <= 0)
      waitToDispose(); 
  }
  
  public void acquire() {
    this.threadsUsing++;
  }
  
  public void release() {
    this.threadsUsing--;
    if (this.threadsUsing <= 0)
      waitToDispose(); 
  }
  
  public void waitToDispose() {
    this.jTextArea1.append("\n\nThreads have Stopped... closing this window in 15 Seconds...\nIf you want to copy this, do it NOW!");
    try {
      Thread.sleep(15000L);
    } catch (InterruptedException ex) {
    
    } finally {
      dispose();
    } 
  }
}
