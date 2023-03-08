package com.mustafafindik.elasticsearchapp.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
