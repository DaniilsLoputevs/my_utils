package java8.lambda;

import java8.structures.Annotations.Good;
import java8.structures.Annotations.Ugly;
import java8.structures.User;
import java8.structures.UserDto;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class AvoidLongLambdas {
    @Ugly
    class LongLambdaInPlace {
        public List<UserDto> convertToDto(List<User> users){
            return users.stream()
                    .map(user -> {
                        UserDto dto = new UserDto();
                        dto.setId(user.getId());
                        dto.setName(user.getName());
                        //it happens to be much more fields and much more logic in terms of remapping these fields
                        return dto;
                    })
                    .collect(toList());
        }
    }
    
    @Good
    class MethodReferenceInsteadOfLambda {
        // конкретный toDto может быть реализован как отдельный класс или как лямбда-функция
        // particular toDto could be implemented as a separate class or as a lambda function
        private Function<User, UserDto> toDto = this::convertToDto;
        
        public List<UserDto> convertToDto(List<User> users){
            return users.stream()
                    .map(toDto)
                    .collect(toList());
        }
        
        private UserDto convertToDto(User user){
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            return dto;
        }
    }
}
