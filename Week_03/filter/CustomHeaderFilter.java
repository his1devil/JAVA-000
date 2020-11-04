package filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Author qiujun
 * @Version @version $Id: CustomHeaderFilter, v0.1 2020-11-04 4:08 下午 qiujun Exp $
 * @Description
 */
public class CustomHeaderFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx) {
        fullHttpRequest.headers().set("netty-server", "localhost");
    }
}
