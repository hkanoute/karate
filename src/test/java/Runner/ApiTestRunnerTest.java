package Runner;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.Assert;
import org.junit.Test;


public class ApiTestRunnerTest {
    @Test
    public void testParallel(){
        Results results = Runner.path("classpath:features").tags("@karateH").parallel(5);
        Assert.assertEquals(0, results.getFailCount());
    }
}
