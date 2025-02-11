package info.loenwind.compare;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import info.loenwind.compare.tools.FileIdReader;
import info.loenwind.compare.tools.Settings;

public class AppWindow {

  private JFrame frame;
  private JTextField textField;
  private JLabel labelScan;
  private JButton buttonScan;
  private final List<File> files = new ArrayList<>();
  private JButton buttonCompare;
  private JLabel lblNewLabel_1;
  private JLabel lblNewLabel_2;
  private JLabel lblNewLabel_3;
  private Component horizontalStrut;
  private JLabel labelCompare;
  private Component horizontalStrut_1;
  private Component verticalStrut;
  private Component horizontalStrut_2;
  private JCheckBox checkMove;
  private JTextField textMove;
  private JButton buttonMove;
  private JLabel lblNewLabel_4;

  private final Settings settings = new Settings();
  private JCheckBox checkSuddenDeath;
  private Component verticalStrut_1;
  private Component verticalStrut_2;
  private JButton btnNewButton;

  private boolean hasLafs() {
    LookAndFeelInfo[] feels = UIManager.getInstalledLookAndFeels();
    return feels != null && feels.length > 1;
  }

  private final ActionListener nextLaf = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      if (hasLafs()) {
        LookAndFeel lookAndFeel = UIManager.getLookAndFeel();
        String name = lookAndFeel.getClass().getName();
        if (name == null) {
          name = "";
        }
        String found = null;
        LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
        for (LookAndFeelInfo lookAndFeelInfo : installedLookAndFeels) {
          String className = lookAndFeelInfo.getClassName();
          if (name.equals(className)) {
            found = null;
          } else if (found == null) {
            found = className;
          }
        }
        if (found == null) {
          found = installedLookAndFeels[0].getClassName();
        }
        try {
          UIManager.setLookAndFeel(found);
          SwingUtilities.updateComponentTreeUI(frame);
          // frame.pack();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e2) {
          e2.printStackTrace();
        }
      }
    }
  };

  public static void run() {
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          AppWindow window = new AppWindow();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  public AppWindow() {
    initialize();
  }

  private void initialize() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e2) {
      e2.printStackTrace();
    }
    frame = new JFrame(Main.APP_NAME);
    frame.setBounds(100, 100, 640, 320);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
    gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
    gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
    frame.getContentPane().setLayout(gridBagLayout);

    verticalStrut = Box.createVerticalStrut(20);
    GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
    gbc_verticalStrut.insets = new Insets(0, 0, 5, 5);
    gbc_verticalStrut.gridx = 0;
    gbc_verticalStrut.gridy = 0;
    frame.getContentPane().add(verticalStrut, gbc_verticalStrut);

    horizontalStrut_2 = Box.createHorizontalStrut(20);
    GridBagConstraints gbc_horizontalStrut_2 = new GridBagConstraints();
    gbc_horizontalStrut_2.insets = new Insets(0, 0, 5, 0);
    gbc_horizontalStrut_2.gridx = 6;
    gbc_horizontalStrut_2.gridy = 0;
    frame.getContentPane().add(horizontalStrut_2, gbc_horizontalStrut_2);

    horizontalStrut_1 = Box.createHorizontalStrut(20);
    GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
    gbc_horizontalStrut_1.insets = new Insets(0, 0, 5, 5);
    gbc_horizontalStrut_1.gridx = 0;
    gbc_horizontalStrut_1.gridy = 1;
    frame.getContentPane().add(horizontalStrut_1, gbc_horizontalStrut_1);

    lblNewLabel_1 = new JLabel("Step 1:");
    GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
    gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel_1.gridx = 1;
    gbc_lblNewLabel_1.gridy = 1;
    frame.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

    horizontalStrut = Box.createHorizontalStrut(20);
    GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
    gbc_horizontalStrut.insets = new Insets(0, 0, 5, 5);
    gbc_horizontalStrut.gridx = 2;
    gbc_horizontalStrut.gridy = 1;
    frame.getContentPane().add(horizontalStrut, gbc_horizontalStrut);

    JLabel lblNewLabel = new JLabel("Source folder:");
    lblNewLabel.setToolTipText("This folder and all its sub-folders will be scanned for PNG and JPG images.");
    GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
    gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
    gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel.gridx = 3;
    gbc_lblNewLabel.gridy = 1;
    frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

    textField = new JTextField();
    lblNewLabel.setLabelFor(textField);
    textField.getDocument().addDocumentListener(new DocumentListener() {

      @Override
      public void removeUpdate(DocumentEvent e) {
        changedUpdate(e);
      }

      @Override
      public void insertUpdate(DocumentEvent e) {
        changedUpdate(e);
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        buttonScan.setEnabled(!textField.getText().isEmpty());
      }
    });
    GridBagConstraints gbc_textField = new GridBagConstraints();
    gbc_textField.insets = new Insets(0, 0, 5, 5);
    gbc_textField.fill = GridBagConstraints.HORIZONTAL;
    gbc_textField.gridx = 4;
    gbc_textField.gridy = 1;
    frame.getContentPane().add(textField, gbc_textField);
    textField.setColumns(10);

    JButton buttonSelect = new JButton("Select...");
    buttonSelect.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if (textField.getText().isEmpty()) {
          chooser.setCurrentDirectory(new File("."));
        } else {
          chooser.setCurrentDirectory(new File(textField.getText()));
        }
        chooser.setDialogTitle("Select images folder");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
          try {
            textField.setText(chooser.getSelectedFile().getCanonicalPath().toString());
            buttonScan.setEnabled(true);
          } catch (IOException e1) {
            e1.printStackTrace();
            textField.setText(e1.getLocalizedMessage());
            buttonScan.setEnabled(false);
          }
          buttonCompare.setEnabled(false);
          labelCompare.setVisible(true);
        }
      }
    });
    GridBagConstraints gbc_buttonSelect = new GridBagConstraints();
    gbc_buttonSelect.insets = new Insets(0, 0, 5, 5);
    gbc_buttonSelect.gridx = 5;
    gbc_buttonSelect.gridy = 1;
    frame.getContentPane().add(buttonSelect, gbc_buttonSelect);

    lblNewLabel_4 = new JLabel("Target folder:");
    lblNewLabel_4.setToolTipText(
        "(optional)\nA folder to move images to when you're done with them.\nSub-folders will be created here depending on the type of images you move (excluded/winners/rated).");
    GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
    gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
    gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel_4.gridx = 3;
    gbc_lblNewLabel_4.gridy = 2;
    frame.getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);

    textMove = new JTextField();
    lblNewLabel_4.setLabelFor(textMove);
    GridBagConstraints gbc_textMove = new GridBagConstraints();
    gbc_textMove.insets = new Insets(0, 0, 5, 5);
    gbc_textMove.fill = GridBagConstraints.HORIZONTAL;
    gbc_textMove.gridx = 4;
    gbc_textMove.gridy = 2;
    frame.getContentPane().add(textMove, gbc_textMove);
    textMove.setColumns(10);

    buttonMove = new JButton("Select...");
    buttonMove.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        if (textMove.getText().isEmpty()) {
          chooser.setCurrentDirectory(new File("."));
        } else {
          chooser.setCurrentDirectory(new File(textMove.getText()));
        }
        chooser.setDialogTitle("Select folder for excluded images");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION) {
          try {
            textMove.setText(chooser.getSelectedFile().getCanonicalPath().toString());
          } catch (IOException e1) {
            e1.printStackTrace();
            textMove.setText(e1.getLocalizedMessage());
            checkMove.setSelected(false);
          }
        }
      }
    });
    GridBagConstraints gbc_buttonMove = new GridBagConstraints();
    gbc_buttonMove.insets = new Insets(0, 0, 5, 5);
    gbc_buttonMove.gridx = 5;
    gbc_buttonMove.gridy = 2;
    frame.getContentPane().add(buttonMove, gbc_buttonMove);

    verticalStrut_1 = Box.createVerticalStrut(20);
    GridBagConstraints gbc_verticalStrut_1 = new GridBagConstraints();
    gbc_verticalStrut_1.insets = new Insets(0, 0, 5, 5);
    gbc_verticalStrut_1.gridx = 2;
    gbc_verticalStrut_1.gridy = 3;
    frame.getContentPane().add(verticalStrut_1, gbc_verticalStrut_1);

    lblNewLabel_2 = new JLabel("Step 2:");
    GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
    gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel_2.gridx = 1;
    gbc_lblNewLabel_2.gridy = 4;
    frame.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

    buttonScan = new JButton("Scan for images");
    buttonScan.setEnabled(false);
    buttonScan.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (textField.getText().isEmpty()) {
          buttonScan.setEnabled(false);
          return;
        }
        File folder = new java.io.File(textField.getText());
        if (!folder.isDirectory()) {
          labelScan.setText("Not a folder");
          buttonScan.setEnabled(false);
        }
        try {
          files.clear();
          Files.walkFileTree(folder.toPath(), new FileVisitor<Path>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
              return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
              if (file.toString().toLowerCase(Locale.ENGLISH).endsWith(".png") || file.toString().toLowerCase(Locale.ENGLISH).endsWith(".jpg")) {
                files.add(file.toFile());
              }
              if (file.getFileName().toString().toLowerCase(Locale.ENGLISH).equals("files.bbs")) {
                FileIdReader.read(settings, file.toFile());
              }
              return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
              return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
              return FileVisitResult.CONTINUE;
            }
          });
          labelScan.setText(files.size() + " images found");
          buttonCompare.setEnabled(!files.isEmpty());
          labelCompare.setVisible(files.isEmpty());
        } catch (IOException e1) {
          e1.printStackTrace();
          labelScan.setText(e1.getLocalizedMessage());
          buttonScan.setEnabled(false);
          buttonCompare.setEnabled(false);
          labelCompare.setVisible(true);
        }
      }
    });
    GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
    gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_1.gridx = 3;
    gbc_btnNewButton_1.gridy = 4;
    frame.getContentPane().add(buttonScan, gbc_btnNewButton_1);

    labelScan = new JLabel("Please select a folder");
    GridBagConstraints gbc_labelScan = new GridBagConstraints();
    gbc_labelScan.gridwidth = 2;
    gbc_labelScan.insets = new Insets(0, 0, 5, 5);
    gbc_labelScan.gridx = 4;
    gbc_labelScan.gridy = 4;
    frame.getContentPane().add(labelScan, gbc_labelScan);

    verticalStrut_2 = Box.createVerticalStrut(20);
    GridBagConstraints gbc_verticalStrut_2 = new GridBagConstraints();
    gbc_verticalStrut_2.insets = new Insets(0, 0, 5, 5);
    gbc_verticalStrut_2.gridx = 2;
    gbc_verticalStrut_2.gridy = 5;
    frame.getContentPane().add(verticalStrut_2, gbc_verticalStrut_2);

    lblNewLabel_3 = new JLabel("Step 3:");
    GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
    gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
    gbc_lblNewLabel_3.gridx = 1;
    gbc_lblNewLabel_3.gridy = 6;
    frame.getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

    buttonCompare = new JButton("Compare images...");
    buttonCompare.setEnabled(false);
    buttonCompare.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        settings.setAutoTrashingEnabled(checkMove.isSelected());
        settings.setSuddenDeathEnabled(checkSuddenDeath.isSelected());
        settings.setTargetFolder(new File(textMove.getText()));
        settings.setTargetFolderValid(settings.getTargetFolder().isDirectory());
        if (settings.isAutoTrashingEnabled() && !settings.isTargetFolderValid()) {
          JOptionPane.showMessageDialog(frame, settings.getTargetFolder() + " is not a valid folder. Moving/copying files is disabled.", "Error",
              JOptionPane.WARNING_MESSAGE);
        }
        settings.setTrashFolder(new File(settings.getTargetFolder(), "excluded"));
        if (settings.isAutoTrashingEnabled() && settings.isTargetFolderValid()) {
          int rollingPostfix = 0;
          while (settings.getTrashFolder().exists() && !settings.getTrashFolder().isDirectory()) {
            settings.setTrashFolder(new File(settings.getTargetFolder(), "excluded_" + (rollingPostfix++)));
          }
          if (!settings.getTrashFolder().exists() && !settings.getTrashFolder().mkdir()) {
            JOptionPane.showMessageDialog(frame, settings.getTrashFolder() + " could not be created. Moving excluded files is disabled.", "Error",
                JOptionPane.WARNING_MESSAGE);
            settings.setTrashFolderValid(false);
          } else {
            settings.setTrashFolderValid(true);
          }
        }

        frame.setVisible(false);
        ImgWindow imgframe = new ImgWindow(settings);
        imgframe.addWindowListener(new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent e1) {
            frame.setVisible(true);
            imgframe.dispose();
          }
        });
        imgframe.setFiles(files);
        imgframe.setVisible(true);

        if ((frame.getExtendedState() & JFrame.MAXIMIZED_BOTH) != 0) {
          imgframe.setExtendedState(imgframe.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        }
      }
    });
    GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
    gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
    gbc_btnNewButton_2.gridx = 3;
    gbc_btnNewButton_2.gridy = 6;
    frame.getContentPane().add(buttonCompare, gbc_btnNewButton_2);

    labelCompare = new JLabel("Please scan for images");
    GridBagConstraints gbc_labelCompare = new GridBagConstraints();
    gbc_labelCompare.gridwidth = 2;
    gbc_labelCompare.insets = new Insets(0, 0, 5, 5);
    gbc_labelCompare.gridx = 4;
    gbc_labelCompare.gridy = 6;
    frame.getContentPane().add(labelCompare, gbc_labelCompare);

    checkMove = new JCheckBox("Automatically move excluded files");
    checkMove.setToolTipText("If enabled, images you \"exclude\" will automatically be moved to a subfolder \"excluded\" in the target folder.");
    GridBagConstraints gbc_checkMove = new GridBagConstraints();
    gbc_checkMove.anchor = GridBagConstraints.NORTHWEST;
    gbc_checkMove.gridwidth = 2;
    gbc_checkMove.insets = new Insets(0, 0, 5, 5);
    gbc_checkMove.gridx = 3;
    gbc_checkMove.gridy = 7;
    frame.getContentPane().add(checkMove, gbc_checkMove);

    checkSuddenDeath = new JCheckBox("Use \"Sudden Death\" voting");
    checkSuddenDeath.setToolTipText("Images that are not given a \"like\" are eliminated from the competition. (Note that they are not \"excluded\".)");
    GridBagConstraints gbc_checkSuddenDeath = new GridBagConstraints();
    gbc_checkSuddenDeath.anchor = GridBagConstraints.WEST;
    gbc_checkSuddenDeath.gridwidth = 2;
    gbc_checkSuddenDeath.insets = new Insets(0, 0, 5, 5);
    gbc_checkSuddenDeath.gridx = 3;
    gbc_checkSuddenDeath.gridy = 8;
    frame.getContentPane().add(checkSuddenDeath, gbc_checkSuddenDeath);

    btnNewButton = new JButton("UI ⇄");
    btnNewButton.addActionListener(nextLaf);
    GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
    gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
    gbc_btnNewButton.gridx = 5;
    gbc_btnNewButton.gridy = 9;
    frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
  }

}
