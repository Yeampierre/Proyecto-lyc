/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LenguajeMiWawita.Sintactico;

import java.util.ArrayList;

/**
 *
 * @author LARRY
 */
public class ParserMiWawita {
        public static void parser(ArrayList<Integer> tokens) {
        int[][] TAS = new int[62][51];
        int fila;
        int columna;
        boolean aceptado = false;
        ArrayList<Integer> pila = new ArrayList<>();
        pila.add(999);
        pila.add(-2);
        tokens.add(999);
        for (int i = 0; i < 62; i++) {
            for (int j = 0; j < 51; j++) {
                TAS[i][j] = -999;
            }
        }
        //Inicializacion de TAS
                            TAS[0][1] = 100;    TAS[0][2] = 1000;   TAS[0][3] = 27;     TAS[0][4] = 29; TAS[0][5] = 28;     TAS[0][6] = 25;     TAS[0][7] = 20; TAS[0][8] = 75; TAS[0][9] = 76; TAS[0][10] = 77;    TAS[0][11] = 78;    TAS[0][12] = 79; TAS[0][13] = 81; TAS[0][14] = 82; TAS[0][15] = 83;   TAS[0][16] =80;   TAS[0][17] =32; TAS[0][18] = 33;    TAS[0][19] = 34;    TAS[0][20] = 36;    TAS[0][21] = 35;    TAS[0][22] = 37;    TAS[0][23] = 74;    TAS[0][24] = 26;    TAS[0][25] = 60;    TAS[0][26] = 61;    TAS[0][27] = 62;    TAS[0][28] = 63;    TAS[0][29] =64; TAS[0][30] = 65;    TAS[0][31] = 66;    TAS[0][32] = 67;    TAS[0][33] = 68;    TAS[0][34] = 69;    TAS[0][35] = 70;    TAS[0][36] = 71;    TAS[0][37] = 72;    TAS[0][38] = 73;    TAS[0][39] = 30;    TAS[0][40] = 31;    TAS[0][41] = 21;    TAS[0][42] = 22;    TAS[0][43] = 23;    TAS[0][44] = 24;    TAS[0][45] = 1100;  TAS[0][46] = 1300;  TAS[0][47] = 1200;  TAS[0][48] = 1401;  TAS[0][49] = 1400;  TAS[0][50] = 999;
        TAS[1][0] = -1;                                                                                                                                                         TAS[1][9]=-211;                                         TAS[1][12]=-211;                                                                                                                                                                                                                                                                                                                                                                                                                                                            TAS[1][36] = -210;  TAS[1][37]=-210;        
        TAS[2][0] = -2;                                             TAS[2][3]=-102;                     TAS[2][5]=-100;     TAS[2][6] = -101;
        TAS[3][0] = -3;                                             TAS[3][3] = -109; 
        TAS[4][0] = -4;                                                                                                                                         TAS[4][8]=-111;                                                                                                            TAS[4][15] = -110;  
        TAS[5][0] = -5;     TAS[5][1] = -112;
        TAS[6][0] = -6;     TAS[6][1] = -113;
        TAS[7][0] = -7;                                                                                                                                                         TAS[7][9]=-115;                                         TAS[7][12]=-115; TAS[7][13]=-114;                  TAS[7][15] = -115;   TAS[7][16] = -115;                                                                                              TAS[7][23] = -115;
        TAS[8][0] = -8;                                                                                                                                                                                                                                                                                                                         TAS[8][18]=-116;    TAS[8][19] = -117;  TAS[8][20] = -118;  TAS[8][21]=-119;    TAS[8][22]=-120;
        TAS[9][0] = -9;                                                                                 TAS[9][5]=-121;
        TAS[10][0] = -10;                                                                                                                                                       TAS[10][9]=-123;                                                                                                                                                TAS[10][18] = -122;  TAS[10][19]=-122;   TAS[10][20]= -122;  TAS[10][21]=-122;   TAS[10][22]=-122;             
        TAS[11][0] = -11;                                                                                                                                                       TAS[11][9]=-125;                                                                                                              TAS[11][16] = -124;    
        TAS[12][0] = -12;   TAS[12][1] = -126;                                                                                                                                  TAS[12][9]=-127;      
        TAS[13][0] = -13;                                                                                                                                                       TAS[13][9]=-129;                                                                                                              TAS[13][16] = -128;                   
        TAS[14][0] = -14;                                                                                                   TAS[14][6]=-130;
        TAS[15][0] = -15;   TAS[15][1] = -131;                                          TAS[15][4]=-131;                                                                                                            TAS[15][11]=-132;                                                                                           TAS[15][17]=-131;TAS[15][18] = -131; TAS[15][19] = -131; TAS[15][20] = -131; TAS[15][21]=-131;  TAS[15][22]=-131;                                                                                                                                                                                                                                                                                                                               TAS[15][39]=-131; TAS[15][40]=-131;   TAS[15][41]=-131;                       TAS[15][43]=-131;   TAS[15][44] = -131;                                
        TAS[16][0] = -16;   TAS[16][1] = -135;                                          TAS[16][4]=-134;                                                                                                                                                                                                                        TAS[16][17]=-133;TAS[16][18] = -133; TAS[16][19] = -133; TAS[16][20] = -133; TAS[16][21]=-133;  TAS[16][22]=-133;                                                                                                                                                                                                                                                                                                                               TAS[16][39]=-134; TAS[16][40]=-134;   TAS[16][41]=-136;                       TAS[16][43]=-136;   TAS[16][44] = -136;                                
        TAS[17][0] = -17;   TAS[17][1] = -147;                                                                                                                  TAS[17][8]=-146;                                                                                                           TAS[17][15] = -146;                                                                                                                                     TAS[17][23] = -145;
        TAS[18][0] = -18;                                                                                                                                       TAS[18][8] = -148;                                                                                                        TAS[18][15] = -148; 
        TAS[19][0] = -19;   TAS[19][1] = -155;  TAS[19][2] = -154;                                                                                                                                                                      TAS[19][12] = -156;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             TAS[19][45] = -153; TAS[19][46] = -152; TAS[19][47] = -151; TAS[19][48] = -149; TAS[19][0] = -150;          
        TAS[20][0] = -20;   TAS[20][1] = -208;  TAS[20][2] = -208;                                                                                              TAS[20][8]=-209;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        TAS[20][45] = -208;      
        TAS[21][0] = -21;                                                                                                                                                                                                                                                                                                       TAS[21][17]=-157; TAS[21][18]=-157;  TAS[21][19]=-157;   TAS[21][20]=-157;   TAS[21][21]=-157;  TAS[21][22]=-157;
        TAS[22][0] = -22;                                                                                                                                                                                                                                                                                                       TAS[22][17]=-218; TAS[22][18] =-219; TAS[22][19] = -219;  TAS[22][20]=-219;  TAS[22][21]=-219;  TAS[22][22] = -219;            
        TAS[23][0] = -23;                                                                                                                                                                                                                                                                                                                         TAS[23][18]=-158;  TAS[23][19] = -159;  TAS[23][20]=-160;  TAS[23][21]=-161;  TAS[23][22] = -162;
        TAS[24][0] = -24;                                                                                                                                                                                                               TAS[24][12] = -221;                                                    TAS[24][16]=-220;                                                                                                                    TAS[24][23] = -221;
        TAS[25][0] = -25;   TAS[25][1] = -104;                                                                                                 TAS[25][7] = -103;                        
        TAS[26][0] = -26;                                           TAS[26][3]=-107;                     TAS[26][5]=-105; TAS[26][6] = -106;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                TAS[26][50] = -108;    
        TAS[27][0] = -27;   TAS[27][1] = -137;                      TAS[27][3]=-137;    TAS[27][4]=-137; TAS[27][5]=-137;                                                                                         TAS[27][11]=-138;                                                                                           TAS[27][17]=-137; TAS[27][18]=-137;  TAS[27][19]=-137;    TAS[27][20]=-137; TAS[27][21]=-137;   TAS[27][22] = -137;                                                                                                                                                                                                                                                                                                                               TAS[27][39]=-137;  TAS[27][40]=-137;  TAS[27][41]=-137;                        TAS[27][43] = -137; TAS[27][44] = -137;                  
        TAS[28][0] = -28;   TAS[28][1] = -141;                      TAS[28][3]=-143;    TAS[28][4]=-140; TAS[28][5]=-144;                                                                                                                                                                                                     TAS[28][17]=-139; TAS[28][18]=-139;  TAS[28][19]=-139;    TAS[28][20]=-139;   TAS[28][21]=-139;   TAS[28][22]=-139;                                                                                                                                                                                                                                                                                                                               TAS[28][39]=-140;  TAS[28][40]=-140;  TAS[28][41]=-142;                        TAS[28][43]=-142;   TAS[28][44]=-142;
        TAS[29][0] = -29;   TAS[29][1] = -225;   TAS[29][2] = -225;                                                                                              TAS[29][8]=-225;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        TAS[29][45] =-225; TAS[29][46] = -227;  TAS[29][47] = -226; TAS[29][48] = -228;  TAS[29][49] = -229;                   
        TAS[30][0] = -30;                                                                                                                                        TAS[30][8]=-222; TAS[30][9]=-223;                                     TAS[30][12] = -223;                                TAS[30][15] = -222;                                                                                                                                                                             TAS[30][25] = -223; TAS[30][26] = -223; TAS[30][27] = -223;   TAS[30][28] = -223; TAS[30][29]=-223; TAS[30][30] = -223; TAS[30][31]=-223; TAS[30][32]=-223;   TAS[30][33] = -223; TAS[30][34] = -223; TAS[30][35] = -223; TAS[30][36] = -223; TAS[30][37] = -223;
        TAS[31][0] = -31;                                                                                                                                                                                                                                                                                                                        TAS[31][18]=-163;
        TAS[32][0] = -32;                                                                                                                                                                                                                                                                                                                                                                                                                          TAS[32][23] = -224;
        TAS[33][0] = -33;                                                                                                                                                                                                              TAS[33][12] = -165;                                                                                                                                                                                         TAS[33][23] = -164;
        TAS[34][0] = -34;                                                               TAS[34][4]=-232;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       TAS[34][39] = -230; TAS[34][40] = -231;      
        TAS[35][0] = -35;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             TAS[35][41] = -234;                      TAS[35][43] = -233; TAS[35][44] = -235;        
        TAS[36][0] = -36;  TAS[36][1] = -237;                                                                                                                                                                                                                                                                                                   TAS[36][18]=-236;
        TAS[37][0] = -37;  TAS[37][1] = -239;                       TAS[37][3] = -239;                   TAS[37][5]=-239;                                                                                         TAS[37][11] = -239;                                                                                          TAS[37][17]=-239;TAS[37][18]=-239;   TAS[37][19]=-239;   TAS[37][20]=-239; TAS[37][21]=-239;   TAS[37][22]=-239;                                                                                                                                                                                                                                                                                                                                TAS[37][39] = -239; TAS[37][40] = -239; TAS[37][41] = -239; TAS[37][42] = -238; TAS[37][43] = -239; TAS[37][44] = -239;         
        TAS[38][0] = -38;                                                                                                                                                                        TAS[38][10]=-241;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     TAS[38][41] = -240;
        TAS[39][0] = -39;  TAS[39][1] = -166;   TAS[39][2] = -166;                                                                                              TAS[39][8] = -167;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      TAS[39][45]=-166;
        TAS[40][0] = -40;                                                                                                                                                         TAS[40][9]=-169;                                     TAS[40][12] = -169;                                                                                                                                                                                                                                TAS[40][25] = -168; TAS[40][26] = -168; TAS[40][27] = -168; TAS[40][28] = -168;  TAS[40][29] = -168;
        TAS[41][0] = -41;                                                                                                                                                                                                                                                                                                                                                                                                                                                                 TAS[41][25] = -170; TAS[41][26] = -171; TAS[41][27] = -172; TAS[41][28] = -173;  TAS[41][29] = -174;
        TAS[42][0] = -42;  TAS[42][1] = -177;   TAS[42][2] = -176;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      TAS[42][45]=-175;
        TAS[43][0] = -43;                                                                                                                                                                                                                                                                                                                                           TAS[43][19] = -178;
        TAS[44][0] = -44;                                                                                                                                                                                                                                                                                                                                                               TAS[44][20] = -179;
        TAS[45][0] = -45;                                                                                                                                                                                                              TAS[45][12] = -181;                                                                                                                                                                                          TAS[45][23] = -180;
        TAS[46][0] = -46;  TAS[46][1] = -182;                                                                                                                   TAS[46][8] = -182;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         TAS[46][38] = -183;                                                                                                                                                                                       TAS[46][48]=-182;  TAS[46][49] = -182;
        TAS[47][0] = -47;  TAS[47][1] = -184;                                                                                                                   TAS[47][8] = -185;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   TAS[47][48]=-184;  TAS[47][49] = -184;
        TAS[48][0] = -48;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  TAS[48][38] = -186; 
        TAS[49][0] = -49;                                                                                                                                                        TAS[49][9]=-188;                                      TAS[49][12] = -188;                                                                                                                                                                                                                                                                                                                                                                                                                                                          TAS[49][36] = -187; TAS[49][37] = -187;
        TAS[50][0] = -50;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           TAS[50][36] = -189; TAS[50][37] = -190;
        TAS[51][0] = -51;  TAS[51][1] = -193;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        TAS[51][48]=-191; TAS[51][49]=-192;                            
        TAS[52][0] = -52;                                                                                                                                                                                                                                                                                                                                                                                 TAS[52][21] = -194;
        TAS[53][0] = -53;                                                                                                                                                                                                              TAS[53][12] = -196;                                                                                                                                                                                        TAS[53][23] = -195;
        TAS[54][0] = -54;                                                                                                                                                                                                                                                                                                                                                                                                      TAS[54][22] = -199;         
        TAS[55][0] = -55;                                                                                                                                                                                                              TAS[55][12] = -201;                                                                                                                                                                                        TAS[55][23] = -200;
        TAS[56][0] = -56;  TAS[56][1] = -202;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   TAS[56][47]=-202;
        TAS[57][0] = -57;                                                                                                                                                        TAS[57][9]=-204;                                      TAS[57][12] = -204;                                                                                                                                                                                                                                TAS[57][25] = -203;
        TAS[58][0] = -58;  TAS[58][1] = -206;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   TAS[58][47]=-205;
        TAS[59][0] = -59;  TAS[59][1] = -207;
        TAS[60][0] = -60;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    TAS[60][30] = -212; TAS[60][31] = -213; TAS[60][32] = -214; TAS[60][33] = -215; TAS[60][34] = -216; TAS[60][35] = -217;
        TAS[61][0] = -61;  TAS[61][1] = -198;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              TAS[61][46] = -197;
        
        System.out.print(convertirTokensALexemas(pila));
        System.out.print(convertirTokensALexemas(tokens));
        System.out.println("");
        while (pila.get(pila.size() - 1) != 1 && pila.get(pila.size() - 1) != 999) {
            fila = pila.get(pila.size() - 1) * -1;
            columna = buscarTerminalTas(TAS, tokens.get(0));
            if (pila.get(pila.size() - 1) < 0 && pila.get(pila.size() - 1) != 1) {
                //System.out.println(fila);
                //System.out.println(columna);
                ejecutarProduccion(pila, TAS[fila][columna] * -1);
            } else if (pila.get(pila.size() - 1).equals(tokens.get(0))) {
                pila.remove(pila.size() - 1);
                tokens.remove(0);
            } else if ((pila.get(pila.size() - 1) > 0) && (tokens.get(0) > 0)) {
                pila.add(1);
            }
            System.out.print(convertirTokensALexemas(pila));
            System.out.print("/////////");
            System.out.print(convertirTokensALexemas(tokens));
            System.out.println("");
        }
        if (pila.get(pila.size() - 1) == 999 && tokens.get(0) == 999) {
            aceptado = true;
        }
        if (aceptado) {
            System.out.println("Entrada se acepta, G es LL(K)");
        } else {
            System.out.println("ERROR DE SINTAXIS");
        }

    }

    public static int buscarTerminalTas(int TAS[][], int valBus) {
        for (int i = 1; i <= 50; i++) {
            if (TAS[0][i] == valBus) {
                return i;
            }
        }
        return 7;
    }

    public static ArrayList<String> convertirTokensALexemas(ArrayList<Integer> tokens) {
        ArrayList<String> conversion = new ArrayList<>();
        for (int token : tokens) {
            conversion.add(buscarLexema(token));
        }
        return conversion;
    }

    public static String buscarLexema(int token) {
        switch (token) {
            case -1:
                return "<MasExpComp>";
            case -2:
                return "<FPCs>";
            case -3:
                return "<Funcion>";
            case -4:
                return "<LlamadaFPClase>";
            case -5:
                return "<IdentificadorFPC>";
            case -6:
                return "<Identificador>";
            case -7:
                return "<Arreglo>";
            case -8:
                return "<TipoDato>";
            case -9:
                return "<Procedimiento>";
            case -10:
                return "<ListaParametrosTipo>";
            case -11:
                return "<MasParametrosTipo>";
            case -12:
                return "<ListaParametros>";
            case -13:
                return "<MasParametros>";
            case -14:
                return "<Clase>";
            case -15:
                return "<LineasCodigo>";
            case -16:
                return "<Linea>";
            case -17:
                return "<AsignOLlamODecObj>";
            case -18:
                return "<LlamadaAux>";
            case -19:
                return "<ValRetorno>";
            case -20:
                return "<ExpComparacion>";
            case -21:
                return "<Declaracion>";
            case -22:
                return "<Constante>";
            case -23:
                return "<TipoDeclaracion>";
            case -24:
                return "<MasId>";
            case -25:
                return "<Main?>";
            case -26:
                return "<FPCsSinMain>";
            case -27:
                return "<LineasCodClase>";
            case -28:
                return "<LineaClase>";
            case -29:
                return "<ValorAsignado>";
            case -30:
                return "<LlamadaOId>";                       
            case -31:
                return "<DeclaracionEnt>";
            case -32:
                return "<Asignacion>";
            case -33:
                return "<InicializacionNum>";
            case -34:
                return "<Instruccion>";
            case -35:
                return "<EControl>";
            case -36:
                return "<Iterador>";
            case -37:
                return "<Negativo>";
            case -38:
                return "<OtraCondicion>";
            case -39:
                return "<ExpArit>";
            case -40:
                return "<MasExpArit>";                               
            case -41:
                return "<OperadorArit>";
            case -42:
                return "<VariableArit>";
            case -43:
                return "<DeclaracionDec>";
            case -44:
                return "<DeclaracionLog>";
            case -45:
                return "<InicializacionLog>";
            case -46:
                return "<ExpLogica>";
            case -47:
                return "<ExpLogicaNormal>";
            case -48:
                return "<ExpLogicaNegada>";
            case -49:
                return "<MasExpLogica>";
            case -50:
                return "<OperadorLog>";
            case -51:
                return "<VariableLog>";
            case -52:
                return "<DeclaracionLet>";
            case -53:
                return "<InicializacionLet>";
            case -54:
                return "<DeclaracionCad>";
            case -55:
                return "<InicializacionCad>";
            case -56:
                return "<ExpConcat>";
            case -57:
                return "<MasExpConcat>";
            case -58:
                return "<VariableCad>";
            case -59:
                return "<DeclaracionObjeto>";
            case -60:
                return "<OperadorComp>";                         
            case -61:
                return "<InicializarLet>";
            case 0:
                return "ERROR";
            case 1:
                return "ERROR";
            case 20:
                return "PRINCIPAL";
            case 21:
                return "si";
            case 22:
                return "sin";
            case 23:
                return "repet";
            case 24:
                return "contar";
            case 25:
                return "clase";
            case 26:
                return "nuevo";
            case 27:
                return "FUNCION";
            case 28:
                return "procedimiento";
            case 29:
                return "RETORNAR";
            case 30:
                return "imprimir";
            case 31:
                return "LEER";
            case 32:
                return "constante";
            case 33:
                return "ENT";
            case 34:
                return "REAL";
            case 35:
                return "let";
            case 36:
                return "log";
            case 37:
                return "cad";
            case 60:
                return ".+";
            case 61:
                return ".-";
            case 62:
                return ".*";
            case 63:
                return "./";
            case 64:
                return ".**";
            case 65:
                return "==";
            case 66:
                return "<>";
            case 67:
                return "<<";
            case 68:
                return ">>";
            case 69:
                return "<<=";
            case 70:
                return "=>>";
            case 71:
                return ".y";
            case 72:
                return ".o";
            case 73:
                return ".no";
            case 74:
                return "->";
            case 75:
                return "(";
            case 76:
                return ")";
            case 77:
                return "{";
            case 78:
                return "}";
            case 79:
                return ":";
            case 80:
                return ",";
            case 81:
                return "[";
            case 82:
                return "]";
            case 83:
                return ".";
            case 84:
                return "#";
            case 1000:
                return "kte";
            case 1100:
                return "litDecimal";
            case 1200:
                return "litCadena";
            case 1300:
                return "litCaracter";
            case 1400:
                return "falso";
            case 1401:
                return "verdadero";
            case 100:
                return "id";
            case 999:
                return "$";
            default:
                return "ERROR";
        }
    }
    public static boolean ejecutarProduccion(ArrayList<Integer> pila, int numProd){
        switch (numProd){
            case 100:
                pila.add(-9);
                break;
            case 101:
                pila.add(-14);
                break;
            case 102:
                pila.remove(pila.size() - 1);
                pila.add(-25);
                pila.add(27);
                break;
            case 103:
                pila.remove(pila.size() - 1);
                pila.add(-26);
                pila.add(78);
                pila.add(-15);
                pila.add(77);
                pila.add(33);
                pila.add(74);
                pila.add(76);
                pila.add(75);
                pila.add(20);
                break;
            case 104:
                pila.remove(pila.size() - 1);
                pila.add(-2);
                pila.add(78);
                pila.add(-15);
                pila.add(77);
                pila.add(-8);
                pila.add(74);
                pila.add(76);
                pila.add(-10);
                pila.add(75);
                pila.add(100);
                break;
            case 105:
                pila.add(-9);
                break;
            case 106:
                pila.add(-14);
                break;
            case 107:
                pila.add(-3);
                break;
            case 109:
                pila.remove(pila.size() - 1);
                pila.add(78);
                pila.add(-15);
                pila.add(77);
                pila.add(-8);
                pila.add(74);
                pila.add(76);
                pila.add(-10);
                pila.add(75);
                pila.add(-5);
                pila.add(27);
                break;
            case 110:
                pila.remove(pila.size() - 1);
                pila.add(-5);
                pila.add(83);
                break;
            case 112:
                pila.remove(pila.size() - 1);
                pila.add(100);
                break;
            case 113:
                pila.remove(pila.size() - 1);
                pila.add(-7);
                pila.add(100);
                break;
            case 114:
                pila.remove(pila.size() - 1);
                pila.add(82);
                pila.add(1000);
                pila.add(81);
                break;
            case 116:
                pila.remove(pila.size() - 1);
                pila.add(33);
                break;
            case 117:
                pila.remove(pila.size() - 1);
                pila.add(34);
                break;
            case 118:
                pila.remove(pila.size() - 1);
                pila.add(36);
                break;
            case 119:
                pila.remove(pila.size() - 1);
                pila.add(35);
                break;
            case 120:
                pila.remove(pila.size() - 1);
                pila.add(37);
                break;
            case 121:
                pila.remove(pila.size() - 1);
                pila.add(78);
                pila.add(-15);
                pila.add(77);
                pila.add(76);
                pila.add(-10);
                pila.add(75);
                pila.add(-5);
                pila.add(28);
                break;
            case 122:
                pila.remove(pila.size() - 1);
                pila.add(-11);
                pila.add(-6);
                pila.add(-8);
                break;
            case 124:
                pila.add(-6);
                pila.add(-8);
                pila.add(80);
                break;
            case 126:
                pila.remove(pila.size() - 1);
                pila.add(-13);
                pila.add(-6);
                break;
            case 128:
                pila.add(-6);
                pila.add(80);
                break;
            case 130:
                pila.remove(pila.size() - 1);
                pila.add(78);
                pila.add(-27);
                pila.add(77);
                pila.add(-5);
                pila.add(25);
                break;
            case 131:
                pila.add(-16);
                break;
            case 133:
                pila.remove(pila.size() - 1);
                pila.add(-21);
                break;
            case 134:
                pila.remove(pila.size() - 1);
                pila.add(-34);
                break;
            case 135:
                pila.remove(pila.size() - 1);
                pila.add(-17);
                pila.add(100);
                break;
            case 136:
                pila.remove(pila.size() - 1);
                pila.add(-35);
                break;
            case 137:
                pila.add(-28);
                break;
            case 139:
                pila.remove(pila.size() - 1);
                pila.add(-21);
                break;
            case 140:
                pila.remove(pila.size() - 1);
                pila.add(-34);
                break;
            case 141:
                pila.remove(pila.size() - 1);
                pila.add(-17);
                pila.add(100);
                break;
            case 142:
                pila.remove(pila.size() - 1);
                pila.add(-35);
                break;
            case 143:
                pila.remove(pila.size() - 1);
                pila.add(-3);
                break;
            case 144:
                pila.remove(pila.size() - 1);
                pila.add(-9);
                break;
            case 145:
                pila.remove(pila.size() - 1);
                pila.add(-32);
                break;
            case 146:
                pila.remove(pila.size() - 1);
                pila.add(79);
                pila.add(-18);
                break;
            case 147:
                pila.remove(pila.size() - 1);
                pila.add(-59);
                break;
            case 148:
                pila.remove(pila.size() - 1);
                pila.add(76);
                pila.add(-12);
                pila.add(75);
                pila.add(-4);
                break;
            case 149:
                pila.remove(pila.size() - 1);
                pila.add(1401);
                break;
            case 150:
                pila.remove(pila.size() - 1);
                pila.add(1400);
                break;
            case 151:
                pila.remove(pila.size() - 1);
                pila.add(1200);
                break;
            case 152:
                pila.remove(pila.size() - 1);
                pila.add(1300);
                break;
            case 153:
                pila.remove(pila.size() - 1);
                pila.add(1100);
                break;
            case 154:
                pila.remove(pila.size() - 1);
                pila.add(1000);
                break;
            case 155:
                pila.remove(pila.size() - 1);
                pila.add(-30);
                pila.add(100);
                break;
            case 157:
                pila.remove(pila.size() - 1);
                pila.add(79);
                pila.add(-23);
                pila.add(-22);
                break;
            case 158:
                pila.remove(pila.size() - 1);
                pila.add(-31);
                break;
            case 159:
                pila.remove(pila.size() - 1);
                pila.add(-43);
                break;
            case 160:
                pila.remove(pila.size() - 1);
                pila.add(-44);
                break;
            case 161:
                pila.remove(pila.size() - 1);
                pila.add(-52);
                break;
            case 162:
                pila.remove(pila.size() - 1);
                pila.add(-54);
                break;
            case 163:
                pila.remove(pila.size() - 1);
                pila.add(-33);
                pila.add(-24);
                pila.add(-6);
                pila.add(33);
                break;
            case 164:
                pila.remove(pila.size() - 1);
                pila.add(-39);
                pila.add(74);
                break;
            case 166:
                pila.remove(pila.size() - 1);
                pila.add(-40);
                pila.add(-42);
                break;
            case 167:
                pila.remove(pila.size() - 1);
                pila.add(-40);
                pila.add(76);
                pila.add(-40);
                pila.add(-42);
                pila.add(75);
                break;
            case 168:
                pila.remove(pila.size() - 1);
                pila.add(-39);
                pila.add(-41);
                break;
            case 170:
                pila.remove(pila.size() - 1);
                pila.add(60);
                break;
            case 171:
                pila.remove(pila.size() - 1);
                pila.add(61);
                break;
            case 172:
                pila.remove(pila.size() - 1);
                pila.add(62);
                break;
            case 173:
                pila.remove(pila.size() - 1);
                pila.add(63);
                break;
            case 174:
                pila.remove(pila.size() - 1);
                pila.add(64);
                break;
            case 175:
                pila.remove(pila.size() - 1);
                pila.add(1100);
                break;
            case 176:
                pila.remove(pila.size() - 1);
                pila.add(1000);
                break;
            case 177:
                pila.remove(pila.size() - 1);
                pila.add(-30);
                pila.add(100);
                break;
            case 178:
                pila.remove(pila.size() - 1);
                pila.add(-33);
                pila.add(-24);
                pila.add(-6);
                pila.add(34);
                break;
            case 179:
                pila.remove(pila.size() - 1);
                pila.add(-45);
                pila.add(-24);
                pila.add(-6);
                pila.add(36);
                break;
            case 180:
                pila.remove(pila.size() - 1);
                pila.add(-46);
                pila.add(74);
                break;
            case 182:
                pila.remove(pila.size() - 1);
                pila.add(-47);
                break;
            case 183:
                pila.remove(pila.size() - 1);
                pila.add(-48);
                break;
            case 184:
                pila.remove(pila.size() - 1);
                pila.add(-49);
                pila.add(-51);
                break;
            case 185:
                pila.remove(pila.size() - 1);
                pila.add(-49);
                pila.add(76);
                pila.add(-49);
                pila.add(-51);
                pila.add(75);
                break;
            case 186:
                pila.remove(pila.size() - 1);
                pila.add(-47);
                pila.add(73);
                break;
            case 187:
                pila.remove(pila.size() - 1);
                pila.add(-46);
                pila.add(-50);
                break;
            case 189:
                pila.remove(pila.size() - 1);
                pila.add(71);
                break;
            case 190:
                pila.remove(pila.size() - 1);
                pila.add(72);
                break;
            case 191:
                pila.remove(pila.size() - 1);
                pila.add(1401);
                break;
            case 192:
                pila.remove(pila.size() - 1);
                pila.add(1400);
                break;
            case 193:
                pila.remove(pila.size() - 1);
                pila.add(-30);
                pila.add(100);
                break;
            case 194:
                pila.remove(pila.size() - 1);
                pila.add(-53);
                pila.add(-24);
                pila.add(-6);
                pila.add(35);
                break;
            case 195:
                pila.remove(pila.size() - 1);
                pila.add(-61);
                pila.add(74);
                break;
            case 197:
                pila.remove(pila.size() - 1);
                pila.add(1300);
                break;
            case 198:
                pila.remove(pila.size() - 1);
                pila.add(-30);
                pila.add(100);
                break;
            case 199:
                pila.remove(pila.size() - 1);
                pila.add(-55);
                pila.add(-24);
                pila.add(-6);
                pila.add(37);
                break;
            case 200:
                pila.remove(pila.size() - 1);
                pila.add(-56);
                pila.add(74);
                break;
            case 202:
                pila.remove(pila.size() - 1);
                pila.add(-57);
                pila.add(-58);
                break;
            case 203:
                pila.remove(pila.size() - 1);
                pila.add(-56);
                pila.add(60);
                break;
            case 205:
                pila.remove(pila.size() - 1);
                pila.add(1200);
                break;
            case 206:
                pila.remove(pila.size() - 1);
                pila.add(-30);
                pila.add(100);
                break;
            case 207:
                pila.remove(pila.size() - 1);
                pila.add(79);
                pila.add(100);
                pila.add(26);
                pila.add(74);
                pila.add(100);
                break;
            case 208:
                pila.remove(pila.size() - 1);
                pila.add(-1);
                pila.add(-42);
                pila.add(-60);
                pila.add(-42);
                break;
            case 209:
                pila.remove(pila.size() - 1);
                pila.add(-1);
                pila.add(76);
                pila.add(-1);
                pila.add(-42);
                pila.add(-60);
                pila.add(-42);
                pila.add(75);
                break;
            case 210:
                pila.remove(pila.size() - 1);
                pila.add(-20);
                pila.add(-50);
                break;
            case 212:
                pila.remove(pila.size() - 1);
                pila.add(65);
                break;
            case 213:
                pila.remove(pila.size() - 1);
                pila.add(66);
                break;
            case 214:
                pila.remove(pila.size() - 1);
                pila.add(67);
                break;
            case 215:
                pila.remove(pila.size() - 1);
                pila.add(68);
                break;
            case 216:
                pila.remove(pila.size() - 1);
                pila.add(69);
                break;
            case 217:
                pila.remove(pila.size() - 1);
                pila.add(70);
                break;
            case 218:
                pila.remove(pila.size() - 1);
                pila.add(32);
                break;
            case 220:
                pila.add(-6);
                pila.add(80);
                break;
            case 222:
                pila.remove(pila.size() - 1);
                pila.add(-18);
                break;
            case 224:
                pila.remove(pila.size() - 1);
                pila.add(79);
                pila.add(-29);
                pila.add(74);
                break;
            case 225:
                pila.remove(pila.size() - 1);
                pila.add(-39);
                break;
            case 226:
                pila.remove(pila.size() - 1);
                pila.add(1200);
                break;
            case 227:
                pila.remove(pila.size() - 1);
                pila.add(1300);
                break;
            case 228:
                pila.remove(pila.size() - 1);
                pila.add(1401);
                break;
            case 229:
                pila.remove(pila.size() - 1);
                pila.add(1400);
                break;
            case 230:
                pila.remove(pila.size() - 1);
                pila.add(79);
                pila.add(76);
                pila.add(-56);
                pila.add(75);
                pila.add(30);
                break;
            case 231:
                pila.remove(pila.size() - 1);
                pila.add(79);
                pila.add(76);
                pila.add(-6);
                pila.add(75);
                pila.add(31);
                break;
            case 232:
                pila.remove(pila.size() - 1);
                pila.add(79);
                pila.add(-19);
                pila.add(29);
                break;
            case 233:
                pila.remove(pila.size() - 1);
                pila.add(78);
                pila.add(-15);
                pila.add(77);
                pila.add(76);
                pila.add(-20);
                pila.add(75);
                pila.add(23);
                break;
            case 234:
                pila.remove(pila.size() - 1);
                pila.add(-37);
                pila.add(78);
                pila.add(-15);
                pila.add(77);
                pila.add(76);
                pila.add(-20);
                pila.add(75);
                pila.add(21);
                break;
            case 235:
                pila.remove(pila.size() - 1);
                pila.add(78);
                pila.add(-15);
                pila.add(77);
                pila.add(76);
                pila.add(-39);
                pila.add(74);
                pila.add(100);
                pila.add(79);
                pila.add(-20);
                pila.add(79);
                pila.add(-36);
                pila.add(75);
                pila.add(24);
                break;
            case 236:
                pila.remove(pila.size() - 1);
                pila.add(1000);
                pila.add(74);
                pila.add(100);
                pila.add(33);
                break;
            case 237:
                pila.remove(pila.size() - 1);
                pila.add(1000);
                pila.add(74);
                pila.add(100);
                break;
            case 238:
                pila.remove(pila.size() - 1);
                pila.add(-38);
                pila.add(22);
                break;
            case 240:
                pila.remove(pila.size() - 1);
                pila.add(-37);
                pila.add(78);
                pila.add(-15);
                pila.add(77);
                pila.add(76);
                pila.add(-20);
                pila.add(75);
                pila.add(21);
                break;
            case 241:
                pila.remove(pila.size() - 1);
                pila.add(78);
                pila.add(-15);
                pila.add(77);
                break;
            case 108:
            case 111:
            case 115:
            case 123:
            case 125:
            case 127:
            case 129:
            case 132:
            case 138:
            case 156:
            case 165:
            case 169:
            case 181:
            case 188:
            case 196:
            case 201:
            case 204:
            case 211:
            case 219:
            case 221:
            case 223:
            case 239:
                pila.remove(pila.size() - 1);
                break;               
            default:
                System.out.println("ERROR, no hay regla de produccion para proceder");
                pila.add(1);
                return false;
        }
        return true;
    }
}
