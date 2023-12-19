package regestry.controllers.dto;

public record RelationDto(
        Integer relationTypeId,
        Integer parentId,
        Integer childId) {
}
