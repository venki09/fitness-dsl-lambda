package venky.project.dsl.skills.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

public class SessionEndedHandler implements RequestHandler {
  @Override
  public boolean canHandle(HandlerInput handlerInput) {
    return false;
  }

  @Override
  public Optional<Response> handle(HandlerInput handlerInput) {
    return null;
  }
}
