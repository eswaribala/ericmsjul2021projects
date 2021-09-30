package elasticsearch.example.demo;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan("elasticsearch.example.demo.properties")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public boolean createTestIndex(RestHighLevelClient restHighLevelClient) throws Exception {
		LocalDateTime ldt = LocalDateTime.now();
		ZonedDateTime zdt = ldt.atZone(ZoneId.of("America/Los_Angeles"));
		long millis = zdt.toInstant().toEpochMilli();
		try {
			DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("rps-sb-dell"+millis);
			restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
		} catch (Exception ignored) {
		}

		CreateIndexRequest createIndexRequest = new CreateIndexRequest("rps-sb-dell"+millis);
		createIndexRequest.settings(
				Settings.builder().put("index.number_of_shards", 1)
						.put("index.number_of_replicas", 0));
		restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);

		return true;
	}
}
