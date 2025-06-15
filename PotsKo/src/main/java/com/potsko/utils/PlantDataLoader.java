package com.potsko.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.potsko.model.Plant;


// Reads the plants JSON file and turns into a list of plant objets
public class PlantDataLoader {
    public static List<Plant> loadPlants(String jsonFilePath) {
        List<Plant> plants = new ArrayList<>();
        try {
            InputStream is = PlantDataLoader.class.getClassLoader().getResourceAsStream("data/plants.json");
            if (is == null) {
                throw new FileNotFoundException("Resource not found: data/plants.json");
            }
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            JSONArray arr = new JSONArray(json);
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);

                int id = obj.getInt("id");
                String name = obj.getString("name");
                String tagalogName = obj.optString("tagalogName", "");
                String scientificName = obj.optString("scientificName", "");
                String category = obj.optString("category", "");
                String wateringFrequency = obj.optString("wateringFrequency", "");
                String sunlight = obj.optString("sunlight", "");
                String plantType = obj.optString("plantType", "");
                String image = obj.optString("image", "");

                // Parse growthStages array
                List<String> growthStages = new ArrayList<>();
                JSONArray growthStagesArr = obj.optJSONArray("growthStages");
                if (growthStagesArr != null) {
                    for (int j = 0; j < growthStagesArr.length(); j++) {
                        growthStages.add(growthStagesArr.getString(j));
                    }
                }

                plants.add(new Plant(
                    id, name, tagalogName, scientificName,
                    category, wateringFrequency, sunlight,
                    plantType, growthStages, image
                ));
            }
        } catch (IOException | JSONException e) {
            System.err.println("Error loading plant data: " + e.getMessage());
        }
        return plants;
    }
}