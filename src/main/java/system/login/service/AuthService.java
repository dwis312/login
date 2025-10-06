/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.login.service;

import system.login.model.AuthResponse;
import system.login.utils.Config;
import system.login.utils.JsonUtil;

/**
 *
 * @author dwi
 */
public class AuthService {

    public static AuthResponse login(String email, String password) throws Exception{
        String endpoint = Config.BASE_URL + "/auth/login";
        String body = String.format("{\"email\":\"%s\",\"password\":\"%s\"}", email, password);
        String response = ApiClient.sendRequest("POST", endpoint, body);
        return JsonUtil.fromJson(response, AuthResponse.class);
    }
    
}
