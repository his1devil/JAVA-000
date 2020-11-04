package inbound;

import filter.CustomHeaderFilter;
import filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import outbound.HttpOutboundHandler;

/**
 * @Author qiujun
 * @Version @version $Id: HttpInboundHandler, v0.1 2020-11-03 3:29 下午 qiujun Exp $
 * @Description
 */
@Slf4j
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private final String proxyServer;
    private HttpOutboundHandler outboundHandler;
    private HttpRequestFilter filter;

    public HttpInboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
        outboundHandler = new HttpOutboundHandler(this.proxyServer);
        this.filter = new CustomHeaderFilter();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            filter.filter(fullHttpRequest, ctx);
            outboundHandler.handle(fullHttpRequest, ctx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
