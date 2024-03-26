package edu.p.eight.model.entity;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class EntityTest {

    @Test
    public void testOverlaps() {
        // Set both cars at the same distance
        Entity entity1 = new Entity(0.0f, null, true);
        Entity entity2 = new Entity(0.0f, null, true);

        boolean result = Entity.overlaps(entity1, entity2);

        assertTrue("Expected result to be true", result);
    }

    @Test
    public void testNotOverlaps() {
        // Set cars at different distances
        Entity entity1 = new Entity(0.0f, null, true);
        Entity entity2 = new Entity(2.0f, null, true);

        boolean result = Entity.overlaps(entity1, entity2);

        assertFalse("Expected result to be false", result);
    }
}