package com.example31._Rest_template;

import com.example31._Rest_template.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
//https://www.javaguides.net/2019/06/spring-resttemplate-get-post-put-and-delete-example.html

    private static final String URL = "http://91.241.64.178:7081/api/users";

    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        Main springRestClient = new Main();

        springRestClient.run();

    }

    private void run() {
        //---------------------------------------------------CREATE-----------------------------------------------------


        ResponseEntity< String > result = restTemplate.getForEntity(URL,
                String.class);

        String cook = result.getHeaders().get("Set-Cookie").get(0);

        String JSESSIONID = cook.substring(0,cook.indexOf(";"));
        System.out.println(JSESSIONID);

        System.out.println(result);
        //---------------------------------------------------POST-------------------------------------------------------

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.set("Cookie", JSESSIONID);
        //headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        User newUser = new User(3l,"James", "Brown", (byte)32);

        HttpEntity<User> entity = new HttpEntity<>(newUser, headers);

        ResponseEntity <String> resultPost = restTemplate.exchange(URL, HttpMethod.POST, entity,
                String.class);

        String answer = resultPost.getBody();
        System.out.println(answer);

        //---------------------------------------------------PUT--------------------------------------------------------

        User updatedUser = new User(3l,"Thomas", "Shelby", (byte)32);

        HttpEntity<User> entityUpdate = new HttpEntity<>(updatedUser, headers);

        ResponseEntity <String> resultPostUpdate = restTemplate.exchange(URL,
                HttpMethod.PUT, entityUpdate, String.class);

        answer += resultPostUpdate.getBody();
        System.out.println(answer);

        //---------------------------------------------------DELETE-----------------------------------------------------


        Map < String, String > params = new HashMap < String, String > ();
        params.put("id", "3");

        ResponseEntity <String> resultPostDelete = restTemplate.exchange(URL + "/" + updatedUser.getId(),
                HttpMethod.DELETE, entityUpdate, String.class);

        answer += resultPostDelete.getBody();
        System.out.println(answer);

    }

}



