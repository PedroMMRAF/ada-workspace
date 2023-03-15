import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedScanner {
    private final InputStreamReader reader;
    private char[] charBuffer;
    private int bufferCount;
    private int bufferSize;

    public BufferedScanner(InputStream stream, int bufferSize) {
        this.reader = new InputStreamReader(stream);
        this.charBuffer = new char[bufferSize];
        this.bufferSize = bufferSize;
    }

    private void resize() {
        this.bufferSize *= 2;
        char[] newBuffer = new char[bufferSize * 2];
        System.arraycopy(charBuffer, 0, newBuffer, 0, newBuffer.length);
        this.charBuffer = newBuffer;
    }

    public void nextLine() throws IOException {
        int read;
        while ((read = reader.read()) != '\n') {
            charBuffer
        }
    }

    public int nextInt() {

    }

}
