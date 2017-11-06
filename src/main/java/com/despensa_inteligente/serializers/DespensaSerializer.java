package com.despensa_inteligente.serializers;

import com.despensa_inteligente.model.Despensa;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class DespensaSerializer extends StdSerializer<Despensa> {
	public DespensaSerializer() {
        this(null);
    }
 
    public DespensaSerializer(Class<Despensa> t) {
        super(t);
    }
 
    @Override
    public void serialize(
      Despensa despensa, 
      JsonGenerator generator, 
      SerializerProvider provider) 
      throws IOException, JsonProcessingException {
        generator.writeNumber(despensa.getId());
    }

}
