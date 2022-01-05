package xlsx;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@State(Scope.Benchmark)
public class AsyncBenchmark {
    public static void main(String[] args) throws Exception {
        org.openjdk.jmh.Main.main(args);
    }
    
    @Param({ "100"})
    public int iterations;
    
    
//    @Setup(Level.Invocation)
//    public void setUp() {
//        murmur3 = Hashing.murmur3_128().newHasher();
//    }
    
    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1)
    @Warmup( iterations = 1, batchSize = 1, time = 2, timeUnit = TimeUnit.NANOSECONDS)
    @Measurement( iterations = 1, batchSize = 1)
    public void objectCreation() {
//        System.out.println("invoke");
        new Object();
        count.addAndGet(1);
//        System.out.println(count.get());
    }
    private static final AtomicLong count = new AtomicLong(0);
    
    
    @TearDown
    public void finish() {
        System.out.println("finish: count=" + count.get());
    }
}