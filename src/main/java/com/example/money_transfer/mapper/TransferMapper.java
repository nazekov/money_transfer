package com.example.money_transfer.mapper;

import com.example.money_transfer.model.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransferMapper {

    TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "cashbox", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    Transfer clone(Transfer transfer);
}
