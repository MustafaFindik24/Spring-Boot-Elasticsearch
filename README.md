# Elasticsearch Nedir?

![elasticsearch](https://user-images.githubusercontent.com/91599453/224652456-ab7a8625-182f-47ad-bb37-cf84a49a7787.png)

Java programlama dili ile geliştirilmiş, Apache Lucene alt yapısına sahip, açık kaynaklı ve ölçeklenebilir olan bir metin arama motoru aracıdır. Verileri ilişkisel olarak değil, döküman tabanlı olarak tutar. Elasticsearch doğrudan metin üzerinden arama yapmak yerine indexlenmiş veriler üzerinden arama yapar. Bu da veriden daha hızlı sonuç üretilmesini sağlar. Dökümanlar JSON tipinde indexlendiği için işlem hızı yüksektir.

# 🎯 Spring Boot uygulamasında Elasticsearch kullanımı

* Spring Boot uygulamasını oluşturduktan sonra pom.xml dosyamızın içerisine Elasticsearch dependency sini ekliyoruz.

```xml
<dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-elasticsearch</artifactId>
</dependency>
```

* Elasticsearch uygulamamızı docker üstünde çalıştıracağız. Bu yüzden Docker Compose dosyamızı projemize ekleyip Elasticsearch container ı çalıştırıyoruz.

```yml
version: '2.2'
services:
  elasticsearch:
    container_name: elasticsearch
    image: docker.elastic.co/elasticsearch/elasticsearch:7.5.2
    ports:
      - "9200:9200"
      - "9300:9300"
    environment:
      - bootstrap.memory_lock=true
      - cluster.name=demo-es
      - ES_JAVA_OPTS=-Xms512m -Xmx512m
      - discovery.type=single-node
      - http.cors.enabled=true
      - http.cors.allow-origin=*
    ulimits:
      memlock:
        soft: -1
        hard: -1
```
* @EnableElasticsearchRepositories anotasyonunu @SpringBootApplication sınıfımızın içerisine ekleyip modelimizi document olarak indexlemek için User sınıfımızda @Document anotasyonunu kullanıyoruz.

```java
@Document(indexName = "users")
public class User {
    @Id
    private String id;
    @Field(name = "isim", type = FieldType.Text)
    private String name;
    @Field(name = "soyisim", type = FieldType.Text)
    private String surname;
    @Field(name = "adres", type = FieldType.Text)
    private String address;
    @Field(name = "dogum_tarihi", type = FieldType.Text)
    private String birthday;
}
```
* Daha sonra UserRepository içerisinde iki farklı Query kullanıldı. Bunlardan birisi elasticsearch, diğeri ise Spring Framework ünün içerisinde bulunan query dir.

```java

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {
    @Query("{\"bool\": {\"must\": [{\"match\": {\"isim\": \"?0\"}}]}}") //boolean query olarak ad ile örtüşmeli
    List<User> getByCustomQuery(String search);
    List<User> findByNameLikeOrSurnameLike(String name, String surname);
}
```
* Service sınıfı oluşturulup RESTAPI yazımı sağlandıktan sonra config package içerisinde Swagger entegrasyonu için @Configuration sınıfı oluşturuldu.

```java
@Configuration
public class OpenUI {

    @Bean
    public OpenAPI customOpenAPI (@Value("${application-description}") String description,
                                  @Value("${application-version}") String version){
        return new OpenAPI()
                .info(new Info()
                        .title("Elasticsearch API")
                        .version(version)
                        .description(description)
                        .license(new License()
                                .name("Elasticsearch API License")));
    }
}
```
* Docker-compose dosyasını çalıştırıp uygulamamızı ayağa kaldırdıktan sonra http://localhost:8080/swagger-ui/index.html üzerinden swaggera giriş yapıyoruz.

![image](https://user-images.githubusercontent.com/91599453/224698907-b5967e80-2c42-4509-bacf-a57df5c8c7c8.png)

* Sonrasında eklediğimiz kayıtları listeleyip get metodu ile birlikte spesifik olarak indexlenen documentları getirdiğini görebiliriz.

![image](https://user-images.githubusercontent.com/91599453/224699344-ecd80854-dd0f-4aaa-8a1b-d8da3e2d04d2.png)
![image](https://user-images.githubusercontent.com/91599453/224699438-506fe09c-d121-4e01-abdb-f71941363c75.png)
