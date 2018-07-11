import org.junit.Assert;
import org.junit.Test;

public class LibraryTest {
    @Test
    public void testCompress() {
        String original = "helloworld";
        Assert.assertFalse(Library.isCompressed(original));
        String compressed = Library.compress(original);
        Assert.assertTrue(Library.isCompressed(compressed));
        Assert.assertEquals(original, Library.decompress(compressed));
    }
}
