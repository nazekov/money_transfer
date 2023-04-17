package com.example.money_transfer.mapper;

import com.example.money_transfer.model.Transfer;
import com.example.money_transfer.model.dto.TransferDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface TransferMapper {

    TransferMapper INSTANCE = Mappers.getMapper(TransferMapper.class);

    @Mapping(source = "cashbox.id", target = "cashboxId")
    TransferDto pojoToDto(Transfer transfer);

    List<TransferDto> transferListToTransferDtoList(List<Transfer> transferList);
}
