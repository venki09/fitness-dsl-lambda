package venky.project.dsl.skills.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.google.common.io.CharStreams;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import venky.project.dsl.skills.Constants;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class ExerciseIntentHandler implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(intentName("ExerciseIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {

    String message = Constants.NUTRITION_INTENT_ERROR_MESSAGE;

    IntentRequest intentRequest = (IntentRequest) input.getRequestEnvelope().getRequest();
    String exercise = "", distance = "", distanceUnit = "", time = "";

    for (Slot slot : intentRequest.getIntent().getSlots().values()) {

      if (slot.getName().equalsIgnoreCase("exercise")) {
        exercise = slot.getValue();
      } else if (slot.getName().equalsIgnoreCase("distance")) {
        distance = slot.getValue();
      } else if (slot.getName().equalsIgnoreCase("distanceUnit")) {
        distanceUnit = slot.getValue();
      } else if (slot.getName().equalsIgnoreCase("time")) {
        time = slot.getValue();
      }
    }

//    System.out.println("Exercise " + exercise);
//    System.out.println("distance " + distance);
//    System.out.println("distance unit " + distanceUnit);
//    System.out.println("time " + time);

    // Call the service here and return the value
    HttpClient httpClient = HttpClients.createMinimal();
    HttpGet httpGet = new HttpGet
        ("http://ec2-52-91-37-178.compute-1.amazonaws.com:8080/fitness-service/exercise_info?exercise=" +
            exercise + "&distance=" + distance + "&unit=" + distanceUnit + "&time=" + time);

    try {
      HttpResponse response = httpClient.execute(httpGet);
      String serviceResponse = CharStreams.
          toString(new InputStreamReader(response.getEntity().getContent(), "utf-8"));

      JsonObject jsonObject = new JsonParser().parse(serviceResponse).getAsJsonObject();
      message = "Nice Job! you have burnt " + jsonObject.get("calories").getAsString() + " calories and your speed is " +
          jsonObject.get("speed").getAsString();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return input.getResponseBuilder()
        .withSpeech(message)
        .withShouldEndSession(true)
        .build();
  }
}
