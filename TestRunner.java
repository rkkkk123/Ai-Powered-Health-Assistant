package com.health.app;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("--- Starting Real Mode Test ---");
        System.out.println("Mock Mode Status: " + DBConnection.USE_MOCK_MODE);
        
        if (DBConnection.USE_MOCK_MODE) {
            System.err.println("ERROR: Mock Mode is still TRUE. Please set it to FALSE in DBConnection.java to test the API.");
            return;
        }

        AIHandler ai = new AIHandler();
        String symptoms = "I have a severe headache and sensitivity to light.";
        
        System.out.println("Testing with symptoms: " + symptoms);
        System.out.println("Contacting Gemini API...");
        
        long start = System.currentTimeMillis();
        String advice = ai.getHealthAdvice(symptoms);
        long end = System.currentTimeMillis();
        
        System.out.println("--- AI Response Received (" + (end - start) + "ms) ---");
        System.out.println(advice);
        System.out.println("-------------------------------");
    }
}
