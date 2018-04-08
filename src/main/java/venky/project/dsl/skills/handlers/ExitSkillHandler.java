package venky.project.dsl.skills.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import venky.project.dsl.skills.Constants;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class ExitSkillHandler implements RequestHandler {
  @Override
  public boolean canHandle(HandlerInput handlerInput) {
    return handlerInput.matches(intentName("AMAZON.StopIntent")
            .or(intentName("AMAZON.PauseIntent")
            .or(intentName("AMAZON.CancelIntent"))));
  }

  @Override
  public Optional<Response> handle(HandlerInput handlerInput) {
    return handlerInput.getResponseBuilder().withSpeech(Constants.EXIT_MESSAGE).build();
  }
}
