package com.despensa_inteligente.serializers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.despensa_inteligente.model.Receita;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

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
         
    	generator.writeStartObject();
        generator.writeNumberField("id", receita.getId());
        generator.writeStringField("titulo", receita.getTitulo());
        generator.writeStringField("modoPreparo", receita.getModoPreparo());
        generator.writeNumberField("cliente", receita.getCliente().getId());
        generator.writeEndObject();
    }

}
