package dev.crashteam.snatcher.mapper;

import java.util.List;

public interface BaseMapper<D, DTO> {
    DTO domainToDto(D domain);

    D dtoToDomain(DTO dto);

    List<DTO> domainsToDtos(List<D> domains);

    List<D> dtosToDomains(List<DTO> dtos);
}
