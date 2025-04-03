package ro.vladded.examples.emoji_surefire;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

import java.util.Random;

public class EmojiTestListener implements TestExecutionListener {

    private static final String[] PASS_REACTIONS = {
            "✅ Test passed! 🥳", "💚 Smooth as butter",
            "🎉 Great success!", "👏 Flawless victory!"
    };

    private static final String[] FAIL_REACTIONS = {
            "💥 Kaboom!", "😱 Test went boom!",
            "🪦 Here lies your logic", "❌ You shall not pass!"
    };

    private static final String[] MEDALS = {
            "🥇", "🥈", "🥉", "🏅", "🎖️"
    };

    private final Random random = new Random();
    private int passed = 0;
    private int failed = 0;

    private String random(String[] arr) {
        return arr[random.nextInt(arr.length)];
    }

    @Override
    public void executionStarted(TestIdentifier testIdentifier) {
        if (testIdentifier.isTest()) {
            System.out.printf("""
                    ------------------------------
                    🎬 Test is rolling...
                    🧪 %s
                    ------------------------------
                    """, testIdentifier.getDisplayName());
        }
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult result) {
        if (!testIdentifier.isTest()) return;

        switch (result.getStatus()) {
            case SUCCESSFUL -> {
                passed++;
                System.out.println(random(MEDALS) + " " + random(PASS_REACTIONS));
            }
            case FAILED -> {
                failed++;
                System.out.printf("""
                        🔥🔥🔥 FAILURE DETECTED 🔥🔥🔥
                        %s
                        ❌ Test: %s
                        """, random(FAIL_REACTIONS), testIdentifier.getDisplayName());
            }
            case ABORTED -> {
                failed++;
                System.out.println("⚠️ Test aborted: " + testIdentifier.getDisplayName());
            }
        }
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        int total = passed + failed;
        System.out.printf("""
                ================================
                🎉 Test Party Summary 🎉
                🟢 Passed: %d
                🔴 Failed: %d
                🧪 Total: %d
                ================================
                """, passed, failed, total);
    }
}
