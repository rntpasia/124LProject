

import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

public class FontFunction {
    private JTextArea textArea;
    private JComboBox<String> fontComboBox;
    private JComboBox<String> fontSizeComboBox;
    private JComboBox<String> fontColorComboBox;
    private JComboBox<String> fontStyleComboBox;
    private JComboBox<String> highlightColorComboBox;
    private ArrayList<Object> highlights;

    public FontFunction(JTextArea textArea, JComboBox<String> fontComboBox, JComboBox<String> fontSizeComboBox, JComboBox<String> fontColorComboBox, JComboBox<String> fontStyleComboBox, JComboBox<String> highlightColorComboBox) {
        this.textArea = textArea;
        this.fontComboBox = fontComboBox;
        this.fontSizeComboBox = fontSizeComboBox;
        this.fontColorComboBox = fontColorComboBox;
        this.fontStyleComboBox = fontStyleComboBox;
        this.highlightColorComboBox = highlightColorComboBox;
        this.highlights = new ArrayList<>();
        
        setupListeners();
    }

    private void setupListeners() {
        fontComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    setFontFamily();
                }
            }
        });

        fontSizeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    setFontSize();
                }
            }
        });

        fontColorComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    setFontColor();
                }
            }
        });

        fontStyleComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    setFontStyle();
                }
            }
        });

        highlightColorComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    setHighlightColor();
                }
            }
        });
    }

    private void setFontFamily() {
        String selectedFont = (String) fontComboBox.getSelectedItem();
        textArea.setFont(new Font(selectedFont, textArea.getFont().getStyle(), textArea.getFont().getSize()));
    }

    private void setFontSize() {
        int selectedSize = Integer.parseInt((String) fontSizeComboBox.getSelectedItem());
        textArea.setFont(new Font(textArea.getFont().getFamily(), textArea.getFont().getStyle(), selectedSize));
    }

    private void setFontColor() {
        String selectedColorStr = (String) fontColorComboBox.getSelectedItem();
        Color selectedColor = Color.BLACK; // Default color

        switch (selectedColorStr) {
            case "Green":
                selectedColor = Color.GREEN;
                break;
            case "Magenta":
                selectedColor = Color.MAGENTA;
                break;
            case "Red":
                selectedColor = Color.RED;
                break;
            case "Gray":
                selectedColor = Color.GRAY;
                break;
            case "Orange":
                selectedColor = Color.ORANGE;
                break;
            case "Black":
            default:
                selectedColor = Color.BLACK;
                break;
        }

        textArea.setForeground(selectedColor);
    }

    private void setFontStyle() {
        String selectedStyle = (String) fontStyleComboBox.getSelectedItem();
        int style = Font.PLAIN; 

        if ("Bold".equals(selectedStyle)) {
            style = Font.BOLD;
        }  
        
        if ("Italic".equals(selectedStyle)) {
            style = Font.ITALIC;
        } 

        textArea.setFont(new Font(textArea.getFont().getFamily(), style, textArea.getFont().getSize()));
    }

    private void setHighlightColor() {
        String selectedColorStr = (String) highlightColorComboBox.getSelectedItem();
        Color highlightColor = getHighlightColor(selectedColorStr);
        applyHighlight(highlightColor);
    }

    private Color getHighlightColor(String colorStr) {
        switch (colorStr) {
           
            case "Green Highlight":
                return Color.GREEN;
            case "Cyan Highlight":
                return Color.CYAN;
            case "Pink Highlight":
                return Color.PINK;
            default:
                return Color.WHITE; 
        }
    }

      private void applyHighlight(Color color) {
        if (color != null) {
            Highlighter highlighter = textArea.getHighlighter();
            HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(color);
            try {
                int start = textArea.getSelectionStart();
                int end = textArea.getSelectionEnd();
                Object highlight = highlighter.addHighlight(start, end, painter);
                highlights.add(highlight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
      
}
