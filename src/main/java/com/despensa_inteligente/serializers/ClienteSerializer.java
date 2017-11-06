package com.despensa_inteligente.serializers;

import com.despensa_inteligente.model.Cliente;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ClienteSerializer extends StdSerializer<Cliente> {
	public ClienteSerializer() {
        this(null);
    }
 
    public ClienteSerializer(Class<Cliente> t) {
        super(t);
    }
 
    @Override
    public void serialize(
      Cliente cliente, 
      JsonGenerator generator, 
      SerializerProvider provider) 
      throws IOException, JsonProcessingException {
        generator.writeNumber(cliente.getId());
    }
}
