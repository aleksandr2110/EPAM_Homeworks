package springintegration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import springintegration.order.Order;
import springintegration.state.OrderState;

import java.util.List;

/**
 * Created by Aleksandr on 18.11.2020.
 */
@Configuration
@EnableIntegration
@ComponentScan(basePackages="springintegration")
@IntegrationComponentScan
public class BasicIntegrationConfig {

    @Bean
    DirectChannel outputChannel() {
        return new DirectChannel();
    }

    @MessagingGateway
    public interface I {
        @Gateway(requestChannel = "orderIFlow.input")
        void processList(List<Order> orders);
    }
    // channel DirectChannel with name orderIFlow.input creates automatic
    @Bean
    public IntegrationFlow orderIFlow() {
        return flow -> flow
                .handle("orderService", "processList")
                .split()
                .<Order> filter(a->!a.getOrderState().equals(OrderState.CANCELED))
                .channel("outputChannel");
    }
}
