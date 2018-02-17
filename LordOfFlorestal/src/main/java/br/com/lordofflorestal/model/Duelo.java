/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lordofflorestal.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;

/**
 *
 * @author gabriel
 */
public class Duelo {

    private int id;
    private String uri;
    private Jogador criadoPor;
    private Jogador oponente;
    private Calendar dataCriacao;
    private SituacaoDuelo situacaoDuelo;
    private Deck deckJogador1;
    private Deck deckJogador2;
    private String vezDe;
    private String batePapo;

    public Duelo() {
        UUID uuid = UUID.randomUUID();
        this.uri = uuid.toString().substring(0, 10);
        dataCriacao = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
        situacaoDuelo = SituacaoDuelo.CRIADO;
        deckJogador1 = new Deck();
        deckJogador2 = new Deck();
        batePapo = "";
    }

    public Jogador getCriadoPor() {
        return criadoPor;
    }

    public void setCriadoPor(Jogador criadoPor) {
        this.criadoPor = criadoPor;
    }

    public Jogador getOponente() {
        return oponente;
    }

    public void setOponente(Jogador oponente) {
        this.oponente = oponente;
    }

    public Calendar getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Calendar dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public SituacaoDuelo getSituacaoDuelo() {
        return situacaoDuelo;
    }

    public void setSituacaoDuelo(SituacaoDuelo situacaoDuelo) {
        this.situacaoDuelo = situacaoDuelo;
    }

    public Deck getDeckJogador1() {
        return deckJogador1;
    }

    public void setDeckJogador1(Deck deckJogador1) {
        this.deckJogador1 = deckJogador1;
    }

    public Deck getDeckJogador2() {
        return deckJogador2;
    }

    public void setDeckJogador2(Deck deckJogador2) {
        this.deckJogador2 = deckJogador2;
    }

    public String getVezDe() {
        return vezDe;
    }

    public void setVezDe(String vezDe) {
        this.vezDe = vezDe;
    }

    public String getBatePapo() {
        return batePapo;
    }

    public void setBatePapo(String batePapo) {
        this.batePapo = batePapo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.uri);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Duelo other = (Duelo) obj;
        if (!Objects.equals(this.uri, other.uri)) {
            return false;
        }
        return true;
    }

}
