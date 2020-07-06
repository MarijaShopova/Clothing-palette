package mk.ukim.finki.emt.productcatalog;

import mk.ukim.finki.emt.sharedkernel.infra.eventlog.RemoteEventLogService;
import mk.ukim.finki.emt.sharedkernel.port.client.RemoteEventLogServiceClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogApplication.class, args);
    }
}
