package edu.p.eight.model.entity;

import edu.p.eight.model.entity.Entity;
import edu.p.eight.view.Texture;
import org.junit.Test;

import java.awt.image.BufferedImage;

import static org.junit.Assert.assertTrue;

public class EntityTest {

    @Test
    public void testOverlaps() {

        BufferedImage img1 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Texture texture1 = new Texture(img1, "Texture1");

        BufferedImage img2 = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Texture texture2 = new Texture(img2, "Texture2");

        // Set overlappingDistance to 1.1 for entity1
        Entity entity1 = new Entity(1.0f, texture1, true);
        entity1.setOverlappingDistance(1.1f);

        Entity entity2 = new Entity(2.0f, texture2, true);

        boolean result = Entity.overlaps(entity1, entity2);

        assertTrue("Expected result to be true", result);
    }
}