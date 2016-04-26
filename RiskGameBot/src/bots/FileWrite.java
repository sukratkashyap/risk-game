package bots;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JTextArea;

public class FileWrite {

    private static StringBuilder builder = new StringBuilder();

    public static void WriteFile(String text, boolean isAppend, JTextArea textArea) {
        builder.append(text).append("\n");
        try (FileWriter fw = new FileWriter("./output.txt", isAppend);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(builder.toString());
            builder = new StringBuilder();
            textArea.setText("");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
