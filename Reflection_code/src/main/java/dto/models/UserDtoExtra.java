package dto.models;

import dto.service.TransferDTO;

import java.util.StringJoiner;

/**
 * One more DTO.
 *
 * @see User
 * @see UserDTO
 */
public class UserDtoExtra {
    @TransferDTO(altNames = {"id"})
    private String extra;
    private String email;
    @TransferDTO(altNames = {"age"})
    private String phone;

    public UserDtoExtra() {
    }

    public UserDtoExtra(String extra, String email, String phone) {
        this.extra = extra;
        this.email = email;
        this.phone = phone;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserDtoExtra.class.getSimpleName() + "[", "]")
                .add("extra='" + extra + "'")
                .add("email='" + email + "'")
                .add("phone='" + phone + "'")
                .toString();
    }
}
