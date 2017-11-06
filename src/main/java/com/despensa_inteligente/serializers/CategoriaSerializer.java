package com.despensa_inteligente.serializers;

import com.despensa_inteligente.model.Categoria;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CategoriaSerializer extends StdSerializer<Categoria> {
	public CategoriaSerializer() {
        this(null);
    }
 
    public CategoriaSerializer(Class<Categoria> t) {
        super(t);
    }
 
    @Override
    public void serialize(
      Categoria categoria, 
      JsonGenerator generator, 
      SerializerProvider provider) 
      throws IOException, JsonProcessingException {
         
//    	generator.writeStartObject();
//        generator.writeNumberField("id", categoria.getId());
//        generator.writeStringField("nome", categoria.getNome());
//        generator.writeStringField("marca", categoria.getMarca());
//        generator.writeStringField("tipo", categoria.getTipo());
//        generator.writeNumberField("peso", categoria.getPeso());
//        generator.writeNumberField("categoria", categoria.getCategoria().getId());
//        generator.writeEndObject();

        generator.writeNumber(categoria.getId());
    }

}
