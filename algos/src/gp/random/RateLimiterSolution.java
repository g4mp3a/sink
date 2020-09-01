package gp.random;

import org.junit.jupiter.api.Test;

import java.time.temporal.ChronoUnit;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;
import java.time.LocalDateTime;


/**
 * Allow only N requests in a time window of M mins. 
 */
 
class RateLimiter {
  
  private Deque q;
  long timeWindow = 0;
  
  public RateLimiter (int N, long M) {
    timeWindow = M;
    q = new LinkedBlockingDeque(N);
  }
  
  public synchronized boolean isAllowed() {
    
    LocalDateTime now = LocalDateTime.now();
    
    if (q.size() == 0) return q.offer(now);

    LocalDateTime windowStartTime = q.peek();
    long diff = ChronoUnit.MINUTES.between(windowsStartTime, now);
    if (diff > timeWindow) {
      q.poll();
    }
    
    return q.offer(now);
  }
}

public class RateLimiterSolution {
  
  @Test
  public void test() {
    
  }
}