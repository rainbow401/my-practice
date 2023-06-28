package com.rainbow.practice.mapstruct;

import com.rainbow.practice.mapstruct.model.Demo1;
import com.rainbow.practice.mapstruct.model.Demo2;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author yanzhihao
 * @since 2023/6/28
 */
@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
interface Convert {

    Logger log = LoggerFactory.getLogger(Convert.class);


    Convert CONVERT = Mappers.getMapper(Convert.class);

    @Mapping(target = "username", expression = "java(test(dto))")
    Demo2 convert(Demo1 dto);

    @Mapping(target = "username", expression = "java(dto.getUsername() != null ? \"1\" : \"2\")")
    Demo2 convert2(Demo1 dto);

    Demo2 convert3(Demo1 dto);

    default String test(Demo1 dto) {
        log.info("test params: [{}]", dto);

        return "1";
    }
}
