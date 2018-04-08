package venky.project.dsl.skills.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import venky.project.dsl.skills.Constants;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {
  @Override
  public boolean canHandle(HandlerInput input) {
    return input.matches(requestType(LaunchRequest.class));
  }

  @Override
  public Optional<Response> handle(HandlerInput input) {
    return input.getResponseBuilder()
        .withSpeech(Constants.WELCOME_MESSAGE)
        .withReprompt(Constants.HELP_MESSAGE)
        .withShouldEndSession(false)
        .build();
  }
}
