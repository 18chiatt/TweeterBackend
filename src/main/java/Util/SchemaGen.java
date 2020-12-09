package Util;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.victools.jsonschema.generator.*;
import model.request.*;

public class SchemaGen {

    public static void main (String[] args){


        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2019_09,OptionPreset.PLAIN_JSON).with(Option.INLINE_ALL_SCHEMAS);
        SchemaGeneratorConfig config = configBuilder.build();
        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(UserStatsRequest.class);

        System.out.println(jsonSchema.toString());
    }
}
