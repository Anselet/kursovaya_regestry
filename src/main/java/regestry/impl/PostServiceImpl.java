package regestry.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import regestry.dao.PostRepository;
import regestry.entities.Post;
import regestry.service.PostService;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    @Transactional
    @Override
    public void create(Post entity) {
        //if (postRepository.existsByNameEqualsIgnoreCase(entity.getName())){
            //throw  new EntityNotValidRuntimeException("".formatted(entity));
        //}
        postRepository.save(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Post> findAllByName(String name){
         return (Collection<Post>) Optional.ofNullable(name)
                 .map(postRepository::findAllByNameContainingIgnoreCase)
                 .orElse(postRepository.findAll());
    }

    @Override
    public Optional<Post> findById(Integer id) {
        return postRepository.findById(id);
    }
}
