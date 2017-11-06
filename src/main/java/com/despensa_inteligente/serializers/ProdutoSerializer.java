package com.despensa_inteligente.serializers;

import java.io.IOException;

import com.despensa_inteligente.model.Produto;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ProdutoSerializer extends StdSerializer<Produto> {
	public ProdutoSerializer() {
        this(null);
    }
 
    public ProdutoSerializer(Class<Produto> t) {
        super(t);
    }
 
    @Override
    public void serialize(
      Produto produto, 
      JsonGenerator generator, 
      SerializerProvider provider) 
      throws IOException, JsonProcessingException {
         
//    	generator.writeStartObject();
//        generator.writeNumberField("id", produto.getId());
//        generator.writeStringField("nome", produto.getNome());
//        generator.writeStringField("marca", produto.getMarca());
//        generator.writeStringField("tipo", produto.getTipo());
//        generator.writeNumberField("peso", produto.getPeso());
//        generator.writeNumberField("categoria", produto.getCategoria().getId());
//        generator.writeEndObject();

        generator.writeNumber(produto.getId());
    }

}
