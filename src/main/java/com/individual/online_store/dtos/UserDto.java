package com.individual.online_store.dtos;


import com.individual.online_store.entities.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated

public class UserDto {
    private Long userId;
    @NotNull(message = "Email cant be null.")
    @NotEmpty(message = "Email must be written.")
    private String email;
    @NotNull(message = "Password cant be null.")
    @NotEmpty(message = "Password must be written.")
    private String password;
    private String city;
    private String address;

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .password(user.getPassword())
                .city(user.getCity())
                .address(user.getAddress())
                .build();
    }

    public static User toEntity(UserDto userDto) {
        return User.builder()
                .userId(userDto.getUserId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .city(userDto.getCity())
                .address(userDto.getAddress())
                .build();
    }
}
