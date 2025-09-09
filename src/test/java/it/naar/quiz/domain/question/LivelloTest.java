package it.naar.quiz.domain.question;

import org.junit.jupiter.api.Test;
import testutil.logging.Loggable;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe di test per verificare il comportamento del metodo getOrdine della classe AtaLevel.Livello.
 * Questa classe include metodi di test per controllare che i valori restituiti da getOrdine 
 * corrispondano ai valori attesi per i vari livelli definiti nell'enumerazione Livello.
 */

class LivelloTest implements Loggable {

    @Test
    void testGetOrdineForL1() {
        // Test for level L1
        AtaLevel.Livello livello = AtaLevel.Livello.L1;
        int ordine = livello.getOrdine();
//        logger().log(Level.INFO, "\nTest result for L1: {0},\n{1}", new String[]{String.valueOf(ordine), livello.getDescrizione()});
        assertEquals(1, ordine, "The ordine for L1 should be 1.");

        logger().log(Level.INFO, "Expected result: {0}", livello);
        logger().log(Level.INFO, "Expected result: {0}", livello.getOrdine());
        logger().log(Level.INFO, "Expected result: {0}", livello.getDescrizione());
    }

    @Test
    void testGetOrdineForL2() {
        // Test for level L2
        AtaLevel.Livello livello = AtaLevel.Livello.L2;
        int ordine = livello.getOrdine();
        assertEquals(2, ordine, "The ordine for L2 should be 2.");
    }

    @Test
    void testGetOrdineForL3() {
        // Test for level L3
        AtaLevel.Livello livello = AtaLevel.Livello.L3;
        int ordine = livello.getOrdine();
        assertEquals(3, ordine, "The ordine for L3 should be 3.");
    }
}