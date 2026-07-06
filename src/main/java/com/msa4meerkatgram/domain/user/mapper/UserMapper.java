package com.msa4meerkatgram.domain.user.mapper;

import com.msa4meerkatgram.domain.user.entities.UserMybatis;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserMybatis findByPk(long id);

    UserMybatis findByEmail(@NotBlank(message = "이메일은 필수입니다") @Pattern(regexp = "^[0-9a-zA-Z](?!.*?[\\-_.]{2})[a-zA-Z0-9\\-_.]{3,63}@[0-9a-zA-Z](?!.*?[\\-_.]{2})[a-zA-Z0-9\\-_.]{3,63}\\.[a-zA-Z]{2,3}$", message = "허용하지 않는 양식입니다") String email);
}
