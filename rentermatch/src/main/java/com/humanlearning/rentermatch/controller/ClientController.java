package com.humanlearning.rentermatch.controller;

import com.humanlearning.rentermatch.domain.Client;
import com.humanlearning.rentermatch.domain.Landlord;
import com.humanlearning.rentermatch.domain.Student;
import com.humanlearning.rentermatch.domain.Tenant;
import com.humanlearning.rentermatch.mapper.ClientMapper;
import com.humanlearning.rentermatch.mapper.LandlordMapper;
import com.humanlearning.rentermatch.mapper.StudentMapper;
import com.humanlearning.rentermatch.mapper.TenantMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("client")
@RestController
public class ClientController {

    @Autowired
    private ClientMapper clientMapper;
    @Autowired
    private LandlordMapper landlordMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TenantMapper tenantMapper;

    @GetMapping("login")
    public String login(String email, String password) {
        //Check whether email is empty
        if (email == null) {
            return "email cannot be empty";
        }
        //Check whether password is empty
        if (password == null) {
            return "password cannot be empty";
        }
        //Select client from database by email
        Client client = clientMapper.selectClient(email);
        //If client does not exist
        if (client == null) {
            return "login failed";
        }
        //Check whether the password matches the one stored in database
        if (password.equals(client.getPassword())) {
            return "login successfully";
        }
        return "wrong password";
    }


    @PostMapping("register")
    public String register(String password, String name, String email) {

        //Check whether email is empty
        if (email == null) {
            return "email cannot be empty";
        }
        //Check whether password is empty
        if (password == null) {
            return "password cannot be empty";
        }
        //Check whether name is empty
        if (name == null) {
            return "name cannot be empty";
        }
        //Select client from database by email
        Client client = clientMapper.selectClient(email);
        //Check whether client already exist
        if (client != null) {
            return "register failed, user already exist";
        }
        //Return 1 if saved successfully; return 0 if failed
        int resultCount = clientMapper.saveClient(password, name, email);
        if (resultCount == 0) {
            return "register failed";
        }
        return "register successfully";
    }


    @GetMapping("getClientByEmail")
    public String getClientByEmail(String email) {
        if (email != null) {
            Client client = clientMapper.selectClientByEmail(email);
            if (client != null) {
                return client.toString();
            } else
                return "The client does not exist.";
        } else
            return "Email cannot be empty.";
    }

    @GetMapping("getClientBycId")
    public String getClientBycId(String cid) {
        if (cid != null) {
            Client client = clientMapper.selectClientBycId(cid);
            if (client != null) {
                return client.toString();
            } else
                return "The client does not exist.";
        } else
            return "cId cannot be empty.";
    }

    @DeleteMapping("deleteClient")
    public String deleteClient(String password, String name, String email) {
        if (password == null) {
            return "password cannot be empty";
        }
        if (name == null) {
            return "name cannot be empty";
        }
        if (email == null) {
            return "email cannot be empty";
        }
        Client client = clientMapper.selectClient(email);
        if (client == null) {
            return "client doest not exist";
        }
        String cid = client.getCid();
        // if client is a landlord, delete landlord first
        Landlord landlord = landlordMapper.selectLandlordBylClientId(cid);
        if (landlord != null) {
            int resultCount = landlordMapper.deleteLandlordBylClientId(cid);
            if (resultCount == 0) {
                return "delete the client as a landlord failed";
            }
        }
        // if client is a student, delete student first
        Student student = studentMapper.selectStudentBysClientId(cid);
        if (student != null) {
            int resultCount = studentMapper.deleteStudentBysClientId(cid);
            if (resultCount == 0) {
                return "delete the client as a student failed";
            }
        }
        // if client is a tenant, delete tenant first
        Tenant tenant = tenantMapper.selectTenantBytClientId(cid);
        if (tenant != null) {
            int resultCount = tenantMapper.deleteTenantBytClientId(cid);
            if (resultCount == 0) {
                return "delete the client as a tenant failed";
            }
        }
        int resultCount = clientMapper.deleteClientBycId(cid);
        if (resultCount == 0) {
            return "delete failed";
        }
        else
            return "client deleted successfully";
    }
}
