/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Evaluador de Expresiones Postfijas
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.desarrollosw.postfijo;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Esta clase representa una clase que evalúa expresiones en notación polaca o
 * postfija. Por ejemplo: 4 5 +
 */
public class EvaluadorPostfijo {

    /**
     * Realiza la evaluación de la expresión postfijo utilizando una pila
     * @param expresion una lista de elementos con números u operadores
     * @return el resultado de la evaluación de la expresión.
     */
    static String  evaluarPostFija(List<String> expresion) {

        // TODO: Realiza la evaluación de la expresión en formato postfijo

        Stack<String> E     = new Stack<>();
        Stack<String> pila  = new Stack<>();

        for (int i = expresion.size() - 1; i >= 0; i--) {E.push(expresion.get(i));}

        String operadores = "+-*/%";
        while (!E.isEmpty()) {

            if (operadores.contains("" + E.peek())) {

                int res = evaluar(E.pop(), pila.pop(), pila.pop());
                pila.push(res + "");

            } else {
                pila.push(E.pop());
            }
        }
        System.out.println("Resultado: " + pila.peek());
        return pila.peek();
    }

    /**
     * Programa principal
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("> ");
        String linea = teclado.nextLine();

        try {
            List<String> expresion = Token.dividir(linea);
            System.out.println(evaluarPostFija(expresion));
        }
        catch (Exception e) {
            System.err.printf("Error grave en la expresión: %s", e.getMessage());
        }
    }
    /**
     * evaluar
     * @ int resultado operacion
     */
    private static int evaluar(String op, String n2, String n1) {

        try {
            int num1 = Integer.parseInt(n1);
            int num2 = Integer.parseInt(n2);

            switch (op) {
                case "+":
                    return (num1 + num2);
                case "-":
                    return (num1 - num2);
                case "*":
                    return (num1 * num2);
                case "/":
                    return (num1 / num2);
                case "%":
                    return (num1 % num2);
                default:
                    return 0;
            }
        } catch (Exception e) {
            System.err.printf("Error grave en la operacion: %s", e.getMessage());
            return -1;
        }
    }
}
