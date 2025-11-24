package com.health.app;

public class AIHandler {

    public String getHealthAdvice(String symptoms) {
        if (symptoms == null || symptoms.trim().isEmpty()) {
            return "Please describe your symptoms to get a recommendation.";
        }

        if (DBConnection.USE_MOCK_MODE) {
            return getMockAdvice(symptoms);
        } else {
            return getRealAIAdvice(symptoms);
        }
    }

    private String getMockAdvice(String symptoms) {
        String lowerCaseSymptoms = symptoms.toLowerCase();
        
        if (lowerCaseSymptoms.contains("fever")) {
            return "Recommendation: Rest, hydration, and monitor temperature. If it exceeds 39°C, seek medical attention.";
        } else if (lowerCaseSymptoms.contains("headache")) {
            return "Recommendation: Reduce screen time, hydrate, and rest in a dark room. Consider over-the-counter pain relief if needed.";
        } else if (lowerCaseSymptoms.contains("cough") || lowerCaseSymptoms.contains("cold")) {
            return "Recommendation: Drink warm fluids, use honey and ginger. Monitor breathing.";
        } else if (lowerCaseSymptoms.contains("stomach") || lowerCaseSymptoms.contains("pain")) {
            return "Recommendation: Avoid heavy meals. Drink water. If pain persists, consult a doctor.";
        } else {
            return "Recommendation: Please consult a specialist for these specific symptoms. Maintain a healthy lifestyle.";
        }
    }

    private String getRealAIAdvice(String symptoms) {
        String apiKey = ""; // Integrated User API Key
        String apiUrl = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro-latest:generateContent?key=" + apiKey;

        try {
            java.net.URL url = new java.net.URL(apiUrl);
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Construct JSON Payload (Manual String construction to avoid external dependencies)
            String jsonInputString = "{"
                    + "\"contents\": [{"
                    + "\"parts\":[{\"text\": \"Act as a doctor. Analyze these symptoms and provide a brief recommendation (max 50 words): " + symptoms + "\"}]"
                    + "}]"
                    + "}";

            try (java.io.OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                try (java.io.BufferedReader br = new java.io.BufferedReader(
                        new java.io.InputStreamReader(conn.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine = null;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    // Simple parsing to extract the text (Assuming standard Gemini response structure)
                    return extractTextFromJSON(response.toString());
                }
            } else {
                try (java.io.BufferedReader br = new java.io.BufferedReader(
                        new java.io.InputStreamReader(conn.getErrorStream(), "utf-8"))) {
                    StringBuilder errorResponse = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        errorResponse.append(line.trim());
                    }
                    return "Error: AI Service Unavailable (HTTP " + responseCode + ") - " + errorResponse.toString();
                } catch (Exception ex) {
                    return "Error: AI Service Unavailable (HTTP " + responseCode + ")";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error connecting to AI Service: " + e.getMessage();
        }
    }

    // Helper to extract text from JSON without external libraries
    private String extractTextFromJSON(String json) {
        try {
            // Very basic parsing logic to find the "text" field
            String marker = "\"text\": \"";
            int startIndex = json.indexOf(marker);
            if (startIndex != -1) {
                startIndex += marker.length();
                int endIndex = json.indexOf("\"", startIndex);
                String result = json.substring(startIndex, endIndex);
                // Handle basic escaped newlines
                return result.replace("\\n", "\n").replace("\\\"", "\"");
            }
            return "Could not parse AI response.";
        } catch (Exception e) {
            return "Error parsing AI response.";
        }
    }
}

