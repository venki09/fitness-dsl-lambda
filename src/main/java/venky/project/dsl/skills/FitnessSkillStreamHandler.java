package venky.project.dsl.skills;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import venky.project.dsl.skills.handlers.*;

public class FitnessSkillStreamHandler extends SkillStreamHandler {

  public FitnessSkillStreamHandler(Skill skill) {
    super(Skills.standard()
        .addRequestHandlers(new LaunchRequestHandler(), new ExerciseIntentHandler(), new NutritionIntentHandler(),
            new HelpIntentHandler(), new ExitSkillHandler(), new SessionEndedHandler())
        .build());
  }
}
