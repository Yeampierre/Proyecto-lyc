/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LenguajeMiWawita.Lexico;

import java.util.ArrayList;

/**
 *
 * @author LARRY
 */
public class Identificador {
        private String lexema;
    private String valor;
    private String tipo;
    final private int token = 100;
    private ArrayList<String> errores = new ArrayList<>();
    
    public Identificador(String lexema, String valor, String tipo) {
        this.lexema = lexema;
        this.valor = valor;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return String.format("%-12s    %9s      %-11s     %4d", lexema, valor, tipo, token );
    }

    /**
     * @return the lexema
     */
    public String getLexema() {
        return lexema;
    }

    /**
     * @param lexema the lexema to set
     */
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the token
     */
    public int getToken() {
        return token;
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
        final Identificador other = (Identificador) obj;
        return this.lexema.equals(other.lexema);
    }

    /**
     * @return the errores
     */
    public ArrayList<String> getErrores() {
        return errores;
    }

    /**
     * @param errores the errores to set
     */
    public void setErrores(ArrayList<String> errores) {
        this.errores = errores;
    }
    public void mostrarErrores(){
        if (errores.size()>0){
            for (String error : errores){
                System.out.println(error);
            }
        }
    }
    public void anadirError(String err){
        if (!errores.contains(err)){
            errores.add(err);
        }
    }
}
