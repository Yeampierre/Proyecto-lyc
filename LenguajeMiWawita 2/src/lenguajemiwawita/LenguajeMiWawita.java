/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lenguajemiwawita;

import LenguajeMiWawita.Lexico.LexerMiWawita;
import static LenguajeMiWawita.Sintactico.ParserMiWawita.parser;

/**
 *
 * @author LARRY
 */
public class LenguajeMiWawita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LexerMiWawita lex = new LexerMiWawita();
        System.out.println(lex.getTokens());
        parser(lex.getTokens());
        lex.encabezadoTablaIdentificadores();
        lex.mostrarTablaID();
    }
    
}
