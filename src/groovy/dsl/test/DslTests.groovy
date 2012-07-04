package test;

import static org.junit.Assert.*;

import org.junit.Test;

class DslTests {
//TODO: escribir el código groovy para que esto ande, si están de acuerdo con el uso.
	@Test
	void testReunionSemestral(){
		planificar reunion {
			[
				PM:"Mobilablame",
			PM: "Zarlanga Object Manager Abstract System",
			PM: "Automatic Losing Reference Counter Garbage colector",
			Marketing:1,
			Gerentes:2
			]
		}
		cancelar si{
			porcentajeDeAsistencia.esMenor(70)
		}
	}
}
