package mk.ukim.finki.emt.favouritemanagement.port.client;

import mk.ukim.finki.emt.sharedkernel.domain.base.RemoteEventLog;
import mk.ukim.finki.emt.sharedkernel.infra.eventlog.RemoteEventLogService;
import mk.ukim.finki.emt.sharedkernel.infra.eventlog.StoredDomainEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Service
public class ProductsEventLogServiceClient implements RemoteEventLogService {

    private final String source;
    private final String serverUrl;
    private final RestTemplate restTemplate;

    public ProductsEventLogServiceClient(@Value("${app.product-catalog.url}") String serverUrl,
                                       @Value("${app.product-catalog.connect-timeout-ms}") int connectTimeout,
                                       @Value("${app.product-catalog.read-timeout-ms}") int readTimeout) {
        this.source = Objects.requireNonNull(serverUrl, "serverUrl must not be null");
        this.serverUrl = serverUrl;
        restTemplate = new RestTemplate();
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        restTemplate.setRequestFactory(requestFactory);
    }

    @Override
    @NonNull
    public String source() {
        return this.source;
    }

    @Override
    public RemoteEventLog currentLog(long lastProcessedId) {
        URI currentLogUri = UriComponentsBuilder.fromUriString(serverUrl)
                .path(String.format("/api/event-log/%d", lastProcessedId)).build().toUri();
        return retrieveLog(currentLogUri);
    }

    @NonNull
    private RemoteEventLog retrieveLog(@NonNull URI uri) {
        ResponseEntity<List<StoredDomainEvent>> response = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<List<StoredDomainEvent>>() {
        });
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new IllegalArgumentException("Could not retrieve log from URI " + uri);
        }
        return new RemoteEventLogImpl(response);
    }

    private class RemoteEventLogImpl implements RemoteEventLog {

        private final List<StoredDomainEvent> events;

        private RemoteEventLogImpl(@NonNull ResponseEntity<List<StoredDomainEvent>> responseEntity) {
            events = List.copyOf(Objects.requireNonNull(responseEntity.getBody()));
        }

        @Override
        public List<StoredDomainEvent> events() {
            return events;
        }
    }


}
