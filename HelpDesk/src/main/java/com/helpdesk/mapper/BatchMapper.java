package com.helpdesk.mapper;

import com.helpdesk.Model.Batch;
import com.helpdesk.dto.BatchDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring",uses = {UserMapper.class})
public interface BatchMapper {
    @Mapping(source = "users",target = "users")
    BatchDTO toDTO(Batch batch);

    @InheritInverseConfiguration
    @Mapping(target = "users",ignore = true)
    Batch toEntity(BatchDTO batchDTO);


//    @Named("mapUsersToIds")
//    static List<Long>  mapUsersToIds(List<User> users) {
//        return users != null?
//                users.stream()
//                        .map(User::getUserId)
//                        .collect(Collectors.toList())
//                :null;
//    }
}
