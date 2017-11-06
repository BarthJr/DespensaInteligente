package com.despensa_inteligente.serializers;

import java.io.IOException;
import java.util.List;

import com.despensa_inteligente.model.ProdutoDespensa;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ProdutoDespensaSerializer extends JsonSerializer<List<ProdutoDespensa>> {

    @Override
    public void serialize(
      List<ProdutoDespensa> value,
      JsonGenerator generator,
      SerializerProvider provider)
      throws IOException, JsonProcessingException {

    	generator.writeStartArray();
    	for(ProdutoDespensa produtoDespensa : value) {
    		generator.writeStartObject();
    		generator.writeNumberField("id", produtoDespensa.getId());
    		generator.writeNumberField("quantidade", produtoDespensa.getQuantidade());
    		generator.writeEndObject();
    	}
        generator.writeEndArray();
    }

}
