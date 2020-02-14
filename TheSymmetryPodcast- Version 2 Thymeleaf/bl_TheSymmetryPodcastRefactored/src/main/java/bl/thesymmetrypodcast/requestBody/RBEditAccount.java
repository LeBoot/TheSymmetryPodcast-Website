/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl.thesymmetrypodcast.requestBody;

import lombok.Data;

/**
 *
 * @author Boone
 */
@Data
public class RBEditAccount {
    private int accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private int regionId;
    private int accountTypeId;
}
