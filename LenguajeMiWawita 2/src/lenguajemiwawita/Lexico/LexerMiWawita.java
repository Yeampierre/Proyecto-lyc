/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LenguajeMiWawita.Lexico;

import static LenguajeMiWawita.Sintactico.ParserMiWawita.buscarLexema;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author LARRY
 */
public class LexerMiWawita {
        static final ArrayList<String> listaPalabrasReservadas = new ArrayList<>(Arrays.asList("PRINCIPAL",
            "si", "sin", "repet", "contar", "clase", "nuevo", "FUNCION", "procedimiento", "RETORNAR", "imprimir",
            "LEER", "constante", "ENT", "REAL", "let", "log", "cad"));
    
    static final ArrayList<String> listaOperadores = new ArrayList<>(Arrays.asList(".+",".-",".*","./",".**",
            "==","<>","<<",">>","<<=","=>>",".y",".o",".no","->","(",")","{","}",":",",","[","]",".","#"));
    
    static final ArrayList<String> listaLogicos = new ArrayList<>(Arrays.asList("falso","verdadero"));
    
    static final String PR = "Palabra reservada";
    static final String OP = "Operador";
    static final String LE = "Literal de entero";
    static final String LR = "Literal de real";
    static final String LS = "Literal de cadena";
    static final String LC = "Literal de caracter";
    static final String LL = "Literal logico";
    static final String CM = "Comentario";
    static final String ID = "Identificador";
    private ArrayList<Integer> tokens = new ArrayList<>();
    private ArrayList<Identificador> identificadores = new ArrayList<>();
    private ArrayList<Identificador> idConDuplicados = new ArrayList<>();
    private ArrayList<String> tablaSimbolos = new ArrayList<>();
    private ArrayList<String> tablaID = new ArrayList<>();
    private ArrayList<Integer> tokensAux = new ArrayList<>();
    
    public LexerMiWawita() {
        Scanner scanner = new Scanner(System.in);
        String cadena = "";
        String input;
        ArrayList<Character> caracteres = new ArrayList<>();
        //************ENTRADA POR CONSOLA**********************
       /*
        System.out.println("Introduce la cadena a analizar:");
        cadena = scanner.nextLine(); 
        */
        //*****************************************************
        
        //************ENTRADA POR ARCHIVO**********************
        
        String rutaArchivo = "prueba.txt";
        System.out.println("LENGUAJE:\n______________________________________\n");
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            while ((input = br.readLine()) != null) {
                cadena=cadena+input;
                System.out.println(input);
            }
            System.out.println("______________________________________");
        } catch (IOException e) {
            System.err.println("Ocurrio un error al leer el archivo: " + e.getMessage());
        } 
        
        //*****************************************************
        for (char c : cadena.toCharArray()) {
             caracteres.add(c);
        }       
        analizadorLexico(caracteres);
        scanner.close();
    }
    public ArrayList<Integer> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<Integer> tokens) {
        this.tokens = tokens;
    }
    public ArrayList<Character> trimInput(ArrayList<Character> caracteres) {
        while (!caracteres.isEmpty() && caracteres.get(0) == ' ') {
            caracteres.remove(0);
        }
        while (!caracteres.isEmpty() && caracteres.get(caracteres.size() - 1) == ' ') {
            caracteres.remove(caracteres.size() - 1);
        }
        return caracteres;
    }
    public void analizadorLexico(ArrayList<Character> caracteres){
        int i = 0;
        char estado = 'A';
        ArrayList<Character> lexema = new ArrayList<>();
        StringBuilder auxLex = new StringBuilder();
        String lexString;       
        encabezadoTablaSimbolos(tablaSimbolos);
        caracteres = trimInput(caracteres);
        while (i < caracteres.size()) {
                      
            if ((caracteres.get(i) == ' ' || caracteres.get(i) == '\n' || caracteres.get(i) == '\t') && lexema.isEmpty()) {
                i++;
                continue;
            } else if ((caracteres.get(i) == ' ' || caracteres.get(i) == '\n' || caracteres.get(i) == '\t') && !lexema.isEmpty() && estado !='H' && estado!='K') {
                tablaSimbolos.add(filaTabla(lexema, estado));
                lexema.clear();
                estado = 'A';
                continue;
            }
            lexema.add(caracteres.get(i));
            //System.out.println(estado); 
            switch (estado) {
                case 'A':
                    if (detectaLetra(caracteres.get(i))) {
                        estado = 'B';
                        break;
                    } else if (detectaDigito(caracteres.get(i))) {
                        estado = 'C';
                        break;
                    } else if (caracteres.get(i) == '\"') {
                        if (i<caracteres.size()-1){
                            estado = 'K';
                            lexema.remove(lexema.size()-1);
                        } else {
                            tablaSimbolos.add(filaTabla(lexema, estado));
                            lexema.clear();
                        }                        
                        break;
                    } else if (caracteres.get(i) == '\'') {
                        if (i < caracteres.size() - 1) {
                            estado = 'M';
                            lexema.remove(lexema.size() - 1);
                        } else {
                            tablaSimbolos.add(filaTabla(lexema, estado));
                            lexema.clear();
                        }
                        break;
                    } else if (caracteres.get(i) == '#') {
                        estado = 'G';
                        tablaSimbolos.add(filaTabla(lexema, estado));
                        lexema.clear();
                        estado = 'H';
                        break;
                    } else {
                        if (i < caracteres.size() - 2) {
                            i++;
                            lexema.add(caracteres.get(i));
                            i++;
                            lexema.add(caracteres.get(i));                            
                            for (Character c : lexema) {
                                auxLex.append(c);
                            }
                            lexString = auxLex.toString();
                            auxLex.delete(0, auxLex.length());
                            for (String ope : listaOperadores){
                                if (ope.equals(lexString)){
                                    estado = 'G';
                                }
                            }
                            if (estado == 'G') {
                                tablaSimbolos.add(filaTabla(lexema, estado));
                                lexema.clear();
                                estado = 'A';
                                break;
                            } else {
                                i = i - 2;
                                lexema.remove(lexema.size()-1);
                                lexema.remove(lexema.size()-1);
                                //System.out.println(lexema);
                                if (i < caracteres.size() - 1) {
                                    i++;
                                    lexema.add(caracteres.get(i));
                                    for (Character c : lexema) {
                                        auxLex.append(c);
                                    }
                                    lexString = auxLex.toString();
                                    auxLex.delete(0, auxLex.length());
                                    for (String ope : listaOperadores) {
                                        if (ope.equals(lexString)) {
                                            estado = 'G';
                                        }
                                    }
                                    if (estado == 'G') {
                                        tablaSimbolos.add(filaTabla(lexema, estado));
                                        lexema.clear();
                                        estado = 'A';
                                        break;
                                    } else {
                                        i--;
                                        lexema.remove(lexema.size()-1);
                                        auxLex.append(lexema.get(0));
                                        lexString = auxLex.toString();
                                        auxLex.delete(0, auxLex.length());
                                        for (String ope : listaOperadores) {
                                            if (ope.equals(lexString)) {
                                                estado = 'G';
                                            }
                                        }
                                        if (estado == 'G') {
                                            tablaSimbolos.add(filaTabla(lexema, estado));
                                            lexema.clear();
                                            if (caracteres.get(i) != '#') {
                                                estado = 'A';
                                            } else {
                                                estado = 'H';
                                            }
                                        } else {
                                            estado = 'X';
                                            tablaSimbolos.add(filaTabla(lexema, estado));
                                            lexema.clear();
                                            estado = 'A';
                                            break;
                                        }

                                    }
                                } else {
                                    auxLex.append(lexema.get(0));
                                    lexString = auxLex.toString();
                                    auxLex.delete(0, auxLex.length());
                                    for (String ope : listaOperadores) {
                                        if (ope.equals(lexString)) {
                                            estado = 'G';
                                        }
                                    }
                                    if (estado == 'G') {
                                        tablaSimbolos.add(filaTabla(lexema, estado));
                                        lexema.clear();
                                        if (caracteres.get(i) != '#') {
                                            estado = 'A';
                                        } else {
                                            estado = 'H';
                                        }
                                    } else {
                                        estado = 'X';
                                        tablaSimbolos.add(filaTabla(lexema, estado));
                                        lexema.clear();
                                        estado = 'A';
                                        break;
                                    }
                                }
                            }
                        } else if (i < caracteres.size() - 1) {
                            i++;
                            lexema.add(caracteres.get(i));
                            for (Character c : lexema) {
                                auxLex.append(c);
                            }
                            lexString = auxLex.toString();
                            auxLex.delete(0, auxLex.length());
                            for (String ope : listaOperadores) {
                                if (ope.equals(lexString)) {
                                    estado = 'G';
                                }
                            }
                            if (estado == 'G') {
                                tablaSimbolos.add(filaTabla(lexema, estado));
                                lexema.clear();
                                estado = 'A';
                                break;
                            } else {
                                i--;
                                lexema.remove(lexema.size()-1);
                                auxLex.append(lexema.get(0));
                                lexString = auxLex.toString();
                                auxLex.delete(0, auxLex.length());
                                for (String ope : listaOperadores) {
                                    if (ope.equals(lexString)) {
                                        estado = 'G';
                                    }
                                }
                                if (estado == 'G') {
                                    
                                    tablaSimbolos.add(filaTabla(lexema, estado));
                                    lexema.clear();
                                    if (caracteres.get(i) != '#') {
                                        estado = 'A';
                                    } else {
                                        estado = 'H';
                                    }
                                } else {
                                    estado = 'X';
                                    tablaSimbolos.add(filaTabla(lexema, estado));
                                    lexema.clear();
                                    estado = 'A';
                                    break;
                                }
                            }
                        } else {
                            auxLex.append(lexema.get(0));
                            lexString = auxLex.toString();
                            auxLex.delete(0, auxLex.length());
                            for (String ope : listaOperadores) {
                                if (ope.equals(lexString)) {
                                    estado = 'G';
                                }
                            }
                            if (estado == 'G') {
                                tablaSimbolos.add(filaTabla(lexema, estado));
                                lexema.clear();
                                if (caracteres.get(i) != '#') {
                                    estado = 'A';
                                } else {
                                    estado = 'H';
                                }
                            } else {
                                estado = 'X';
                                tablaSimbolos.add(filaTabla(lexema, estado));
                                lexema.clear();
                                estado = 'A';
                                break;
                            }
                        }
                    }
                    break;
                case 'B':
                    if (detectaDigito(caracteres.get(i)) || detectaLetra(caracteres.get(i)) || caracteres.get(i) == '_') {
                        estado = 'B';
                    } else {
                        lexema.remove(lexema.size()-1);
                        i--;
                        tablaSimbolos.add(filaTabla(lexema, estado));
                        lexema.clear();
                        estado = 'A';                   
                    }
                    break;
                case 'C':
                    if (detectaDigito(caracteres.get(i))){
                        estado = 'C';
                    } else if (caracteres.get(i)=='.' && i<caracteres.size()-1){
                        estado = 'E';
                    } else {
                        lexema.remove(lexema.size()-1);
                        i--;
                        tablaSimbolos.add(filaTabla(lexema, estado));
                        lexema.clear();
                        estado = 'A';  
                    }
                    break;
                case 'E':
                    if (detectaDigito(caracteres.get(i))){
                        estado='F';
                    } else {
                        while (!detectaDigito(lexema.get(lexema.size()-1))){
                            lexema.remove(lexema.size()-1);
                            i--;
                        }
                        estado = 'C';
                        tablaSimbolos.add(filaTabla(lexema, estado));
                        lexema.clear();
                        estado = 'A';
                    }
                    break;
                case 'F':
                    if (detectaDigito(caracteres.get(i))){
                        estado = 'F';
                    } else {
                        lexema.remove(lexema.size()-1);
                        i--;
                        tablaSimbolos.add(filaTabla(lexema, estado));
                        lexema.clear();
                        estado = 'A';  
                    }
                    break;
                case 'G':   //los lexemas que llegan a este estado son guardados en la tabla y se regresa al estado A en la misma iteración
                    break;
                case 'H':
                    if (caracteres.get(i)!='#'){
                        if (i<caracteres.size()-1){
                            estado = 'H';
                        } else {
                            estado = 'A';
                            tablaSimbolos.add(filaTabla(lexema, estado));
                            lexema.clear();                            
                        }
                    } else {
                        if (lexema.size() <= 1) {
                            estado = 'G';
                            tablaSimbolos.add(filaTabla(lexema, estado));
                            lexema.clear();
                            estado = 'A';
                        } else {
                            lexema.remove(lexema.size()-1);
                            tablaSimbolos.add(filaTabla(lexema, estado));
                            lexema.clear();
                            estado = 'J';
                            i--;
                        }

                    }
                    break;
                case 'J':
                    lexema.remove(lexema.size()-1);
                    estado='G';
                    lexema.add(caracteres.get(i));
                    tablaSimbolos.add(filaTabla(lexema, estado));
                    lexema.clear();
                    estado = 'A';
                    break;
                case 'K':
                    if (caracteres.get(i) != '\"') {
                        if (i<=caracteres.size()-1){
                            estado = 'K';
                            if (i==caracteres.size()-1){    //inicia el modo panico
                                while (!lexema.isEmpty()){
                                    i--;
                                    lexema.remove(lexema.size()-1);
                                }
                                lexema.add(caracteres.get(i));
                                estado='A';
                                tablaSimbolos.add(filaTabla(lexema, estado));
                                lexema.clear();
                            }
                        } else {
                            estado='A';
                            tablaSimbolos.add(filaTabla(lexema, estado));
                            lexema.clear();
                        }                        
                    } else {
                        if (lexema.size() <= 1) {
                            lexema.clear();                           
                            estado = 'A';
                        } else {
                            lexema.remove(lexema.size()-1);
                            estado='L';
                            tablaSimbolos.add(filaTabla(lexema, estado));
                            lexema.clear();
                            estado = 'L';
                            i--;
                        }
                    }
                    break;
                case 'L':
                    lexema.clear();
                    estado = 'A';
                    break;
                case 'M':
                    if (caracteres.get(i) != '\'') {
                        if (i <= caracteres.size() - 1) {
                            estado = 'M';
                            if (i == caracteres.size() - 1) {
                                while (!lexema.isEmpty()) {                     //modo panico
                                    i--;
                                    lexema.remove(lexema.size() - 1);
                                }
                                lexema.add(caracteres.get(i));
                                estado = 'A';
                                tablaSimbolos.add(filaTabla(lexema, estado));
                                lexema.clear();
                            }
                        }                
                    } else {
                        if (lexema.size() <= 1) {
                            estado = 'A';
                            tablaSimbolos.add(filaTabla(lexema, estado));
                            lexema.clear();
                            estado = 'A';
                        } else if (lexema.size()==2){
                            estado='N';
                            lexema.remove(lexema.size() - 1);
                            tablaSimbolos.add(filaTabla(lexema, estado));
                            lexema.clear();
                            estado = 'N';
                            i--;
                        } else {
                            while (!lexema.isEmpty()) {
                                i--;
                                lexema.remove(lexema.size() - 1);
                            }
                            lexema.add(caracteres.get(i));
                            estado = 'A';
                            tablaSimbolos.add(filaTabla(lexema, estado));
                            lexema.clear();
                        }

                    }
                    break;
                case 'N':
                    lexema.clear();
                    estado = 'A';
                    break;                    
            }
            i++;
        }
        if (!lexema.isEmpty()){
            tablaSimbolos.add(filaTabla(lexema, estado));
            lexema.clear();
            estado = 'A';       
        }
        mostrarTabla(tablaSimbolos);
        tokensAux = new ArrayList<>(tokens);
        //separarTabla(tablaID);
        //mostrarTabla(tablaID);
    }
    public String filaTabla(ArrayList<Character> lexema, char estado) {
        StringBuilder lexe = new StringBuilder();
        String lexString;
        String resultado;
        int token;
        boolean idRepetido=false;
        for (Character c : lexema) {
            lexe.append(c);
        }
        lexString = lexe.toString();
        token = buscaToken(lexString, estado);
        tokens.add(token);
        switch (estado) {
            case 'B':
                String tipo = esIdentificador(lexString);
                if (tipo.equals(ID)) {                    
                    token = 100;
                    for (Identificador id : identificadores){
                        if (id.getLexema().equals(lexString)){
                            idRepetido=true;
                            idConDuplicados.add(new Identificador(lexString, "NULO", "ND"));
                        }
                    }
                    if (!idRepetido){
                        identificadores.add(new Identificador(lexString, "NULO", "ND"));
                        idConDuplicados.add(new Identificador(lexString, "NULO", "ND"));
                    }
                } else {
                    token = buscaToken(lexString, estado);
                }
                resultado = String.format("%-10s  %-18s  %-4d", lexString, esIdentificador(lexString), token);
                break;
            case 'C':
                resultado = String.format("%-10s  %-18s  %-4d", lexString, LE, token);
                break;
            case 'F':
                resultado = String.format("%-10s  %-18s  %-4d", lexString, LR, token);
                break;
            case 'G':
                resultado = String.format("%-10s  %-18s  %-4d", lexString, OP, token);
                break;
            case 'H':
                resultado = String.format("%-10s  %-18s  ", lexString, CM);
                break;
            case 'J':
                resultado = String.format("%-10s  %-18s  %-4d", lexString, OP, token);
                break;
            case 'K':
                resultado = String.format("%-10s  %-18s  %-4d", lexString, LS, token);
                break;
            case 'L':
                resultado = String.format("%-10s  %-18s  %-4d", lexString, LS, token);
                break;
            case 'N':
                resultado = String.format("%-10s  %-18s  %-4d", lexString, LC, token);
                break;
            default:
                resultado = String.format("%-10s  %-18s  %-4d", lexString, "Error", token);              
                break;                
        }
        return resultado;
    }
    public String esIdentificador(String lexema){
        for (String pReservada : listaPalabrasReservadas){
            if (lexema.equals(pReservada)){
                return PR;
            }
        }
        for (String litLogico : listaLogicos){
            if (lexema.equals(litLogico)){
                return LL;
            }
        }
        return ID;
    }
    public int buscaToken(String lexema, char estado){
        int i;
        switch (estado){
            case 'B':
                for (i=0; i<listaPalabrasReservadas.size();i++){
                    if (listaPalabrasReservadas.get(i).equals(lexema)){
                        //tokens.add(20+i);
                        return 20+i;
                    }
                }
                for (i=0; i<listaLogicos.size();i++){
                    if (listaLogicos.get(i).equals(lexema)){
                        //tokens.add(1400+i);
                        return 1400+i;
                    }
                }
                //tokens.add(100);
                return 100;
            case 'C':
                //tokens.add(1000);
                return 1000;
            case 'F':
                //tokens.add(1100);
                return 1100;
            case 'G':
                for (i=0; i<listaOperadores.size();i++){
                    if (listaOperadores.get(i).equals(lexema)){
                        //tokens.add(60+i);
                        return 60+i;
                    }
                }
                //tokens.add(999);
                return 999;
            case 'J':
                //tokens.add(0);
                return 0;
            case 'L':
                //tokens.add(1200);
                return 1200;
            case 'N':
                //tokens.add(1300);
                return 1300;
            default:
                //tokens.add(1);
                return 1;
        }
    }
    public boolean detectaDigito(char caracter) {
        return caracter >= '0' && caracter <= '9';
    }
    public boolean detectaLetra(char caracter){
        return (caracter>='a' && caracter<= 'z') || (caracter>='A' && caracter<= 'Z');
    }
    public void encabezadoTablaSimbolos(ArrayList<String> tabla){
        tabla.add("TABLA DE SIMBOLOS");
        tabla.add("______________________________________");
        tabla.add(String.format("%-10s  %-18s  %-4s", "Lexema", "Tipo", "Token"));
        tabla.add("______________________________________");
    }
    public void encabezadoTablaIdentificadores(){
        System.out.println("\nTABLA DE IDENTIFICADORES");
        System.out.println("____________________________________________________");
        System.out.println(String.format("%-12s    %10s     %4s   %13s", "Lexema", "Valor", "Tipo", "Token"));
        System.out.println("____________________________________________________");
    }
    public void mostrarTabla(ArrayList<String> tabla){
        for (String s : tabla){
            System.out.println(s);
        }
    }
    public void mostrarTablaID(){
        buscarTipoID();
        for (Identificador id: identificadores) {
            System.out.println(id);
        }
        faltaDeclaracion();
        listaErrores();
        verificarOperaciones();
    }
    /**
     * @return the identificadores
     */
    public ArrayList<Identificador> getIdentificadores() {
        return identificadores;
    }

    /**
     * @param identificadores the identificadores to set
     */
    public void setIdentificadores(ArrayList<Identificador> identificadores) {
        this.identificadores = identificadores;
    }

    public void buscarTipoID() {
        int i = 0;
        int k;
        boolean fin;
        String tipoAux = "";
        for (int j = 0; j < tokensAux.size(); j++) {
            if (tokensAux.get(j) == 100) {
                k = j;
                for (Identificador id : identificadores) {
                    fin = false;
                    if (id.equals(idConDuplicados.get(i))) {
                        //System.out.println(id.getLexema());     
                        if (tokensAux.get(j - 1) == 27) {
                            while ((k < tokensAux.size()) && !fin) {
                                switch (tokensAux.get(k)) {
                                    case 33:
                                        if (id.getTipo().equals("ND")) {
                                            id.setTipo("ENT");
                                            fin=true;
                                        } else {
                                            id.anadirError("ERROR CON ID \"" + idConDuplicados.get(i).getLexema() + "\"... REDECLARACION DE TIPO");
                                        }
                                        break;
                                    case 34:
                                        if (id.getTipo().equals("ND")) {
                                            id.setTipo("REAL");
                                            fin=true;
                                        } else {
                                            id.anadirError("ERROR CON ID \"" + idConDuplicados.get(i).getLexema() + "\"... REDECLARACION DE TIPO");
                                        }
                                        break;
                                    case 35:
                                        if (id.getTipo().equals("ND")) {
                                            id.setTipo("let");
                                            fin=true;
                                        } else {
                                            id.anadirError("ERROR CON ID \"" + idConDuplicados.get(i).getLexema() + "\"... REDECLARACION DE TIPO");
                                        }
                                        break;
                                    case 36:
                                        if (id.getTipo().equals("ND")) {
                                            id.setTipo("log");
                                            fin=true;
                                        } else {
                                            id.anadirError("ERROR CON ID \"" + idConDuplicados.get(i).getLexema() + "\"... REDECLARACION DE TIPO");
                                        }
                                        break;
                                    case 37:
                                        if (id.getTipo().equals("ND")) {
                                            id.setTipo("cad");
                                            fin=true;
                                        } else {
                                            id.anadirError("ERROR CON ID \"" + idConDuplicados.get(i).getLexema() + "\"... REDECLARACION DE TIPO");
                                        }
                                        break;
                                }
                                k++;
                            }
                        } else if (tokensAux.get(j - 1) == 25) {
                            if (id.getTipo().equals("ND")) {
                                id.setTipo("CLASE");
                            }
                        } else if (tokensAux.get(j - 1) == 28) {
                            if (id.getTipo().equals("ND")){
                                id.setTipo("PROCEDIMIENTO");
                            }
                        } else if (tokensAux.get(j + 1) == 100 || tokensAux.get(j - 1) == 26) {
                        } else if (tokensAux.get(j - 1) == 100) {
                            if (id.getTipo().equals("ND")) {
                                id.setTipo(idConDuplicados.get(i - 1).getLexema());
                            } else {
                                id.anadirError("ERROR CON ID \"" + idConDuplicados.get(i).getLexema() + "\"... REDECLARACION DE TIPO");
                            }
                        } else {
                            while ((k > 0) && tokensAux.get(k) != 77 && tokensAux.get(k) != 78 && tokensAux.get(k) != 79 && !fin && tokensAux.get(k) != 75 && tokensAux.get(k) != 74) {
                                switch (tokensAux.get(k)) {
                                    case 33:
                                        if (id.getTipo().equals("ND")) {            
                                            id.setTipo("ENT");
                                            fin=true;
                                        } else {     
                                            id.anadirError("ERROR CON ID \"" + idConDuplicados.get(i).getLexema() + "\"... REDECLARACION DE TIPO");
                                        }
                                        break;
                                    case 34:
                                        if (id.getTipo().equals("ND")) {
                                            id.setTipo("REAL");
                                            fin=true;
                                        } else {
                                            id.anadirError("ERROR CON ID \"" + idConDuplicados.get(i).getLexema() + "\"... REDECLARACION DE TIPO");
                                        }
                                        break;
                                    case 35:
                                        if (id.getTipo().equals("ND")) {
                                            id.setTipo("let");
                                            fin=true;
                                        } else {
                                            id.anadirError("ERROR CON ID \"" + idConDuplicados.get(i).getLexema() + "\"... REDECLARACION DE TIPO");
                                        }
                                        break;
                                    case 36:
                                        if (id.getTipo().equals("ND")) {
                                            id.setTipo("log");
                                            fin=true;
                                        } else {
                                            id.anadirError("ERROR CON ID \"" + idConDuplicados.get(i).getLexema() + "\"... REDECLARACION DE TIPO");
                                        }
                                        break;
                                    case 37:
                                        if (id.getTipo().equals("ND")) {
                                            id.setTipo("cad");
                                            fin=true;
                                        } else {
                                            id.anadirError("ERROR CON ID \"" + idConDuplicados.get(i).getLexema() + "\"... REDECLARACION DE TIPO");
                                        }
                                        break;
                                }
                                k--;
                            }
                        }
                    }
                }
                i++;
            }
        }
    }

    public void verificarOperaciones() {
        int j = 0;
        int k;
        boolean asignInvalida = false;
        String id1;
        String id2;
        String idInvalido="";
        String tipo1;
        String tipo2;
        System.out.println("");
        for (int i = 0; i < tokensAux.size(); i++) {
            k=1;
            tipo1="";
            tipo2="";
            id1="";
            id2="";
            switch (tokensAux.get(i)) {
                case 60:
                    while (tokensAux.get(i - k) == 75 || tokensAux.get(i - k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i - k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i - k) == 1000) {
                        tipo1 = "ENT";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1100) {
                        tipo1 = "REAL";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1200) {
                        tipo1 = "cad";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i - k));
                    }
                    k = 1;
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1000) {
                        tipo2 = "ENT";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1100) {
                        tipo2 = "REAL";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1200) {
                        tipo2 = "cad";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id2 = buscarLexema(tokensAux.get(i + k));
                    }
                    
                    if (tipo1.equals("ENT") || tipo1.equals("REAL")) {
                        if (!tipo2.equals("ENT") && !tipo2.equals("REAL")) {
                            System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                        }
                    } else if (!tipo1.equals(tipo2) || tipo1.equals("") && tipo2.equals("")) {
                        System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                    }
                    break;
                case 61:
                    while (tokensAux.get(i - k) == 75 || tokensAux.get(i - k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i - k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i - k) == 1000) {
                        tipo1 = "ENT";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1100) {
                        tipo1 = "REAL";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i - k));
                    }
                    k = 1;
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1000) {
                        tipo2 = "ENT";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1100) {
                        tipo2 = "REAL";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id2 = buscarLexema(tokensAux.get(i + k));
                    }
                    if (tipo1.equals("ENT") || tipo1.equals("REAL")) {
                        if (!tipo2.equals("ENT") && !tipo2.equals("REAL")) {
                            System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                        }
                    } else if (!tipo1.equals(tipo2) || tipo1.equals("") && tipo2.equals("")) {
                        System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                    }
                    break;
                case 62:
                    while (tokensAux.get(i - k) == 75 || tokensAux.get(i - k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i - k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i - k) == 1000) {
                        tipo1 = "ENT";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1100) {
                        tipo1 = "REAL";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i - k));
                    }
                    k = 1;
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1000) {
                        tipo2 = "ENT";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1100) {
                        tipo2 = "REAL";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id2 = buscarLexema(tokensAux.get(i + k));
                    }
                    if (tipo1.equals("ENT") || tipo1.equals("REAL")) {
                        if (!tipo2.equals("ENT") && !tipo2.equals("REAL")) {
                            System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                        }
                    } else if (!tipo1.equals(tipo2) || tipo1.equals("") && tipo2.equals("")) {
                        System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                    }
                    break;
                case 63:
                    while (tokensAux.get(i - k) == 75 || tokensAux.get(i - k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i - k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i - k) == 1000) {
                        tipo1 = "ENT";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1100) {
                        tipo1 = "REAL";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i - k));
                    }
                    k = 1;
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1000) {
                        tipo2 = "ENT";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1100) {
                        tipo2 = "REAL";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id2 = buscarLexema(tokensAux.get(i + k));
                    }
                    if (tipo1.equals("ENT") || tipo1.equals("REAL")) {
                        if (!tipo2.equals("ENT") && !tipo2.equals("REAL")) {
                            System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                        }
                    } else if (!tipo1.equals(tipo2) || tipo1.equals("") && tipo2.equals("")) {
                        System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                    }
                    break;
                case 64:
                    while (tokensAux.get(i - k) == 75 || tokensAux.get(i - k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i - k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i - k) == 1000) {
                        tipo1 = "ENT";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1100) {
                        tipo1 = "REAL";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i - k));
                    }
                    k = 1;
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1000) {
                        tipo2 = "ENT";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1100) {
                        tipo2 = "REAL";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id2 = buscarLexema(tokensAux.get(i + k));
                    }
                    if (tipo1.equals("ENT") || tipo1.equals("REAL")) {
                        if (!tipo2.equals("ENT") && !tipo2.equals("REAL")) {
                            System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                        }
                    } else if (!tipo1.equals(tipo2) || tipo1.equals("") && tipo2.equals("")) {
                        System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                    }
                    break;
                case 65:
                    while (tokensAux.get(i - k) == 75 || tokensAux.get(i - k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i - k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i - k) == 1000) {
                        tipo1 = "ENT";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1100) {
                        tipo1 = "REAL";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i - k));
                    }
                    k = 1;
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1000) {
                        tipo2 = "ENT";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1100) {
                        tipo2 = "REAL";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id2 = buscarLexema(tokensAux.get(i + k));
                    }
                    if (tipo1.equals("ENT") || tipo1.equals("REAL")) {
                        if (!tipo2.equals("ENT") && !tipo2.equals("REAL")) {
                            System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                        }
                    } else if (!tipo1.equals(tipo2) || tipo1.equals("") && tipo2.equals("")) {
                        System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                    }
                    break;
                case 66:
                    while (tokensAux.get(i - k) == 75 || tokensAux.get(i - k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i - k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i - k) == 1000) {
                        tipo1 = "ENT";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1100) {
                        tipo1 = "REAL";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i - k));
                    }
                    k = 1;
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1000) {
                        tipo2 = "ENT";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1100) {
                        tipo2 = "REAL";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id2 = buscarLexema(tokensAux.get(i + k));
                    }
                    if (tipo1.equals("ENT") || tipo1.equals("REAL")) {
                        if (!tipo2.equals("ENT") && !tipo2.equals("REAL")) {
                            System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                        }
                    } else if (!tipo1.equals(tipo2) || tipo1.equals("") && tipo2.equals("")) {
                        System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                    }
                    break;
                case 67:
                    while (tokensAux.get(i - k) == 75 || tokensAux.get(i - k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i - k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i - k) == 1000) {
                        tipo1 = "ENT";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1100) {
                        tipo1 = "REAL";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i - k));
                    }
                    k = 1;
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1000) {
                        tipo2 = "ENT";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1100) {
                        tipo2 = "REAL";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id2 = buscarLexema(tokensAux.get(i + k));
                    }
                    if (tipo1.equals("ENT") || tipo1.equals("REAL")) {
                        if (!tipo2.equals("ENT") && !tipo2.equals("REAL")) {
                            System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                        }
                    } else if (!tipo1.equals(tipo2) || tipo1.equals("") && tipo2.equals("")) {
                        System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                    }
                    break;
                case 68:
                    while (tokensAux.get(i - k) == 75 || tokensAux.get(i - k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i - k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i - k) == 1000) {
                        tipo1 = "ENT";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1100) {
                        tipo1 = "REAL";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i - k));
                    }
                    k = 1;
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1000) {
                        tipo2 = "ENT";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1100) {
                        tipo2 = "REAL";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id2 = buscarLexema(tokensAux.get(i + k));
                    }
                    if (tipo1.equals("ENT") || tipo1.equals("REAL")) {
                        if (!tipo2.equals("ENT") && !tipo2.equals("REAL")) {
                            System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                        }
                    } else if (!tipo1.equals(tipo2) || tipo1.equals("") && tipo2.equals("")) {
                        System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                    }
                    break;
                case 69:
                    while (tokensAux.get(i - k) == 75 || tokensAux.get(i - k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i - k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i - k) == 1000) {
                        tipo1 = "ENT";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1100) {
                        tipo1 = "REAL";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i - k));
                    }
                    k = 1;
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1000) {
                        tipo2 = "ENT";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1100) {
                        tipo2 = "REAL";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id2 = buscarLexema(tokensAux.get(i + k));
                    }
                    if (tipo1.equals("ENT") || tipo1.equals("REAL")) {
                        if (!tipo2.equals("ENT") && !tipo2.equals("REAL")) {
                            System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                        }
                    } else if (!tipo1.equals(tipo2) || tipo1.equals("") && tipo2.equals("")) {
                        System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                    }
                    break;
                case 70:
                    while (tokensAux.get(i - k) == 75 || tokensAux.get(i - k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i - k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i - k) == 1000) {
                        tipo1 = "ENT";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1100) {
                        tipo1 = "REAL";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i - k));
                    }
                    k = 1;
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1000) {
                        tipo2 = "ENT";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1100) {
                        tipo2 = "REAL";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id2 = buscarLexema(tokensAux.get(i + k));
                    }
                    if (tipo1.equals("ENT") || tipo1.equals("REAL")) {
                        if (!tipo2.equals("ENT") && !tipo2.equals("REAL")) {
                            System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                        }
                    } else if (!tipo1.equals(tipo2) || tipo1.equals("") && tipo2.equals("")) {
                        System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                    }
                    break;
                case 71:
                    while (tokensAux.get(i - k) == 75 || tokensAux.get(i - k) == 76 || tokensAux.get(i - k) == 73) {
                        k++;
                    }
                    if (tokensAux.get(i - k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i - k) == 1400) {
                        tipo1 = "log";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1401) {
                        tipo1 = "log";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i - k));
                    }
                    k = 1;
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76 || tokensAux.get(i + k) == 73) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1400) {
                        tipo2 = "log";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1401) {
                        tipo2 = "log";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id2 = buscarLexema(tokensAux.get(i + k));
                    }
                    if (!(tipo1.equals("log") && tipo1.equals(tipo2))) {
                        System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                    }
                    break;
                case 72:
                    while (tokensAux.get(i - k) == 75 || tokensAux.get(i - k) == 76 || tokensAux.get(i - k) == 73) {
                        k++;
                    }
                    if (tokensAux.get(i - k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i - k) == 1400) {
                        tipo1 = "log";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else if (tokensAux.get(i - k) == 1401) {
                        tipo1 = "log";
                        id1 = buscarLexema(tokensAux.get(i - k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i - k));
                    }
                    k = 1;
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76 || tokensAux.get(i + k) == 73) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1400) {
                        tipo2 = "log";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1401) {
                        tipo2 = "log";
                        id2 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id2 = buscarLexema(tokensAux.get(i + k));
                    }
                    if (!(tipo1.equals("log") && tipo1.equals(tipo2))) {
                        System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + id2);
                    }
                    break;
                case 73:
                    while (tokensAux.get(i + k) == 75 || tokensAux.get(i + k) == 76 || tokensAux.get(i + k) == 73) {
                        k++;
                    }
                    if (tokensAux.get(i + k) == 100) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                    } else if (tokensAux.get(i + k) == 1400) {
                        tipo1 = "log";
                        id1 = buscarLexema(tokensAux.get(i + k));
                    } else if (tokensAux.get(i + k) == 1401) {
                        tipo1 = "log";
                        id1 = buscarLexema(tokensAux.get(i + k));
                    } else {
                        id1 = buscarLexema(tokensAux.get(i + k));
                    }
                    if (!tipo1.equals("log")) {
                        System.out.println("ERROR EN EXPRESION: " + buscarLexema(tokensAux.get(i)) + id1);
                    }
                    break;
                case 74:
                    if (tokensAux.get(i - 1) != 76 && tokensAux.get(i + 1) != 26) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j - 1))) {
                                tipo1 = id.getTipo();
                                id1 = id.getLexema();
                            }
                        }
                        asignInvalida = false;
                        while (tokensAux.get(i + k) != 79 && !asignInvalida && tokensAux.get(i + k) != 77) {
                            tipo2 = "";
                            id2 = "";
                            if (tipo1.equals("REAL") || tipo1.equals("ENT")) {
                                if (tokensAux.get(i + k) == 100) {
                                    for (Identificador id : identificadores) {
                                        if (id.equals(idConDuplicados.get(j))) {
                                            tipo2 = id.getTipo();
                                            id2 = id.getLexema();
                                        }
                                    }
                                } else if (tokensAux.get(i + k) == 1000) {
                                    tipo2 = "ENT";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 1100) {
                                    tipo2 = "REAL";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 1400) {
                                    tipo2 = "log";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 1401) {
                                    tipo2 = "log";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 1200) {
                                    tipo2 = "cad";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 1300) {
                                    tipo2 = "let";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 75){
                                    tipo2 = "conti";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 76){
                                    tipo2 = "conti";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 26){
                                    tipo2 = "conti";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else {
                                    tipo2="conti";
                                }
                                if (!tipo2.equals("REAL") && !tipo2.equals("ENT") && !tipo2.equals("conti")) {
                                    asignInvalida = true;
                                    idInvalido = id2;
                                }
                            } else {
                                if (tokensAux.get(i + k) == 100) {
                                    for (Identificador id : identificadores) {
                                        if (id.equals(idConDuplicados.get(j))) {
                                            tipo2 = id.getTipo();
                                            id2 = id.getLexema();
                                        }
                                    }
                                } else if (tokensAux.get(i + k) == 1000) {
                                    tipo2 = "ENT";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 1100) {
                                    tipo2 = "REAL";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 1400) {
                                    tipo2 = "log";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 1401) {
                                    tipo2 = "log";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 1200) {
                                    tipo2 = "cad";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 1300) {
                                    tipo2 = "let";
                                    id2 = buscarLexema(tokensAux.get(i + k));
                                } else if (tokensAux.get(i + k) == 75) {
                                    tipo2 = "conti";
                                } else if (tokensAux.get(i + k) == 76) {
                                    tipo2 = "conti";
                                } else if (tokensAux.get(i + k) == 26){
                                    tipo2 = "conti";
                                } else {
                                    tipo2 = "conti";
                                }
                                if (!tipo1.equals(tipo2) && !tipo2.equals("conti")) {
                                    asignInvalida = true;
                                    idInvalido = id2;
                                }
                            }
                            k++;
                        }
                        if (asignInvalida) {
                            System.out.println("ERROR EN EXPRESION: " + id1 + buscarLexema(tokensAux.get(i)) + idInvalido);
                        }
                    } else if (tokensAux.get(i + 1) == 26) {
                        for (Identificador id : identificadores) {
                            if (id.equals(idConDuplicados.get(j-1))) {
                                tipo2 = id.getTipo();
                                id2 = id.getLexema();
                            }
                        }
                        if (!tipo2.equals(idConDuplicados.get(j).getLexema())){
                            System.out.println("ERROR EN EXPRESION: " + idConDuplicados.get(j-1).getLexema() + buscarLexema(tokensAux.get(i)) + idConDuplicados.get(j).getLexema());
                        }
                    }              
                    break;            
                case 100:
                    j++;
                    break;

            }
        }
    }
    public void listaErrores(){
        boolean hayError = false;
        for (Identificador id: identificadores){
            if (id.getErrores().size()>0 && !hayError){
                System.out.println("\nERRORES DE DECLARACION:");
                id.mostrarErrores();
                hayError=true;
            } else if (id.getErrores().size()>0){
                id.mostrarErrores();
            }
        }
        if (!hayError){
            System.out.println("\nNo hay errores de REALlaracion");
        }
    }
    public void faltaDeclaracion (){
        for (Identificador id: identificadores){
            if (id.getTipo().equals("ND")){
                id.getErrores().add("ERROR CON ID \"" + id.getLexema() + "\"... VARIABLE NO DECLARADA");
            }
        }
    }
    /**
     * @return the tablaSimbolos
     */
    public ArrayList<String> getTablaSimbolos() {
        return tablaSimbolos;
    }

    /**
     * @param tablaSimbolos the tablaSimbolos to set
     */
    public void setTablaSimbolos(ArrayList<String> tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }

    /**
     * @return the tablaID
     */
    public ArrayList<String> getTablaID() {
        return tablaID;
    }

    /**
     * @param tablaID the tablaID to set
     */
    public void setTablaID(ArrayList<String> tablaID) {
        this.tablaID = tablaID;
    }

    /**
     * @return the idConDuplicados
     */
    public ArrayList<Identificador> getIdConDuplicados() {
        return idConDuplicados;
    }

    /**
     * @param idConDuplicados the idConDuplicados to set
     */
    public void setIdConDuplicados(ArrayList<Identificador> idConDuplicados) {
        this.idConDuplicados = idConDuplicados;
    }
}
