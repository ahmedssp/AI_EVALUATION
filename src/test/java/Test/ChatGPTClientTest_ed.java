package Test;

import Serialization.MessageR;
import Serialization.RequestBodyc;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static DataMethod.ApachePOI.Read_ExcelSheet;
import static DataMethod.ApachePOI.insertExcel;

public class ChatGPTClientTest_ed {

    String apiKey = System.getenv("OPENAI_API_KEY");
    String path ="/resources/variable_test.xlsx";
    int coulum=0;

    int ROWS_QUESTIONS = (int) Read_ExcelSheet(path, "sheet1")[coulum][2];

    @Test
    public void testChatGPTAPI2() {


//        String question_ = (String) Read_ExcelSheet(path, "sheet1")[coulum][0]; //[][constant=0]
          String question_="moon";
        //methods one to write request body

        String requestBody_0 = "{\n" +
                "    \"model\": \"gpt-3.5-turbo\",\n" +
                "    \"messages\": [\n" +
                "      {\n" +
                "        \"role\": \"system\",\n" +
                "        \"content\": \"You are a helpful assistant.\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"role\": \"user\",\n" +
                "         \"content\": \"" + question_ + "\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }";

  //____methods two to write request body ( serialization )________________________________

        // Create messages
        MessageR systemMessage = new MessageR();
        systemMessage.setRole("system");
        systemMessage.setContent("You are a helpful assistant.");

        MessageR userMessage = new MessageR();
        userMessage.setRole("user");
        userMessage.setContent(question_);

        // Create list of messages
        List<MessageR> messages = new ArrayList<>();
        messages.add(systemMessage);
        messages.add(userMessage);

        // Create request body

        RequestBodyc requestBody = new RequestBodyc();
        requestBody.setModel("gpt-3.5-turbo");
        requestBody.setMessages(messages);

//____________request____________________________________________________________
        Response response = RestAssured.given()
                .baseUri("https://api.openai.com/v1/chat/completions")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .body(requestBody)
                .post();

//____________printing____________________________________________________________

        String content = response.jsonPath().getString("choices[0].message.content");
        // Print the content
        System.out.println("Content: >" + content);
        //print all response
        System.out.println(response.getBody().asString());

//        ChatCompletion chatCompletionResponse = response.as(ChatCompletion.class);
//        MessageR m = response.as(MessageR.class);
//        System.out.println(m.getContent());
//        System.out.println(response.getBody().asString());
//        Extract the content from the JSON response
//____________inserting ____________________________________________________________
//        insertExcel(content, coulum+1, 1,path); //logic   [][constant =1]

    }



}
