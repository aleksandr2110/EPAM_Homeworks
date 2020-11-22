package springintegration;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.integration.channel.DirectChannel;
import springintegration.config.BasicIntegrationConfig;
import springintegration.order.Order;
import springintegration.state.OrderState;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aleksandr on 18.11.2020.
 */
public class Main {

    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Main.class);

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(BasicIntegrationConfig.class);
        ctx.refresh();
        String csvFile = "src/main/java/springintegration/orders.csv";
        List<Order> orderList = getOrders(csvFile);
        DirectChannel outputChannel = ctx.getBean("outputChannel", DirectChannel.class);
        outputChannel.subscribe(o -> logger.debug("Output: {}", o));
        ctx.getBean(BasicIntegrationConfig.I.class).processList(orderList);
    }
    public static List<Order> getOrders(String csvFile)
    {
        CSVReader reader = null;
        List<Order> orderList = new ArrayList<>();
        int x=0;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                if(x==0) {
                    x++;
                    continue;
                }

                long id = Long.parseLong(line[0]);
                OrderState orderState = OrderState.valueOf(line[1]);
                orderList.add(new Order(id, orderState, line[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}
