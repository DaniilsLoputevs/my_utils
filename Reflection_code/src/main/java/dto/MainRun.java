package dto;

import dto.models.User;
import dto.models.UserDTO;
import dto.models.UserDtoExtra;
import dto.service.Transfer;

public class MainRun {
    public static void main(String[] args) {
        var base = new User(5555,
                "nameFrom",
                "ageFrom",
                "infoFrom",
                "extraFrom",
                "emailFrom",
                "phoneFrom"
        );
        var transfer = new Transfer();

        UserDTO dto = transfer.toDto(base, UserDTO.class);
        UserDtoExtra dtoExtra = transfer.toDto(base, UserDtoExtra.class);

        System.out.println(dto);
        System.out.println(dtoExtra);

        // back transfer work as well,
        // but other fields have default value.(String == null, int == 0 ...)
        System.out.println(transfer.toDto(dto, User.class));
    }

}