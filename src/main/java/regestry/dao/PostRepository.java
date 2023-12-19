package regestry.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import regestry.entities.Post;

import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    Iterable<Post> findAllByNameContainingIgnoreCase(String name);

    @Override
    Optional<Post> findById(Integer integer);
    //boolean existsByNameEqualsIgnoreCase(String name);
}
