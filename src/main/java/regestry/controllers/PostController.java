package regestry.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import regestry.controllers.dto.PostDto;
import regestry.controllers.dto.RelationTypeDto;
import regestry.entities.Post;
import regestry.service.PostService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/findAll")
    public Collection<Post> findAll (@RequestParam(value = "name", required = false) String name) {
        return postService.findAllByName(name);
    }

    @GetMapping("/findById")
    public Optional<Post> findById(@RequestParam(value = "id", required = false) Integer id){
        return postService.findById(id);
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "*")
    public void create(@RequestBody PostDto entity) {
        postService.create(mapDtoToDomain(entity));
    }

    private Post mapDtoToDomain(PostDto entity){
        return new Post()
                .setName(entity.name())
                .setDescription(entity.description());
    }

    @GetMapping("/getDto")
    public PostDto getDto(){
        return new PostDto("name", "description");
    }
}
