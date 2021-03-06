import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.io.IOUtils;

public class Library {
        public static String compress(String string) {
                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(string.length())) {
                        try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream)) {
                                gzipOutputStream.write(string.getBytes("UTF-8"));
                        }
                        return outputStream.toString("ISO-8859-1");
                } catch (IOException e) {
                        throw new RuntimeException("compress string failed");
                }
        }

        public static String decompress(String string) {
                try (GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(string.getBytes("ISO-8859-1")))) {
                        return new String(IOUtils.toByteArray(gzipInputStream), "UTF-8");
                } catch (IOException e) {
                        throw new RuntimeException("decompress string failed");
                }
        }

        public static boolean isCompressed(String string) {
                try {
                        byte[] bytes = string.getBytes("ISO-8859-1");
                        return (bytes[0] == (byte) (GZIPInputStream.GZIP_MAGIC)) && (bytes[1] == (byte) (GZIPInputStream.GZIP_MAGIC >> 8));
                } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException("check is compressed failed");
                }
        }
}
