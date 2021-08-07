package com.m2ra.meuprojeto.modelosretorno;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.m2ra.meuprojeto.modelo.TipoCozinha;

import lombok.Data;
import lombok.NonNull;

@Data
@JsonRootName("tiposcozinha")
public class TipoCozinhaXmlWrapper {
	
	@NonNull
	@JacksonXmlElementWrapper(useWrapping = false)
	@JsonProperty("tipocozinha")
	private List<TipoCozinha> tiposcozinha;

}
