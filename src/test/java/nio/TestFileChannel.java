package nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestFileChannel {

    @Test
    public void Test1() throws IOException {
        FileChannel inChannel=FileChannel.open(Paths.get("D:/web/java8.zip"), StandardOpenOption.READ);
        FileChannel outChannel=FileChannel.open(Paths.get("D:/web/java.zip"),StandardOpenOption.WRITE,StandardOpenOption.CREATE,StandardOpenOption.READ);
        MappedByteBuffer inMappedByteBuffer= inChannel.map(FileChannel.MapMode.READ_ONLY,inChannel.position(),inChannel.size());
        MappedByteBuffer outMappedByteBuffer=outChannel.map(FileChannel.MapMode.READ_WRITE,outChannel.position(),inChannel.size());
        //建立直接缓冲区

        byte[] bytes=new byte[inMappedByteBuffer.limit()];
        inMappedByteBuffer.get(bytes);
        outMappedByteBuffer.put(bytes);
        inChannel.close();
        outChannel.close();

    }

    public static void main(String[] args) throws IOException {
        FileChannel inChannel=FileChannel.open(Paths.get("D:/web/java8.zip"), StandardOpenOption.READ);
        FileChannel outChannel=FileChannel.open(Paths.get("D:/web/java.zip"),StandardOpenOption.WRITE,StandardOpenOption.CREATE,StandardOpenOption.READ);
//      inChannel.transferTo(0,inChannel.size(),outChannel);
        outChannel.transferFrom(inChannel,inChannel.position(),inChannel.size());
        inChannel.close();
        outChannel.close();
    }
}
