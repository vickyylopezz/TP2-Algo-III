package edu.fiuba.algo3.modelo;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import edu.fiuba.algo3.App;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Kahoot {

    public void leerArchivo() throws URISyntaxException, IOException {

        File file = getFileFromResources("Preguntas2.json");
        String path = file.getAbsolutePath();

        JsonReader reader = new JsonReader(new FileReader(path, StandardCharsets.US_ASCII));
        JsonElement parser = JsonParser.parseReader(reader);

        JsonArray array = parser.getAsJsonArray();
        for (JsonElement element: array) {
            JsonObject object = element.getAsJsonObject();

            System.out.println(object.get("pregunta"));
        }
    }

    private File getFileFromResources(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found.");
        } else {
            //return new File(resource.getFile());
            return Paths.get(resource.toURI()).toFile();
        }
    }

    private Pregunta crearPregunta(JsonObject object) throws PreguntaError {
        String titulo = object.get("pregunta").getAsString();
        Pregunta pregunta;

        switch(object.get("tipo").getAsString()){
            case "multiple":
                pregunta = new MultipleChoiceClasico(titulo, 0);
                break;
                //((MultipleChoiceClasico) pregunta).agregarOpcionCorrecta(object.get("opciones_correctas").);

            case "VFClasico":
                pregunta = new VerdaderoFalsoClasico(titulo, 0);
                break;

            case "VFPenalidad":
                pregunta = new VerdaderoFalsoConPenalidad(titulo, true);
                break;

            default:
                throw new PreguntaError("No se reconoce el tipo de pregunta");
        }
        return pregunta;
    }

    /*
    public static void mani(String args[]) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();


        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("Preguntas.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray questions = (JSONArray) obj;
            System.out.println(questions);

            questions.forEach(q -> parseQuestionObject( (JSONObject) q));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseQuestionObject(JSONObject question) {
        String title = (String) question.get("question");

        if ((String) question.get("type") == "boolean") {
            System.out.println("TrueFalse");
            parseTrueFalseQuestion(question);
        }
        if ((String) question.get("type") == "multiple") {
            System.out.println("MultipleChoice");
            parseMultipleChoiceQuestion(question);
        }
        System.out.println("");
    }

    private static void parseTrueFalseQuestion(JSONObject question) {
        String correctOption = (String) question.get("correct_answer");
        System.out.println(correctOption);

        ArrayList<String> incorrectOptions = (ArrayList<String>) question.get("incorrect_answers");
        for (String q : incorrectOptions) {
            System.out.println(q);
        }
    }

    private static void parseMultipleChoiceQuestion(JSONObject question) {
        String correctOption = (String) question.get("correct_answer");
        System.out.println(correctOption);

        ArrayList<String> incorrectOptions = (ArrayList<String>) question.get("incorrect_answers");
        for (String q : incorrectOptions) {
            System.out.println(q);
        }
    }*/
}