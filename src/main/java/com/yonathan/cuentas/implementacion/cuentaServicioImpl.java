package com.yonathan.cuentas.implementacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yonathan.cuentas.entidades.Cuenta;
import com.yonathan.cuentas.repositorio.cuentaRepositorio;
import com.yonathan.cuentas.servicio.cuentaServicio;

@Service("cuentaServicio")
public class cuentaServicioImpl implements cuentaServicio {
	@Autowired
	@Qualifier("cuentaRepositorio")
	public cuentaRepositorio cuentaRepositorio;

	private static final Logger log = LoggerFactory.getLogger(cuentaServicioImpl.class);
	ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

    private String[] cerosString;
    private int[] nuevesInt;
    Cuenta cuentaGenerada;
	
	@Override
	public boolean agregar(String cadena) {
//		Se crea una expresion regular para solo "0" y "-" y repeticion n veces
		Pattern pat = Pattern.compile("[[0]+[-]{1}[0]+]{1,}|[0]+");
//		Se compara la cadena introducida por el usuario con la expresion regular
		Matcher mat = pat.matcher(cadena);

//		Si la cadena cumple con la expresion
		if (mat.matches()) {
			log.info("Matcher - OK");
//			Se llama al metodo Generar con el parametro cadena que es la introducida por el usuario
			Generador(cadena);
//			Devuelve "true" si todo funciono bien
			return true;
		} else {
//			Si la cadena no cumple con la expresion, se devuelve un "false" indicando que hubo un error
			log.info("Matcher - FAIL");
			return false;
		}
	}
	
	private void Insertar(String cuenta, String identificador) {
		cuentaGenerada = new Cuenta(1, 1, 1, java.sql.Date.valueOf("2019-07-11"), 1, cuenta,
				"Juan Perez Martinez", 1, 'A', "$", 1, 1);
		cuentaRepositorio.save(cuentaGenerada);
	}

//	Ejemplo "00-00-00"
	public void Generador(String cadena) {
//		Se corta la cadena introducida por el usuario en secciones, las cuales estan 
//		divididas por un "-" y se guardan en un array "cerosString"
        cerosString = cadena.split("-");

//        Se asigna un valor especifico al array "nuevesInt", el tamaño es igual al array anterior
        nuevesInt = new int[cerosString.length];
//      El for va de cero a la cantidad de secciones que tiene el array "cerosString", 
//        por ejemplo: 00-00-00 = 3 secciones.
        for (int i = 0; i < cerosString.length; i++) {
//        	Aqui se cambian los ceros de las secciones en nueves  000 => 999
            nuevesInt[i] = Integer.valueOf(cerosString[i].replaceAll("0", "9"));
        }
        
//        Esta variable "valor", sirve para mas adelante concatenar los valores del array
        String valor;
//        restarAMil, sirve para poder saber si se va a restar alguna cantidad indefinida a los 1000 registros
        int restarAMil = 0;
//        valorInicioFor nos sirve para detectar cuantas secciones tiene la cadena y 
//        determinar si se inicia con cero o uno
        int valorInicioFor = 0;
//        Esta condicion nos tetecta que si la cadena tiene menos de 3 secciones
        if (cerosString.length < 3) {
//        	Aqui detecta si tiene solo una seccion
            if (cerosString.length == 1) {
//            	La variable "valorInicioFor" es igualada a 1, para que el ciclo comience desde 1
                valorInicioFor = 1;
            }
//            Aqui se detecta cuantos digitos tiene el valor que esta en "nuevesInt[0]", y se envia a un metodo 
//            el cual sirve para concatenar los ceros a la izquierda, este metodo recibe 2 parametros, la longitud y
//            el valor que se va a concatenar despues de los ceros en este caso es: numeroCeros(999,1) ó numeroCeros(3 digitos,1 valor)
            cerosString[0] = numeroCeros(String.valueOf(nuevesInt[0]).length(), 1);
        } else {
//        	Si la condicion de que si la cadena es menor que 3 no se cumple, se inicia este for el cual realiza n ciclos dependiendo del
//        	numero de secciones que hay en el array "cerosString", al cual se le restan 2 para lograr obtener unicamente los valores
//        	antes del todal de secciones. Supongamos este array: cerosString["000","00","000","0"], entonces el for solamente hace el ciclo de 
//        	la posicion 0 a la posicion 1, dejando la posicion 2 y 3 fuera
            for (int i = 0; i < cerosString.length - 2; i++) {
//            	Se asigna el valor del array cerosString[] en su posicion i(la cual itera junto con el for)
//            	a la variable valor
                valor = cerosString[i];
//                Esto devuelve el valor con los ceros a la izquierda concatenados
                valor = numeroCeros(valor.length(), 1);
//                Se asigna el valor de la variable valor a la posicion i del array cerosString[] para seguir a partir de 1
                cerosString[i] = valor;
//                Se vacia la variable valor para no contaminar la otra parte donde se ocupa
                valor = "";
//                Este for hace la concatenacion de los valores que tiene el array cerosString[], para colocarlos al lado izquierdo
                for (String cerosString1 : cerosString) {
                    valor += cerosString1 + "-";
                }
//                Aqui se llama al metodo "Insertar", para mandar la cadena al repositorio y guardarla en la db
                Insertar(valor.substring(0, valor.length() - 1), "A");
            }
        }

//        Esta condicion valida que si la cadena es mayor a 2 secciones
        if (cerosString.length > 2) {
//        	Si la condicion se cumple se le asigna el antepenultimo valor del array cerosString[] a la variable valor
            valor = cerosString[cerosString.length - 2];
//            Aqui se concatenan los ceros a la izquierda
            cerosString[cerosString.length - 2] = numeroCeros(valor.length(), 1);
//        	Se asigna el penultimo valor del array cerosString[] a la variable valor
            valor = cerosString[cerosString.length - 1];
//          Aqui se concatenan los ceros a la izquierda
            cerosString[cerosString.length - 1] = numeroCeros(valor.length(), 1);
//            Aqui se guarda el valor que se va a restar a los 1000 ciclos que hace el while mas adelante
            restarAMil = cerosString.length - 2;
        }

//        Este contador nos sirve para detener el ciclo while
        int contador = 0;
//        La variable inserts nos sirve para deterninar cuantos registros se enviaran a la base de datos
        int inserts = 1000 - restarAMil;//Mil 1,000
//        La variable ciclos guarda el valor del la ultima posision del array nuevesInt[]
        int ciclos = nuevesInt[nuevesInt.length - 1];
//        Este while nos sirve para realizar los ciclos que estan en "inserts"
        while (contador < inserts) {
//        	Este for sirve para iterar el ultimo valor del array e ir ginsertando los valores a la db
            for (int i = valorInicioFor; i <= ciclos; i++) {
//            	La variable ceros, guarda el valor que tiene cada insert en la ultima posicion
                String ceros = numeroCeros(cerosString[cerosString.length - 1].length(), i);
//                Se concatena las primeras secciones a la izquierda y tambien se concatena el valor de ceros
                String insert = concatenar(cerosString, ceros);
//                Se manda a insertar el valor a la db
                Insertar(insert, "N");
//                Esta condicion nos permite saber si el contador i ya es igual a la variable ciclos en este caso por ejemplo puede ser:
//                	"99 == 99"
                if (i == ciclos) {
//                	Si la condicion se cumple se asigna el valor de ciclos a la penultima posicion del array cerosString[]
                    cerosString[nuevesInt.length - 1] = String.valueOf(ciclos);
//                    El metodo "revicionDeValoresEnArrays" devuelve un 0 o 1 
                    int aux = revicionDeValoresEnArrays();
//                    Si el metodo anterior devuelve "1", la condicion del if se cumple
                    if (aux == 1) {
//                    	y se iguala el contador a los insert para parar el ciclo while
                        contador = inserts;
                        break;
                    }
                }
//                El contador incrementa 1 mas 
                contador += 1;
//                Este if solo da aviso de que el contador es mayor a inserts y hace parar el ciclo while
                if (contador >= inserts) {
//                	Aqui avisa
                    System.err.println("Paro el ciclo");
//                	Aqui detiene
                    break;
                }
            }
        }
//        Esto solo es para saber en que posision se quedo el contador, no tiene importancia aún
        System.out.println("Contador: " + contador);
    }
	
//	Este metodo hace todas las comparaciones de los 2 arrays cerosString[] y nuevesInt[] para saber si el numero de 
//	cerosString[n] es igual a nuevesInt[n], por ejemplo: 999 == 999 en el caso de que si sean igual, sino: 987 == 999
//	en el caso de que no sean iguales
    private int revicionDeValoresEnArrays() {
//    	El for recorre desde cero hasta el valor de posisiones en el array cerosString[]
        for (int i = 0; i < cerosString.length; i++) {
//        	Esta condicion valida que si el valor de nuevesInt[n] es igual a cerosString[n], la cual esta convertida en int
            if (nuevesInt[i] == Integer.valueOf(cerosString[i])) {
//            	Esta condicion valida que si la comparacion se esta haciendo en el elemento 0 del array
//            	Esto evita que se produzca un error al memento de restar a una posicion menos
                if (i != 0) {
//                	Esta condicion valida que si los valores en las posiciones antes de nuevesInt[n] y cerosString[n], son diferentes
//                	se incremente a 1 mas el valor que ya tienen
                    if (!(nuevesInt[i - 1] == Integer.valueOf(cerosString[i - 1]))) {
//                    	Aqui se conviente a int lo que este en cerosString[i - 1]
                        int aux = Integer.valueOf(cerosString[i - 1]);
//                        Aqui se crea un String, el cual nos sirve adelante para darle formato al valor
                        String a = "%0" + String.valueOf(cerosString[i - 1]).length() + "d";
//                        Se asigna el valor ya formateado a la posicion del array cerosString[i - 1]
                        cerosString[i - 1] = String.format(a, aux + 1);
                    } else {
//                    	Esto reinicia el valor del array cerosString[i - 1] a ceros para que vuelva a segir iterando mas adelante
//                    	y no se quede en su valor maximo
                        cerosString[i - 1] = numeroCeros(String.valueOf(nuevesInt[i - 1]).length(), i -2); //Cambiar a un 1 para iniciar con 1 enves de cero
                    }
                } else {
//                	Aqui solo se avisa que se alcanzo el numero maximo porque ya no se puede segur restando a una 
//                	posicion antes de la actual, ya que esta posicion ya no existe 
                    System.err.println("¡Se alcanzo el numero maximo!");
//                    y regresa 1 claro
                    return 1;
                }
            }
        }
//        sino, regresa un 0
        return 0;
    }

//    Este metodo recibe un array y un valor, lo unico que hace es recorrer las posiciones del arrya
//    y concatenarlas, para poderlas retornar en un String
    private String concatenar(String[] array, String valor) {
//    	Se inicializa la variable "concatenacion" en ""
        String concatenacion = "";
//        el for recorre desde 0 hata el numero maximo que tiene el array que llego como parametro al metodo
        for (int i = 0; i < array.length - 1; i++) {
//        	Se concatena el valor de la posicion del array a lo que ya tiene la variable "concatenacion" mas un "-" al final
            concatenacion += array[i] + "-";
        }
//        se concatenan el valor al final de la concatenacion
        concatenacion += valor;
//        Se envia de regreso el valor concatenado
        return concatenacion;
    }

//    Este metodo recibe como parametro dos int, los cuales determinan la longitud de la partes y el valor a concatenar
    private String numeroCeros(int tamanioParte, int tamanioIteracion) {
//    	Se inicializa la variable "resultado" en ""
        String resultado = "";
//        Se saca el valor de ceros que va a tener a la izquierda
        int numeroCeros = tamanioParte - Integer.toString(tamanioIteracion).length();
//        el ciclo se hace dependiendo de los ceros a la izquierda
        for (int k = 0; k < numeroCeros; k++) {
//        	Se concatenan los 0 en la variable "resultado"
            resultado += "0";
        }
        String aux = resultado + tamanioIteracion;
//        Regresa la cantidad con los ceros a la izquierda
        return aux;
    }

}
