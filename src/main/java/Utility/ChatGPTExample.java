package Utility;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class ChatGPTExample {

    private static final String API_URL = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "your_api_key_here"; // Replace with your actual API key
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient httpClient = new OkHttpClient();
    private final Gson gson = new Gson();

    public String askChatGPT(String prompt) throws IOException {
        // Prepare the request body
        String jsonBody = gson.toJson(new RequestBodyData(prompt));
        RequestBody body = RequestBody.create(jsonBody, JSON);

        // Build the request
        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .post(body)
                .build();

        // Send the request and handle the response
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Parse the response body
            ResponseData responseData = gson.fromJson(response.body().string(), ResponseData.class);
            return responseData.choices[0].text;
        }
    }

    static class RequestBodyData {
        String model = "text-davinci-003"; // Specify the model you want to use
        String prompt;
        int max_tokens = 50;

        public RequestBodyData(String prompt) {
            this.prompt = prompt;
        }
    }

    static class ResponseData {
        Choice[] choices;

        static class Choice {
            String text;
        }
    }

    public static void main(String[] args) {
        ChatGPTExample chatGPT = new ChatGPTExample();
        try {
            String response = chatGPT.askChatGPT("what is cars ");
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
