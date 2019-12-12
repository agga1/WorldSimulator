package agh.cs.utilsClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ParamsParser {
    private static final String defaultParameters = "parameters.json";

    private static final String resources = "src/main/resources/".replace("/", File.separator);
    private static final String images = resources + File.separator + "images" + File.separator;
    private static final String configuration = resources + "configuration" + File.separator;

    public static Params parse() {
        return parse(defaultParameters);
    }

    public static Params parse(String name) {
        Gson gson = new Gson();
        try{
            return gson.fromJson(new FileReader(configuration+name), Params.class);
        }catch (Exception e){
            System.out.println("sth went wrong");
        }
        return null;
    }
}

