package net.petrikainulainen.spring.trenches.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class is a configuration bean that contains the configuration of our web layer.
 * @author Petri Kainulainen
 */
@Component
public final class WebProperties {

    private final String protocol;

    private final String serverHost;

    private final int serverPort;

    @Autowired
    public WebProperties(@Value("${app.server.protocol}") String protocol,
                         @Value("${app.server.host}") String serverHost,
                         @Value("${app.server.port}") int serverPort) {
        checkThatProtocolIsValid(protocol);

        this.protocol = protocol.toLowerCase();
        this.serverHost = serverHost;
        this.serverPort = serverPort;
    }

    private void checkThatProtocolIsValid(String protocol) {
        if (!protocol.equalsIgnoreCase("http") && !protocol.equalsIgnoreCase("https")) {
            throw new IllegalArgumentException(String.format(
                    "Protocol: %s is not allowed. Allowed protocols are: http and https.",
                    protocol
            ));
        }
    }

    /**
     * Returns the protocol that is used to access our application. Legal values are: http and https.
     * @return
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Returns the host of the server that runs our application.
     * @return
     */
    public String getServerHost() {
        return serverHost;
    }

    /**
     * Returns the port that listened by the server that runs our application.
     * @return
     */
    public int getServerPort() {
        return serverPort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("protocol", this.protocol)
                .append("serverHost", this.serverHost)
                .append("serverPort", this.serverPort)
                .toString();
    }
}
