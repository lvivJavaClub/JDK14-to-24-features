import java.time.LocalDate;
import java.util.Scanner;

void main() {

  var start = System.currentTimeMillis();
  System.out.println(f(2_000));
  System.out.println(System.currentTimeMillis()- start);
  start = System.currentTimeMillis();
  System.out.println(f_opt(2_000));
  System.out.println(System.currentTimeMillis() - start);
  System.out.println(f_opt2(2_000));
}

long f(int n) {
  long cnt = 0;
  for (int i = 1; i <= n; i++)
    for (int j = 1; j <= i; j++)
      for (int k = 1; k <= j; k++)
        cnt += 1;
  return cnt;
}


long f_opt(int n) {
  long cnt = 0;
  for (int i = 1; i <= n; i++)
      cnt += (i ^ 2) + i;

  return cnt/2;
}

long f_opt2(int n) {
  return (long) Math.pow(n, 3);
}


int majorityElement5(int[] nums) {
  int winner = 0;
  int counter = 0;

  for (int currentNum : nums) {
    if (currentNum == winner) {
      ++counter;
    } else if (counter == 0) {
      winner = currentNum;
      counter = 1;
    } else {
      --counter;
    }
  }

  counter = 0;
  for (int num : nums) {
    if (num == winner) {
      ++counter;
    }
  }

  if (counter > nums.length / 2) {
    return winner;
  }

  throw new IllegalStateException("COULD NOT BE!");
}

int majorityElement3(int[] nums) {
  if (nums.length == 1) {
    return nums[0];
  }

  Arrays.sort(nums);

  int maxCounter = 0, currentCounter = 0;
  int currentNumber = nums[0];
  for (int i = 0; i < nums.length - 1; ++i) {
    int current = nums[i];
    int next = nums[i + 1];


    if (currentCounter == 0) {
      currentNumber = current;
    }

    if (current == next) {
      ++currentCounter;
    }

    if (currentCounter > maxCounter) {
      maxCounter = Math.max(maxCounter, currentCounter + 1);
      currentNumber = current;
    }

    if (current != next) {
      currentCounter = 0;
    }

    if (maxCounter > nums.length / 2) {
      return currentNumber;
    }
  }

  return currentNumber;
}

int majorityElement2(int[] nums) {
  Map<Integer, Integer> numCounters = new HashMap<>();

  for (int num : nums) {
    var counter = numCounters.merge(num, 1, Integer::sum);
    if (counter > nums.length / 2) {
      return num;
    }
  }

  return numCounters.entrySet().stream()
      .max(Map.Entry.comparingByValue())
      .map(Map.Entry::getKey)
      .orElse(0);
}

int majorityElement1(int[] nums) {
  return Arrays.stream(nums)
      .boxed()
      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
      .entrySet().stream().max(Map.Entry.comparingByValue())
      .map(Map.Entry::getKey)
      .orElse(0);
}

