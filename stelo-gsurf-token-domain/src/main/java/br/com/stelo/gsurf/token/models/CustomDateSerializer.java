package br.com.stelo.gsurf.token.models;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateSerializer extends StdSerializer<Date> {
  
	private static final long serialVersionUID = 1L;
	
	private SimpleDateFormat formatter 
      = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
 
    public CustomDateSerializer() {
        this(null);
    }
 
    public CustomDateSerializer(Class t) {
        super(t);
    }
     
    @Override
    public void serialize (Date value, JsonGenerator gen, SerializerProvider arg2)
      throws IOException, JsonProcessingException {
    	formatter.setTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));//getTimeZone("UTC"));
        gen.writeString(formatter.format(value));
    }
    }
