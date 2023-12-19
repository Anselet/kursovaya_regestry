package regestry.controllers.dto;

public record RelationUpdateDto(
        Integer child,
        Integer parent,
        Integer relationType
) {
}
