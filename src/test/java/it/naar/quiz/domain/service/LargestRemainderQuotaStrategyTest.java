package it.naar.quiz.domain.service;

import it.naar.quiz.domain.model.question.AbstractArgument;
import it.naar.quiz.domain.model.question.ArgumentAta;
import it.naar.quiz.domain.model.question.Quota;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LargestRemainderQuotaStrategyTest {

    /**
     * Test per il metodo targets nella classe LargestRemainderQuotaStrategy.
     * Il metodo targets distribuisce un dato numero di obiettivi nelle quote,
     * assicurando prima l’assegnazione dei numeri base e poi allocando i resti
     * in base all’ordine decrescente della loro parte frazionaria fino a esaurimento degli obiettivi.
     */

    @Test
    void testExactDistribution() {
        // Given
        LargestRemainderQuotaStrategy strategy = new LargestRemainderQuotaStrategy();
        List<Quota> quotas = List.of(
                new Quota(new MockArgument(1), 50),
                new Quota(new MockArgument(2), 50)
        );
        int totalTargets = 10;

        // When
        Map<AbstractArgument, Integer> result = strategy.targets(totalTargets, quotas);

        // Then
        assertEquals(5, result.get(new MockArgument(1)));
        assertEquals(5, result.get(new MockArgument(2)));
    }

    @Test
    void testWithRemainderAllocation() {
        // Given
        LargestRemainderQuotaStrategy strategy = new LargestRemainderQuotaStrategy();
        List<Quota> quotas = List.of(
                new Quota(new MockArgument(1), 33),
                new Quota(new MockArgument(2), 33),
                new Quota(new MockArgument(3), 34)
        );
        int totalTargets = 10;

        // When
        Map<AbstractArgument, Integer> result = strategy.targets(totalTargets, quotas);

        // Then
        assertEquals(3, result.get(new MockArgument(1)));
        assertEquals(3, result.get(new MockArgument(2)));
        assertEquals(4, result.get(new MockArgument(3)));
    }

    @Test
    void testHandlesZeroTargets() {
        // Given
        LargestRemainderQuotaStrategy strategy = new LargestRemainderQuotaStrategy();
        List<Quota> quotas = List.of(
                new Quota(new MockArgument(1), 50),
                new Quota(new MockArgument(2), 50)
        );
        int totalTargets = 0;

        // When
        Map<AbstractArgument, Integer> result = strategy.targets(totalTargets, quotas);

        // Then
        assertEquals(0, result.get(new MockArgument(1)));
        assertEquals(0, result.get(new MockArgument(2)));
    }

    @Test
    void testUnequalPercentagesWithRounding() {
        // Given
        LargestRemainderQuotaStrategy strategy = new LargestRemainderQuotaStrategy();
        List<Quota> quotas = List.of(
                new Quota(new MockArgument(1), 60),
                new Quota(new MockArgument(2), 25),
                new Quota(new MockArgument(3), 15)
        );
        int totalTargets = 8;

        // When
        Map<AbstractArgument, Integer> result = strategy.targets(totalTargets, quotas);

        // Then
        assertEquals(5, result.get(new MockArgument(1))); // 60% of 8 is 4.8, rounds up to 5
        assertEquals(2, result.get(new MockArgument(2))); // 25% of 8 is 2
        assertEquals(1, result.get(new MockArgument(3))); // 15% of 8 is 1.2, rounds down to 1
    }

    @Test
    void testEmptyQuotaList() {
        // Given
        LargestRemainderQuotaStrategy strategy = new LargestRemainderQuotaStrategy();
        List<Quota> quotas = List.of();
        int totalTargets = 10;

        // When
        Map<AbstractArgument, Integer> result = strategy.targets(totalTargets, quotas);

        // Then
        assertEquals(0, result.size());
    }

    @Test
    void testQuotasExceeding100Percent() {
        // Given
        LargestRemainderQuotaStrategy strategy = new LargestRemainderQuotaStrategy();
        List<Quota> quotas = List.of(
                new Quota(new MockArgument(1), 120),
                new Quota(new MockArgument(2), 80)
        );
        int totalTargets = 5;

        // When
        Map<AbstractArgument, Integer> result = strategy.targets(totalTargets, quotas);

        // Then
        assertEquals(3, result.get(new MockArgument(1))); // Proportional handling
        assertEquals(2, result.get(new MockArgument(2)));
    }

    // Mock implementation of AbstractArgument for testing
    private static class MockArgument extends AbstractArgument {
        private final int mockId;

        public MockArgument(int mockId) {
            this.mockId = mockId;
            setText(null);
        }

        @Override
        public int getMockId() {
            return mockId;
        }

        @Override
        public void setText(String text) {
            text = "Argument_" + Integer.toString(mockId);
            super.setText(text);
        }

        @Override
        public void setDescription(String description) {
        }

        @Override
        public String getText() {
            return null;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public ArgumentAta getAtaChapter() {
            return null;
        }

        @Override
        public boolean isAtaChapter() {
            return false;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            MockArgument that = (MockArgument) obj;
            return mockId == that.mockId;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(mockId);
        }
    }
}