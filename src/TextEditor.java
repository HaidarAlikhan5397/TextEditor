import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import javax.swing.border.EmptyBorder;
//import java.io.BorderLayout;
public class TextEditor implements ActionListener {
    //    Declaring properties of TexEditor
    JFrame frame;

    JMenuBar menuBar;

    JMenu file, edit;

    //    File menuItems
    JMenuItem newFile, openFile, saveFile;

    //   EditMenu Items
    JMenuItem cut, copy, paste, selectAll, close;

    //    TextArea
    JTextArea textarea;

    TextEditor() {
//        Initialize a frame
        frame = new JFrame();

//        Initialize a MenuBar
        menuBar = new JMenuBar();

//         Initialize textarea
        textarea = new JTextArea();

//        Initialize Menus
        file = new JMenu("File");
        edit = new JMenu("Edit");


//         Initialize fileMenu items
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

//        Add Action Listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

//        Add menuItem to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);


//       Initialize edit menu items
        cut = new JMenuItem("Cut");
        paste = new JMenuItem("Paste");
        copy = new JMenuItem("copy");
        selectAll = new JMenuItem("SelectAll");
        close = new JMenuItem("Close");

//        Adding ActionListener to the Edit Menu
        cut.addActionListener(this);
        paste.addActionListener(this);
        copy.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

//        Adding to EditMenu
        edit.add(cut);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(copy);
        edit.add(close);

//        Add Menus in menuBar
        menuBar.add(file);
        menuBar.add(edit);

//        Set MenuBar to frame
        frame.setJMenuBar(menuBar);

//       create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textarea, BorderLayout.CENTER);
//        CREATE SCROLL PANE
        JScrollPane scrollPane = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        add scroll pane  to panel
        panel.add(scrollPane);
//        add panel to frame
        frame.add(panel);

//       Set Dimension of Frame
        frame.setBounds(100, 100, 400, 400);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            textarea.cut();
        }
        if (actionEvent.getSource() == paste) {
            textarea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            textarea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            System.exit(0);
        }
        if (actionEvent.getSource() == openFile) {
//            Open a fileChooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
//            If we have clicked on open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
//                Getting selected file
                File file = fileChooser.getSelectedFile();
//                Get the path of selected file
                String filePath = file.getPath();
                try {
//                    initial file  reader
                    FileReader fileReader = new FileReader(filePath);
//                    Initial Buffered  Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
//                  REad content of file line by line
                    while ((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    textarea.setText(output);
                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if (actionEvent.getSource() == saveFile) {
//            Open a fileChooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
//            If we have clicked on open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".text");

                try {
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textarea.write(bufferedWriter);
                    bufferedWriter.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }


    public static void main(String[] args) {
        TextEditor te = new  TextEditor();
    }
}
