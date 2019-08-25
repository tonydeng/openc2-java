package com.github.tonydeng.openc2.json.serialzer;

import com.github.tonydeng.openc2.OpenC2Command;
import com.github.tonydeng.openc2.utilities.Keys;
import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Customized serializer to encode a OpenC2Command object into a JSON String
 *
 * @author Tony Deng
 * @version V1.0
 * @date 2019-08-25 21:17
 **/
@Slf4j
public class OpenC2CommandSeriaizer implements JsonSerializer<OpenC2Command> {
    @Override
    public JsonElement serialize(OpenC2Command command, Type type, JsonSerializationContext context) {
        val root = new JsonObject();
        if (command.hasHeader()) {

        }

        if (command.hasId()) {
            root.addProperty(Keys.ID, command.getId());
        }

        root.addProperty(Keys.ACTION, command.getAction());

        String targetType = command.getTarget().getObjectType();
        Map<String, Object> target = command.getTarget().getAll();

        if (null == target.get(targetType)) {
            
        }


        return root;
    }
}
