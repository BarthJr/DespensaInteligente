package com.despensa_inteligente.serializers;

import com.despensa_inteligente.model.Receita;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ReceitaSerializer extends StdSerializer<Receita> {
	public ReceitaSerializer() {
        this(null);
    }
 
    public ReceitaSerializer(Class<Receita> t) {
        super(t);
    }
 
    @Override
    public void serialize(
      Receita receita, 
      JsonGenerator generator, 
      SerializerProvider provider) 
      throws IOException, JsonProcessingException {
        generator.writeNumber(receita.getId());
    }

}
