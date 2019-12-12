package agh.cs.configuration;

import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;

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

