package com.mustafafindik.elasticsearchapp.Repository;

import com.mustafafindik.elasticsearchapp.Entity.User;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {
    @Query("{\"bool\": {\"must\": [{\"match\": {\"isim\": \"?0\"}}]}}") //boolean query olarak ad ile örtüşmeli
    List<User> getByCustomQuery(String search);
    List<User> findByNameLikeOrSurnameLike(String name, String surname);
}
