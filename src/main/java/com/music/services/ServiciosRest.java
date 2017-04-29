package com.music.services;

import com.music.domain.Artista;
import com.music.domain.Musico;

public interface ServiciosRest {
	public Musico consultarArtista(Integer id);
	public int crearArtista(Artista artista);
	public int borrar(Integer id);
}
