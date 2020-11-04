package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author qiujun
 * @Version @version $Id: HttpServer, v0.1 2020-11-03 10:33 上午 antaifeng qiujun $
 * @Description
 */
@Slf4j
public class HttpServer {
    
    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    private ServerBootstrap serverBootstrap;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        serverBootstrap = new ServerBootstrap();
        bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("boss", true));
        workerGroup = new NioEventLoopGroup(0, new DefaultThreadFactory("worker", true));
        serverBootstrap.group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 1024)
            .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE);
            //.childHandler(new HttpHandlerInitializer());

        ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
        if (channelFuture.isSuccess()) {
            logger.info("Server started with port={}", port);
        }
        channelFuture.channel().closeFuture().sync();
    }

    public void shutdown() {
        try {
            if (bossGroup != null) {
                bossGroup.shutdownGracefully();
            }
            if (workerGroup != null) {
                workerGroup.shutdownGracefully();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }


}
