package venky.project.dsl.skills.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import venky.project.dsl.skills.Constants;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class ExerciseIntentHandler implements RequestHandler {

  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(intentName("ExerciseIntent"));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {
    return input.getResponseBuilder()
        .withSpeech(Constants.EXERCISE_INTENT_MESSAGE)
        .withShouldEndSession(true)
        .build();
  }
}
