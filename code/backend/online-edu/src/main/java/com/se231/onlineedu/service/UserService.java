package com.se231.onlineedu.service;

import java.util.List;

import com.se231.onlineedu.message.request.SignInUserForm;
import com.se231.onlineedu.message.response.PersonalInfo;
import com.se231.onlineedu.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

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
    User getUserInfo(Long userId)throws Exception;

    /**
     * this service allow admin to manage users' personal information or a user to modify his personal information.
     * @param id    the id of managed user
     * @param personalInfo  the form admin submit
     * @return  user information form after changing.
     * @throws Exception    mainly throw not found exception
     */
    User manageUserInfo(Long id,PersonalInfo personalInfo)throws Exception;

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

    /**
     * this service allows admin or super admin to buck import users' information.
     * @param excel the excel used to bulk import user information
     * @return  String to return hints
     * @throws Exception IO Exception
     */
    ResponseEntity<String> bulkImportUser(MultipartFile excel)throws Exception;

    /**
     * this service allows user to update his personal avatar
     * @param avatarUrl url of upload image
     * @param id    id of requesting user
     * @return  user info
     * @throws Exception    mainly not found exception
     */
    User updateUserAvatar(String avatarUrl, Long id) throws Exception;


    /**
     * this service confirm to change password
     * @param id  user id request to verify
     * @param password new password
     * @return  user info
     * @throws Exception exception
     */
    User updateUserPasswordConfirm(Long id, String password) throws Exception;

    /**
     * this service confirm to change password
     * @param id  user id request to verify
     * @param email new email
     * @return  user info
     * @throws Exception exception
     */
    User updateUserEmailConfirm(Long id, String email) throws Exception;

    ResponseEntity<?> saveUserSignIn(Long id, SignInUserForm signInUserForm) throws Exception;
}
