package com.se231.onlineedu.service;

import java.util.List;
import com.se231.onlineedu.message.response.PersonalInfo;
import com.se231.onlineedu.model.User;

/**
 * User Service Interface
 *
 * interface of main service related to user.
 *
 * @author Zhe Li
 *
 * @date 2019/07/08
 */
public interface UserService {
    /**
     * this service allow user to get his own information
     * @param userId the id of the getting user
     * @return  user information form
     * @throws Exception mainly throw not found exception
     */
    PersonalInfo getUserInfo(Long userId)throws Exception;

    /**
     * this service allow user to modify his own information
     * @param userId the id of the modifying user
     * @param personalInfo the form user submit
     * @return  user information form
     * @throws Exception mainly throw not found exception
     */
    PersonalInfo modifyUserInfo(Long userId,PersonalInfo personalInfo)throws Exception;

    /**
     * this service allow admin to manage users' personal information
     * @param id    the id of managed user
     * @param personalInfo  the form admin submit
     * @return  user information form after changing.
     * @throws Exception    mainly throw not found exception
     */
    PersonalInfo manageUserInfo(Long id,PersonalInfo personalInfo)throws Exception;

    /**
     * this service allow admin to get a list of all user
     * @return list of all users
     */
    List<User> getAllUser();

    /**
     * check if there is same username
     * @param username pending username
     * @return true if exists same username, false if doesn't exist.
     */
    boolean checkSameUsername(String username);

    /**
     * check if there is same email
     * @param email pending email
     * @return true if exists same email, false if doesn't exist.
     */
    boolean checkSameEmail(String email);

    /**
     * check if there is same telephone number
     * @param tel pending telephone number
     * @return true if exists same telephone number, false if doesn't exist.
     */
    boolean checkSameTel(String tel);
}
