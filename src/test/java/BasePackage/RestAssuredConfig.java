//package BasePackage;
//
//import io.restassured.RestAssured;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.client.HttpClientBuilder;
//
//public class RestAssuredConfig {
//
//    static {
//        // Configure the request config with 5-minute timeout
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(300000) // Connection timeout (5 minutes)
//                .setSocketTimeout(300000)  // Read timeout (5 minutes)
//                .build();
//
//        // Create an HTTP client with the custom request configuration
//        CloseableHttpClient httpClient = HttpClients.custom()
//                .setDefaultRequestConfig(requestConfig)
//                .build();
//
//        // Set the HTTP client for RestAssured
//        RestAssured.config = RestAssured.config()
//                .httpClient(RestAssured.config().httpClientConfig()
//                        .httpClientFactory(() -> httpClient));
//    }
//}