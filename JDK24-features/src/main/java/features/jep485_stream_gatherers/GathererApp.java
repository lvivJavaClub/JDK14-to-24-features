package features.jep485_stream_gatherers;

import java.util.List;
import java.util.stream.Gatherer;
import java.util.stream.Gatherers;

public class GathererApp {
  public static void main(String[] args) {

    var numbers = List.of(1, 2, 3, 4, 5);

    var result = numbers.stream()
        .gather(Gatherer.of((_, element, downstream) -> {
          return downstream.push(element * element);
        }))
        .toList();

    System.out.println(result);
  }
}
