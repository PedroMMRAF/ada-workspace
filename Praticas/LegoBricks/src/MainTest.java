import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MainTest {
    @Test
    void test1() {
        test("4 4\n....\nbbyy\nbyyy\n....\n", 16);
    }

    @Test
    void test2() {
        test("3 6\nggrrrr\ngyyrrr\nggrrrr\n", 2048);
    }

    @Test
    void test3() {
        //
        test(
                "16 16\n" +
                "................\n" +
                "......R.........\n" +
                "................\n" +
                "..R.R.R.R.R.RRR.\n" +
                "..RRR.R.R.R.R.R.\n" +
                "..R.R.R.R.R.RRR.\n" +
                "..R.R.R.RRR.R...\n" +
                "YYYYYYYYYYYYYYYY\n" +
                "YYYYYYYYYYYYYYYY\n" +
                "...GGG.GGG.G.G..\n" +
                ".....G.G.G.G.G..\n" +
                "...GGG.G.G.G.G..\n" +
                "...G...G.G.G.G..\n" +
                "...GGG.GGG.G.G..\n" +
                "................\n" +
                "................\n",
                153_979_111_604_224L
        );
    }

    private void test(String input, long output) {
        try {
            assertEquals(Main.run(new ByteArrayInputStream(input.getBytes())), output);
        }
        catch (IOException err) {
            fail(err);
        }
    }
}