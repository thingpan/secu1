package com.test.secu1.user.mapper;

import java.util.List;

import com.test.secu1.user.vo.UserInfoVO;

public interface UserInfoMapper {
	UserInfoVO selectUserInfoByUiId(String uiId);
	int insertUserInfo(UserInfoVO user);
	int updateUserInfoUiPwd(UserInfoVO user);
}
