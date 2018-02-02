package nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
/**
 * 阻塞传输
 */
public class TestSocketChannel {

    @Test
    public  void  client() throws IOException {
        SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",9999));
        FileChannel inChannel=FileChannel.open(Paths.get("D:/web/java8.zip"), StandardOpenOption.READ);
        ByteBuffer buffer=ByteBuffer.allocateDirect(10240);
        while (inChannel.read(buffer)!=-1){
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }
        socketChannel.shutdownInput();
        //接收服务器反馈
        int len=0;
        while ((len=socketChannel.read(buffer))!=-1){
            buffer.flip();
            System.out.println(new String(buffer.array(),0,len));
            buffer.clear();
        }
        socketChannel.close();
        inChannel.close();


    }

    @Test
    public void server() throws IOException {
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        SocketChannel socketChannel=serverSocketChannel.accept();
        FileChannel outChannel=FileChannel.open(Paths.get("D:/web/java.zip"),StandardOpenOption.READ,StandardOpenOption.WRITE,StandardOpenOption.CREATE);
        ByteBuffer buffer=ByteBuffer.allocateDirect(10240);
        while (socketChannel.read(buffer)!=-1){
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }
        buffer.put("接收数据成功".getBytes());
        buffer.flip();
        outChannel.write(buffer);
        socketChannel.close();
        outChannel.close();
        serverSocketChannel.close();
    }
}
