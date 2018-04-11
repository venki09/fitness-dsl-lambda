package venky.project.dsl.skills.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.io.CharStreams;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import venky.project.dsl.skills.Constants;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class NutritionIntentHandler implements RequestHandler {
  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(intentName("NutritionIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {
    String message = Constants.NUTRITION_INTENT_ERROR_MESSAGE;

    IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
    for(Slot slot : intentRequest.getIntent().getSlots().values()) {
      if(slot.getName().equals("food")) {
        if(slot.getValue() != null && (slot.getValue().equalsIgnoreCase("apple") ||
            slot.getValue().equalsIgnoreCase("orange"))) {
          // Call the service here and return the value
          HttpClient httpClient = HttpClients.createMinimal();
          HttpGet httpGet = new HttpGet
              ("http://ec2-52-91-37-178.compute-1.amazonaws.com:8080/fitness-service/nutrition_info?food=" +
                  slot.getValue());

          try {
            HttpResponse response = httpClient.execute(httpGet);
            String serviceResponse = CharStreams.
                toString(new InputStreamReader(response.getEntity().getContent(), "utf-8"));

            System.out.println("response string: " + serviceResponse);

            JsonObject jsonObject = new JsonParser().parse(serviceResponse).getAsJsonObject();
            message = jsonObject.get("name").getAsString() + " has " +
                jsonObject.get("calories").getAsString() + " calories with " + jsonObject.get("sugar").getAsString() +
                " of sugar";
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }

    return input.getResponseBuilder()
        .withSpeech(message)
        .withShouldEndSession(true)
        .build();
  }
}
