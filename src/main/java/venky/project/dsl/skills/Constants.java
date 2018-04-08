package venky.project.dsl.skills;

public class Constants {

  private static String NUTRITION_HELP_MESSAGE = "How many calories in apple or orange";
  private static String EXERCISE_HELP_MESSAGE = "I ran or biked or swam some miles in some time";
  public static String HELP_MESSAGE = "You can ask me 2 things. " + NUTRITION_HELP_MESSAGE + " or " + EXERCISE_HELP_MESSAGE;

  public static String EXIT_MESSAGE = "Thank you for using the fitness skills. Goodbye!";
  public static String WELCOME_MESSAGE = "Welcome to my fitness skill.";

  // TODO to be removed. This is just a temporary holder to test intent invocation
  public static String NUTRITION_INTENT_MESSAGE = "hmm so you wanted to ask for nutrition info. Hold on, let me learn how to do that";
  public static String EXERCISE_INTENT_MESSAGE = "hmm so you want to log your workout. Hold on, let me learn how to do that";
}
