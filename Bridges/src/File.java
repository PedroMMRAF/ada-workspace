package Bridges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class File {
    public static void main(String[] args) throws IOException {
        String file;

        try (BufferedReader rd = new BufferedReader(new InputStreamReader(System.in))) {
            file = String.format("data/%s.txt", rd.readLine());
        }

        System.setIn(File.class.getResourceAsStream(file));
        Main.main(args);
    }
}
