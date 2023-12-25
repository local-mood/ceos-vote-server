package com.ceos.vote.auth.dto;

import com.ceos.vote.domain.member.entity.DevPart;
import com.ceos.vote.domain.member.entity.Member;
import com.ceos.vote.domain.team.entity.Team;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequestDto {

    @NotBlank(message = "닉네임은 필수 입력값입니다.")
    private String username;

    @NotBlank(message = "ID는 필수 입력값입니다.")
    private String userid;

    @NotBlank(message = "이메일은 필수 입력값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$",
            message = "비밀번호는 8~16자리수여야 합니다. " +
            "영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String password;

    private Long teamId;

    private Integer devPartId;

    public Member toMember(PasswordEncoder passwordEncoder, Team team) {
        return Member.builder()
                .username(username)
                .userid(userid)
                .password(passwordEncoder.encode(password))
                .email(email)
                .voteFlagBe(Boolean.FALSE)
                .voteFlagFe(Boolean.FALSE)
                .voteFlagTeam(Boolean.FALSE)
                .voteCnt(0)
                .team(team)
                .devPart(DevPart.of(devPartId))
                .build();
    }

}
