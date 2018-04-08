package venky.project.dsl.skills.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import venky.project.dsl.skills.Constants;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class NutritionIntentHandler implements RequestHandler {
  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(intentName("NutritionIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {
    return input.getResponseBuilder()
        .withSpeech(Constants.NUTRITION_INTENT_MESSAGE)
        .withShouldEndSession(false)
        .build();
  }
}
