package ttp.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ttp.jackson.bean.GenderEnum;

public class JacksonDemo {
    public static void main(String[] args) throws IOException {
        ObjectMapper om1 = new ObjectMapper();
        String jsonStr = om1.writeValueAsString(GenderEnum.MALE);
        System.out.println(jsonStr);
        GenderEnum genderEnum = om1.readValue(jsonStr, GenderEnum.class);
        System.out.println(genderEnum);

        SimpleModule module = new SimpleModule("custom gender serializer");
        module.addSerializer(GenderEnum.class, new GenderEnumSerializer(GenderEnum.class));
        module.addDeserializer(GenderEnum.class, new GenderEnumDeSerializer(GenderEnum.class));

        // 给两个 object mapper 都注册 module
        // om1 失效，可能是缓存机制？
        om1.registerModule(module);
        ObjectMapper om2 = new ObjectMapper();
        om2.registerModule(module);

        jsonStr = om2.writeValueAsString(GenderEnum.FEMALE);
        System.out.println(jsonStr);
        genderEnum = om2.readValue(jsonStr, GenderEnum.class);
        System.out.println(genderEnum);

    }

    static class GenderEnumSerializer extends StdSerializer<GenderEnum> {
        public GenderEnumSerializer(Class<GenderEnum> t) {
            super(t);
        }

        @Override
        public void serialize(GenderEnum genderEnum, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("code", genderEnum.getCode());
            jsonGenerator.writeStringField("description", genderEnum.getDescription());
            jsonGenerator.writeEndObject();
        }
    }

    static class GenderEnumDeSerializer extends StdDeserializer<GenderEnum> {
        public GenderEnumDeSerializer(Class<GenderEnum> t) {
            super(t);
        }

        @Override
        public GenderEnum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
            int code = ((NumericNode)treeNode.get("code")).intValue();
            return GenderEnum.findByCode(code);
        }
    }
}
