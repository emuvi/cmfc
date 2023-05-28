package br.com.pointel.cmfd;

import com.formdev.flatlaf.FlatDarculaLaf;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.RandomAccessFile;
import static java.lang.Thread.sleep;
import java.nio.channels.FileChannel;
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
                        sleep(700);
                        if (autoPaste.get()) {
                            doPaste();
                        }
                        if (autoFolder.get()) {
                            doFolder();
                        }
                        if (autoMove.get()) {
                            doMove();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();
    }

    private void doPaste() throws Exception {
        String testing = WizSwing.getStringOnClipboard();
        if (testing != null) {
            testing = testing.replaceAll("\\s+", " ");
            final String clipboard = testing.trim();
            if (!clipboard.isEmpty()) {
                if (SwingUtilities.isEventDispatchThread()) {
                    if (!jtfClipboard.getText().equals(clipboard)) {
                        jtfClipboard.setText(clipboard);
                    }
                } else {
                    SwingUtilities.invokeAndWait(() -> {
                        if (!jtfClipboard.getText().equals(clipboard)) {
                            jtfClipboard.setText(clipboard);
                        }
                    });
                }
            }
        }
    }

    private void doFolder() throws Exception {
        File folderDestiny = getFolderDesitny();
        if (folderDestiny != null && !folderDestiny.exists()) {
            Files.createDirectories(folderDestiny.toPath());
        }
    }

    private void doMove() throws Exception {
        String origin = jtfOrigin.getText();
        if (!origin.isEmpty()) {
            File folderOrigin = new File(origin);
            if (folderOrigin.isDirectory()) {
                File folderDestiny = getFolderDesitny();
                if (folderDestiny != null && folderDestiny.isDirectory()) {
                    for (var insideOrigin : folderOrigin.listFiles()) {
                        File destiny = new File(folderDestiny, insideOrigin.getName());
                        if (insideOrigin.isDirectory()) {
                            FileUtils.moveDirectory(insideOrigin, destiny);
                        } else {
                            if (canMove(insideOrigin)) {
                                FileUtils.moveFile(insideOrigin, destiny);
                            }
                        }
                    }
                }
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

    private boolean canMove(File file) {
        try ( FileChannel channel = new RandomAccessFile(file, "rw").getChannel()) {
            try ( var lock = channel.tryLock()) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlbTitle = new javax.swing.JLabel();
        jcbAlwaysOnTop = new javax.swing.JCheckBox();
        jcbAutoPaste = new javax.swing.JCheckBox();
        jbtClipboardLeft = new javax.swing.JButton();
        jtfClipboard = new javax.swing.JTextField();
        jbtClipboardEquals = new javax.swing.JButton();
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
        jbtMountedEquals = new javax.swing.JButton();
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
        jbtDoPaste = new javax.swing.JButton();
        jbtDoFolder = new javax.swing.JButton();
        jbtDoMove = new javax.swing.JButton();
        jbtShortcut1Equals = new javax.swing.JButton();
        jbtShortcut2Equals = new javax.swing.JButton();
        jbtShortcut3Equals = new javax.swing.JButton();
        jbtIndexEquals = new javax.swing.JButton();
        jbtFolderMove = new javax.swing.JButton();
        jtfMagic = new javax.swing.JTextField();
        jbtMagic = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CMFD");
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

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

        jbtClipboardEquals.setText("=");
        jbtClipboardEquals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtClipboardEqualsActionPerformed(evt);
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

        jbtMountedEquals.setText("=");
        jbtMountedEquals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtMountedEqualsActionPerformed(evt);
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

        jbtDoPaste.setText(">");
        jbtDoPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDoPasteActionPerformed(evt);
            }
        });

        jbtDoFolder.setText(">");
        jbtDoFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDoFolderActionPerformed(evt);
            }
        });

        jbtDoMove.setText(">");
        jbtDoMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtDoMoveActionPerformed(evt);
            }
        });

        jbtShortcut1Equals.setText("=");
        jbtShortcut1Equals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShortcut1EqualsActionPerformed(evt);
            }
        });

        jbtShortcut2Equals.setText("=");
        jbtShortcut2Equals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShortcut2EqualsActionPerformed(evt);
            }
        });

        jbtShortcut3Equals.setText("=");
        jbtShortcut3Equals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtShortcut3EqualsActionPerformed(evt);
            }
        });

        jbtIndexEquals.setText("=");
        jbtIndexEquals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtIndexEqualsActionPerformed(evt);
            }
        });

        jbtFolderMove.setText("~");
        jbtFolderMove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtFolderMoveActionPerformed(evt);
            }
        });

        jbtMagic.setText("~");
        jbtMagic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtMagicActionPerformed(evt);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jcbAutoPaste)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtDoPaste))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtClipboardLeft)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfClipboard)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtClipboardRight)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtClipboardEquals))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtIndexLeft)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jspIndexFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jspIndexValue, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jbtShortcut1Left)
                                    .addComponent(jbtShortcut2Left)
                                    .addComponent(jbtShortcut3Left))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jtfShortcut1)
                                    .addComponent(jtfShortcut3)
                                    .addComponent(jtfShortcut2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jbtShortcut3Right)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jbtShortcut3Equals))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jbtShortcut2Right)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jbtShortcut2Equals))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jbtShortcut1Right)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jbtShortcut1Equals)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jbtIndexRight)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtIndexEquals))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbtMountedClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtMountedBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfMounted)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtMountedCopy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtMountedEquals))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jtfMagic)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtMagic))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlbRoot)
                            .addComponent(jlbDestiny)
                            .addComponent(jlbOrigin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtFolderMove)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jcbAutoMove, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtDoMove))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jtfRoot)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtRootSelect)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtRootOpen))
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
                        .addComponent(jcbAutoFolder, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtDoFolder)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jlbTitle)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbAlwaysOnTop)
                    .addComponent(jcbAutoPaste)
                    .addComponent(jbtDoPaste))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfClipboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtClipboardRight)
                    .addComponent(jbtClipboardEquals)
                    .addComponent(jbtClipboardLeft))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfShortcut1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtShortcut1Right)
                    .addComponent(jbtShortcut1Left)
                    .addComponent(jbtShortcut1Equals))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfShortcut2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtShortcut2Right)
                    .addComponent(jbtShortcut2Left)
                    .addComponent(jbtShortcut2Equals))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfShortcut3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtShortcut3Right)
                    .addComponent(jbtShortcut3Left)
                    .addComponent(jbtShortcut3Equals))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtIndexLeft)
                    .addComponent(jspIndexFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jspIndexValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtIndexRight)
                    .addComponent(jbtIndexEquals))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtMountedClear)
                    .addComponent(jtfMounted, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtMountedCopy)
                    .addComponent(jbtMountedBack)
                    .addComponent(jbtMountedEquals))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbAutoFolder)
                    .addComponent(jbtDoFolder))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbAutoMove)
                    .addComponent(jbtDoMove)
                    .addComponent(jbtFolderMove))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtOriginSelect)
                    .addComponent(jtfOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlbOrigin)
                    .addComponent(jbtOriginOpen))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfMagic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtMagic))
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
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.ADD_LEFT_PASTE);
        } else {
            addOnLeft(jtfClipboard.getText());
        }
    }//GEN-LAST:event_jbtClipboardLeftActionPerformed

    private void jbtClipboardEqualsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtClipboardEqualsActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.SET_PASTE);
        } else {
            addEquals(jtfClipboard.getText());
        }
    }//GEN-LAST:event_jbtClipboardEqualsActionPerformed

    private void jbtClipboardRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtClipboardRightActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.ADD_RIGHT_PASTE);
        } else {
            addOnRight(jtfClipboard.getText());
        }
    }//GEN-LAST:event_jbtClipboardRightActionPerformed

    private void jbtShortcut1LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut1LeftActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.ADD_LEFT_PART_1);
        } else {
            addOnLeft(jtfShortcut1.getText());
        }
    }//GEN-LAST:event_jbtShortcut1LeftActionPerformed

    private void jbtShortcut1RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut1RightActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.ADD_RIGHT_PART_1);
        } else {
            addOnRight(jtfShortcut1.getText());
        }
    }//GEN-LAST:event_jbtShortcut1RightActionPerformed

    private void jbtShortcut2LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut2LeftActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.ADD_LEFT_PART_2);
        } else {
            addOnLeft(jtfShortcut2.getText());
        }
    }//GEN-LAST:event_jbtShortcut2LeftActionPerformed

    private void jbtShortcut2RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut2RightActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.ADD_RIGHT_PART_2);
        } else {
            addOnRight(jtfShortcut2.getText());
        }
    }//GEN-LAST:event_jbtShortcut2RightActionPerformed

    private void jbtShortcut3LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut3LeftActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.ADD_LEFT_PART_3);
        } else {
            addOnLeft(jtfShortcut3.getText());
        }
    }//GEN-LAST:event_jbtShortcut3LeftActionPerformed

    private void jbtShortcut3RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut3RightActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.ADD_RIGHT_PART_3);
        } else {
            addOnRight(jtfShortcut3.getText());
        }
    }//GEN-LAST:event_jbtShortcut3RightActionPerformed

    private void jbtMountedCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMountedCopyActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.COPY_MOUNTED);
        } else {
            jtfMounted.selectAll();
            jtfMounted.copy();
        }
    }//GEN-LAST:event_jbtMountedCopyActionPerformed

    private void jbtMountedClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMountedClearActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.CLEAR_MOUNTED);
        } else {
            jtfMounted.setText("");
            parted.clear();
        }
    }//GEN-LAST:event_jbtMountedClearActionPerformed

    private void jbtIndexLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtIndexLeftActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.ADD_LEFT_INDEX);
        } else {
            addOnLeft(getIndexText());
            addIndexValue();
        }
    }//GEN-LAST:event_jbtIndexLeftActionPerformed

    private void jbtIndexRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtIndexRightActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.ADD_RIGHT_INDEX);
        } else {
            addOnRight(getIndexText());
            addIndexValue();
        }
    }//GEN-LAST:event_jbtIndexRightActionPerformed

    private void jbtMountedBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMountedBackActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.UNDO_PART);
        } else {
            if (!parted.isEmpty()) {
                String part = parted.remove(parted.size() - 1);
                jtfMounted.setText(jtfMounted.getText().replace(part, ""));
            }
        }
    }//GEN-LAST:event_jbtMountedBackActionPerformed

    private void jbtMountedEqualsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMountedEqualsActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.SET_MOUNTED);
        } else {
            jtfDesitny.setText(
                    jtfMounted.getText()
                            .replaceAll("\\s+", " ")
                            .replace('/', '-')
                            .replace('\\', '-')
                            .replace(':', '-')
                            .replace('|', '-'));
        }
    }//GEN-LAST:event_jbtMountedEqualsActionPerformed

    private void jcbAutoFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAutoFolderActionPerformed
        autoFolder.set(jcbAutoFolder.isSelected());
    }//GEN-LAST:event_jcbAutoFolderActionPerformed

    private void jbtRootSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRootSelectActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.SEL_ROOT);
        } else {
            selectFolder(jtfRoot);
        }
    }//GEN-LAST:event_jbtRootSelectActionPerformed

    private void jbtRootOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtRootOpenActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.GO_ROOT);
        } else {
            openFolder(jtfRoot);
        }
    }//GEN-LAST:event_jbtRootOpenActionPerformed

    private void jbtDestinySelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDestinySelectActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.SEL_DESTINY);
        } else {
            openFolder(jtfDesitny, jtfRoot);
        }
    }//GEN-LAST:event_jbtDestinySelectActionPerformed

    private void jbtDestinyOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDestinyOpenActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.GO_DESTINY);
        } else {
            openFolder(jtfDesitny, jtfRoot);
        }
    }//GEN-LAST:event_jbtDestinyOpenActionPerformed

    private void jbtOriginSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOriginSelectActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.SEL_ORIGIN);
        } else {
            selectFolder(jtfOrigin);
        }
    }//GEN-LAST:event_jbtOriginSelectActionPerformed

    private void jbtOriginOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtOriginOpenActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.GO_ORIGIN);
        } else {
            openFolder(jtfOrigin);
        }
    }//GEN-LAST:event_jbtOriginOpenActionPerformed

    private void jcbAutoMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAutoMoveActionPerformed
        autoMove.set(jcbAutoMove.isSelected());
    }//GEN-LAST:event_jcbAutoMoveActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            WizProps.load("CMFD");
            jtfClipboard.setText(WizProps.get("clipboard", ""));
            jtfDesitny.setText(WizProps.get("destiny", ""));
            jtfMagic.setText(WizProps.get("magic", ""));
            jtfMounted.setText(WizProps.get("mounted", ""));
            jtfOrigin.setText(WizProps.get("origin", ""));
            jtfRoot.setText(WizProps.get("root", ""));
            jtfShortcut1.setText(WizProps.get("shortcut1", ""));
            jtfShortcut2.setText(WizProps.get("shortcut2", ""));
            jtfShortcut3.setText(WizProps.get("shortcut3", ""));
            jspIndexFormat.setValue(WizProps.get("index-format", 2));
            jspIndexValue.setValue(WizProps.get("index-value", 1));
        } catch (Exception ex) {
            WizSwing.showError(ex);
        }
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            WizProps.set("clipboard", jtfClipboard.getText());
            WizProps.set("destiny", jtfDesitny.getText());
            WizProps.set("magic", jtfMagic.getText());
            WizProps.set("mounted", jtfMounted.getText());
            WizProps.set("origin", jtfOrigin.getText());
            WizProps.set("root", jtfRoot.getText());
            WizProps.set("shortcut1", jtfShortcut1.getText());
            WizProps.set("shortcut2", jtfShortcut2.getText());
            WizProps.set("shortcut3", jtfShortcut3.getText());
            WizProps.set("index-format", (Integer) jspIndexFormat.getValue());
            WizProps.set("index-value", (Integer) jspIndexValue.getValue());
            WizProps.save("CMFD");
        } catch (Exception ex) {
            WizSwing.showError(ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void jbtDoPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDoPasteActionPerformed
        try {
            if (isMagicAction(evt)) {
                addMagicAction(STUFF.RUN_PASTE);
            } else {
                doPaste();
            }
        } catch (Exception e) {
            WizSwing.showError(e);
        }
    }//GEN-LAST:event_jbtDoPasteActionPerformed

    private void jbtDoFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDoFolderActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.RUN_FOLDER);
        } else {
            try {
                doFolder();
            } catch (Exception e) {
                WizSwing.showError(e);
            }
        }
    }//GEN-LAST:event_jbtDoFolderActionPerformed

    private void jbtDoMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtDoMoveActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.RUN_MOVE);
        } else {
            try {
                doMove();
            } catch (Exception e) {
                WizSwing.showError(e);
            }
        }
    }//GEN-LAST:event_jbtDoMoveActionPerformed

    private void jbtShortcut1EqualsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut1EqualsActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.SET_PART_1);
        } else {
            addEquals(jtfShortcut1.getText());
        }
    }//GEN-LAST:event_jbtShortcut1EqualsActionPerformed

    private void jbtShortcut2EqualsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut2EqualsActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.SET_PART_2);
        } else {
            addEquals(jtfShortcut2.getText());
        }
    }//GEN-LAST:event_jbtShortcut2EqualsActionPerformed

    private void jbtShortcut3EqualsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtShortcut3EqualsActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.SET_PART_3);
        } else {
            addEquals(jtfShortcut3.getText());
        }
    }//GEN-LAST:event_jbtShortcut3EqualsActionPerformed

    private void jbtIndexEqualsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtIndexEqualsActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.SET_INDEX);
        } else {
            addEquals(getIndexText());
        }
    }//GEN-LAST:event_jbtIndexEqualsActionPerformed

    private void jbtFolderMoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtFolderMoveActionPerformed
        if (isMagicAction(evt)) {
            addMagicAction(STUFF.RUN_FOLDER_MOVE);
        } else {
            try {
                doFolder();
                doMove();
                openFolder(jtfDesitny, jtfRoot);
            } catch (Exception e) {
                WizSwing.showError(e);
            }
        }
    }//GEN-LAST:event_jbtFolderMoveActionPerformed

    private void jbtMagicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtMagicActionPerformed
        makeMagic();
    }//GEN-LAST:event_jbtMagicActionPerformed

    private void addOnLeft(String part) {
        jtfMounted.setText(part + jtfMounted.getText());
        parted.add(part);
    }

    private void addOnRight(String part) {
        jtfMounted.setText(jtfMounted.getText() + part);
        parted.add(part);
    }

    private void addEquals(String part) {
        jtfMounted.setText(part);
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
        selectFolder(field, null);
    }

    private void selectFolder(JTextField field, JTextField rootField) {
        String text = field.getText();
        String root = rootField != null ? rootField.getText() : "";
        File selected = null;
        if (!text.isEmpty()) {
            selected = root.isEmpty() ? new File(text) : new File(root, text);
        }
        selected = WizSwing.selectFolder(selected);
        field.setText(selected.getAbsolutePath());
    }

    private void openFolder(JTextField field) {
        openFolder(field, null);
    }

    private void openFolder(JTextField field, JTextField rootField) {
        String text = field.getText();
        String root = rootField != null ? rootField.getText() : "";
        if (!text.isEmpty()) {
            try {
                File file = root.isEmpty() ? new File(text) : new File(root, text);
                WizSwing.open(file);
            } catch (Exception ex) {
                WizSwing.showError(ex);
            }
        }
    }

    private String getRecipe() {
        return jtfMagic.getText()
                .replaceAll("\\s", "")
                .replaceAll("\\++", "+");
    }

    private boolean isMagicAction(ActionEvent evt) {
        return (evt != null && ((evt.getModifiers() & ActionEvent.ALT_MASK) == ActionEvent.ALT_MASK));
    }

    private void addMagicAction(STUFF magic) {
        var recipe = getRecipe();
        if (!recipe.isEmpty()) {
            recipe += "+";
        }
        recipe += magic.name();
        jtfMagic.setText(recipe);
    }

    private void makeMagic() {
        var recipe = getRecipe();
        var stuffed = recipe.split("\\+");
        for (var stuff : stuffed) {
            var making = STUFF.valueOf(stuff);
            switch (making) {
                case RUN_PASTE ->
                    jbtDoPasteActionPerformed(null);
                case ADD_LEFT_PASTE ->
                    jbtClipboardLeftActionPerformed(null);
                case ADD_RIGHT_PASTE ->
                    jbtClipboardRightActionPerformed(null);
                case SET_PASTE ->
                    jbtClipboardEqualsActionPerformed(null);
                case ADD_LEFT_PART_1 ->
                    jbtShortcut1LeftActionPerformed(null);
                case ADD_RIGHT_PART_1 ->
                    jbtShortcut1RightActionPerformed(null);
                case SET_PART_1 ->
                    jbtShortcut1EqualsActionPerformed(null);
                case ADD_LEFT_PART_2 ->
                    jbtShortcut2LeftActionPerformed(null);
                case ADD_RIGHT_PART_2 ->
                    jbtShortcut2RightActionPerformed(null);
                case SET_PART_2 ->
                    jbtShortcut2EqualsActionPerformed(null);
                case ADD_LEFT_PART_3 ->
                    jbtShortcut3LeftActionPerformed(null);
                case ADD_RIGHT_PART_3 ->
                    jbtShortcut3RightActionPerformed(null);
                case SET_PART_3 ->
                    jbtShortcut3EqualsActionPerformed(null);
                case ADD_LEFT_INDEX ->
                    jbtIndexLeftActionPerformed(null);
                case ADD_RIGHT_INDEX ->
                    jbtIndexRightActionPerformed(null);
                case SET_INDEX ->
                    jbtIndexEqualsActionPerformed(null);
                case CLEAR_MOUNTED ->
                    jbtMountedClearActionPerformed(null);
                case UNDO_PART ->
                    jbtMountedBackActionPerformed(null);
                case COPY_MOUNTED ->
                    jbtMountedCopyActionPerformed(null);
                case SET_MOUNTED ->
                    jbtMountedEqualsActionPerformed(null);
                case RUN_FOLDER ->
                    jbtDoFolderActionPerformed(null);
                case SEL_ROOT ->
                    jbtRootSelectActionPerformed(null);
                case GO_ROOT ->
                    jbtRootOpenActionPerformed(null);
                case SEL_DESTINY ->
                    jbtDestinySelectActionPerformed(null);
                case GO_DESTINY ->
                    jbtDestinyOpenActionPerformed(null);
                case RUN_FOLDER_MOVE ->
                    jbtFolderMoveActionPerformed(null);
                case RUN_MOVE ->
                    jbtDoMoveActionPerformed(null);
                case SEL_ORIGIN ->
                    jbtOriginSelectActionPerformed(null);
                case GO_ORIGIN ->
                    jbtOriginOpenActionPerformed(null);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbtClipboardEquals;
    private javax.swing.JButton jbtClipboardLeft;
    private javax.swing.JButton jbtClipboardRight;
    private javax.swing.JButton jbtDestinyOpen;
    private javax.swing.JButton jbtDestinySelect;
    private javax.swing.JButton jbtDoFolder;
    private javax.swing.JButton jbtDoMove;
    private javax.swing.JButton jbtDoPaste;
    private javax.swing.JButton jbtFolderMove;
    private javax.swing.JButton jbtIndexEquals;
    private javax.swing.JButton jbtIndexLeft;
    private javax.swing.JButton jbtIndexRight;
    private javax.swing.JButton jbtMagic;
    private javax.swing.JButton jbtMountedBack;
    private javax.swing.JButton jbtMountedClear;
    private javax.swing.JButton jbtMountedCopy;
    private javax.swing.JButton jbtMountedEquals;
    private javax.swing.JButton jbtOriginOpen;
    private javax.swing.JButton jbtOriginSelect;
    private javax.swing.JButton jbtRootOpen;
    private javax.swing.JButton jbtRootSelect;
    private javax.swing.JButton jbtShortcut1Equals;
    private javax.swing.JButton jbtShortcut1Left;
    private javax.swing.JButton jbtShortcut1Right;
    private javax.swing.JButton jbtShortcut2Equals;
    private javax.swing.JButton jbtShortcut2Left;
    private javax.swing.JButton jbtShortcut2Right;
    private javax.swing.JButton jbtShortcut3Equals;
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
    private javax.swing.JTextField jtfMagic;
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

    public static enum STUFF {
        RUN_PASTE, ADD_LEFT_PASTE, ADD_RIGHT_PASTE, SET_PASTE,
        ADD_LEFT_PART_1, ADD_RIGHT_PART_1, SET_PART_1,
        ADD_LEFT_PART_2, ADD_RIGHT_PART_2, SET_PART_2,
        ADD_LEFT_PART_3, ADD_RIGHT_PART_3, SET_PART_3,
        ADD_LEFT_INDEX, ADD_RIGHT_INDEX, SET_INDEX,
        CLEAR_MOUNTED, UNDO_PART, COPY_MOUNTED, SET_MOUNTED,
        RUN_FOLDER, SEL_ROOT, GO_ROOT, SEL_DESTINY, GO_DESTINY,
        RUN_FOLDER_MOVE, RUN_MOVE, SEL_ORIGIN, GO_ORIGIN
    }

}
