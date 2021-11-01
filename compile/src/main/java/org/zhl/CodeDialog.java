package org.zhl;

import javax.swing.*;
import java.io.*;
import java.util.Objects;

/**
 * @author zhanghanlin
 * @date 2021/10/31
 **/
public class CodeDialog extends Reader {

    private String buffer = null;

    private int pos = 0;

    protected String showDialog() {
        JTextArea area = new JTextArea(20, 40);
        area.setTabSize(4);
        JScrollPane pane = new JScrollPane(area);
        int result = JOptionPane
            .showOptionDialog(null, pane, "Input", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null,
                null);
        if (Objects.equals(result,JOptionPane.OK_OPTION)){
            return area.getText();
        }else {
            return null;
        }
    }

    public  static Reader file() throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
            return new BufferedReader(new FileReader(chooser.getSelectedFile()));
        }else {
            throw new FileNotFoundException("no file specified");
        }
    }

    protected void print(String s){
        System.out.printf(s);
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        if (Objects.isNull(buffer)) {
            String in = showDialog();
            if (Objects.isNull(in)) {
                return -1;
            }else {
                print(in);
                buffer = in + "\n";
                pos = 0;
            }
        }
        int size = 0 ;
        int length = buffer.length();
        while (pos < length && size < len){
            cbuf[off+size++] = buffer.charAt(pos++);
        }
        if (pos == length){
            buffer = null;
        }
        return size;
    }

    @Override
    public void close() throws IOException {

    }
}
