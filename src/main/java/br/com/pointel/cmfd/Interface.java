package br.com.pointel.cmfd;

import com.formdev.flatlaf.FlatDarculaLaf;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.commons.io.FileUtils;

public class Interface extends javax.swing.JFrame {

    private final AtomicBoolean autoPaste = new AtomicBoolean(false);
    private final AtomicBoolean autoFolder = new AtomicBoolean(false);
    private final AtomicBoolean autoMove = new AtomicBoolean(false);
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
                        if (autoPaste.get()) {
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
                        if (autoFolder.get()) {
                            File folderDestiny = getFolderDesitny();
                            if (folderDestiny != null && !folderDestiny.exists()) {
                                Files.createDirectories(folderDestiny.toPath());
                            }
                        }
                        if (autoMove.get()) {
                            String origin = jtfOrigin.getText();
                            if (!origin.isEmpty()) {
                                File folderOrigin = new File(origin);
                                if (folderOrigin.isDirectory()) {
                                    File folderDestiny = getFolderDesitny();
                                    if (folderDestiny != null && !folderDestiny.isDirectory()) {
                                        for (var insideOrigin : folderOrigin.listFiles()) {
                                            File destiny = new File(folderDestiny, insideOrigin.getName());
                                            if (insideOrigin.isDirectory()) {
                                                FileUtils.moveDirectory(insideOrigin, destiny);
                                            } else {
                                                FileUtils.moveFile(insideOrigin, destiny);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        WizSwing.showError(e);
                    }
                }
            }

            private File getFolderDesitny() {
                String destiny = jtfDesitny.getText();
                if (!destiny.isEmpty()) {
                    String root = jtfRoot.getText();
                    File folderRoot = root.isEmpty() ? null : new File(root);
                    File folderDestiny;
                    if (folderRoot != null) {
                        folderDestiny = new File(folderRoot, destiny);
                    } else {
                        folderDestiny = new File(destiny);
                    }
                    return folderDestiny;
                }
                return null;
            }
        }.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbTitle = new javax.swing.JLabel();
        jcbAlwaysOnTop = new javax.swing.JCheckBox();
        jcbAutoPaste = new javax.swing.JCheckBox();
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
        jbtMountedAdd = new javax.swing.JButton();
        jcbAutoFolder = new javax.swing.JCheckBox();
        jlbRoot = new javax.swing.JLabel();
        jtfRoot = new javax.swing.JTextField();
        jbtRootSelect = new javax.swing.JButton();
        jbtRootOpen = new javax.swing.JButton();
        jlbDestiny = new javax.swing.JLabel();
        jtfDesitny = new javax.swing.JTextField();
        jbtDestinySelect = new javax.swing.JButton();
        jbtDestinyOpen = new javax.swing.JButton();
        jcbAutoMove = new javax.swing.JCheckBox();
        jlbOrigin = new javax.swing.JLabel();
        jtfOrigin = new javax.swing.JTextField();
        jbtOriginSelect = new javax.swing.JButton();
        jbtOriginOpen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CMFD");
        setLocationByPlatform(true);

        jlbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbTitle.setText("Copy and Mount for Desktop");

        jcbAlwaysOnTop.setText("Always On Top");
        jcbAlwaysOnTop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAlwaysOnTopActionPerformed(evt);
            }
        });

        jcbAutoPaste.setText("Auto Paste");
        jcbAutoPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAutoPasteActionPerformed(evt);
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

        jbtMountedAdd.setText("=");
        jbtMountedAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtMountedAddActionPerformed(evt);
            }
        });

        jcbAutoFolder.setText("Auto Folder");
        jcbAutoFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAutoFolderActionPerformed(evt);
            }
        });

        jlbRoot.setText("Root");

        jbtRootSelect.setText("^");
        jbtRootSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRootSelectActionPerformed(evt);
            }
        });

        jbtRootOpen.setText("*");
        jbtRootOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtRootOpenActionPerformed(evt);
            }
        });

        jlbDestiny.setText("Destiny");

        jbtDestinySelect.setText("^");
        jbtDestinySelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDestinySelectActionPerformed(evt);
            }
        });

        jbtDestinyOpen.setText("*");
        jbtDestinyOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDestinyOpenActionPerformed(evt);
            }
        });

        jcbAutoMove.setText("Auto Move");
        jcbAutoMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAutoMoveActionPerformed(evt);
            }
        });

        jlbOrigin.setText("Origin");

        jbtOriginSelect.setText("^");
        jbtOriginSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOriginSelectActionPerformed(evt);
            }
        });

        jbtOriginOpen.setText("*");
        jbtOriginOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtOriginOpenActionPerformed(evt);
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
                        .addComponent(jcbAutoPaste))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtMountedClear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtMountedBack))
                            .addComponent(jlbRoot)
                            .addComponent(jlbDestiny)
                            .addComponent(jlbOrigin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfRoot)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtRootSelect)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtRootOpen))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfMounted)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtMountedCopy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtMountedAdd))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jtfDesitny)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtDestinySelect)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtDestinyOpen))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jtfOrigin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtOriginSelect)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtOriginOpen))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbAutoFolder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbAutoMove, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(jcbAutoPaste))
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
                    .addComponent(jbtMountedBack)
                    .addComponent(jbtMountedAdd))
                .addGap(18, 18, 18)
                .addComponent(jcbAutoFolder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfRoot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbRoot)
                    .addComponent(jbtRootSelect)
                    .addComponent(jbtRootOpen))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfDesitny, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbDestiny)
                    .addComponent(jbtDestinySelect)
                    .addComponent(jbtDestinyOpen))
                .addGap(18, 18, 18)
                .addComponent(jcbAutoMove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtOriginSelect)
                    .addComponent(jtfOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbOrigin)
                    .addComponent(jbtOriginOpen))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcbAlwaysOnTopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAlwaysOnTopActionPerformed
        setAlwaysOnTop(jcbAlwaysOnTop.isSelected());
    }//GEN-LAST:event_jcbAlwaysOnTopActionPerformed

    private void jcbAutoPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAutoPasteActionPerformed
        autoPaste.set(jcbAutoPaste.isSelected());
    }//GEN-LAST:event_jcbAutoPasteActionPerformed

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
            String part = parted.remove(parted.size() - 1);
            jtfMounted.setText(jtfMounted.getText().replace(part, ""));
        }
    }//GEN-LAST:event_jbtMountedBackActionPerformed

    private void jbtMountedAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMountedAddActionPerformed
        jtfDesitny.setText(jtfMounted.getText());
    }//GEN-LAST:event_jbtMountedAddActionPerformed

    private void jcbAutoFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAutoFolderActionPerformed
        autoFolder.set(jcbAutoFolder.isSelected());
    }//GEN-LAST:event_jcbAutoFolderActionPerformed

    private void jbtRootSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRootSelectActionPerformed
        selectFolder(jtfRoot);
    }//GEN-LAST:event_jbtRootSelectActionPerformed

    private void jbtRootOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRootOpenActionPerformed
        openFolder(jtfRoot);
    }//GEN-LAST:event_jbtRootOpenActionPerformed

    private void jbtDestinySelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDestinySelectActionPerformed
        openFolder(jtfDesitny);
    }//GEN-LAST:event_jbtDestinySelectActionPerformed

    private void jbtDestinyOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDestinyOpenActionPerformed
        openFolder(jtfDesitny);
    }//GEN-LAST:event_jbtDestinyOpenActionPerformed

    private void jbtOriginSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOriginSelectActionPerformed
        selectFolder(jtfOrigin);
    }//GEN-LAST:event_jbtOriginSelectActionPerformed

    private void jbtOriginOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOriginOpenActionPerformed
        openFolder(jtfOrigin);
    }//GEN-LAST:event_jbtOriginOpenActionPerformed

    private void jcbAutoMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAutoMoveActionPerformed
        autoMove.set(jcbAutoMove.isSelected());
    }//GEN-LAST:event_jcbAutoMoveActionPerformed

    private void addOnLeft(String part) {
        jtfMounted.setText(part + jtfMounted.getText());
        parted.add(part);
    }

    private void addOnRight(String part) {
        jtfMounted.setText(jtfMounted.getText() + part);
        parted.add(part);
    }

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

    private void selectFolder(JTextField field) {
        String text = field.getText();
        File selected = null;
        if (!text.isEmpty()) {
            selected = new File(text);
        }
        selected = WizSwing.selectFolder(selected);
        field.setText(selected.getAbsolutePath());
    }

    private void openFolder(JTextField field) {
        String text = field.getText();
        if (!text.isEmpty()) {
            try {
                WizSwing.open(new File(text));
            } catch (Exception ex) {
                WizSwing.showError(ex);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtClipboardCopy;
    private javax.swing.JButton jbtClipboardLeft;
    private javax.swing.JButton jbtClipboardRight;
    private javax.swing.JButton jbtDestinyOpen;
    private javax.swing.JButton jbtDestinySelect;
    private javax.swing.JButton jbtIndexLeft;
    private javax.swing.JButton jbtIndexRight;
    private javax.swing.JButton jbtMountedAdd;
    private javax.swing.JButton jbtMountedBack;
    private javax.swing.JButton jbtMountedClear;
    private javax.swing.JButton jbtMountedCopy;
    private javax.swing.JButton jbtOriginOpen;
    private javax.swing.JButton jbtOriginSelect;
    private javax.swing.JButton jbtRootOpen;
    private javax.swing.JButton jbtRootSelect;
    private javax.swing.JButton jbtShortcut1Left;
    private javax.swing.JButton jbtShortcut1Right;
    private javax.swing.JButton jbtShortcut2Left;
    private javax.swing.JButton jbtShortcut2Right;
    private javax.swing.JButton jbtShortcut3Left;
    private javax.swing.JButton jbtShortcut3Right;
    private javax.swing.JCheckBox jcbAlwaysOnTop;
    private javax.swing.JCheckBox jcbAutoFolder;
    private javax.swing.JCheckBox jcbAutoMove;
    private javax.swing.JCheckBox jcbAutoPaste;
    private javax.swing.JLabel jlbDestiny;
    private javax.swing.JLabel jlbOrigin;
    private javax.swing.JLabel jlbRoot;
    private javax.swing.JLabel jlbTitle;
    private javax.swing.JSpinner jspIndexFormat;
    private javax.swing.JSpinner jspIndexValue;
    private javax.swing.JTextField jtfClipboard;
    private javax.swing.JTextField jtfDesitny;
    private javax.swing.JTextField jtfMounted;
    private javax.swing.JTextField jtfOrigin;
    private javax.swing.JTextField jtfRoot;
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
