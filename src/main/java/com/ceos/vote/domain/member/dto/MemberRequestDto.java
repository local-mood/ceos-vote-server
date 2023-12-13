package com.ceos.vote.domain.member.dto;

import com.ceos.vote.domain.member.entity.Member;
import com.ceos.vote.domain.team.entity.Team;
import com.ceos.vote.domain.devPart.entity.DevPart;
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
public class MemberRequestDto {

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

    private Boolean voteFlagMember;
    private Boolean voteFlagTeam;
    private Integer voteCnt;

    private Team team;
    private DevPart devPart;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .username(username)
                .userid(userid)
                .password(passwordEncoder.encode(password))
                .email(email)
                .voteFlagMember(voteFlagMember)
                .voteFlagTeam(voteFlagTeam)
                .voteCnt(voteCnt)
                .team(team)
                .devPart(devPart)
                .build();
    }

}
