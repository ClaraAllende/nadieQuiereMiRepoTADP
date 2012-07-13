package dsl

import static org.junit.Assert.*

import org.junit.Test

import ar.edu.utn.tadp.empresa.Empresa

class EmpresaDSLTest {

	@Test
	void testEmpresaHasDynamicMethods() {
		def empresaDSL = new EmpresaDSL(new Empresa());
		
		assertNotNull(empresaDSL.getClass().metaClass.getMetaMethod("sector", String))
		
		assertNotNull(empresaDSL.getClass().metaClass.getMetaMethod("gerente"))
		assertNotNull(empresaDSL.getClass().metaClass.getMetaMethod("gerente", String))
		assertNotNull(empresaDSL.getClass().metaClass.getMetaMethod("programador"))
		assertNotNull(empresaDSL.getClass().metaClass.getMetaMethod("programador", String))
		assertNotNull(empresaDSL.getClass().metaClass.getMetaMethod("liderTecnico"))
		assertNotNull(empresaDSL.getClass().metaClass.getMetaMethod("liderTecnico", String))
		assertNotNull(empresaDSL.getClass().metaClass.getMetaMethod("projectLeader"))
		assertNotNull(empresaDSL.getClass().metaClass.getMetaMethod("projectLeader", String))
		assertNotNull(empresaDSL.getClass().metaClass.getMetaMethod("diseniadorGrafico"))
		assertNotNull(empresaDSL.getClass().metaClass.getMetaMethod("diseniadorGrafico", String))
		
		assertNotNull(empresaDSL.getClass().metaClass.getMetaMethod("proyector"))
		assertNotNull(empresaDSL.getClass().metaClass.getMetaMethod("notebook"))
	}
}
