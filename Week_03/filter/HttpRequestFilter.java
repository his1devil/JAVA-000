package filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Author qiujun
 * @Version @version $Id: HttpRequestFilter, v0.1 2020-11-04 4:06 下午 qiujun Exp $
 * @Description
 */
public interface HttpRequestFilter {
    void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx);
}
