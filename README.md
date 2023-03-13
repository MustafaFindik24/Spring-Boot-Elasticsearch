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
