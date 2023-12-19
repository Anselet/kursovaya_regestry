package regestry.service;

import regestry.entities.Post;

import java.util.Collection;
import java.util.Optional;

public interface PostService {
    void create(Post entity);

    Collection<Post> findAllByName(String name);

    Optional<Post> findById(Integer id);
}
