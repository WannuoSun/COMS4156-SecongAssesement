package com.humanlearning.rentermatch;

import javax.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = {
    RentermatchApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ClientControllerTest {

  @Resource
  private MockMvc mockMvc;

  private final String testAuthHeader =
      "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTgyNTc3NTYyMSwiaWF0IjoxNjcwM"
          + "jU1NjIxfQ.I90zi32fdL-kFfxMRc75MHnfGWlWIgx44VQnW9fiPh9FhQkD87_r_pTPal-DMh3CK0P-Gg"
          + "WZaKeCCcj_P3ysMA";

  @Test
  @DisplayName("Empty Email Login")
  void testMock1() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .post("/client/login")
            .header("Authorization", testAuthHeader)
            .param("email", "")
            .param("password", ""))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().string("email cannot be empty"))
        .andReturn();
  }

  @Test
  @DisplayName("Empty Password Login")
  void testMock2() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .post("/client/login")
            .header("Authorization", testAuthHeader)
            .param("email", "kevinceltics09@hotmail.com")
            .param("password", ""))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().string("password cannot be empty"))
        .andReturn();
  }

  @Test
  @DisplayName("Wrong Password Login")
  void testMock3() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .post("/client/login")
            .header("Authorization", testAuthHeader)
            .param("email", "kevinceltics09@hotmail.com")
            .param("password", "1234"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().string("wrong password"))
        .andReturn();
  }

  @Test
  @DisplayName("Client Not Exist Login")
  void testMock4() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .post("/client/login")
            .header("Authorization", testAuthHeader)
            .param("email", "null@hotmail.com")
            .param("password", "1234"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().string("no email associated"))
        .andReturn();
  }

  @Test
  @DisplayName("Correct Password Login")
  void testMock5() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .post("/client/login")
            .header("Authorization", testAuthHeader)
            .param("email", "kevinceltics09@hotmail.com")
            .param("password", "123"))
        .andExpect(MockMvcResultMatchers.status().isOk())
//        .andExpect(MockMvcResultMatchers.content().string("login successfully"))
        .andReturn();
  }

  @Test
  @DisplayName("Empty Email Register")
  void testMock6() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .post("/client/register")
            .header("Authorization", testAuthHeader)
            .param("name", "Lena Smith")
            .param("password", "123")
            .param("email", ""))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().string("register fail, email cannot be empty!"))
        .andReturn();
  }

  @Test
  @DisplayName("Empty Password Register")
  void testMock7() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .post("/client/register")
            .header("Authorization", testAuthHeader)
            .param("name", "Lena Smith")
            .param("password", "")
            .param("email", "kevinceltics09@hotmail.com"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(
            MockMvcResultMatchers.content().string("register fail, password cannot be empty!"))
        .andReturn();
  }

  @Test
  @DisplayName("Empty Name Register")
  void testMock8() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .post("/client/register")
            .header("Authorization", testAuthHeader)
            .param("name", "")
            .param("password", "123")
            .param("email", "kevinceltics09@hotmail.com"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().string(
            "register fail, password must contain minimum eight characters, at least one letter and one number!"))
        .andReturn();
  }

  @Test
  @DisplayName("Duplicate User Register")
  void testMock9() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .post("/client/register")
            .header("Authorization", testAuthHeader)
            .param("name", "Lena Smith")
            .param("password", "123")
            .param("email", "kevinceltics09@hotmail.com"))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().string(
            "register fail, password must contain minimum eight characters, at least one letter and one number!"))
        .andReturn();
  }

  @Test
  @DisplayName("Client Successfully Register")
  void testMock10() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .post("/client/register")
            .header("Authorization", testAuthHeader)
            .param("name", "John Smith")
            .param("password", "TESTtest123")
            .param("email", "johnsmith@hotmail.com"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        //.andExpect(MockMvcResultMatchers.content().string("register successfully"))
        .andReturn();
  }

  @Test
  @DisplayName("Client Not Exist getClientByEmail")
  void testMock11() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .get("/client/getClientByEmail")
            .header("Authorization", testAuthHeader)
            .param("email", "johnwhite@hotmail.com"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("The client does not exist."))
        .andReturn();
  }

  @Test
  @DisplayName("Email Empty getClientByEmail")
  void testMock12() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .get("/client/getClientByEmail")
            .header("Authorization", testAuthHeader)
            .param("email", ""))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("Email cannot be empty."))
        .andReturn();
  }

  @Test
  @DisplayName("Client Not Exist getClientBycId")
  void testMock13() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .get("/client/getClientBycId")
            .header("Authorization", testAuthHeader)
            .param("cid", "50"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("The client does not exist."))
        .andReturn();
  }

  @Test
  @DisplayName("cId Empty getClientBycId")
  void testMock14() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .get("/client/getClientBycId")
            .header("Authorization", testAuthHeader)
            .param("cid", ""))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("cId cannot be empty."))
        .andReturn();
  }

  @Test
  @DisplayName("Password Empty deleteClient")
  void testMock15() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .delete("/client/deleteClient")
            .header("Authorization", testAuthHeader)
            .param("name", "John Smith")
            .param("password", "")
            .param("email", "johnsmith@hotmail.com"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("password cannot be empty"))
        .andReturn();
  }

  @Test
  @DisplayName("Name Empty deleteClient")
  void testMock16() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .delete("/client/deleteClient")
            .header("Authorization", testAuthHeader)
            .param("name", "")
            .param("password", "123")
            .param("email", "johnsmith@hotmail.com"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("name cannot be empty"))
        .andReturn();
  }

  @Test
  @DisplayName("Email Empty deleteClient")
  void testMock17() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .delete("/client/deleteClient")
            .header("Authorization", testAuthHeader)
            .param("name", "John Smith")
            .param("password", "123")
            .param("email", ""))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("email cannot be empty"))
        .andReturn();
  }

  @Test
  @DisplayName("Client Not Exist deleteClient")
  void testMock18() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .delete("/client/deleteClient")
            .header("Authorization", testAuthHeader)
            .param("name", "John Smith")
            .param("password", "123")
            .param("email", "johnwhite@hotmail.com"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("client doest not exist"))
        .andReturn();
  }

  @Test
  @DisplayName("Client Delete Successfully deleteClient")
  void testMock19() throws Exception {
    MvcResult result = mockMvc.perform(MockMvcRequestBuilders
            .delete("/client/deleteClient")
            .header("Authorization", testAuthHeader)
            .param("name", "John Smith")
            .param("password", "TESTtest123")
            .param("email", "johnsmith@hotmail.com"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string("client deleted successfully"))
        .andReturn();
  }
}
