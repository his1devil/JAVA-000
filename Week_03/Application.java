import inbound.HttpInboundServer;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author qiujun
 * @Version @version $Id: Application, v0.1 2020-11-03 2:26 下午 qiujun Exp $
 * @Description
 */
@Slf4j
public class Application {

    public static final String GATEWAY_NAME ="NIOGateway";

    public static final String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) {
        String proxyServer = System.getProperty("proxyServer", "http://localhost:8080");
        String proxyPort = System.getProperty("proxyPort", "8888");

        HttpInboundServer server = new HttpInboundServer(8888, "http://localhost:8080");
        log.info("GATEWAY={} {} started.", GATEWAY_NAME, GATEWAY_VERSION);
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
