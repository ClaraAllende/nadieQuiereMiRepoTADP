package ar.edu.utn.tadp.costos;

import java.math.BigDecimal;

import ar.edu.utn.tadp.organizables.Organizable;

public interface Costeable {

	BigDecimal dameTuCostoPara(Organizable ubicable);
}
