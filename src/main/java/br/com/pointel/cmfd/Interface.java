package br.com.pointel.cmfc;

import com.formdev.flatlaf.FlatDarculaLaf;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Interface extends javax.swing.JFrame {

    private final AtomicBoolean capture = new AtomicBoolean(false);
    private final List<String> parted = new ArrayList<>();

    public Interface() {
        initComponents();
        startCapturer();
    }

    private void startCapturer() {
        new Thread("CMFC Capturer") {
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(500);
                        if (capture.get()) {
                            String testing = WizSwing.getStringOnClipboard();
                            if (testing != null) {
                                testing = testing.replaceAll("\\s+", " ");
                                final String clipboard = testing.trim();
                                if (!clipboard.isEmpty()) {
                                    SwingUtilities.invokeAndWait(() -> {
                                        if (!jtfClipboard.getText().equals(clipboard)) {
                                            jtfClipboard.setText(clipboard);
                                        }
                                    });
                                }
                            }
                        }
                    } catch (Exception e) {
                        WizSwing.showError(e);
                    }
                }
            }
        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbTitle = new javax.swing.JLabel();
        jcbAlwaysOnTop = new javax.swing.JCheckBox();
        jcbCapture = new javax.swing.JCheckBox();
        jbtClipboardLeft = new javax.swing.JButton();
        jtfClipboard = new javax.swing.JTextField();
        jbtClipboardCopy = new javax.swing.JButton();
        jbtClipboardRight = new javax.swing.JButton();
        jbtShortcut1Left = new javax.swing.JButton();
        jtfShortcut1 = new javax.swing.JTextField();
        jbtShortcut1Right = new javax.swing.JButton();
        jbtShortcut2Left = new javax.swing.JButton();
        jtfShortcut2 = new javax.swing.JTextField();
        jbtShortcut2Right = new javax.swing.JButton();
        jbtShortcut3Left = new javax.swing.JButton();
        jtfShortcut3 = new javax.swing.JTextField();
        jbtShortcut3Right = new javax.swing.JButton();
        jbtIndexLeft = new javax.swing.JButton();
        jspIndexFormat = new javax.swing.JSpinner();
        jspIndexValue = new javax.swing.JSpinner();
        jbtIndexRight = new javax.swing.JButton();
        jbtMountedClear = new javax.swing.JButton();
        jbtMountedBack = new javax.swing.JButton();
        jtfMounted = new javax.swing.JTextField();
        jbtMountedCopy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CMFC");
        setLocationByPlatform(true);

        jlbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTitle.setText("Copy and Mount for Clipboard");

        jcbAlwaysOnTop.setText("Always On Top");
        jcbAlwaysOnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAlwaysOnTopActionPerformed(evt);
            }
        });

        jcbCapture.setText("Capture");
        jcbCapture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbCaptureActionPerformed(evt);
            }
        });

        jbtClipboardLeft.setText("+");
        jbtClipboardLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtClipboardLeftActionPerformed(evt);
            }
        });

        jbtClipboardCopy.setText("C");
        jbtClipboardCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtClipboardCopyActionPerformed(evt);
            }
        });

        jbtClipboardRight.setText("+");
        jbtClipboardRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtClipboardRightActionPerformed(evt);
            }
        });

        jbtShortcut1Left.setText("+");
        jbtShortcut1Left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShortcut1LeftActionPerformed(evt);
            }
        });

        jbtShortcut1Right.setText("+");
        jbtShortcut1Right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShortcut1RightActionPerformed(evt);
            }
        });

        jbtShortcut2Left.setText("+");
        jbtShortcut2Left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShortcut2LeftActionPerformed(evt);
            }
        });

        jbtShortcut2Right.setText("+");
        jbtShortcut2Right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShortcut2RightActionPerformed(evt);
            }
        });

        jbtShortcut3Left.setText("+");
        jbtShortcut3Left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShortcut3LeftActionPerformed(evt);
            }
        });

        jbtShortcut3Right.setText("+");
        jbtShortcut3Right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShortcut3RightActionPerformed(evt);
            }
        });

        jbtIndexLeft.setText("+");
        jbtIndexLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtIndexLeftActionPerformed(evt);
            }
        });

        jspIndexFormat.setValue(2);

        jspIndexValue.setValue(1);

        jbtIndexRight.setText("+");
        jbtIndexRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtIndexRightActionPerformed(evt);
            }
        });

        jbtMountedClear.setText("X");
        jbtMountedClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtMountedClearActionPerformed(evt);
            }
        });

        jbtMountedBack.setText("<");
        jbtMountedBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtMountedBackActionPerformed(evt);
            }
        });

        jbtMountedCopy.setText("C");
        jbtMountedCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtMountedCopyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jcbAlwaysOnTop)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(jcbCapture))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtClipboardLeft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfClipboard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtClipboardCopy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtClipboardRight))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtIndexLeft)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jspIndexFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jspIndexValue))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtShortcut1Left)
                                    .addComponent(jbtShortcut2Left)
                                    .addComponent(jbtShortcut3Left))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfShortcut2)
                                    .addComponent(jtfShortcut1)
                                    .addComponent(jtfShortcut3))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jbtShortcut3Right, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jbtShortcut2Right, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jbtShortcut1Right, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jbtIndexRight, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtMountedClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtMountedBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfMounted)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtMountedCopy)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jlbTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbAlwaysOnTop)
                    .addComponent(jcbCapture))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfClipboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtClipboardRight)
                    .addComponent(jbtClipboardCopy)
                    .addComponent(jbtClipboardLeft))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfShortcut1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtShortcut1Right)
                    .addComponent(jbtShortcut1Left))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfShortcut2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtShortcut2Right)
                    .addComponent(jbtShortcut2Left))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfShortcut3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtShortcut3Right)
                    .addComponent(jbtShortcut3Left))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtIndexLeft)
                    .addComponent(jspIndexFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jspIndexValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtIndexRight))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtMountedClear)
                    .addComponent(jtfMounted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtMountedCopy)
                    .addComponent(jbtMountedBack))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbAlwaysOnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAlwaysOnTopActionPerformed
        setAlwaysOnTop(jcbAlwaysOnTop.isSelected());
    }//GEN-LAST:event_jcbAlwaysOnTopActionPerformed

    private void jcbCaptureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbCaptureActionPerformed
        capture.set(jcbCapture.isSelected());
    }//GEN-LAST:event_jcbCaptureActionPerformed

    private void jbtClipboardLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtClipboardLeftActionPerformed
        addOnLeft(jtfClipboard.getText());
    }//GEN-LAST:event_jbtClipboardLeftActionPerformed

    private void jbtClipboardCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtClipboardCopyActionPerformed
        jtfClipboard.selectAll();
        jtfClipboard.copy();
    }//GEN-LAST:event_jbtClipboardCopyActionPerformed

    private void jbtClipboardRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtClipboardRightActionPerformed
        addOnRight(jtfClipboard.getText());
    }//GEN-LAST:event_jbtClipboardRightActionPerformed

    private void jbtShortcut1LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut1LeftActionPerformed
        addOnLeft(jtfShortcut1.getText());
    }//GEN-LAST:event_jbtShortcut1LeftActionPerformed

    private void jbtShortcut1RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut1RightActionPerformed
        addOnRight(jtfShortcut1.getText());
    }//GEN-LAST:event_jbtShortcut1RightActionPerformed

    private void jbtShortcut2LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut2LeftActionPerformed
        addOnLeft(jtfShortcut2.getText());
    }//GEN-LAST:event_jbtShortcut2LeftActionPerformed

    private void jbtShortcut2RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut2RightActionPerformed
        addOnRight(jtfShortcut2.getText());
    }//GEN-LAST:event_jbtShortcut2RightActionPerformed

    private void jbtShortcut3LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut3LeftActionPerformed
        addOnLeft(jtfShortcut3.getText());
    }//GEN-LAST:event_jbtShortcut3LeftActionPerformed

    private void jbtShortcut3RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut3RightActionPerformed
        addOnRight(jtfShortcut3.getText());
    }//GEN-LAST:event_jbtShortcut3RightActionPerformed

    private void jbtMountedCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMountedCopyActionPerformed
        jtfMounted.selectAll();
        jtfMounted.copy();
    }//GEN-LAST:event_jbtMountedCopyActionPerformed

    private void jbtMountedClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMountedClearActionPerformed
        jtfMounted.setText("");
        parted.clear();
    }//GEN-LAST:event_jbtMountedClearActionPerformed

    private void jbtIndexLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtIndexLeftActionPerformed
        addOnLeft(getIndexText());
        addIndexValue();
    }//GEN-LAST:event_jbtIndexLeftActionPerformed

    private void jbtIndexRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtIndexRightActionPerformed
        addOnRight(getIndexText());
        addIndexValue();
    }//GEN-LAST:event_jbtIndexRightActionPerformed

    private void jbtMountedBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMountedBackActionPerformed
        if (!parted.isEmpty()) {
            String part = parted.remove(parted.size() -1);
            jtfMounted.setText(jtfMounted.getText().replace(part, ""));
        }
    }//GEN-LAST:event_jbtMountedBackActionPerformed

    private String getIndexText() {
        Integer format = (Integer) jspIndexFormat.getValue();
        Integer value = (Integer) jspIndexValue.getValue();
        String result = value.toString();
        while (result.length() < format) {
            result = "0" + result;
        }
        return result;
    }
    
    private void addIndexValue() {
        Integer value = (Integer) jspIndexValue.getValue();
        jspIndexValue.setValue(value + 1);
    }
    
    private void addOnLeft(String part) {
        jtfMounted.setText(part + jtfMounted.getText());
        parted.add(part);
    }
    
    private void addOnRight(String part) {
        jtfMounted.setText(jtfMounted.getText() + part);
        parted.add(part);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtClipboardCopy;
    private javax.swing.JButton jbtClipboardLeft;
    private javax.swing.JButton jbtClipboardRight;
    private javax.swing.JButton jbtIndexLeft;
    private javax.swing.JButton jbtIndexRight;
    private javax.swing.JButton jbtMountedBack;
    private javax.swing.JButton jbtMountedClear;
    private javax.swing.JButton jbtMountedCopy;
    private javax.swing.JButton jbtShortcut1Left;
    private javax.swing.JButton jbtShortcut1Right;
    private javax.swing.JButton jbtShortcut2Left;
    private javax.swing.JButton jbtShortcut2Right;
    private javax.swing.JButton jbtShortcut3Left;
    private javax.swing.JButton jbtShortcut3Right;
    private javax.swing.JCheckBox jcbAlwaysOnTop;
    private javax.swing.JCheckBox jcbCapture;
    private javax.swing.JLabel jlbTitle;
    private javax.swing.JSpinner jspIndexFormat;
    private javax.swing.JSpinner jspIndexValue;
    private javax.swing.JTextField jtfClipboard;
    private javax.swing.JTextField jtfMounted;
    private javax.swing.JTextField jtfShortcut1;
    private javax.swing.JTextField jtfShortcut2;
    private javax.swing.JTextField jtfShortcut3;
    // End of variables declaration//GEN-END:variables

    public static void start(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Interface().setVisible(true);
        });
    }

}
