package features.jep499_structured_task_scope;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.StructuredTaskScope.Subtask;

public class StructuredApp {

  public static void main(String[] args) throws Exception {

    // executorSolution();
    structuredSolution();
  }

  private static void structuredSolution() throws ExecutionException, InterruptedException {
    try (var scope = new StructuredTaskScope.ShutdownOnSuccess<>()) {
      // Fork two subtasks
      Subtask<String> weatherTask = scope.fork(() -> fetchWeather("Lviv"));
      Subtask<String> newsTask = scope.fork(() -> fetchNews("Technology"));

      // Wait for both subtasks to complete and Propagate errors if any
      scope.join();          // Wait for both subtasks to complete and Propagate errors if any

      // Retrieve results
      String weather = weatherTask.get();
      String news = newsTask.get();

      System.out.println("Weather: " + weather);
      System.out.println("News: " + news);
    }
  }

  private static void executorSolution() throws InterruptedException, ExecutionException {

    try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
      var weatherFuture = executor.submit(() -> fetchWeather("Lviv"));
      var newsFuture = executor.submit(() -> fetchNews("Technology"));

      String weather = weatherFuture.get();  // Block until result is available
      String news = newsFuture.get();        // Block until result is available

      System.out.println("Weather: " + weather);
      System.out.println("News: " + news);
    } catch (ExecutionException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

  private static String fetchWeather(String city) throws InterruptedException {
    Thread.sleep(1000);
    return "Sunny, 2°C in " + city;
  }

  private static String fetchNews(String category) throws InterruptedException {
    Thread.sleep(1500);
    return "Latest " + category + " news: Java 24 released!";
  }
}
