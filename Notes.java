import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.TitledBorder;


public class Notes implements ActionListener {
    JFrame window;
    JTextArea textArea;
    JScrollPane scrollPane;
    JMenuBar menu;
    JMenu File, Help;
    JMenuItem JNew, JOpen, JSave, JSaveAs;
    JPanel mainPanel, fontPanel, alignPanel, timerPanel;
    JComboBox<String> fontComboBox, fontSize, fontColor, fontStyle, fontBG;
    String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    JButton Left, Center, Right;

    NoteFunction noteFunc = new NoteFunction(this);
    FontFunction fontFunc;

    public Notes() {
        createWindow();
        createTextArea();
        createMenu();
        createMenuItem();
        createFormatPanel();
        createFontPanel();
        createAlignPanel();
        fontFunc = new FontFunction(textArea, fontComboBox, fontSize, fontColor, fontStyle, fontBG);
        window.setVisible(true);
        
        // Instantiate LockdownManager
        new LockdownManager(this); // Passing reference of Notes to LockdownManager
        
        window.setVisible(true);
    }

    public void createWindow() {
        window = new JFrame("Study Up");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set the window to full screen
        window.setSize(1000, 600); // Set the desired width and height
        window.setLocationRelativeTo(null);
        
    }

    public void createTextArea() {
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(40, 40, 40, 40));
        scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        window.add(scrollPane);
    }

    public void createFormatPanel() {
        mainPanel = new JPanel();
        mainPanel.setBorder(new TitledBorder("Note Formatting"));
        mainPanel.setLayout(new GridLayout(1, 3));
        mainPanel.setPreferredSize(new Dimension(1000, 120));
        mainPanel.setBackground(Color.getHSBColor(202, 94, 88));
        window.add(mainPanel, BorderLayout.NORTH);

        fontPanel = new JPanel();
        fontPanel.setBorder(new TitledBorder("Font"));
        fontPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        fontPanel.setBackground(Color.white);
        mainPanel.add(fontPanel);

        alignPanel = new JPanel();
        alignPanel.setBorder(new TitledBorder("Align"));
        alignPanel.setLayout(new GridLayout(2, 2));
        alignPanel.setBackground(Color.white);
        mainPanel.add(alignPanel);

        timerPanel = new JPanel();
        timerPanel.setBorder(new TitledBorder("Timer"));
        timerPanel.setBackground(Color.white);
        mainPanel.add(timerPanel);
    }

    public void createFontPanel() {
        fontComboBox = new JComboBox<>(fontNames);
        fontSize = new JComboBox();
        fontColor = new JComboBox();
        fontBG = new JComboBox();
        fontStyle = new JComboBox();

        fontPanel.add(fontComboBox);

        fontPanel.add(fontSize);
        fontSize.addItem("12");
        fontSize.addItem("18");
        fontSize.addItem("24");

        fontPanel.add(fontColor);
        fontColor.addItem("Black");
        fontColor.addItem("Gray");
        fontColor.addItem("Red");
        fontColor.addItem("Green");
        fontColor.addItem("Orange");
        fontColor.addItem("Magenta");

        fontPanel.add(fontBG);
        fontBG.addItem("Green Highlight");
        fontBG.addItem("Cyan Highlight");
        fontBG.addItem("Pink Highlight");
        
        fontPanel.add(fontStyle);
        fontStyle.addItem("Bold");
        fontStyle.addItem("Italic");
    }

    public void createAlignPanel() {
        Left = new JButton("Left");
        Right = new JButton("Right");
        Center = new JButton("Center");
        

        Left.addActionListener(this);
        Right.addActionListener(this);
        Center.addActionListener(this);
        

        alignPanel.add(Left);
        alignPanel.add(Right);
        alignPanel.add(Center);
        
    }

    public void createMenu() {
        menu = new JMenuBar();
        window.setJMenuBar(menu);

        File = new JMenu("File");
        menu.add(File);

        Help = new JMenu("Help");
        menu.add(Help);
    }

    public void createMenuItem() {
        JNew = new JMenuItem("New");
        JNew.addActionListener(this);
        JNew.setActionCommand("New Note");
        File.add(JNew);

        JOpen = new JMenuItem("Open");
        JOpen.addActionListener(this);
        JOpen.setActionCommand("Open Note");
        File.add(JOpen);

        JSave = new JMenuItem("Save");
        JSave.addActionListener(this);
        JSave.setActionCommand("Save Note");
        File.add(JSave);

        JSaveAs = new JMenuItem("Save As");
        JSaveAs.addActionListener(this);
        JSaveAs.setActionCommand("Note Save As");
        File.add(JSaveAs);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        switch (cmd) {
            case "New Note":
                noteFunc.newNote();
                break;
            case "Open Note":
                noteFunc.openNote();
                break;
            case "Save Note":
                noteFunc.saveNote();
                break;
            case "Note Save As":
                noteFunc.saveAsNote();
                break;
            case "Left":
                alignText("Left");
                break;
            case "Center":
                alignText("Center");
                break;
            case "Right":
                alignText("Right");
                break;
            
        }
    }

    private void alignText(String alignment) {
        String text = textArea.getText();
        String[] lines = text.split("\n");
        StringBuilder alignedText = new StringBuilder();

        for (String line : lines) {
            switch (alignment) {
                case "Left":
                    alignedText.append(line).append("\n");
                    break;
                case "Right":
                    int spacesToCenter = (textArea.getWidth() - textArea.getFontMetrics(textArea.getFont()).stringWidth(line)) / 2;
                    alignedText.append(" ".repeat(Math.max(0, spacesToCenter))).append(line).append("\n");
                    break;
                case "Center":
                    int spacesToRight = textArea.getWidth() - textArea.getFontMetrics(textArea.getFont()).stringWidth(line);
                    alignedText.append(" ".repeat(Math.max(0, spacesToRight))).append(line).append("\n");
                    break;
                
            }
        }
        textArea.setText(alignedText.toString());
    }

    public static void main(String[] args) {
        new Notes();
    }
}
