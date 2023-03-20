package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] dims = reader.readLine().split(" ");

            MatrixProblem p = new MatrixProblem(dims.length);

            for (String dim : dims) {
                p.add(Integer.parseInt(dim));
            }

            System.out.println(p.solve());
        }
    }
}